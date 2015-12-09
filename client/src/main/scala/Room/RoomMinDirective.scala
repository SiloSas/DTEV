package Room

import com.greencatsoft.angularjs.extensions.ModalService
import com.greencatsoft.angularjs.{ElementDirective, TemplatedDirective, injectable}
import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("roomMin")
class RoomMinDirective(modal: ModalService) extends ElementDirective with TemplatedDirective {
  override val templateUrl = "assets/templates/Room/roomMin.html"
}

