package Agreement

import com.greencatsoft.angularjs.core.Window
import com.greencatsoft.angularjs.{Attributes, ElementDirective, TemplatedDirective, injectable}
import org.scalajs.dom.{Element, document}
import org.scalajs.dom.html.Html
import org.scalajs.dom.raw.UIEvent

import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("agreement")
class AgreementDirective(window: Window) extends ElementDirective with TemplatedDirective {
  override val templateUrl = "assets/templates/Agreement/agreement.html"

  override def link(scope: ScopeType, elements: Seq[Element], attrs: Attributes): Unit = {
    elements.headOption.map(_.asInstanceOf[Html]) foreach { element =>
      def setNewHeight(newHeight: Double): Unit = {
        element.style.height = newHeight + "px"
      }
      setNewHeight(element.clientWidth * 0.66)

      window.onresize = (event: UIEvent) =>
        setNewHeight(element.clientWidth * 0.66)

    }
  }
}

