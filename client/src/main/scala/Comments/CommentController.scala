package Comments

import java.util.UUID

import com.greencatsoft.angularjs.core.{RouteParams, Timeout}
import com.greencatsoft.angularjs.extensions.{ModalService, ModalInstance}
import com.greencatsoft.angularjs.{AbstractController, injectable}
import shared.Comment
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}
import org.scalajs.dom.console
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.concurrent.ExecutionContext.Implicits.global


@JSExport
@injectable("commentController")
class CommentController(scope: CommentScope, newComment: NewComment, commentService: CommentService)
  extends AbstractController[CommentScope](scope) {

  scope.comments = js.Array[shared.Comment]()

  cleanNewComment()

  scope.newComment = newComment

  def cleanNewComment() = {
    newComment.id = UUID.randomUUID().toString
    newComment.title = ""
    newComment.comment = ""
    newComment.userName = ""
    newComment.rate = 0
  }

  commentService.findAll() onComplete {
    case Success(foundComments) =>
      scope.$apply {
        scope.comments = foundComments.toJSArray
      }
    case Failure(t: Throwable) =>
      handleError(t)
  }


  @JSExport
  def post(): Unit = {
    val comment = Comment(id = UUID.randomUUID().toString, title = scope.newComment.title, comment = scope.newComment.comment,
      userName = scope.newComment.userName, rate = scope.newComment.rate, date = new Date().toString)
    commentService.post(comment) onComplete {
      case Success(0) =>
        println("raté")
      case Success(1) =>
        scope.$apply {
          scope.comments :+= comment
          cleanNewComment()
        }
      case Failure(t: Throwable) =>
        handleError(t)
    }
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }

}