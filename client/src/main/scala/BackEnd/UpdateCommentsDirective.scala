package BackEnd

import Comments.CommentService
import com.greencatsoft.angularjs.{ElementDirective, TemplatedDirective, injectable}
import shared.Comment
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("updateComments")
class UpdateCommentsDirective(commentService: CommentService)
  extends ElementDirective
    with TemplatedDirective {

  override val templateUrl = "assets/templates/BackEnd/commentsUpdate.html"

  @JSExport
  def updateComment(comment: js.Any) = {
    commentService.update(read[Comment](JSON.stringify(comment)))
  }
}