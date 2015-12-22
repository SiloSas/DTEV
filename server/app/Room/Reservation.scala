package Room

import java.sql.Date
import java.text.{SimpleDateFormat, DateFormat}
import javax.inject.Inject
import database.MyPostgresDriver.api._

import database.{MyDBTableDefinitions, MyPostgresDriver}
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}

import scala.concurrent.Future


case class Reservation(id: Long, roomId: String, arrivalDate: Date, departureDate: Date)


class ReservationMethods @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
    extends HasDatabaseConfigProvider[MyPostgresDriver]
    with MyDBTableDefinitions {

  def save(reservation: Reservation): Future[Int] = db.run(reservations += reservation)

  def delete(reservationId: Long): Future[Int] = db.run(reservations.filter(_.id === reservationId).delete)

  def findReservationsInInterval(start: String, end: String): Future[Seq[Reservation]] = {
    val inputDateFormat: DateFormat = new SimpleDateFormat("yyyy-MM-dd")
//    val outputDateFormat: DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm")
//    System.out.println(outputDateFormat.format(inputDateFormat.parse("09-SEP-2013 10:00")))
    val arrivalDate = inputDateFormat.parse(start)
    val arrivalSqlDate = new Date(arrivalDate.getTime)
    val departureDate = inputDateFormat.parse(start)
    val departureSqlDate = new Date(departureDate.getTime)

    db.run(reservations
      .filter(reservation => reservation.arrivalDate >= arrivalSqlDate && reservation.departureDate <= departureSqlDate)
      .result)
  }
}