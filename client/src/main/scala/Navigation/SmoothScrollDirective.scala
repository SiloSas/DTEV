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
        val anchorPositionPlus50: Double = document.getElementById("firstDescriptionText").getBoundingClientRect().top + 50

        val container = document.getElementsByClassName("parallax").item(0).asInstanceOf[Html]

        var actualPosition: Double = 50

        var a: Int = 0

        a = dom.setInterval(() => {
            if(actualPosition < anchorPositionPlus50) {
                container.scrollTop = actualPosition
              actualPosition += 50
            } else
                dom.clearInterval(a)
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