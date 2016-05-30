package tariffs

import com.greencatsoft.angularjs.core.HttpService
import com.greencatsoft.angularjs.{AbstractController, injectable}
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.{JSExport, JSExportAll}

@JSExportAll
@injectable("tariffsController")
class TariffsController(http: HttpService, scope: TariffsScope) extends AbstractController[TariffsScope](scope) {

  case class Tariffs(id: Option[Long], text: String)

  @JSExport
  def find = {
    http.get[js.Any]("/tariffs").map(JSON.stringify(_)) map { t =>
      scope.tariffs = read[Seq[Tariffs]](t).head.text
    } recover {
      case e: Exception => print(e)
    }
  }
}
