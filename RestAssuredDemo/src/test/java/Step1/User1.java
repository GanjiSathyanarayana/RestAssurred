package Step1;

import org.json.simple.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class User1 {
	RequestSpecification req;
	Response res;
	JsonPath path;
	JSONObject obj;
	String expJ, expN;
	
	@Given("User is on reqres URL")
	public void user_is_on_reqres_url() {
		RestAssured.baseURI="https://reqres.in/";
				    req=RestAssured.given();
	}

	@When("User enters the {string} , and {string}")
	public void user_enters_the_and(String name, String job) {
		obj=new JSONObject();
		obj.put("name",name);
		obj.put("job", job);
		expJ=job;
		expN=name;
		req.header("Content-Type","application/json");
		System.out.println(obj);
	}

	@When("Users hits the users API")
	public void users_hits_the_users_api() {
		res=req.body(obj.toJSONString()).post("api/users");
	}

	@Then("Users are added to list")
	public void users_are_added_to_list() {
		System.out.println(res.asPrettyString());
		path=res.jsonPath();
		String jobdata=path.getString("job");
		System.out.println(jobdata);
		Assert.assertEquals(jobdata, expJ);
		
		
	
	}
}
