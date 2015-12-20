package Booking

import com.greencatsoft.angularjs.core.RouteParams
import com.greencatsoft.angularjs.extensions.ModalInstance
import com.greencatsoft.angularjs.{AbstractController, injectable}
import org.scalajs.dom.console
import shared.Room
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.annotation.JSExport


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
