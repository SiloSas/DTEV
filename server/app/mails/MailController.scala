package mails

import javax.inject.Inject

import play.api.libs.json.{Format, JsError, JsSuccess, Json}
import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global

class MailController @Inject() (mailMethods: MailMethods) extends Controller {

  def send(content: String, from: String) = Action.async { request =>
    mailMethods.send(content, from) map { _ => Ok }
  }
}
