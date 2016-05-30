import com.greencatsoft.angularjs.core.{Route, RouteProvider}
import com.greencatsoft.angularjs.{inject, Config}


object RoutingConfig extends Config {

  @inject
  var routeProvider: RouteProvider = _

  override def initialize() {

    routeProvider
      .when(
        path = "/",
        route = Route(
          templateUrl = "/assets/templates/home.html",
          title = "Main"))
      .when(
        path = "/rooms/:id",
        route = Route(
          templateUrl = "/assets/templates/Room/room.html",
          title = "Room",
          controller = "roomController"))
      .when(
        path = "/search/:start/:end",
        route = Route(
          templateUrl = "/assets/templates/Booking/booking.html",
          title = "Research",
          controller = "bookingController"))
      .when(
        path = "/contact",
        route = Route(
          templateUrl = "/assets/templates/Contact/contact.html",
          title = "Contact",
          controller = "contactController"))
      .when(
        path = "/mentionsLegales",
        route = Route(
          templateUrl = "/assets/templates/legalNotice/legalNotice.html",
          title = "legalNotice"))
      .when(
        path = "/cookies",
        route = Route(
          templateUrl = "/assets/templates/legalNotice/cookiesNotice.html",
          title = "cookiesNotice"))
      .when(
        path = "/tarifs",
        route = Route(
          templateUrl = "/assets/templates/legalNotice/tariffs.html",
          title = "cookiesNotice"))
//      .when(
//        path = "/admin",
//        route = Route(
//          templateUrl = "/assets/templates/BackEnd/main.html",
//          title = "Admin",
//          controller = "backEndController"))
  }
}