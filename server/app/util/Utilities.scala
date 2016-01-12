package util

import javax.inject.Inject

import play.api.libs.json.JsObject
import play.api.libs.mailer._
import play.api.mvc.{Action, Controller}
import shared.Message
import upickle.default._


class Utilities @Inject() (mailerClient: MailerClient) extends Controller {

  object CommentOrRoom extends Enumeration {
    type CommentOrReservationNotification = Value
    val CommentNotification, ReservationNotification = Value
  }
  import CommentOrRoom._

  val destinationMail = Seq("<simongarnier07@hotmail.fr>")

  def sendNotificationMail(content: CommentOrReservationNotification): Unit = {
    val actualContent = content match {
      case CommentNotification => "Un commentaire a été posté. Vous pouvez le valider dès à présent "
      case ReservationNotification => "Une réservation a été faite, vous pouvez en voir les détails "
    }

    val email = Email(
      subject = "Des Toits En Ville : vous avez une notification",
      from = "notifications.destoitsenville@gmail.com",
      to = destinationMail,
      bodyHtml = Some(
        s"""<html><body><p>$actualContent
           |à cette addresse : <a href="des-toits-en-ville.com/#/admin">des-toits-en-ville.com/#/admin</a></p></body></html>""".stripMargin)
    )

    mailerClient.send(email)
  }

  def contact = Action(parse.json) { request =>
    val message = read[Message](request.body.as[JsObject].toString)

    val email = Email(
      subject = "Des Toits En Ville : vous avez un nouveau message",
      from = message.email,
      to = destinationMail,
      bodyHtml = Some(message.message)
    )

    mailerClient.send(email)

    Ok
  }
}
