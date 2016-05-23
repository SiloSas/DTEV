package generalDescription

import com.greencatsoft.angularjs.core.{HttpService, SceService, Timeout}
import com.greencatsoft.angularjs.{AbstractController, injectable}

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.concurrent.ExecutionContext.Implicits.global

@JSExport
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

  def update(text: String): Future[Int] = {
    http.put[js.Any]("/generalDesc") map { r =>
      scope.desc = text
      r.asInstanceOf[Int]
    }
  }
}
