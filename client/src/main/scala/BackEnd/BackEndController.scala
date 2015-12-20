package BackEnd

import com.greencatsoft.angularjs.core.{HttpService, RouteParams}
import com.greencatsoft.angularjs.{AbstractController, injectable}
import org.scalajs.dom.console

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("backEndController")
class BackEndController(scope: BackEndScope, $routeParams: RouteParams, http: HttpService)
    extends AbstractController[BackEndScope](scope) {


  val eventuallyIsConnected = http.get[String]("/isConnected")
    .map(isConnected => isConnected.toBoolean)
    .recover { case e: Exception => false}

  eventuallyIsConnected map { isConnected =>
    scope.isConnected = isConnected
    scope.$digest()
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
