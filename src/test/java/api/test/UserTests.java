package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.payload.User;
import io.restassured.response.Response;
import api.endpoints.*;

public class UserTests {

	Faker faker = new Faker();
	User userpayload = new User();
	
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		
		//faker = new Faker();
		//userpayload = new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());

    }
	@Test(priority=1)
	public void testPostUser() {
		logger.info("------------CreatingUser----------");
		Response response = UserEndPoints.createuser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("------------UserCreated----------");

	}
	
	
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("------------ReadingUserInfo----------");

		Response response = UserEndPoints.readuser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("------------UserInfoDisplayed----------");

	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		logger.info("------------UpdatingUser----------");

		//Update data using userpayload
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateuser(this.userpayload.getUsername(),userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("------------UserUpdated----------");

		//checking data after update
		Response responseafterupdate = UserEndPoints.readuser(this.userpayload.getUsername());
		Assert.assertEquals(responseafterupdate.getStatusCode(),200);
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		logger.info("------------DeletingUser----------");

		Response response = UserEndPoints.deleteuser(this.userpayload.getUsername());		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("------------UserDeleted----------");

	}
	
	
	
}
