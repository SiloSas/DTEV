package generalDescription

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext

class GeneralDescController @Inject()(generalDescription: GeneralDescription,
                                      implicit val executionContext: ExecutionContext) extends Controller {
  def find() = Action.async { generalDescription.find map { desc => Ok(Json.toJson(desc)) } }
  def update(text: String) = Action.async { generalDescription.update(text) map { r => Ok(Json.toJson(r)) } }
}
