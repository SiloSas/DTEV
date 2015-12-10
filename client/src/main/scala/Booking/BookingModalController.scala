package Booking


import Room.RoomScope
import com.greencatsoft.angularjs.core.{RouteParams, Timeout}
import com.greencatsoft.angularjs.extensions.{ModalService, ModalInstance}
import com.greencatsoft.angularjs.{AbstractController, injectable}
import shared.Room
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}
import org.scalajs.dom.console

@JSExport
@injectable("bookingModalController")
class BookingModalController(scope: BookingScope, modalInstance: ModalInstance[Any], room: String, routeParams: RouteParams)
  extends AbstractController[BookingScope](scope) {

  scope.room = read[Room](room)
  scope.rooms = js.Array[Room]()
  scope.start = new Date(routeParams.get("start").asInstanceOf[String])
  scope.end = new js.Date(routeParams.get("end").asInstanceOf[String])

  @JSExport
  def close() = modalInstance.close()

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }

}
