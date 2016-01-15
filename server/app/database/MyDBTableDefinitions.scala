package database

import java.sql.Date

import Room.ReservationDB
import administration.UserActor.User
import database.MyPostgresDriver.api._
import shared.{Agreement, Comment, Room}


trait MyDBTableDefinitions {

  class Rooms(tag: Tag) extends Table[Room](tag, "rooms") {
    def id = column[String]("id")
    def name = column[String]("name")
    def presentation = column[String]("presentation")
    def header = column[String]("header")
    def images = column[String]("images")
    def isAnApartment = column[Boolean]("isanapartment")
    def price = column[String]("price")

    def * = (id, name, presentation, header, images, isAnApartment, price) <> ((Room.apply _).tupled, Room.unapply)
  }
  lazy val rooms = TableQuery[Rooms]

  class Agreements(tag: Tag) extends Table[Agreement](tag, "agreements") {
    def id = column[String]("id")
    def title = column[String]("title")
    def description = column[String]("description")
    def image = column[String]("image")

    def * = (id, title, description, image) <> ((Agreement.apply _).tupled, Agreement.unapply)
  }
  lazy val agreements = TableQuery[Agreements]

  class Comments(tag: Tag) extends Table[Comment](tag, "comments") {
    def id = column[String]("id")
    def comment = column[String]("comment")
    def userName = column[String]("username")
    def rate = column[Int]("rate")
    def date = column[String]("date")
    def isValidated = column[Boolean]("isvalidated")

    def * = (id, comment, userName, rate, date, isValidated) <> ((Comment.apply _).tupled, Comment.unapply)
  }
  lazy val comments = TableQuery[Comments]

  class Users(tag: Tag) extends Table[User](tag, "users") {
    def id = column[Int]("userid", O.PrimaryKey)
    def login = column[String]("login")
    def password = column[String]("password")

    def * = login <> (User, User.unapply)
  }
  lazy val users = TableQuery[Users]

  class Reservations(tag: Tag) extends Table[ReservationDB](tag, "reservations") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def roomId = column[String]("roomid")
    def roomName = column[String]("roomname")
    def arrivalDate = column[Date]("arrivaldate")
    def departureDate = column[Date]("departuredate")
    def numberOfPersons = column[Int]("numberofpersons")
    def firstName = column[String]("firstname")
    def name = column[String]("name")
    def email = column[String]("email")
    def phoneNumber = column[String]("phonenumber")
    def extraBed = column[Boolean]("extrabed")

    def * = (id.?, roomId, roomName, arrivalDate, departureDate, numberOfPersons, firstName, name, email, phoneNumber,
      extraBed) <> ((ReservationDB.apply _).tupled, ReservationDB.unapply)
  }
  lazy val reservations = TableQuery[Reservations]
}