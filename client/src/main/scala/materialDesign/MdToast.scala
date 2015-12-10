package materialDesign

import com.greencatsoft.angularjs.core.Promise
import com.greencatsoft.angularjs.extensions.material.BottomSheetHideOrCancel
import com.greencatsoft.angularjs.injectable

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll

@injectable("$mdToast")
trait MdToastService extends js.Object {

  def show(mdToast: js.Object): Promise[BottomSheetHideOrCancel] = js.native

  def simple(content: String): js.Object = js.native

}


trait MdToastOption extends js.Object {

  var textContent: String = js.native

  var position: String = js.native

  var hideDelay: Int = js.native
}
