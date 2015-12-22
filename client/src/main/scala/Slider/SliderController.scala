package Slider

import Room.RoomService
import com.greencatsoft.angularjs.core.{Location, RouteParams, Timeout}
import com.greencatsoft.angularjs.{AbstractController, injectable}
import org.scalajs.dom.console

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.annotation.JSExportAll
import scala.util.{Failure, Success}

@JSExportAll
case class ActiveImage(step: Int, url: String, url1: String)
@injectable("sliderController")
class SliderController(sliderScope: SliderScope, roomService: RoomService, timeout: Timeout, location: Location, $routeParams: RouteParams)
  extends AbstractController[SliderScope](sliderScope) {

  if(location.path().indexOf("rooms") > -1) findById($routeParams.get("id").toString)
  else findAll()


  def findAll(): Any = {
    roomService.findAll() onComplete {
      case Success(rooms) =>
        scope.$apply {
          sliderScope.images = rooms.map(_.images).toJSArray
          scope.activeImage = ActiveImage(step = 0, url = scope.images.head, url1 = scope.images.head)
          if(sliderScope.images.length > 1) {
            timeout(fn = () => {
              changeActiveImage(scope.images.tail)
            },
              delay = 6000,
              invokeApply = true
            )
          } else {
          }
        }
      case Failure(t) => handleError(t)
    }
  }

  def findById(id: String): Any = {
    roomService.findById(id) onComplete {
      case Success(maybeRoom) =>
        maybeRoom match {
          case Some(room) =>
            scope.$apply {
              sliderScope.images = Seq(room.images).toJSArray
              scope.activeImage = ActiveImage(step = 0, url = scope.images.head, url1 = scope.images.head)
              if (sliderScope.images.length > 1) {
                timeout(fn = () => {
                  changeActiveImage(scope.images.tail)
                },
                  delay = 6000,
                  invokeApply = true
                )
              } else {
              }
            }
          case None =>
        }
      case Failure(t: Throwable) =>
        handleError(t)
    }
  }


  def changeActiveImage(images: Seq[String]): Any = {
    images.headOption match {
      case Some(image) =>
        val step =
          if (scope.activeImage.step == 0) 1
          else 0

        val image0 =
          if (step == 0) image
          else scope.activeImage.url

        val image1 =
          if (step == 1) image
          else scope.activeImage.url1

        scope.activeImage = ActiveImage(step = step, url = image0, url1 = image1)

        timeout(fn = () => {
          changeActiveImage(images.tail)
        },
          delay = 6000,
          invokeApply = true
        )
      case _ =>
        changeActiveImage(scope.images)
    }
  }

  private def handleError(t: Throwable) {
    console.error(s"An error has occured: $t")
  }
}