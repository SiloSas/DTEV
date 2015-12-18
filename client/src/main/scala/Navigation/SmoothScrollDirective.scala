package Navigation

import com.greencatsoft.angularjs.core.Timeout
import com.greencatsoft.angularjs.extensions.ModalService
import com.greencatsoft.angularjs.{Attributes, ElementDirective, injectable}
import org.scalajs.dom
import org.scalajs.dom.{Element, document}
import org.scalajs.dom.html._
import org.scalajs.dom.raw.MouseEvent

import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("smoothScroll")
class SmoothScrollDirective(modal: ModalService, timeout: Timeout) extends ElementDirective {

  def scrollToAnchor(): Any = {
    val offset = 80
    val anchorPositionPlus50: Double = document.getElementById("firstDescriptionText").getBoundingClientRect().top + offset

    val container = document.getElementsByClassName("parallax").item(0).asInstanceOf[Html]

    var actualPosition: Double = offset

    var scrollIterationFunction: Int = 0

    scrollIterationFunction = dom.setInterval(() => {
      if(actualPosition < anchorPositionPlus50) {
        container.scrollTop = actualPosition
        actualPosition += offset
      } else
        dom.clearInterval(scrollIterationFunction)
    }, 5)
  }

  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes) {
    elems.headOption.map(_.asInstanceOf[Html]) foreach { elem =>
      elem.onclick = (event: MouseEvent) => {
        scrollToAnchor()
      }
    }
  }
}