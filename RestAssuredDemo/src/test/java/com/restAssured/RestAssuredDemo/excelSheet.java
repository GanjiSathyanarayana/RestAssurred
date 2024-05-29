package com.restAssured.RestAssuredDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class excelSheet {

	RequestSpecification req;
	Response res;
	File file;
	FileInputStream fis;
	Workbook w;
	Sheet s;

  @BeforeTest
  public void init() {
	  RestAssured.baseURI="https://reqres.in";
  }
 
  @Test()
  public void addData() throws IOException{
	  file=new File("C:\\Users\\SAGANJI\\Desktop\\new\\RestAssuredDemo\\src\\test\\res\\MyExcel\\MyEx.xlsx");
	  fis=new FileInputStream(file);
	  w=new XSSFWorkbook(fis);
	  //s=w.getSheetAt(0);
	  s=w.getSheet("User");
	  int row=s.getPhysicalNumberOfRows();
	  
	  int col=s.getRow(0).getPhysicalNumberOfCells();
	  String st=s.getSheetName();
	  System.out.println(st);
	  System.out.println("Rows:"+row+" col:"+col);
	  String name=s.getRow(1).getCell(0).toString();
	  String job=s.getRow(1).getCell(1).getStringCellValue();
	  req=RestAssured.given();
	  JSONObject obj=new JSONObject();
	  	obj.put("name",name);
		obj.put("job",job);
		req.headers("Content-Type","application/json");
		res=req.body(obj.toJSONString()).post("/api/users");
		
  }
}
