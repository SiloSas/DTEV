package Map

import com.greencatsoft.angularjs.{ElementDirective, TemplatedDirective, injectable}
import scala.scalajs.js.annotation.JSExport

@JSExport
@injectable("homeMap")
class MapDirective() extends ElementDirective with TemplatedDirective {
  override val templateUrl = "assets/templates/Map/homeMap.html"
}

