package StepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class UserList {
	RequestSpecification req;
	Response res;
	@Given("user is on reqres URL")
	public void user_is_on_reqres_url() {
	    RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given();
	  
	}

	@When("user hits the Users API")
	public void user_hits_the_users_api() {
		res=req.get("api/users?page=2");
	
	}

	@Then("all the users is displayed")
	public void all_the_users_is_displayed() {
	    String data= res.asPrettyString();
	    JsonPath path=res.jsonPath();
	    String id=path.getString("data[1].id" );
	  
	}

}
