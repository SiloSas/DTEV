package Room

import com.greencatsoft.angularjs.core.{Timeout, Window}
import com.greencatsoft.angularjs.extensions.{ModalOptions, ModalService}
import com.greencatsoft.angularjs.{Attributes, ElementDirective, TemplatedDirective, injectable}
import org.scalajs.dom.Element
import org.scalajs.dom.html._
import org.scalajs.dom.console
import org.scalajs.dom.document
import org.scalajs.dom.raw.{Event, UIEvent}
import shared.Room
import upickle.default._
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("roomMin")
class RoomMinDirective(modal: ModalService, window: Window, timeout: Timeout) extends ElementDirective with TemplatedDirective {
  override val templateUrl = "assets/templates/Room/roomMin.html"

  override def link(scope: ScopeType, elements: Seq[Element], attrs: Attributes): Unit = {
    elements.headOption.map(_.asInstanceOf[Html]) foreach { element =>
      var elementWidth = element.getBoundingClientRect().width
      def setNewHeight(newHeight: Double): Unit = {
        val roomsLength = element.parentElement.getElementsByTagName("roomMin").length
        val rooms =  element.parentElement.getElementsByTagName("roomMin")
        for(i <- 0 until roomsLength -1) {
          rooms.item(i).asInstanceOf[Html].style.height = newHeight + "px"
        }
      }
      timeout (fn = () => {
        setNewHeight(Math.ceil(element.clientWidth * 0.62893081761))
      }, 150, invokeApply = false)

      window.onresize = (event: UIEvent) =>
        setNewHeight(Math.ceil(element.clientWidth * 0.62893081761))
    }
  }

  @JSExport
  def openModal(room: Room): Unit = {
    val newModal: ModalOptions = new js.Object().asInstanceOf[ModalOptions]

    newModal.templateUrl = "assets/templates/Booking/bookingModal.html"
    newModal.controller = "bookingModalController"
    newModal.windowClass = "bookingModal"

    newModal.resolve = new js.Object().asInstanceOf[js.Dictionary[js.Any]]
    newModal.resolve("room") = write(room)
    modal.open(newModal)
  }
}

