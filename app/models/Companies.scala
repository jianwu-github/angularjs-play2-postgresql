package models

import scala.slick.driver.PostgresDriver.simple._

case class Company(id: Int, name: String)

class Companies(tag: Tag) extends Table[Company](tag, "companies") {
  def id = column[Int]("id")
  def name = column[String]("name")
  def * = (id, name) <> (Company.tupled, Company.unapply)
}

