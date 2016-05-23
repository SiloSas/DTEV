package generalDescription

import javax.inject.Inject

import database.MyPostgresDriver.api._
import database.{MyDBTableDefinitions, MyPostgresDriver}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

import scala.concurrent.{ExecutionContext, Future}


case class Description(text: String)

class GeneralDescription @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                                   implicit val ec: ExecutionContext)
    extends HasDatabaseConfigProvider[MyPostgresDriver] with MyDBTableDefinitions {

  def update(newDesc: String): Future[Int] = db.run(generalDescriptions.update(Description(newDesc)))

  def find(): Future[String] = db.run(generalDescriptions.result.head) map(_.text)
}
