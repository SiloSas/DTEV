package tariffs

import com.greencatsoft.angularjs.core.{HttpService, SceService, Timeout}
import com.greencatsoft.angularjs.{AbstractController, injectable}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExportAll
import scala.util.control.NonFatal
import upickle.default._

case class Tariff(text: String)

@JSExportAll
@injectable("tariffsController")
class TariffsController(http: HttpService, scope: TariffsScope, timeout: Timeout, sceService: SceService)
    extends AbstractController[TariffsScope](scope) {

  http.get[js.Any]("/tariffs") map { tariffs =>
    org.scalajs.dom.console.log(tariffs)

    timeout(() => scope.tariffs = sceService.trustAsHtml(tariffs.asInstanceOf[String]))
  }

  var stateMessage = ""

  def update(text: String): Unit = {
    stateMessage = "Veuillez patienter"
    http.put[js.Any](s"/tariffs", write(Tariff(text))).map { _ =>
      timeout(() => {
        stateMessage = "La modification a bien été prise en compte"
        scope.tariffs = text
      })
    }.recover {
      case NonFatal(e) =>
        stateMessage = "Désolé, une erreur s'est produite, veuillez réessayer."
    }
  }
}
