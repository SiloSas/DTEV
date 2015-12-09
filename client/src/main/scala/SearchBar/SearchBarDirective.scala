package SearchBar

import com.greencatsoft.angularjs.{TemplatedDirective, ElementDirective, injectable}

import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("searchBar")
class SearchBarDirective extends ElementDirective with TemplatedDirective {
  override val templateUrl = "assets/templates/SearchBar/searchBar.html"
}