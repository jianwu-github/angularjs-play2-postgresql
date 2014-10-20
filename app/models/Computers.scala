package models

import scala.slick.driver.PostgresDriver.simple._

case class Computer(id: Int, name: String, manufacturerId: Int)

class Computers(tag: Tag) extends Table[Computer](tag, "computers")  {
  def id = column[Int]("id")
  def name = column[String]("name")
  def manufacturerId = column[Int]("manufacture_id")
  def * = (id, name, manufacturerId) <> (Computer.tupled, Computer.unapply)
}