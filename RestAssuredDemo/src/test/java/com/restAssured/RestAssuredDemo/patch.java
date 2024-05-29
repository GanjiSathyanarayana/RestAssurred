package com.restAssured.RestAssuredDemo;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class patch {
	 public static void main(String[] args) {
			RestAssured.baseURI="https://reqres.in/";
			RequestSpecification req=RestAssured.given();
			
			JSONObject obj = new JSONObject();
			obj.put("name", "Narayana");
			obj.put("job", "S/W");
			
			req.body(obj.toJSONString());
			Response res=req.patch("api/users/2");
			System.out.println(res.asPrettyString());
			System.out.println(res.getStatusCode());
		}
}
