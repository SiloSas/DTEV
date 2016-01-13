package BackEnd

import com.greencatsoft.angularjs.core.{HttpService, RouteParams}
import com.greencatsoft.angularjs.{AbstractController, injectable}
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExport

case class ClientReservationDB(id: Long,
                         roomId: String,
                         arrivalDate: String,
                         departureDate: String,
                         numberOfPersons: Int,
                         firstName: String,
                         name: String,
                         email: String,
                         phoneNumber: String,
                         extraBed: Boolean)

@JSExport
@injectable("backEndController")
class BackEndController(backEndScope: BackEndScope, $routeParams: RouteParams, http: HttpService)
  extends AbstractController[BackEndScope](backEndScope) {


  val eventuallyIsConnected = http.get[String]("/isConnected")
    .map(isConnected => isConnected.toBoolean)
    .recover { case e: Exception => false }

  eventuallyIsConnected map { isConnected =>
    backEndScope.isConnected = isConnected
    backEndScope.$digest()
  }


  http.get[js.Any]("/reservations")
    .map { r =>
      println("r = " + JSON.stringify(r))
      val resas = read[Seq[ClientReservationDB]](JSON.stringify(r))

      println("resas = " + resas)

      scope.$apply(backEndScope.reservations = resas)
    }

}
