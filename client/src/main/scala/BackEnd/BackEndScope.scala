package BackEnd

import com.greencatsoft.angularjs.core.Scope

import scala.scalajs.js

trait BackEndScope extends Scope {
  var isConnected: Boolean = js.native
}