package api.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import api.methods.ApiMethods;
import io.restassured.response.Response;

public class TestCases {
	
	
	@Test(priority=1)
	public void testGetUser() throws IOException {
		Object logger;
		Response response = ApiMethods.getuser();
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);

	}
	
}
