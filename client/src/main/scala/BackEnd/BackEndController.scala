package BackEnd

import com.greencatsoft.angularjs.core.RouteParams
import com.greencatsoft.angularjs.{AbstractController, injectable}
import org.scalajs.dom.console

import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("backEndController")
class BackEndController(scope: BackEndScope, $routeParams: RouteParams)
    extends AbstractController[BackEndScope](scope) {

  scope.isConnected = false

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
