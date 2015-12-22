package util

import javax.inject.Inject

import play.api.libs.mailer._


class Utilities @Inject() (mailerClient: MailerClient) {

  object CommentOrRoom extends Enumeration {
    type CommentOrReservationNotification = Value
    val CommentNotification, ReservationNotification = Value
  }
  import CommentOrRoom._

  def sendNotificationMail(content: CommentOrReservationNotification): Unit = {
    val actualContent = content match {
      case CommentNotification => "Un commentaire a été posté. Vous pouvez le valider dès à présent "
      case ReservationNotification => "Une réservation a été faite, vous pouvez en voir les détails "
    }

    val email = Email(
      subject = "Des Toits En Ville : vous avez une notification",
      from = "notifications.destoitsenville@gmail.com",
      to = Seq("<simongarnier07@hotmail.fr>"),
      bodyHtml = Some(
        s"""<html><body><p>$actualContent
           |à cette addresse : <a href="des-toits-en-ville.com/#/admin">des-toits-en-ville.com/#/admin</a></p></body></html>""".stripMargin)
    )

    mailerClient.send(email)
  }
}
