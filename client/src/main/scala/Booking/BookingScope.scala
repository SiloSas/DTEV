package Booking

import com.greencatsoft.angularjs.core.Scope
import shared.Room

import scala.scalajs.js

trait BookingScope extends Scope {
  var availableRooms: js.Array[Room] = js.native
  var rooms: js.Array[Room] = js.native
  var room: Room = js.native
  var start: js.Date = js.native
  var end: js.Date = js.native
}