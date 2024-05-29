package com.restAssured.RestAssuredDemo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getSingle {
   public static void main(String args[]) {
	   RestAssured.baseURI="https://reqres.in/";
	   RequestSpecification a=RestAssured.given();
	   
	   Response r=a.get("api/users/2");
	   System.out.println(r.asString());
	   System.out.println(r.equals(r));
	   System.out.println(r.getStatusCode());
	   System.out.println(r.contentType());
	   
   }
}
