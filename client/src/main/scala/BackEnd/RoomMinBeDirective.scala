package BackEnd

import Room.RoomService
import com.greencatsoft.angularjs.core.{Timeout, Window}
import com.greencatsoft.angularjs.{Attributes, ElementDirective, TemplatedDirective, injectable}
import org.scalajs.dom.Element
import org.scalajs.dom.html._
import org.scalajs.dom.raw.UIEvent

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("roomMinBe")
class RoomMinBeDirective(window: Window, timeout: Timeout, roomService: RoomService) extends ElementDirective with TemplatedDirective {

  override val templateUrl = "assets/templates/BackEnd/roomMinBe.html"

  override def link(scope: ScopeType, elements: Seq[Element], attrs: Attributes): Unit = {
    elements.headOption.map(_.asInstanceOf[Html]) foreach { element =>
      def setNewHeight(newHeight: Double): Unit = {
        element.style.height = newHeight + "px"
      }
      timeout (fn = () => {
        setNewHeight(Math.ceil(element.clientWidth * 0.62893081761))
      }, 100, invokeApply = false)

      window.onresize = (event: UIEvent) =>
        setNewHeight(Math.ceil(element.clientWidth * 0.62893081761))
    }
  }

  @JSExport
  def update(room: js.Any) = {
    println("lkjlkjlkj")
    org.scalajs.dom.console.log(room)
    roomService.update(room)
  }
}