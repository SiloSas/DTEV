import Agreement.{AgreementController, AgreementServiceFactory, AgreementDirective}
import Booking.{BookingModalController, BookingController}
import Comments.{CommentServiceFactory, CommentsDirective, CommentController}
import Contact.{ContactServiceFactory, ContactDirective, ContactController}
import Map.MapDirective
import Room.{RoomMinHomeDirective, RoomController, RoomMinDirective, RoomServiceFactory}
import RoomNav.{SlideLeftDirective, RoomNavDirective}
import SearchBar.SearchBarDirective
import Slider.{SliderContentDirective, SliderController, SliderDirective}
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
      .controller[ContactController]
      .controller[BookingController]
      .controller[BookingModalController]
      .directive[RoomMinDirective]
      .directive[AgreementDirective]
      .directive[RoomMinHomeDirective]
      .directive[SliderDirective]
      .directive[SliderContentDirective]
      .directive[CommentsDirective]
      .directive[MapDirective]
      .directive[RoomNavDirective]
      .directive[SlideLeftDirective]
      .directive[SearchBarDirective]
      .directive[ContactDirective]
      .factory[RoomServiceFactory]
      .factory[AgreementServiceFactory]
      .factory[CommentServiceFactory]
      .factory[ContactServiceFactory]
      .config(RoutingConfig)
  }
}
