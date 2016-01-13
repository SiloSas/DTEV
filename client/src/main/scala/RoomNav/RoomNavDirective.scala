package RoomNav

import com.greencatsoft.angularjs.extensions.material.Sidenav
import com.greencatsoft.angularjs.{TemplatedDirective, ElementDirective, injectable}

import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("roomNav")
class RoomNavDirective(sidenav: Sidenav) extends ElementDirective with TemplatedDirective  {
  override val templateUrl = "assets/templates/RoomNav/roomNav.html"

  @JSExport
  def toggleLeft(): Any = {
    sidenav("left").toggle()
  }
}