package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.data.Form
import play.api.data.Forms.{mapping, text, of}
import play.api.data.format.Formats.doubleFormat
import play.api.data.validation.Constraints

import models._

object CompanyApi extends Controller {
  implicit val readsCreateCompany = (
    (__ \ "id").read(Reads.min[Int](1)) and   
    (__ \ "name").read(Reads.minLength[String](1))
  )(Company.apply _)
  
  implicit val writesCompany = Json.writes[Company]
  
  def list = Action { implicit request =>
    val companyList = CompaniesDao.list()
    Ok(Json.toJson(companyList))
  }
  
  def findBy(id: Int) = Action { implicit request =>
    val company = CompaniesDao.get(id)
    Ok(Json.toJson(company))
  }
  
  def create = Action(parse.json) { implicit request =>
      request.body.validate[Company] match {
        case JsSuccess(createCompany, _) =>
          CompaniesDao.create(createCompany) match {
            case Some(company) => Ok(Json.toJson(company))
            case None => InternalServerError
          }
        case JsError(errors) =>
          BadRequest
      }
  }
  
  def update(id: Int) = Action(parse.json) { implicit request =>
    request.body.validate[Company] match {
      case JsSuccess(updateCompany, _) =>
        CompaniesDao.update(updateCompany) match {
          case Some(company) => Ok(Json.toJson(company))
          case None => InternalServerError
        }
      case JsError(errors) =>
        BadRequest
    }
  }
}