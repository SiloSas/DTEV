package mails

import java.io.File
import javax.inject.Inject

import play.api.libs.ws._

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json.{Format, Json}

import scala.concurrent.Future

final case class Attachment(name: String, contentType: String, file: File)
final case class MailWithAttachments(subject: String,
                                     content: String,
                                     recipients: Seq[String],
                                     attachments: Seq[Attachment])
final case class MailResult(hasSucceeded: Boolean, errorStatus: Int = 0)

class MailMethods @Inject()(wSClient: WSClient) {

  private val mailGunRequest = wSClient
    .url("https://api.mailgun.net/v3/sandbox7cf91efa20a44e9c81d3344e9f565e0e.mailgun.org/messages")
    .withAuth("api", "key-1adeded763a2b700e2876487fe04f1b9", WSAuthScheme.BASIC)

  def send(content: String, email: String): Future[MailResult] = mailGunRequest
    .post(Map(
      "from" -> Seq("Des toits en ville <" + email + ">"),
      "to" -> Seq("jpgarcia4@free.fr"),
      "subject" -> Seq("Vous avez un nouveau message"),
      "html" -> Seq(content))) map { response =>
    response.status match {
      case 200 =>
        println("Mail sent")
        MailResult(hasSucceeded = true)

      case errorStatus: Int =>
        println(response)
        println(content)
        println(email)
        println("Error: Mail not sent, errorStatus: " + errorStatus)
        MailResult(hasSucceeded = false, errorStatus = errorStatus)
    }
  }
}
