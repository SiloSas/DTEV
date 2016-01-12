package SearchBar

import com.greencatsoft.angularjs._
import com.greencatsoft.angularjs.core.Location
import materialDesign.MdToastService

import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.annotation.JSExport


@JSExport
@injectable("searchBar")
class SearchBarDirective(mdToastService: MdToastService, location: Location, filterService: FilterService)
    extends ElementDirective with TemplatedDirective with IsolatedScope {

  override val templateUrl = "assets/templates/SearchBar/searchBar.html"

  @JSExport
  def setEndDate(startDate: Date): Date = new Date(startDate.getTime() + (1000*60*60*24))

  @JSExport
  def search(start: js.Date, end: js.Date): Any = {
    if (!js.isUndefined(start)) {
      val startString = start.getTime()
      val endString = end.getTime()

      location.path("search/" + startString + "/" + endString)
    } else {
      val missingDateToast =
        mdToastService.simple("Veuillez renseigner une date de départ et d'arrivée au format jj/mm/AAAA")
      missingDateToast._options.position = "{right: true}"
      mdToastService.show(missingDateToast)
    }
  }
}