import Agreement.{AgreementController, AgreementDirective, AgreementServiceFactory}
import BackEnd.{AgreementBackEndDirective, RoomMinBeDirective}
import Booking.{BookingController, BookingModalController}
import Comments.{CommentController, CommentServiceFactory, CommentsDirective}
import Contact.{ContactController, ContactDirective, ContactServiceFactory}
import Map.MapDirective
import Navigation.SmoothScrollDirective
import Room.{RoomController, RoomMinDirective, RoomMinHomeDirective, RoomServiceFactory}
import RoomNav.{RoomNavDirective, SlideLeftDirective}
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
      .directive[RoomMinBeDirective]
      .directive[AgreementBackEndDirective]
      .directive[SmoothScrollDirective]
      .factory[RoomServiceFactory]
      .factory[AgreementServiceFactory]
      .factory[CommentServiceFactory]
      .factory[ContactServiceFactory]
      .config(RoutingConfig)
  }
}
