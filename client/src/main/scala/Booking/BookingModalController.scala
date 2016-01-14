package Booking

import com.greencatsoft.angularjs.core.{HttpService, RouteParams, Timeout}
import com.greencatsoft.angularjs.extensions.ModalInstance
import com.greencatsoft.angularjs.{AbstractController, injectable}
import materialDesign.MdToastService
import shared.Room
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExport


case class ReservationForm(firstName: String,
                           name: String,
                           email: String,
                           phoneNumber: String,
                           numberOfPersons: Int,
                           extraBed: Boolean)

case class Reservation(room: Room,
                       start: Long,
                       end: Long,
                       reservationForm: ReservationForm)


@JSExport
@injectable("bookingModalController")
class BookingModalController(scope: BookingScope,
                             modalInstance: ModalInstance[Any],
                             room: String,
                             routeParams: RouteParams,
                             timeout: Timeout,
                             httpService: HttpService,
                             mdToast: MdToastService)
  extends AbstractController[BookingScope](scope) {

  scope.room = read[Room](room)
  scope.rooms = js.Array[Room]()
  scope.start = routeParams.get("start").toString.toLong
  scope.end = routeParams.get("end").toString.toLong
  scope.totalPrice = 0
  scope.numberOfNights = calculateNumberOfDaysBetween(scope.start, scope.end)

  @JSExport
  def close() = modalInstance.close()

  @JSExport
  def book(reservationForm: js.Any) = {

    val reservationFormTyped = read[ReservationForm](JSON.stringify(reservationForm))

    val reservation = Reservation(
      room = scope.room,
      start = scope.start,
      end = scope.end,
      reservationForm = reservationFormTyped)

    httpService.post[js.Any]("/reservation", write(reservation)) map { a =>

      timeout(() => {
        val toast = mdToast.simple("Merci, votre réservation a bien été enregistrée.")
        toast._options.position = "{right: true}"
        mdToast.show(toast)

        modalInstance.close()
      })
    } recover {
      case e: Exception =>
        val toast = mdToast.simple("Désolé, une erreur s'est produite.")
        toast._options.position = "{right: true}"
        mdToast.show(toast)
    }
  }

  @JSExport
  def addOrRemoveExtraBedToTotalPrice(extraBed: Boolean) = {
    val newPrice = extraBed match {
      case false => scope.totalPrice - 15
      case true => scope.totalPrice + 15
    }

    scope.$apply { scope.totalPrice = newPrice }
  }

  @JSExport
  def computeTotalPrice = {
    val totalNightsPrice = scope.numberOfNights match {
      case i if i < 7 => i * 65
      case j if j < 21 => j * 52
      case k => k * 39
    }

    val totalPrice = totalNightsPrice

    timeout(() => scope.totalPrice = totalPrice)
  }

  computeTotalPrice

  def calculateNumberOfDaysBetween(startDate: Long, endDate: Long): Int = {
    val millisecondsPerDay = 24 * 60 * 60 * 1000

    ((endDate - startDate) / millisecondsPerDay).toInt
  }
}