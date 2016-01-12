package Contact

import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.{AbstractController, injectable}
import shared.Message

import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("contactController")
class ContactController(scope: Scope, contactService: ContactService) extends AbstractController[Scope](scope) {

  @JSExport
  def post(email: String, message: String) = {
    val newMessage = Message(email = email, message = message)
    contactService.post(newMessage)
  }
}
