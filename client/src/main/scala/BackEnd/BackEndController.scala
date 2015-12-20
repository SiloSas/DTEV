package BackEnd

import com.greencatsoft.angularjs.core.{HttpService, RouteParams}
import com.greencatsoft.angularjs.{AbstractController, injectable}
import org.scalajs.dom.console

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("backEndController")
class BackEndController(backEndScope: BackEndScope, $routeParams: RouteParams, http: HttpService)
    extends AbstractController[BackEndScope](backEndScope) {


  val eventuallyIsConnected = http.get[String]("/isConnected")
    .map(isConnected => isConnected.toBoolean)
    .recover { case e: Exception => false}

  eventuallyIsConnected map { isConnected =>
    backEndScope.isConnected = isConnected
    backEndScope.$digest()
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
