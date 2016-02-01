package Comments

import java.util.UUID

import com.greencatsoft.angularjs.{AbstractController, injectable}
import materialDesign.MdToastService
import org.scalajs.dom.console
import shared.Comment

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}


@JSExport
@injectable("commentController")
class CommentController(scope: CommentScope,
                        newComment: NewComment,
                        commentService: CommentService,
                        mdToast: MdToastService)
  extends AbstractController[CommentScope](scope) {

  scope.comments = js.Array[shared.Comment]()

  cleanNewComment()
  scope.newComment = newComment

  def cleanNewComment() = {
    newComment.id = UUID.randomUUID().toString
    newComment.comment = ""
    newComment.userName = ""
    newComment.rate = 0
  }

  commentService.findAll() onComplete {
    case Success(foundComments) =>

      val commentsWithFormattedStringDates = foundComments.map { c =>
        c.copy(date = new Date(c.date).getTime().toString)
      }

      scope.$apply {
        scope.comments = commentsWithFormattedStringDates.toJSArray
      }
    case Failure(t: Throwable) =>
      handleError(t)
  }

  def displayToast(): Any = {
    var toast = mdToast.simple("Merci, votre avis a bien été posté, il va être soumis à modération.")
    toast._options.position = """{
        bottom: false
        top: true
        left: false
        right: true
      }"""
    mdToast.show(toast)
  }

  def displayErrorToast(): Any = {
    var toast = mdToast.simple("Désolé, une erreur s'est produite.")
    toast._options.position = """{
        bottom: false
        top: true
        left: false
        right: true
      }"""
    mdToast.show(toast)
  }

  @JSExport
  def post(): Unit = {
    val a = new Date().getDay() + "." + new Date().getMonth() + "." + new Date().getFullYear()
    println("a = " + a)

    val comment = Comment(
      id = UUID.randomUUID().toString,
      comment = scope.newComment.comment,
      userName = scope.newComment.userName,
      rate = scope.newComment.rate,
      date = new Date().toString,
      isValidated = false)

    commentService.post(comment) onComplete {
      case Success(1) =>
        displayToast()
        scope.$apply {
          scope.comments :+= comment
          cleanNewComment()
        }
      case Success(_) =>
        displayErrorToast()
      case Failure(t: Throwable) =>
        displayErrorToast()
        handleError(t)
    }
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}