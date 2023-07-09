package api.methods;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ApiMethods {
	
public static Response getuser() {
		
		Response response = given().baseUri("https://reqres.in/").contentType(ContentType.JSON).
				accept(ContentType.JSON).
				when().get("api/users?page=2").then().extract().response();
		return response;
	}

}
