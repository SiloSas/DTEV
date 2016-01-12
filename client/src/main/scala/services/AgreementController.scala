package services

import com.greencatsoft.angularjs.{AbstractController, injectable}
import org.scalajs.dom.console
import shared.Agreement
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.annotation.JSExportAll
import scala.util.{Failure, Success}


@JSExportAll
@injectable("agreementController")
class AgreementController(scope: AgreementScope, agreementService: AgreementService) extends AbstractController[AgreementScope](scope) {

  scope.agreements = js.Array[Agreement]()
  agreementService.findAll() onComplete {
    case Success(agreements) =>
      scope.$apply {
        scope.agreements = agreements.toJSArray
      }
    case Failure(t) => handleError(t)
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
