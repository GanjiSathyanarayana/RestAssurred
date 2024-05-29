package com.restAssured.RestAssuredDemo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class AppTest 
{
	public static void main(String args[]) {
		
		RestAssured.baseURI="https://reqres.in/";
		RequestSpecification req=RestAssured.given();
		
		Response res=req.get("api/users?page=2");
		System.out.println(res.asPrettyString());
		System.out.println(res.getStatusCode());
		System.out.println(res.getStatusLine());
		System.out.println(res.getBody());
	
		
		
	}
}
