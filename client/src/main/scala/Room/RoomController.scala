package Room

import com.greencatsoft.angularjs.core._
import com.greencatsoft.angularjs.{AbstractController, injectable}
import org.scalajs.dom.console
import org.scalajs.dom.raw.KeyboardEvent
import shared.Room
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.annotation.JSExportAll
import scala.util.{Failure, Success}

@JSExportAll
@injectable("roomController")
class RoomController(scope: RoomScope,
                     service: RoomService,
                     $routeParams: RouteParams,
                     location: Location,
                     timeout: Timeout,
                     window: Window,
                     http: HttpService)
    extends AbstractController[RoomScope](scope) {

  if(location.path().indexOf("rooms") > -1) findById($routeParams.get("id").toString)

  findAll()

  def findAll(): Any = {
    scope.rooms = js.Array[Room]()
    service.findAll() onComplete {
      case Success(rooms) => scope.$apply { scope.rooms = rooms.toJSArray }
      case Failure(t) => handleError(t)
    }
  }

  var images = Array[String]()

  def findById(id: String): Any = {
    service.findById(id) onComplete {
      case Success(maybeRoom) =>
        maybeRoom match {
          case Some(room) =>
            scope.$apply {
              scope.room = room
              images = scope.room.images.split(",")//.asInstanceOf[js.Array[String]]
            }

          case None =>
        }

      case Failure(t: Throwable) =>
        handleError(t)
    }
  }

  def update(room: Room) = {
    http.post[js.Any]("/room", write(room)) map { resp =>
    } recover {
      case e: Exception => print(e)
    }
  }

  scope.index = 0
  var index = 0

  var inProgress = true
  var slider = false

  def prevIndex(): Unit = {
    timeout(() => {
      if(index > 0) index = index - 1
      else index = images.length - 1
    })
  }

  def nextIndex(): Unit = {
    timeout(() => {
      if(index < images.length - 1) index = index + 1
      else index = 0
      println(index)
    })
  }

//  window.onkeydown =  (event: KeyboardEvent) => {
//    if (event.keyCode == 37) prevIndex()
//    if (event.keyCode == 39) nextIndex()
//  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}
