package Room

import javax.inject.Inject

import database.MyPostgresDriver.api._
import database.{MyDBTableDefinitions, MyPostgresDriver}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits._
import shared.Room

import scala.concurrent.Future
import scala.language.postfixOps


class RoomMethods @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                            reservationMethods: ReservationMethods)
  extends HasDatabaseConfigProvider[MyPostgresDriver]
    with MyDBTableDefinitions {

  def findAll: Future[Seq[Room]] = db.run(rooms.result)

  def findById(id: String): Future[Option[Room]] = db.run(rooms.filter(_.id === id).result) map (_.headOption)

  def findAvailable(start: Long, end: Long): Future[Seq[Room]] = {
    reservationMethods.findReservationsInInterval(start, end) flatMap { reservations =>
      val nonAvailableIds = reservations.map(_.roomId).distinct
      db.run(rooms.filterNot(r => r.id.inSet(nonAvailableIds)).result)
    }
  }

  def update(room: Room): Future[Int] = db.run(rooms.filter(_.id === room.id).update(room))
}
