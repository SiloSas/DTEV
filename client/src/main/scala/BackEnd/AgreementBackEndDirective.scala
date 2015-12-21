package BackEnd

import com.greencatsoft.angularjs.core.{Timeout, Window}
import com.greencatsoft.angularjs.{Attributes, ElementDirective, TemplatedDirective, injectable}
import org.scalajs.dom.Element
import org.scalajs.dom.html._
import org.scalajs.dom.raw.UIEvent

import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("agreementBe")
class AgreementBackEndDirective(window: Window, timeout: Timeout) extends ElementDirective with TemplatedDirective {

  override val templateUrl = "assets/templates/BackEnd/agreementBe.html"

  override def link(scope: ScopeType, elements: Seq[Element], attrs: Attributes): Unit = {
    elements.headOption.map(_.asInstanceOf[Html]) foreach { element =>
      def setNewHeight(newHeight: Double): Unit = {
        element.style.height = newHeight + "px"
      }

      timeout (fn = () => {
        setNewHeight(Math.ceil(element.clientWidth * 0.66))
      }, 50, invokeApply = false)

      window.onresize = (event: UIEvent) =>
        setNewHeight(Math.ceil(element.clientWidth * 0.66))
    }
  }
}