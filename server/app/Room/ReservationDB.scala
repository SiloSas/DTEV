package Room

import java.sql.Date
import javax.inject.Inject

import database.MyPostgresDriver.api._
import database.{MyDBTableDefinitions, MyPostgresDriver}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

import scala.concurrent.Future


case class ReservationDB(id: Option[Long],
                         roomId: String,
                         roomName: String,
                         arrivalDate: Date,
                         departureDate: Date,
                         numberOfPersons: Int,
                         firstName: String,
                         name: String,
                         email: String,
                         phoneNumber: String,
                         extraBed: Boolean)


class ReservationMethods @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
    extends HasDatabaseConfigProvider[MyPostgresDriver]
    with MyDBTableDefinitions {

  def save(reservation: ReservationDB): Future[Int] = db.run(reservations += reservation)

  def delete(reservationId: Long): Future[Int] = db.run(reservations.filter(_.id === reservationId).delete)

  def findAll(): Future[Seq[ReservationDB]] = {
    val nowLong = new java.util.Date().getTime
    val now = new Date(nowLong)

    db.run(reservations.filter(_.arrivalDate > now).result)
  }

  def findReservationsInInterval(start: Long, end: Long): Future[Seq[ReservationDB]] = {
    val arrivalSqlDate = new Date(start)
    val departureSqlDate = new Date(end)

    db.run(reservations
      .filter(reservation => reservation.arrivalDate >= arrivalSqlDate && reservation.departureDate <= departureSqlDate)
      .result)
  }
}