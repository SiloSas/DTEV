package Comments

import com.greencatsoft.angularjs.{ElementDirective, TemplatedDirective, injectable}
import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("ngComments")
class CommentsDirective() extends ElementDirective with TemplatedDirective {
  override val templateUrl = "assets/templates/Comments/comment.html"
}

