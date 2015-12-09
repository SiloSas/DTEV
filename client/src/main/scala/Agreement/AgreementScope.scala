package Agreement

import com.greencatsoft.angularjs.core.Scope
import shared.Agreement

import scala.scalajs.js

trait AgreementScope extends Scope {

  var agreements: js.Array[Agreement] = js.native

}
