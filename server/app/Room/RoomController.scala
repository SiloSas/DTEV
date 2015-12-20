package Room

import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.{Action, _}
import shared.Room
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


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

  def update = Action.async(parse.json) { request =>
    val room = read[Room](request.body.as[JsObject].toString)

    roomMethods.update(room) map { numberOfRowsUpdated =>
        Ok(Json.toJson(numberOfRowsUpdated))
    }
  }
}