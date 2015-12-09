package RoomNav

import com.greencatsoft.angularjs.extensions.material.Sidenav
import com.greencatsoft.angularjs.{ClassDirective, injectable}

import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("slideLeft")
class SlideLeftDirective(sidenav: Sidenav) extends ClassDirective {
  @JSExport
  def close(): Any = {
    sidenav("left").close()
  }
}