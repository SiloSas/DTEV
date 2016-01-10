package RoomNav

import com.greencatsoft.angularjs.extensions.material.Sidenav
import com.greencatsoft.angularjs.{AttributeDirective, injectable}

import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("slideLeft")
class SlideLeftDirective(sidenav: Sidenav) extends AttributeDirective {

  @JSExport
  def close(): Any = {
    println("lkjlkjlkjlkjlkjljk")
    sidenav("left").close()
  }

}