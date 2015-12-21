package Comments

import javax.inject.Inject

import database.MyPostgresDriver.api._
import database.{MyDBTableDefinitions, MyPostgresDriver}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import shared.Comment

import scala.concurrent.Future
import scala.language.postfixOps


class CommentsMethods @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[MyPostgresDriver]
  with MyDBTableDefinitions {

  def findAll: Future[Seq[shared.Comment]] = db.run(comments.result)

  def post(comment: shared.Comment): Future[Int] = db.run(comments += comment)
  
  def update(comment: Comment): Future[Int] = db.run(comments.filter(_.id === comment.id).update(comment))
}