package models

import org.postgresql.ds.PGSimpleDataSource
import scala.slick.driver.PostgresDriver.simple._
import play.api.db.DB

trait CompaniesDao {
  def list(): Iterable[Company]
  
  def limit(n: Int): List[Company]
  
  def create(company: Company): Option[Company]
  
  def get(id: Int): Option[Company]
  
  def update(company: Company): Option[Company]
  
  def delete(id: Int): Boolean
}

object CompaniesDao extends CompaniesDao {
  def db = {
    val ds = new PGSimpleDataSource
    ds.setDatabaseName("postgres")
    ds.setUser("postgres")
    ds.setPassword("postgres")
    Database.forDataSource(ds)
  }

  val companies = Companies.companies
  
  def list() = db withSession { implicit session =>
    companies.list
  }
  
  def limit(n: Int) = db withSession { implicit session =>
    companies.take(n).list
  }
  
  def create(company: Company): Option[Company] = db withSession { implicit session =>
    val result = companies += company
    
    if (result > 0) get(company.id)
    else None
  }
  
  def get(id: Int): Option[Company] = db withSession { implicit session =>
    companies.filter(c => c.id === id).firstOption    
  }
  
  def update(company: Company): Option[Company] = db withSession { implicit session =>    
    val result = companies.filter( c => c.id === company.id).update(company)
    
    if (result > 0) get(company.id)
    else None
  }  
  
  def delete(id: Int): Boolean = db withSession { implicit session =>
     val result = companies.filter(c => c.id === id).delete
     
     result > 0
  }
}