package images

import java.io.{ByteArrayOutputStream, File}
import javax.imageio.ImageIO

import administration.Authenticated
import com.sksamuel.scrimage.Image
import com.sksamuel.scrimage.ScaleMethod.Bicubic
import play.Play
import play.api.mvc._

object Images extends Controller {

  def getImage(fileName: String) = Action {
    val imageFile = new File(Play.application().path().getPath + "/public/images/" + fileName)
    if (imageFile.length > 0) {
      val image = ImageIO.read(imageFile)
      val dotIndex: Int = fileName.indexOf(".") + 1
      val extension = fileName.drop(dotIndex)
      val baos = new ByteArrayOutputStream()

      ImageIO.write(image, extension, baos)

      Ok(baos.toByteArray).as("image/" + extension)
    } else {
      NotFound(fileName)
    }
  }

  def uploadImage = Authenticated(parse.multipartFormData) { request =>
    request.body.file("picture").map { image =>
      image.contentType match {
        case Some(_)  =>
          val filename = image.filename

          val path = Play.application().path().getPath + "/public/images/" + filename

          val inFile = Image.fromFile(image.ref.file)

          val out = inFile.width match {
            case toBig if toBig > 600 => inFile.scale(600.0/toBig, Bicubic)
            case _ => inFile
          }

          out.output(path.stripSuffix(".jpg") + ".png")

          Ok(path.stripPrefix(Play.application().path().getPath + "/public/").stripSuffix(".jpg") + ".png")

        case _ =>
          Unauthorized("Wrong content type")
      }
    }.getOrElse { BadRequest }
  }
}
