import database.MyDBTableDefinitions
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}


class TheTest extends PlaySpec with OneAppPerSuite with MyDBTableDefinitions {

  "The test" must {

    "a" in {

//      println(db.run(reservations))
      1 mustBe 2
    }
  }
}
