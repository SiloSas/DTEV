package tariffs

import javax.inject.Inject

import database.MyPostgresDriver.api._
import database.{MyDBTableDefinitions, MyPostgresDriver}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.{Action, _}
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global

case class Tariffs(id: Option[Long], text: String)

class TariffsController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
    extends Controller
    with HasDatabaseConfigProvider[MyPostgresDriver]
    with MyDBTableDefinitions {

  def find() = Action.async {
    db.run(tariffsTable.result.head).map { tariffs =>
      Ok(tariffs.text)
    }
  }

  def update(text: String) = Action.async {
    db.run(tariffsTable.map(_.text).update(text)) map { _ =>
      Ok
    }
  }
}
