package Room

import javax.inject.Inject
import database.MyPostgresDriver.api._
import database.{MyDBTableDefinitions, MyPostgresDriver}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits._
import shared.Room
import scala.concurrent.Future
import scala.language.postfixOps


class RoomMethods @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[MyPostgresDriver]
    with MyDBTableDefinitions {

  def findAll: Future[Seq[Room]] = db.run(rooms.result) map (_.toSeq)

  def findById(id: String): Future[Option[Room]] = db.run(rooms.filter(_.id === id).result) map (_.headOption)

  def findAvailable(start: String, end: String): Future[Seq[Room]] = db.run(rooms.result) map (_.tail)

}