package controllers

import play.api.mvc._


object Application extends Controller {

  def index = Action {
    Ok(views.html.index("SharedMessages.itWorks"))
  }

  def admin = Action { request =>
    request.session.get("connected") match {
      case Some(uuid) =>
        Ok(views.html.admin(""))
      case None =>
        Ok(views.html.connection(""))
    }
  }
}
