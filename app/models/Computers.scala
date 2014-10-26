package models

import scala.slick.driver.PostgresDriver.simple._

case class Computer(id: Int, name: String, manufacturerId: Int)

class Computers(tag: Tag) extends Table[Computer](tag, "computer")  {
  def id = column[Int]("id")
  def name = column[String]("name")
  def manufacturerId = column[Int]("manufacture_id")
  def * = (id, name, manufacturerId) <> (Computer.tupled, Computer.unapply)
  
  // Foreign Key Relationship
  def manufacture = foreignKey("manufacture_fk", manufacturerId, Companies.companies)(_.id)
}

object Computers {
  val computers = scala.slick.driver.PostgresDriver.simple.TableQuery[Computers]
}
