package controllers

import play.api._
import play.api.mvc._

import models._

object Application extends Controller {

  def index = Action {
    val tenCompanies = companiesdao.limitCompanies(10)
    Ok(views.html.index("Your new application is ready.", tenCompanies))
  }

}