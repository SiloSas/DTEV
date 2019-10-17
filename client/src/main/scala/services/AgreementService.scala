package services

import com.greencatsoft.angularjs.core.HttpService
import com.greencatsoft.angularjs.{Factory, Service, injectable}
import shared._
import upickle.default._

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success, Try}


@injectable("agreementService")
class AgreementService(http: HttpService) extends Service {
  require(http != null, "Missing argument 'http'.")

  @JSExport
  var agreements = Seq.empty[Agreement]
  def findAll(): Future[Seq[Agreement]] = {
    if(agreements != Seq.empty)
      Future(agreements)
    else {
      http.get[js.Any]("/agreements")
        .map { JSON.stringify(_) }
        .map { foundAgreements =>
          agreements = read[Seq[Agreement]](foundAgreements)
          agreements
        }
    }
  }

  @JSExport
  def update(agreement: Agreement) = {
    http.post[js.Any]("/agreement", write(agreement)) map { resp =>
    } recover {
      case e: Exception => print(e)
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


@injectable("agreementService")
class AgreementServiceFactory(http: HttpService) extends Factory[AgreementService] {

  override def apply(): AgreementService = new AgreementService(http)
}