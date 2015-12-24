package Booking

import com.greencatsoft.angularjs.core.{Timeout, RouteParams}
import com.greencatsoft.angularjs.extensions.ModalInstance
import com.greencatsoft.angularjs.{AbstractController, injectable}
import shared.Room
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("bookingModalController")
class BookingModalController(scope: BookingScope,
                             modalInstance: ModalInstance[Any],
                             room: String,
                             routeParams: RouteParams,
                             timeout: Timeout)
    extends AbstractController[BookingScope](scope) {

  scope.room = read[Room](room)
  scope.rooms = js.Array[Room]()
  scope.start = routeParams.get("start").asInstanceOf[String]
  scope.end = routeParams.get("end").asInstanceOf[String]
  scope.totalPrice = 0
  scope.numberOfNights = calculateNumberOfDaysBetween(scope.start, scope.end)

  @JSExport
  def close() = modalInstance.close()

  @JSExport
  def computeInfoValues = {
    val totalNightsPrice = scope.numberOfNights match {
      case i if i < 7 => i * 65
      case j if j < 21 => j * 52
      case k => k * 39
    }
    val totalPrice = totalNightsPrice
    println("totalPrice = " + totalPrice)

    timeout(() => scope.totalPrice = totalPrice)
  }

  computeInfoValues

  def calculateNumberOfDaysBetween(startDate: String, endDate: String): Int = {
    val start = new Date(startDate.replaceAll("-", "/"))
    val end = new Date(endDate.replaceAll("-", "/"))
    val millisecondsPerDay = 24 * 60 * 60 * 1000
    println("millisecondsPerDay = " + millisecondsPerDay)
    println(start.getMilliseconds())
    println(end.getMilliseconds())
    println("end = " + end)

    val a = (end.getTime() - start.getTime()) / millisecondsPerDay
    println("a = " + a)
    println("aI = " + a.toInt)
    a.toInt
  }
}
