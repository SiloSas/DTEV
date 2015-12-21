package Comments

import javax.inject.Inject

import administration.Authenticated
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.{Action, _}
import shared.Comment
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global


class CommentsController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, val commentsMethods: CommentsMethods)
    extends Controller {
  def findAll() = Action.async {
    commentsMethods.findAll.map { comments =>
      println("writeComms = " + write(comments))
      Ok(write(comments))
    }
  }

  def post = Action.async(parse.json) { request =>
    val comment = read[Comment](request.body.as[JsObject].toString)

    commentsMethods.post(comment).map { changedLines =>
      Ok(write(changedLines))
    }
  }

  def update = Authenticated.async(parse.json) { request =>
    val comment = read[Comment](request.body.as[JsObject].toString)

    commentsMethods.update(comment) map { numberOfRowsUpdated =>
      Ok(Json.toJson(numberOfRowsUpdated))
    }
  }
}