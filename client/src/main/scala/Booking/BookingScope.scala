package Booking

import com.greencatsoft.angularjs.core.Scope
import shared.Room

import scala.scalajs.js


@js.native
trait BookingScope extends Scope {
  var availableRooms: js.Array[Room] = js.native
  var rooms: js.Array[Room] = js.native
  var room: Room = js.native
  var start: Long = js.native
  var end: Long = js.native
  var totalPrice: Int = js.native
  var numberOfNights: Int = js.native
}