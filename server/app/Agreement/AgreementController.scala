package Agreement

import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc.{Action, _}
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global

class AgreementController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                                    val agreementMethods: AgreementMethods)
    extends Controller {

  def findAll() = Action.async {
    agreementMethods.findAll.map { agreements =>
      Ok(write(agreements))
    }
  }
}