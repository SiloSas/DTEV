package BackEnd

import com.greencatsoft.angularjs.core._
import com.greencatsoft.angularjs.{AbstractController, injectable}
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExport

@JSExport
case class ClientReservationDB(roomId: String,
                              roomName: String,
                               arrivalDate: String,
                               departureDate: String,
                               numberOfPersons: Int,
                               firstName: String,
                               name: String,
                               email: String,
                               phoneNumber: String,
                               extraBed: Boolean,
                               extraBreakfast: Boolean)

@JSExport
@injectable("backEndController")
class BackEndController(backEndScope: BackEndScope, $routeParams: RouteParams, http: HttpService, timeout: Timeout,
                        location: Location)
  extends AbstractController[BackEndScope](backEndScope) {


  val eventuallyIsConnected = http.get[String]("/isConnected")
    .map(isConnected => isConnected.toBoolean)
    .recover { case e: Exception => false }

  eventuallyIsConnected map { isConnected =>
    backEndScope.isConnected = isConnected
    backEndScope.$digest()
  }


  http.get[js.Any]("/reservations") map { r =>
    timeout(() => backEndScope.$apply(backEndScope.reservations =
      read[Seq[ClientReservationDB]](JSON.stringify(r)).toJSArray))
  }
}
