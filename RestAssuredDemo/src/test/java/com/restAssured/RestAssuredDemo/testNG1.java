package com.restAssured.RestAssuredDemo;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testNG1 {
	RequestSpecification req;
	Response res;
 @BeforeTest
	public void init() {
	 RestAssured.baseURI="https://reqres.in/";
		
	}
 @Test(priority=-1)
 public void getData() {
	  req=RestAssured.given();
		
	  res=req.get("api/users?page=2");
		//System.out.println(res.asPrettyString());
		JsonPath data=res.jsonPath();
		String email=data.getString("data[0].email");
		System.out.println(email);
		Assert.assertEquals(email, "michael.lawson@reqres.in");
 }
 @Test(enabled=false)
  public void addData() {
	  JSONObject obj = new JSONObject();
		obj.put("name", "Narayana");
		obj.put("job", "S/W");

		req.body(obj.toJSONString());
		Response res=req.put("api/users");
		System.out.println(res.asPrettyString());
 }
 @AfterTest
 public void deallocatMem() {
	 req=null;
	 res=null;
 }
 
}
