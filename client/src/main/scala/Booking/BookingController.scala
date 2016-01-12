package Booking

import Room.RoomService
import com.greencatsoft.angularjs.core.{RouteParams, Timeout}
import com.greencatsoft.angularjs.{AbstractController, injectable}
import shared.Room
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import org.scalajs.dom.console
import scala.concurrent.ExecutionContext.Implicits.global

@JSExport
@injectable("bookingController")
class BookingController(scope: BookingScope, timeout: Timeout, service: RoomService, $routeParams: RouteParams)
  extends AbstractController[BookingScope](scope) {

  var isWaitingAvailableRooms: Boolean = true

  scope.rooms = js.Array[Room]()
  scope.availableRooms = js.Array[Room]()

  service.findAll() onComplete {
    case Success(rooms) => scope.$apply { scope.rooms = rooms.filter(isAvailable).toJSArray }
    case Failure(t) => handleError(t)
  }


  val start = $routeParams.get("start").toString.toLong
  val end = $routeParams.get("end").toString.toLong

  service.findAvailable(start, end) onComplete {
    case Success(rooms) =>
      scope.$apply {
        scope.availableRooms = rooms.toJSArray
        scope.rooms = scope.rooms.filter(isAvailable)
//        timeout(() => {isWaitingAvailableRooms = false}, 1500)
        isWaitingAvailableRooms = false
      }

    case Failure(t) =>
      handleError(t)
  }

  def isAvailable(room: Room): Boolean = {
    scope.availableRooms.filter(_.id == room.id).length == 0
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
