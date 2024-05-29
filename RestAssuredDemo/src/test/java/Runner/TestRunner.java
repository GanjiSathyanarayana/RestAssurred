package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\res\\Features\\AddUpdateUsers.feature",
glue="StepUpdate",
tags="@users",
//dryRun=true,
monochrome=false,
plugin= {"pretty", "html:target/reqresReport/UserReport.html"
		,"json:target/JsonReport/UserReport.json",
		"junit:target/JunitReport/UserReport.xml", 
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
	
}




	
	
