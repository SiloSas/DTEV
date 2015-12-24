package Booking

import com.greencatsoft.angularjs.core.Scope
import shared.Room

import scala.scalajs.js

trait BookingScope extends Scope {
  var availableRooms: js.Array[Room] = js.native
  var rooms: js.Array[Room] = js.native
  var room: Room = js.native
  var start: String = js.native
  var end: String = js.native
  var totalPrice: Int = js.native
  var numberOfNights: Int = js.native
}