package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
public class DDTests {

	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostuser(String userID, String userName, String Firstname, String Lastname, String useremail, String pwd, String ph)
	{
		User userpayload=new User();
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(userName);
		userpayload.setFirstname(Firstname);
		userpayload.setLastname(Lastname);
		userpayload.setEmail(useremail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);
		
		Response response = UserEndPoints.createuser(userpayload);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteuserbyName(String userName)
	{
		Response response = UserEndPoints.deleteuser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}
