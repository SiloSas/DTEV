package Room

import java.util.UUID
import javax.inject.Inject

import administration.Authenticated
import play.api.Logger
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.{Action, _}
import shared.Room
import upickle.default._
import util.Utilities

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal


case class ReservationForm(firstName: String,
                           name: String,
                           email: String,
                           phoneNumber: String,
                           numberOfPersons: Int,
                           extraBed: Boolean,
                           extraBreakfast: Boolean)

case class Reservation(room: Room,
                       start: Long,
                       end: Long,
                       reservationForm: ReservationForm)

class RoomController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                               val roomMethods: RoomMethods,
                               val reservationMethods: ReservationMethods,
                               utilities: Utilities)
  extends Controller {

  def findAll() = Action.async {
    roomMethods.findAll.map { rooms =>
      Ok(write(rooms))
    }
  }

  def findById(id: String) = Action.async {
    roomMethods.findById(id).map { roomFound =>
      Ok(write(roomFound))
    }
  }

  def findAvailable(start: Long, end: Long) = Action.async {
    roomMethods.findAvailable(start, end).map { roomsFound =>
      Ok(write(roomsFound))
    }
  }

  def update = Authenticated.async(parse.json) { request =>
    val room = read[Room](request.body.as[JsObject].toString)

    roomMethods.update(room) map { numberOfRowsUpdated => Ok(Json.toJson(numberOfRowsUpdated)) }
  }

  case class ReservationDBWithStringDate(roomId: String,
                                         roomName: String,
                                         arrivalDate: String,
                                         departureDate: String,
                                         numberOfPersons: Int,
                                         firstName: String,
                                         name: String,
                                         email: String,
                                         phoneNumber: String,
                                         extraBed: Boolean,
                                         extraBreakfast: Boolean)

  implicit val reservationDBWrites = Json.writes[ReservationDB]
  implicit val reservationDBWithStringDate = Json.writes[ReservationDBWithStringDate]

  def findAllReservations = Authenticated.async {
    reservationMethods.findAll() map { rs => val goodRS = rs map { r =>

      ReservationDBWithStringDate(r.roomId, r.roomName, r.arrivalDate.toString, r.departureDate.toString,
        r.numberOfPersons, r.firstName, r.name, r.email, r.phoneNumber, r.extraBed, r.extraBreakfast)
    }

      Ok(Json.toJson(goodRS))
    }
  }

  def findAllReservationsString = Authenticated.async {
    val goodRS = reservationMethods.findAll() map { rs =>
      rs map { r =>

        println(r.arrivalDate.toString, r.departureDate.toString)

        ReservationDBWithStringDate(r.roomId, r.roomName, r.arrivalDate.toString, r.departureDate.toString,
          r.numberOfPersons, r.firstName, r.name, r.email, r.phoneNumber, r.extraBed, r.extraBreakfast)
      }
    }

//    goodRS map { println }

    val aaa = goodRS map {
      case emptyy if emptyy.isEmpty => ""
      case ee =>

        val events =  ee.map { aa =>
          val extraBedString = aa.extraBed match { case true => "oui"; case _ => "non" }
          val extraBreakfastString = aa.extraBreakfast match { case true => "oui"; case _ => "non" }
          "BEGIN:VEVENT" + "\n" +
            "UID:" + UUID.randomUUID() + "\n" +
            "DTSTART:" + aa.arrivalDate.replaceAll("-", "") + "T160000Z" + "\n" +
            "DTEND:" + aa.departureDate.replaceAll("-", "") + "T120000Z" + "\n" +
            "SUMMARY:" + aa.roomName + "\n" +
             "DESCRIPTION:" + aa.firstName + "\t" + aa.name + "\t" + aa.phoneNumber + "\t" + aa.email + "\t" +
            "nombre de personnes : " + aa.numberOfPersons + "\t" +
            "lit d'appoint: " + extraBedString + "\t" +
            "petit déjeuner: " + extraBreakfastString + "\n" +
            "CLASS:PRIVATE" + "\n" +
            "END:VEVENT" + "\n"
        }.mkString("\n")

      val icsCalendar =
        """BEGIN:VCALENDAR""" + "\n" +
        """VERSION:2.0""" + "\n" +
        """PRODID:-//hacksw/handcal//NONSGML v1.0//EN""" + "\n" +
        events +
        "END:VCALENDAR"

//        println("icsCalendar = " + icsCalendar)

      icsCalendar
    }

     aaa map { u => Ok(Json.toJson(u)) }

  }

  def postReservation = Action.async(parse.json) { request =>
    val reservation = read[Reservation](request.body.as[JsObject].toString)

    reservationMethods.save(reservationToReservationDB(reservation)) map { res =>
      import utilities.CommentOrRoom._
      utilities.sendNotificationMail(ReservationNotification)

      Ok(Json.toJson(res))
    } recover {
      case NonFatal(e) =>
        Logger.error(e.toString)
        InternalServerError
    }
  }

  def reservationToReservationDB(reservation: Reservation): ReservationDB = {
    val start = new java.sql.Date(reservation.start)
    val end = new java.sql.Date(reservation.end)

    ReservationDB(
      None,
      reservation.room.id,
      reservation.room.name,
      start,
      end,
      reservation.reservationForm.numberOfPersons,
      reservation.reservationForm.firstName,
      reservation.reservationForm.name,
      reservation.reservationForm.email,
      reservation.reservationForm.phoneNumber,
      reservation.reservationForm.extraBed,
      reservation.reservationForm.extraBreakfast)
  }
}