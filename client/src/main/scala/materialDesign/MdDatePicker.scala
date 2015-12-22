package materialDesign

import com.greencatsoft.angularjs.{Config, inject, injectable}

import scala.scalajs.js
import scala.scalajs.js.Date


@injectable("$mdDateLocaleProvider")
trait MdDateLocaleProvider extends js.Object {
  var formatDate: js.Function = js.native
}


object MdDateLocaleProviderConfig extends Config {

  @inject
  var mdDateLocalProvider: MdDateLocaleProvider = _

  override def initialize() {
    org.scalajs.dom.console.log(mdDateLocalProvider)
    mdDateLocalProvider.formatDate = (date: Date) => {
      val splittedString = date.toString.split("\\s")
      val day = splittedString(2)
      val year = splittedString(3)
      val month = splittedString(1) match {
        case "Jan" => "01"
        case "Feb" => "02"
        case "Mar" => "03"
        case "Apr" => "04"
        case "May" => "05"
        case "Jun" => "06"
        case "Jul" => "07"
        case "Aug" => "08"
        case "Sep" => "09"
        case "Oct" => "10"
        case "Nov" => "11"
        case "Dec" => "12"
      }

      day + "/" + month + "/" + year
    }
  }
}