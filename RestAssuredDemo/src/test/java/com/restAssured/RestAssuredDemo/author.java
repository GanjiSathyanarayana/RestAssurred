package com.restAssured.RestAssuredDemo;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class author {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		 JSONObject obj= new JSONObject();
		 obj.put("userName", "Sathya");
		 obj.put("password", "Sathya@1");
		RequestSpecification req=RestAssured.given().auth().
	    basic("Sathya", "Sathya@1").
	    header("Content-Type", "application/json").
	    body(obj.toJSONString());
		
		
		Response res = req.post("/Account/v1/Authorized");
		 System.out.println(res.asString());

		 RequestSpecification req1=RestAssured.given().header("Content-Type", "application/json").body(obj.toJSONString());
        Response res1=req1.post("/Account/v1/GenerateToken");
        System.out.println(res1.asPrettyString());
		
	}

}
