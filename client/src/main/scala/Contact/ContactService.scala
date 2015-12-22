package Contact

import com.greencatsoft.angularjs.core.HttpService
import com.greencatsoft.angularjs.{Factory, Service, injectable}
import shared.Message

import scala.scalajs.js
import scala.scalajs.js.JSON

import scala.scalajs.js.annotation.JSExport

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.util.{Failure, Success, Try}


@injectable("contactService")
class ContactService(http: HttpService) extends Service {
  require(http != null, "Missing argument 'http'.")
  @JSExport
  def post(message: Message): Future[String] = {
    http.post[js.Any]("/Contact")
      .map(JSON.stringify(_))
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
class ContactServiceFactory(http: HttpService) extends Factory[ContactService] {

  override def apply(): ContactService = new ContactService(http)
}
