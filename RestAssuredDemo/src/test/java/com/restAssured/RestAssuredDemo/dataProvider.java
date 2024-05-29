package com.restAssured.RestAssuredDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class dataProvider {
	RequestSpecification req;
	Response res;
	JsonPath data;
	File file;
	FileInputStream fis;
	Workbook w;
	Sheet s;
	@DataProvider(name="createUser")
	public Object[][] getData() throws IOException{
		file=new File("C:\\Users\\SAGANJI\\Desktop\\new\\RestAssuredDemo\\src\\test\\res\\MyExcel\\MyEx.xlsx");
		  fis=new FileInputStream(file);
		  w=new XSSFWorkbook(fis);
		  //s=w.getSheetAt(0);
		  s=w.getSheet("User");
		  int row=s.getPhysicalNumberOfRows();
		  
		  int col=s.getRow(0).getPhysicalNumberOfCells();
		
		
		Object[][] data=new Object[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				data[i][j]=s.getRow(i).getCell(j).toString();
			}
		}
		
		return data;
	}
	@BeforeTest
	public void init() {
		RestAssured.baseURI="https://reqres.in/";
	}
	@Test(dataProvider="createUser")
	
	public void addData(String name, String job) {
		req=RestAssured.given();
		JSONObject obj=new JSONObject();
		obj.put("name",name);
		obj.put("job", job);
		req.header("Content-Type","application/json");
		res=req.body(obj.toJSONString()).post("api/users");
		System.out.println(obj);
		req.body(obj.toJSONString());
		data=res.jsonPath();
		String jobdata=data.getString("job");
		System.out.println(jobdata);
		Assert.assertEquals(jobdata, job);
	}
	@AfterTest
	public void deallocateMem() {
		req=null;
		res=null;
	}
}
