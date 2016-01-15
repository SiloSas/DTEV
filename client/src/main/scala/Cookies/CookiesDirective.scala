package Cookies

import com.greencatsoft.angularjs.core.{Timeout, Window}
import com.greencatsoft.angularjs.{Attributes, AttributeDirective, injectable}
import org.scalajs.dom.html.Html
import org.scalajs.dom.raw.{Event, UIEvent, Element}
import org.scalajs.dom.document
import org.scalajs.dom.console
import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("cookies")
class CookiesDirective(window: Window, timeout: Timeout) extends AttributeDirective {
  var baseHeight = window.innerHeight.toDouble
  override def link(scope: ScopeType, elements: Seq[Element], attrs: Attributes): Unit = {
    elements.headOption.map(_.asInstanceOf[Html]) foreach { element =>
      document.getElementById("mainContainer").asInstanceOf[Html].style.height = baseHeight + "px"
      def setMainContainerHeight(): Unit = {
        val cookiesHeight = document.getElementById("cookieHeadband").getBoundingClientRect().height
        val footerHeight = document.getElementById("searchBar").getBoundingClientRect().height
        if(footerHeight > 10) {
          console.log( cookiesHeight, footerHeight)
          val newHeight = window.innerHeight - cookiesHeight - footerHeight
          document.getElementById("mainContainer").asInstanceOf[Html].style.height = newHeight + "px"
          baseHeight = newHeight
        }
        else {
          timeout( () => {
            setMainContainerHeight()
          }, 100)
        }
      }
      timeout( () => {
        setMainContainerHeight()
      }, 100)
      attrs.$observe("cookies", (value: String) => {
        timeout( () => {
          setMainContainerHeight()
        }, 100)
        setMainContainerHeight()
      })
      window.addEventListener("resize", (event: Event) => {
        timeout( () => {
          setMainContainerHeight()
        }, 100)
        setMainContainerHeight()
      })
      scope.$on("$locationChangeSuccess", () => {
        console.log("yooy")
        timeout( () => {
          setMainContainerHeight()
        }, 100)
        setMainContainerHeight()
    })
    }
  }
}