package Contact
import com.greencatsoft.angularjs.core.{Scope, RouteParams, Timeout}
import com.greencatsoft.angularjs.extensions.{ModalService, ModalInstance}
import com.greencatsoft.angularjs.{AbstractController, injectable}
import shared.Message
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}
import org.scalajs.dom.console

@JSExport
@injectable("contactController")
class ContactController(scope: Scope, modalInstance: ModalInstance[Any], contactService: ContactService)
  extends AbstractController[Scope](scope) {

  @JSExport
  def close() = modalInstance.close()

  @JSExport
  def post(email: String, message: String) = {
    val newMessage = Message(email = email, message = message)
    contactService.post(newMessage)
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
