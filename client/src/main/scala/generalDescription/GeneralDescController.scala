package generalDescription

import com.greencatsoft.angularjs.core.{HttpService, SceService, Timeout}
import com.greencatsoft.angularjs.{AbstractController, injectable}

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportAll}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal

@JSExportAll
@injectable("generalDescController")
class GeneralDescController(scope: GeneralDescScope, http: HttpService, timeout: Timeout, sceService: SceService)
  extends AbstractController[GeneralDescScope](scope) {

  http.get[js.Any]("/generalDesc") map { desc =>
    timeout(() => scope.desc = sceService.trustAsHtml(desc.asInstanceOf[String]))
  }

  def find(): Future[String] = {
    http.get[js.Any]("/generalDesc") map { desc =>

      scope.desc = desc.asInstanceOf[String]
      desc.asInstanceOf[String]
    }
  }

  var stateMessage = ""

  def update(text: String): Unit = {
    stateMessage = "Veuillez patienter"
    http.put[js.Any](s"/generalDesc?text=$text").map { _ =>
      timeout(() => {
        stateMessage = "La modification a bien été prise en compte"
        scope.desc = text
      }) }.recover {
      case NonFatal(e) =>
        stateMessage = "Désolé, une erreur s'est produite, veuillez réessayer."
    }
  }
}
