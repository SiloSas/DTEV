package BackEnd

import com.greencatsoft.angularjs.core.Scope
import shared.Room

import scala.scalajs.js


trait BackEndScope extends Scope {
  var isConnected: Boolean = js.native

  var rooms: js.Array[Room] = js.native

  var room: Room = js.native

  var reservations: js.Array[ClientReservationDB] = js.native
}
