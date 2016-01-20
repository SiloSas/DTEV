import BackEnd.{AgreementBackEndDirective, BackEndController, RoomMinBeDirective, UpdateCommentsDirective}
import Booking.{BookingController, BookingModalController}
import Comments.{CommentController, CommentServiceFactory, CommentsDirective}
import Contact.{ContactController, ContactServiceFactory}
import Cookies.CookiesDirective
import Map.MapDirective
import Navigation.{AnimationsAtScrollDirective, SmoothScrollDirective}
import Room.{RoomController, RoomMinDirective, RoomMinHomeDirective, RoomServiceFactory}
import RoomNav.{RoomNavDirective, SlideLeftDirective}
import SearchBar.SearchBarDirective
import Slider.{SliderContentDirective, SliderController, SliderDirective}
import com.greencatsoft.angularjs._
import materialDesign.MdDateLocaleProviderConfig
import services.{AgreementController, AgreementDirective, AgreementServiceFactory}

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport


@JSExport
object App extends JSApp {

  override def main() {
    val module = Angular.module("app", Seq("ngAnimate", "ngAria", "ngMaterial", "mm.foundation", "ngRoute", "ngMap",
      "uploader", "themingAngularMaterial"))

    module.controller[RoomController]
      .controller[SliderController]
      .controller[AgreementController]
      .controller[CommentController]
      .controller[ContactController]
      .controller[BookingController]
      .controller[BookingModalController]
      .controller[BackEndController]
      .directive[RoomMinDirective]
      .directive[AgreementDirective]
      .directive[RoomMinHomeDirective]
      .directive[SliderDirective]
      .directive[SliderContentDirective]
      .directive[CommentsDirective]
      .directive[UpdateCommentsDirective]
      .directive[MapDirective]
      .directive[RoomNavDirective]
      .directive[SlideLeftDirective]
      .directive[SearchBarDirective]
      .directive[RoomMinBeDirective]
      .directive[AgreementBackEndDirective]
      .directive[SmoothScrollDirective]
      .directive[AnimationsAtScrollDirective]
      .directive[CookiesDirective]
      .factory[RoomServiceFactory]
      .factory[AgreementServiceFactory]
      .factory[CommentServiceFactory]
      .factory[ContactServiceFactory]
      .config(RoutingConfig)
      .config(MdDateLocaleProviderConfig)
  }
}
