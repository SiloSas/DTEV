package Contact

import com.greencatsoft.angularjs.extensions.{ModalOptions, ModalService}
import com.greencatsoft.angularjs.{Attributes, ElementDirective, injectable}
import org.scalajs.dom.Element
import org.scalajs.dom.html._
import org.scalajs.dom.raw.MouseEvent

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("ngContact")
class ContactDirective(modal: ModalService) extends ElementDirective {

  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes) {
    elems.headOption.map(_.asInstanceOf[Html]) foreach { elem =>
      elem.onclick = (event: MouseEvent) => {
        val newModal: ModalOptions = new js.Object().asInstanceOf[ModalOptions]
        newModal.templateUrl = "assets/templates/Contact/contact.html"
        newModal.controller = "contactController"
        newModal.windowClass = "bookingModal"
        modal.open(newModal)
      }
    }
  }
}