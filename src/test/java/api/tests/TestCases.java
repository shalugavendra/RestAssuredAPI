package api.tests;

import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import api.methods.ApiMethods;
import io.restassured.response.Response;

public class TestCases {
	
	@Test(priority=1)
	public void testGetUser() {
		Object logger;
		Response response = ApiMethods.getuser();
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);

	}

}
