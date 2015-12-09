package Comments

import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc.{Action, _}
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global

class CommentsController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, val commentsMethods: CommentsMethods)
  extends Controller {
  def findAll() = Action.async {
    commentsMethods.findAll.map { comments =>
      Ok(write(comments))
    }
  }

  def post(comment: String) = Action.async {
    commentsMethods.post(read[shared.Comment](comment)).map { changedLines =>
      Ok(write(changedLines))
    }
  }
}