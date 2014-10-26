package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.data.Form
import play.api.data.Forms.{mapping, text, of}
import play.api.data.format.Formats.doubleFormat
import play.api.data.validation.Constraints

import models._

object ComputerApi extends Controller {
  implicit val readsCreateComputer = (
    (__ \ "id").read(Reads.min[Int](1)) and   
    (__ \ "name").read(Reads.minLength[String](1)) and
    (__ \ "manufactureId").read(Reads.min[Int](1))
  )(Computer.apply _)
  
  implicit val writesComputer = Json.writes[Computer]
  
  def list = Action { implicit request =>
    val computerList = ComputersDao.list()
    Ok(Json.toJson(computerList))
  }
  
  def findBy(id: Int) = Action { implicit request =>
    val computer = ComputersDao.get(id)
    Ok(Json.toJson(computer))
  }
  
  def findByManufacture(manufactureId: Int) = Action { implicit request =>
    val computerList = ComputersDao.listByManufacture(manufactureId)
    Ok(Json.toJson(computerList))
  }
  
  def create = Action(parse.json) { implicit request =>
      request.body.validate[Computer] match {
        case JsSuccess(createComputer, _) =>
          ComputersDao.create(createComputer) match {
            case Some(computer) => Ok(Json.toJson(computer))
            case None => InternalServerError
          }
        case JsError(errors) =>
          BadRequest
      }
  }
  
  def update(id: Int) = Action(parse.json) { implicit request =>
    request.body.validate[Computer] match {
      case JsSuccess(updateComputer, _) =>
        ComputersDao.update(updateComputer) match {
          case Some(computer) => Ok(Json.toJson(computer))
          case None => InternalServerError
        }
      case JsError(errors) =>
        BadRequest
    }
  }

}