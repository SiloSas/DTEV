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
        case AuthenticationResponse(true) => Ok("Vous êtes connecté").withSession("connected" -> "true")
        case AuthenticationResponse(false) => Unauthorized("Identifiants incorrects")
      }
    }
  }

  def isConnected = Authenticated { request =>
    Ok("true")
  }

  def logout = Action { Ok("Vous êtes bien déconnecté").withNewSession }

//  def uploadImage = Authenticated(parse.multipartFormData) { request =>
//    request.uuid match {
//      case None =>
//        Unauthorized("Unauthorized")
//      case _ =>
//        request.body.file("picture").map { image =>
//
//          image.contentType match {
//            case Some(fileExtension) if fileExtension == "image/tiff" || fileExtension == "image/jpg" ||
//              fileExtension == "image/jpeg" || fileExtension == "image/png" || fileExtension == "image/svg" ||
//              fileExtension == "application/pdf" =>
//
//              val filename = UUID.randomUUID().toString + image.filename
//              image.ref.moveTo(new File("public/pictures/" + filename), replace = true)
//
//              Ok(filename)
//
//            case _ =>
//              Unauthorized("Wrong content type")
//          }
//        }.getOrElse { BadRequest }
//    }
//  }
}
