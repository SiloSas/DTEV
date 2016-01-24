package Room

import com.greencatsoft.angularjs.core.{HttpService, Location, RouteParams}
import com.greencatsoft.angularjs.{AbstractController, injectable}
import org.scalajs.dom.console
import shared.Room
import upickle.default._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.annotation.{JSExport, JSExportAll}
import scala.util.{Failure, Success}


@JSExportAll
@injectable("roomController")
class RoomController(scope: RoomScope,
                     service: RoomService,
                     $routeParams: RouteParams,
                     location: Location,
                     http: HttpService)
    extends AbstractController[RoomScope](scope) {

  if(location.path().indexOf("rooms") > -1) findById($routeParams.get("id").toString)
  else findAll()

  def findAll(): Any = {
    scope.rooms = js.Array[Room]()
    service.findAll() onComplete {
      case Success(rooms) => scope.$apply { scope.rooms = rooms.toJSArray }
      case Failure(t) => handleError(t)
    }
  }

  def findById(id: String): Any = {
    service.findById(id) onComplete {
      case Success(maybeRoom) =>
        maybeRoom match {
          case Some(room) => scope.$apply { scope.room = room }
          case None =>
        }
      case Failure(t: Throwable) =>
        handleError(t)
    }
  }

  @JSExport
  def update(room: Room) = {
    http.post[js.Any]("/room", write(room)) map { resp =>
    } recover {
      case e: Exception => print(e)
    }
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
