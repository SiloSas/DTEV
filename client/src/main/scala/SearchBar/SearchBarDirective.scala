package SearchBar

import com.greencatsoft.angularjs._
import com.greencatsoft.angularjs.core.Location
import materialDesign.MdToastService

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("searchBar")
class SearchBarDirective(mdToastService: MdToastService, location: Location, filterService: FilterService)
    extends ElementDirective with TemplatedDirective {

  override val templateUrl = "assets/templates/SearchBar/searchBar.html"

  @JSExport
  def search(start: js.Date, end: js.Date): Any = {
    println(start)
    println(end)
    if (!js.isUndefined(start)) {
      val startString = start.getFullYear() + "-" + start.getMonth() + "-" + start.getDay()
      val endString = end.getFullYear() + "-" + end.getMonth() + "-" + end.getDay()
      location.path("search/" + startString + "/" + endString)
    } else {
      val missingDateToast = mdToastService.simple("Veuillez renseigner une date de départ et d'arrivée")
      missingDateToast._options.position = "{right: true}"
      mdToastService.show(missingDateToast)
    }
  }
}