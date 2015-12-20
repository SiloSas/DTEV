package services

import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc.{Action, _}
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global

class ServicesController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                                   val agreementMethods: ServicesMethods)
    extends Controller {

  def findAll() = Action.async {
    agreementMethods.findAll.map { agreements =>
      Ok(write(agreements))
    }
  }
}