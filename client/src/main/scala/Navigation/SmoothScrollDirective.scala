package Navigation

import com.greencatsoft.angularjs.core.{Window, Timeout}
import com.greencatsoft.angularjs.extensions.ModalService
import com.greencatsoft.angularjs.{Attributes, ElementDirective, injectable}
import org.scalajs.dom
import org.scalajs.dom.{Element, document}
import org.scalajs.dom.html._
import org.scalajs.dom.raw.MouseEvent

import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("smoothScroll")
class SmoothScrollDirective(window: Window, modal: ModalService, timeout: Timeout) extends ElementDirective {

  def scrollToAnchor(): Any = {
    val container = document.getElementsByClassName("parallax").item(0).asInstanceOf[Html]

    val anchorPosition: Double = document.getElementById("firstDescriptionText").getBoundingClientRect().top

    val startingPosition = container.scrollTop

    var actualPosition: Double = startingPosition

    val offset = 50

    val cookieHeadbandHeight = document.getElementById("cookieHeadband").asInstanceOf[Html].clientHeight

    var scrollIterationFunction: Int = 0

    scrollIterationFunction = dom.setInterval(() => {
      if(actualPosition < anchorPosition + startingPosition + cookieHeadbandHeight) {
        container.scrollTop = actualPosition + offset
        actualPosition += offset
      } else
        dom.clearInterval(scrollIterationFunction)
    }, 2)
  }

  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes) {
    elems.headOption.map(_.asInstanceOf[Html]) foreach { elem =>
      elem.onclick = (event: MouseEvent) => {
        scrollToAnchor()
      }
    }
  }
}