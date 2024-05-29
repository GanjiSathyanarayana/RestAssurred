package com.restAssured.RestAssuredDemo;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class delete {
	 public static void main(String[] args) {
			RestAssured.baseURI="https://reqres.in/";
			RequestSpecification req=RestAssured.given();
			
			
			Response res=req.delete("api/users/2");
			
			System.out.println(res.getStatusCode());
		}
}
