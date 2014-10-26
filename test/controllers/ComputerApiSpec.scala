package controllers

import play.api.test.{PlaySpecification, FakeRequest}
import play.api.libs.json.Json

class ComputerApiSpec extends PlaySpecification {
  "Computers REST API " should {
    "list computers by Apple" in {
      val response = call(ComputerApi.findByManufacture(1), FakeRequest())
      status(response) must equalTo (OK)
      
      // matching data defined in app_db.sql
      contentAsString(response) must contain("MacBook Pro")
    }
  }
}