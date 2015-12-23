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
  scope.totalPrice = 0

  @JSExport
  def close() = modalInstance.close()

  @JSExport
  def computeInfoValues(startDate: String, endDate: String) = {
    println("okokkok")
    val a = new js.Date(startDate)
    val b = new js.Date(endDate)

    val millisecondsPerDay = 24 * 60 * 60 * 1000
    val c = b.getMilliseconds() - a.getMilliseconds() / millisecondsPerDay

    scope.$apply(scope.totalPrice = c * 5)//scope.room.price.toInt)
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
