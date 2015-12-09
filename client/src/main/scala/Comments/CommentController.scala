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


@JSExport
@injectable("commentController")
class CommentController(scope: CommentScope, newComment: NewComment)
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

  val comment = Comment(
    id = "1",
    title = "genial!!!!",
    comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec pretium ullamcorper massa, sit amet ornare erat blandit ut.",
    userName = "user1",
    rate = 4,
    date = new Date())
  val comment1 = Comment(
    id = "2",
    title = "cool",
    comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec pretium ullamcorper massa, sit amet ornare erat blandit ut.",
    userName = "user2",
    rate = 3,
    date = new Date())
  val comment2 = Comment(
    id = "3",
    title = "un autre commentaire",
    comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec pretium ullamcorper massa, sit amet ornare erat blandit ut.",
    userName = "user3",
    rate = 5,
    date = new Date())

  val comments = Seq(comment, comment1, comment2)

  scope.comments = comments.toJSArray


  @JSExport
  def post(): Unit = {
    val comment = Comment(id = UUID.randomUUID().toString, title = scope.newComment.title, comment = scope.newComment.comment,
      userName = scope.newComment.userName, rate = scope.newComment.rate, date = new Date())

    scope.comments :+= comment
    cleanNewComment()
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }

}