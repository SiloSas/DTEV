package Navigation

import com.greencatsoft.angularjs.core.{Window, Compile, Timeout}
import com.greencatsoft.angularjs.extensions.ModalService
import com.greencatsoft.angularjs.{AttributeDirective, Attributes, injectable}
import org.scalajs.dom
import org.scalajs.dom.Element
import org.scalajs.dom.html._
import org.scalajs.dom.raw.{Event, NodeList}

import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("animationsAtScroll")
class AnimationsAtScrollDirective(modal: ModalService, timeout: Timeout, compile: Compile, window: Window) extends AttributeDirective {

  val mainContainer: Element = dom.document.getElementsByClassName("parallax").item(0).asInstanceOf[Html]

  override def link(scope: ScopeType, elems: Seq[Element], attrs: Attributes) = {
      elems foreach { elem =>
        def applyAnimationWhenVisible(): Unit = {
          val offsetInPx = window.innerHeight
          val elementPosition: Int = elem.getBoundingClientRect().top.toInt - offsetInPx

          addClassAndRemoveListenerIfVisible(elem, elementPosition, removeMainContainerListener)

          def addAnimationWhenElementIsVisible(): (Event) => _ = (event: Event) => {
            addClassAndRemoveListenerIfVisible(elem, elementPosition, removeMainContainerListener)
          }

          def removeMainContainerListener() = {
            mainContainer.removeEventListener("scroll", addAnimationWhenElementIsVisible())
          }

          mainContainer.addEventListener("scroll", addAnimationWhenElementIsVisible())
        }

        timeout(() => {
          val images: NodeList = dom.document.getElementsByTagName("img")
          val numberOfImages = images.length

          waitForImageToBeCompleted(0)
          def waitForImageToBeCompleted(i: Int): Unit = {
            timeout( () => {
              if(i == numberOfImages)
                applyAnimationWhenVisible()
              else
                if(!images(i).asInstanceOf[Image].complete)
                  waitForImageToBeCompleted(i)
                else
                  waitForImageToBeCompleted(i + 1)
            }, 100)
          }
      }, 0)
    }
  }

  def addClassAndRemoveListenerIfVisible(elem: Element, elementPosition: Int, removeMainContainerListener: () => Unit): Unit = {
    if (mainContainer.scrollTop > elementPosition) {
      elem.classList.add("animatedAtScroll")
      elem.removeAttribute("animations-at-scroll")
      removeMainContainerListener()
    }
  }
}