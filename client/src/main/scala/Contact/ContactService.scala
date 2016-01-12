package Contact

import com.greencatsoft.angularjs.core.HttpService
import com.greencatsoft.angularjs.{Factory, Service, injectable}
import materialDesign.MdToastService
import shared.Message
import upickle.default._

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success, Try}


@injectable("contactService")
class ContactService(http: HttpService,
                     mdToast: MdToastService) extends Service {
  require(http != null, "Missing argument 'http'.")

  def displaySuccessToast(): Any = {
    var toast = mdToast.simple("Votre message nous a bien été envoyé.")
    toast._options.position = """{
        bottom: false
        top: true
        left: false
        right: true
      }"""
    mdToast.show(toast)
  }

  def displayErrorToast(): Any = {
    var toast = mdToast.simple("Désolé, une erreur s'est produite.")
    toast._options.position = """{
        bottom: false
        top: true
        left: false
        right: true
      }"""
    mdToast.show(toast)
  }

  @JSExport
  def post(message: Message): Unit = {
    println(message)

    http.post[js.Any]("/contact", write(message)) map { _ =>
      displaySuccessToast()
    } recover {
      case _ => displayErrorToast()
    }
  }

  protected def flatten[T](future: Future[Try[T]]): Future[T] = future flatMap {
    case Success(s) => Future.successful(s)
    case Failure(f) => Future.failed(f)
  }
  protected def parameterizeUrl(url: String, parameters: Map[String, Any]): String = {
    require(url != null, "Missing argument 'url'.")
    require(parameters != null, "Missing argument 'parameters'.")

    parameters.foldLeft(url)((base, kv) =>
      base ++ { if (base.contains("?")) "&" else "?" } ++ kv._1 ++ "=" + kv._2)
  }
}


@injectable("contactService")
class ContactServiceFactory(http: HttpService, mdToastService: MdToastService) extends Factory[ContactService] {

  override def apply(): ContactService = new ContactService(http, mdToastService)
}
