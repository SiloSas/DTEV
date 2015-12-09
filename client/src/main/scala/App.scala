import Agreement.{AgreementController, AgreementServiceFactory, AgreementDirective}
import Comments.{CommentsDirective, CommentController}
import Map.MapDirective
import Room.{RoomMinHomeDirective, RoomController, RoomMinDirective, RoomServiceFactory}
import RoomNav.RoomNavDirective
import Slider.{SliderController, SliderDirective}
import com.greencatsoft.angularjs._

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport
object App extends JSApp {

  override def main() {
    val module = Angular.module("app", Seq("ngAnimate", "ngAria", "ngMaterial", "mm.foundation", "ngRoute", "ngMap"))

    module
    .controller[RoomController]
      .controller[SliderController]
      .controller[AgreementController]
      .controller[CommentController]
      .directive[RoomMinDirective]
      .directive[AgreementDirective]
      .directive[RoomMinHomeDirective]
      .directive[SliderDirective]
      .directive[CommentsDirective]
      .directive[MapDirective]
      .directive[RoomNavDirective]
      .factory[RoomServiceFactory]
      .factory[AgreementServiceFactory]
      .config(RoutingConfig)
  }
}
