package models

import org.postgresql.ds.PGSimpleDataSource
import scala.slick.driver.PostgresDriver.simple._
import play.api.db.DB

trait ComputersDao {
  def list(): Iterable[Computer]
  
  def listByManufacture(manufactureId: Int): List[Computer]
  
  def limit(n: Int): List[Computer]
  
  def create(computer: Computer): Option[Computer]
  
  def get(id: Int): Option[Computer]
  
  def update(computer: Computer): Option[Computer]
  
  def delete(id: Int): Boolean
}

object ComputersDao extends ComputersDao {
  def db = {
    val ds = new PGSimpleDataSource
    ds.setDatabaseName("postgres")
    ds.setUser("postgres")
    ds.setPassword("postgres")
    Database.forDataSource(ds)
  }
  
  val computers = Computers.computers 
  
  def list() = db withSession { implicit session =>
    computers.list
  }
  
  def listByManufacture(manufactureId: Int) = db withSession { implicit session =>
    val result = for { c <- computers if c.manufacturerId === manufactureId } yield (c)
    result.run.toList
  }
  
  def limit(n: Int) = db withSession { implicit session =>
    computers.take(n).list
  }
  
  def create(computer: Computer): Option[Computer] = db withSession { implicit session =>
    val result = computers += computer
    
    if (result > 0) get(computer.id )
    else None
  }
  
  def get(id: Int): Option[Computer] = db withSession { implicit session =>
    computers.filter(c => c.id === id).firstOption
  }
  
  def update(computer: Computer): Option[Computer] = db withSession { implicit session =>
    val result = computers.filter(c => c.id === computer.id).update(computer)
    
    if (result > 0) get(computer.id)
    else None
  }  
  
  def delete(id: Int): Boolean = db withSession { implicit session =>
     val result = computers.filter(c => c.id === id).delete
     
     result > 0
  }
}