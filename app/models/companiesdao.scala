package models

import org.postgresql.ds.PGSimpleDataSource
import scala.slick.driver.PostgresDriver.simple._
import play.api.db.DB

object companiesdao {
  def session = {
    val ds = new PGSimpleDataSource
    ds.setDatabaseName("postgres")
    ds.setUser("postgres")
    ds.setPassword("postgres")
    Database.forDataSource(ds).createSession
  }

  val queryCompanies = TableQuery[Companies]
  
  def listCompanies = queryCompanies.list(session)
  
  def limitCompanies(n: Int) = queryCompanies.take(n).list(session)
}