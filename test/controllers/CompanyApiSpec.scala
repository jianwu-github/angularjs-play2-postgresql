package controllers

import play.api.test.{PlaySpecification, FakeRequest}
import play.api.libs.json.Json

class CompanyApiSpec extends PlaySpecification {
  "Compay REST API " should {
    "list companies" in {
      val response = call(CompanyApi.list, FakeRequest())
      status(response) must equalTo (OK)
      
      // matching data defined in app_db.sql
      contentAsString(response) must contain("\"id\":1,\"name\":\"Apple Inc.\"")
    }
    
    "get first company" in {
      val response = call(CompanyApi.findBy(1), FakeRequest())
      status(response) must equalTo (OK)
      
      // matching data defined in app_db.sql
      contentAsString(response) must contain("\"id\":1,\"name\":\"Apple Inc.\"")
    }
  }
}