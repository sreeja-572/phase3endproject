package courseEndProject;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Assignment003And004 {
	
	@Test(priority='1')
	public void assignment003User()
	{
		RestAssured.given()
		.baseUri("https://petstore.swagger.io/v2/user/Uname001")
		.when()
		.get()
		.then()
		.statusCode(200)
		.log().all()
		.body("username", Matchers.equalTo("Uname001"))
		.body("email", Matchers.equalTo("Positive@Attitude.com"))
		.body("userStatus", Matchers.equalTo(1))
		;
	
		
		
	}
	
	
	@Test(priority='2')
	public void assignment004login()
	{
		RestAssured.given()
		.baseUri("https://petstore.swagger.io/v2/user/login")
		.auth().preemptive().basic("Uname001", "@tt!tude")
		.when()
		.get()
		.then()
		.statusCode(200)
		.log().all()
		.body("message", Matchers.anything());
	
		
		
	}

}







	