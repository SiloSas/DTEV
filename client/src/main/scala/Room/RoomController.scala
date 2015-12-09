package Room


import com.greencatsoft.angularjs.{AbstractController, injectable}
import org.scalajs.dom.console
import shared.Room
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.annotation.JSExportAll
import scala.util.{Failure, Success}


@JSExportAll
@injectable("roomController")
class RoomController(scope: RoomScope, service: RoomService) extends AbstractController[RoomScope](scope) {


  scope.rooms = js.Array[Room]()
  service.findAll() onComplete {
    case Success(rooms) =>
      scope.$apply {
        scope.rooms = rooms.toJSArray
      }
    case Failure(t) => handleError(t)
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }

}
