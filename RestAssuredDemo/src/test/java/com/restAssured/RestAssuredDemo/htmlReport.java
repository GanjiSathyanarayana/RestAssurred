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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class htmlReport {
	RequestSpecification req;
	Response res;
	JsonPath data;
	File file;
	FileInputStream fis;
	Workbook w;
	Sheet s;
	ExtentReports report;
	ExtentTest test;
	@DataProvider
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
		report = new ExtentReports("C:\\Users\\SAGANJI\\OneDrive - Capgemini\\Desktop\\report\\ReportTest.html");
		test = report.startTest("Adding the users to excel creating users");
	}
	@Test(dataProvider="getData")
	
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
		if(job.equals(jobdata)) {
			test.log(LogStatus.PASS," Data is valid for expected "+job+" and actual "+ jobdata);
		}
		else if(name.isEmpty()) {
			test.log(LogStatus.INFO, name);
		}
		
		
		else {
			test.log(LogStatus.FAIL,"Data is invalid for expected "+job+" and actual "+ jobdata);
		}
		
	}
	@AfterTest
	public void deallocateMem() {
		req=null;
		res=null;
		report.endTest(test);
		report.flush();
	}
}
