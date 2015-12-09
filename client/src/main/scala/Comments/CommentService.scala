package Comments

import com.greencatsoft.angularjs.core.HttpService
import com.greencatsoft.angularjs.{Factory, Service, injectable}
import shared._
import upickle.default._
import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success, Try}

@injectable("commentService")
class CommentService(http: HttpService) extends Service {
  require(http != null, "Missing argument 'http'.")


  @JSExport
  var comments = Seq.empty[Comment]
  def findAll(): Future[Seq[Comment]] = /*flatten*/ {
    // Append a timestamp to prevent some old browsers from caching the result.
    if(comments != Seq.empty) Future(comments)
    else {
      http.get[js.Any]("/comments")
        .map {
          JSON.stringify(_)
        }
        .map { foundComments =>
          comments = read[Seq[Comment]](foundComments)
          comments
        }
    }
  }

  @JSExport
  def post(comment: Comment): Future[Int] = {
    http.post[js.Any]("/comments?comment=" + write(comment)) map { resp =>
      read[Int](JSON.stringify(resp))
    }
  }

  protected def flatten[T](future: Future[Try[T]]): Future[T] = future flatMap {
    case Success(s) => Future.successful(s)
    case Failure(f) => Future.failed(f)
  }
  protected def parameterizeUrl(url: String, parameters: Map[String, Any]): String = {
    require(url != null, "Missing argument 'url'.")
    require(parameters != null, "Missing argument 'parameters'.")

    parameters.foldLeft(url)((base, kv) =>
      base ++ { if (base.contains("?")) "&" else "?" } ++ kv._1 ++ "=" + kv._2)
  }
}


@injectable("commentService")
class CommentServiceFactory(http: HttpService) extends Factory[CommentService] {

  override def apply(): CommentService = new CommentService(http)
}
