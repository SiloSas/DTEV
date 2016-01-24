package services

import javax.inject.Inject

import administration.Authenticated
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.{Json, JsObject}
import play.api.mvc.{Action, _}
import shared.{Agreement, Room}
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global
import shared.Agreement


class ServicesController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                                   val agreementMethods: ServicesMethods)
    extends Controller {

  def findAll() = Action.async {
    agreementMethods.findAll.map { agreements =>
      Ok(write(agreements))
    }
  }

  def update = Authenticated.async(parse.json) { request =>
    val agreement = read[Agreement](request.body.as[JsObject].toString)

    agreementMethods.update(agreement) map { numberOfRowsUpdated => Ok(Json.toJson(numberOfRowsUpdated)) }
  }
}