package BackEnd

import com.greencatsoft.angularjs.core.{Timeout, Window}
import com.greencatsoft.angularjs.{Attributes, ElementDirective, TemplatedDirective, injectable}
import org.scalajs.dom.Element
import org.scalajs.dom.html._
import org.scalajs.dom.raw.UIEvent
import services.AgreementService
import shared.{Agreement, Room}
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("agreementBe")
class AgreementBackEndDirective(window: Window, timeout: Timeout, agreementService: AgreementService)
  extends ElementDirective with TemplatedDirective {

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


  @JSExport
  def updateAgreement(service: js.Any): Unit = {
    org.scalajs.dom.console.log(service)
    agreementService.update(read[Agreement](JSON.stringify(service)))
  }
}