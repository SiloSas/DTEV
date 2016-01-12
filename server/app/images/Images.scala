package images

import java.io.{ByteArrayOutputStream, File}
import javax.imageio.ImageIO

import administration.Authenticated
import play.Play
import play.api.mvc._


object Images extends Controller {

  def getImage(fileName: String) = Action {
    val imageFile = new File(Play.application().path().getPath + "/public/images/" + fileName)
    val image = ImageIO.read(imageFile)
    if (imageFile.length > 0) {

      val resourceType = fileName.substring(fileName.length()-3)
      val baos = new ByteArrayOutputStream()
      ImageIO.write(image, resourceType, baos)

      Ok(baos.toByteArray).as("image/" + resourceType)
      //resource type such as image+png, image+jpg
    } else {
      NotFound(fileName)
    }
  }

  def uploadImage = Authenticated(parse.multipartFormData) { request =>
    request.body.file("picture").map { image =>
      image.contentType match {
        case Some(fileExtension)  =>

          println(image)
          val filename = image.filename
          image.ref.moveTo(new File(Play.application().path().getPath + "/public/images/" + filename), replace = true)

          Ok("images/" +filename)

        case _ =>
          Unauthorized("Wrong content type")
      }
    }.getOrElse { BadRequest }
  }
}
