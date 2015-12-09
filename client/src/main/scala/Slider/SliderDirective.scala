package Slider

import com.greencatsoft.angularjs.core.Window
import com.greencatsoft.angularjs.{Attributes, ElementDirective, TemplatedDirective, injectable}
import org.scalajs.dom.{Element, document}
import org.scalajs.dom.html.Html
import org.scalajs.dom.raw.UIEvent

import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("slider")
class SliderDirective(window: Window) extends ElementDirective with TemplatedDirective {
  override val templateUrl = "assets/templates/Slider/slider.html"

  override def link(scope: ScopeType, elements: Seq[Element], attrs: Attributes): Unit = {
    elements.headOption.map(_.asInstanceOf[Html]) foreach { element =>
      val content = document.getElementById("slider-content").asInstanceOf[Html]
      def setNewHeight(newHeight: Double): Unit = {
        if (newHeight < window.innerHeight) {
          element.style.height = newHeight + "px"
          content.style.marginTop = newHeight + "px"
        } else {
          element.style.height = window.innerHeight + "px"
          content.style.marginTop = window.innerHeight + "px"
        }
      }
      setNewHeight(window.innerWidth * 0.75369458128)

      window.onresize = (event: UIEvent) =>
        setNewHeight(window.innerWidth * 0.75369458128)

    }
  }
}
