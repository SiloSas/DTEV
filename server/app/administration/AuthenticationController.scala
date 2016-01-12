package administration

import javax.inject.{Inject, Named, Singleton}

import administration.UserActor.{AuthenticationRequest, AuthenticationResponse}
import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc.{Controller, _}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.{implicitConversions, postfixOps}


@Singleton
class AuthenticationController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
                                         @Named("user-actor") userActor: ActorRef)
  extends Controller {

  implicit val timeout = Timeout(5 seconds)

  def authenticate(login: String, password: String) = Action.async {
    (userActor ? AuthenticationRequest(login, password)).mapTo[Future[AuthenticationResponse]] flatMap { resp =>
      resp map {
        case AuthenticationResponse(true) =>
          Redirect("/admin").withSession("connected" -> "true")
        case AuthenticationResponse(false) =>
          Unauthorized("Identifiants incorrects")
      }
    }
  }

  def isConnected = Authenticated { request => Ok("true") }

  def logout = Action { Ok("Vous êtes bien déconnecté").withNewSession }
}
