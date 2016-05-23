import database.MyDBTableDefinitions
import generalDescription.GeneralDescription
import org.scalatest.concurrent.ScalaFutures._
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.db.DBApi
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext

class TheTest extends PlaySpec with OneAppPerSuite with MyDBTableDefinitions {
  lazy val appBuilder = new GuiceApplicationBuilder()
  lazy val injector = appBuilder.injector()
  lazy val dbConfProvider = injector.instanceOf[DatabaseConfigProvider]
  lazy val databaseApi = injector.instanceOf[DBApi]
  lazy val wsClient = injector.instanceOf[WSClient]
  lazy val ec = injector.instanceOf[ExecutionContext]

  lazy val generalDescription = new GeneralDescription(dbConfProvider, ec)

  "General description" must {

    "be found and updated" in {
      whenReady(generalDescription.update(
        """La maison d'hôtes Des Toits en Ville vous accueille dans la ville animée de Villeurbanne, à seulement 15 minutes du centre de Lyon. Agrémentée d'un jardin, elle sert chaque jour un petit-déjeuner continental.
          Les hébergements Des Toits en Ville sont décorés avec élégance. Chacun dispose d'une salle de bains privative, d'une connexion Wi-Fi gratuite et d'une télévision. Certains comportent également un lave-linge ou une terrasse meublée. La cuisine des appartements est équipée de plaques de cuisson, d'un réfrigérateur et d'un four micro-ondes. Certaines sont pourvues d'un coin repas.
          La maison d'hôtes Des Toits en Ville est située à 4,4 km de la gare de Lyon Part Dieu et à 280 mètres de la station de métro Cusset. Vous trouverez un parking privé à proximité, accessible moyennant des frais supplémentaires.
        """)) { response =>
          response mustBe 1

          whenReady(generalDescription.find()) { desc =>

            desc.startsWith("La maison") mustBe true

            whenReady(generalDescription.update("newDesc")) { response =>

              response mustBe 1
              whenReady(generalDescription.find()) { newDesc =>

                newDesc mustBe "newDesc"
                whenReady(generalDescription.update(
                  """La maison d'hôtes Des Toits en Ville vous accueille dans la ville animée de Villeurbanne, à seulement 15 minutes du centre de Lyon. Agrémentée d'un jardin, elle sert chaque jour un petit-déjeuner continental.
                          Les hébergements Des Toits en Ville sont décorés avec élégance. Chacun dispose d'une salle de bains privative, d'une connexion Wi-Fi gratuite et d'une télévision. Certains comportent également un lave-linge ou une terrasse meublée. La cuisine des appartements est équipée de plaques de cuisson, d'un réfrigérateur et d'un four micro-ondes. Certaines sont pourvues d'un coin repas.
                          La maison d'hôtes Des Toits en Ville est située à 4,4 km de la gare de Lyon Part Dieu et à 280 mètres de la station de métro Cusset. Vous trouverez un parking privé à proximité, accessible moyennant des frais supplémentaires.
                  """)) { response =>
                  response mustBe 1
                }
            }
          }
        }
      }
    }
  }
}
