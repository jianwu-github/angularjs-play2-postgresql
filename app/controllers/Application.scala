package controllers

import play.api._
import play.api.mvc._

import models._

object Application extends Controller {
  val appTitle = "AngularJS UI with Play2 Demo App"
  
  def index(any: String) = Action {
    Ok(views.html.index(appTitle))
  }
  
  def about = Action {
    Ok(views.html.about(appTitle))
  }

  def previewcompanies = Action {
    val tenCompanies = CompaniesDao.limit(10)
    Ok(views.html.previewcompanies(tenCompanies))
  }
}