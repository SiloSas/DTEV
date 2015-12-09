package database

import shared.{Room, Agreement}
import MyPostgresDriver.api._

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

  class Agreements(tag: Tag) extends Table[Agreement](tag, "rooms") {
    def id = column[String]("id")
    def title = column[String]("name")
    def description = column[String]("presentation")
    def image = column[String]("images")

    def * = (id, title, description, image) <> ((Agreement.apply _).tupled, Agreement.unapply)
  }
  lazy val agreements = TableQuery[Agreements]
}