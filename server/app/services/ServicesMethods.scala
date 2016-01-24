package services

import javax.inject.Inject

import database.MyPostgresDriver.api._
import database.{MyDBTableDefinitions, MyPostgresDriver}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits._
import shared.Agreement

import scala.concurrent.Future
import scala.language.postfixOps


class ServicesMethods @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
    extends HasDatabaseConfigProvider[MyPostgresDriver]
    with MyDBTableDefinitions {

  def findAll: Future[Seq[shared.Agreement]] = db.run(agreements.result) map (_.toSeq)

  def update(agreement: Agreement): Future[Int] = db.run(agreements.filter(_.id === agreement.id).update(agreement))
}