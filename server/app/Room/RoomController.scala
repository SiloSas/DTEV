package Room

import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc.{Action, _}
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global

class RoomController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, val roomMethods: RoomMethods)
    extends Controller {
  def findAll() = Action.async {
    roomMethods.findAll.map { rooms =>
      Ok(write(rooms))
    }
  }
  def findById(id: String) = Action.async {
    roomMethods.findById(id).map { roomFound =>
        Ok(write(roomFound))
    }
  }

  def findAvailable(start: String, end: String) = Action.async {
    roomMethods.findAvailable(start, end).map { roomsFound =>
        Ok(write(roomsFound))
    }
  }
}