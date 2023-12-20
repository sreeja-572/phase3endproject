package courseEndProject;


import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Assignment001 {
	
	Logger logger = LogManager.getLogger(Assignment001.class);
	
	@Test(priority='1')
	public void Assignment001Post() {
		
		
		logger.info("Course End Project - Assignment001 - Post Request");
		File file = new File("C:\\Users\\DELL\\eclipse-workspace\\SL_SeleniumDemo_Workspace\\Phase-3-EndProject\\src\\main\\resource\\data.json");
		int id = RestAssured.given()
				.baseUri("https://petstore.swagger.io/v2/pet")
				.contentType(ContentType.JSON)
				.body(file)
				.when().post()
				.then()
				.statusCode(200)
				.log().all()
				.body("name", Matchers.equalTo("Doggie")).extract().path("id");
				logger.trace("The status code is checked");
				System.out.println(id);
				logger.trace("ID has been captured and validated");
						
			
		
	}
	@Test(priority='2', dependsOnMethods="Assignment001Post")
	public void assignment001Get() {
		
		logger.info("Course End Project - Assignment001 - Get Request");
		
		int id = RestAssured.given()
				.baseUri("https://petstore.swagger.io/v2/pet/344")
				.when().get()
				.then().statusCode(200)
				.log().all()
				.body("status", Matchers.equalTo("available")).extract().path("category.id");
		System.out.println(id);
		logger.trace("ID  and status has been captured and validated");
	}
	
	@Test(priority='3', dependsOnMethods="assignment001Get")
	public void assignment001Delete() {
		
		logger.info("Course End Project - Assignment001 - Delete Request");
		
		RestAssured.given()
				.baseUri("https://petstore.swagger.io/v2/pet/344")
				.when().delete()
				.then().statusCode(200)
				.log().all()
				.body("code", Matchers.equalTo(200))
				.body("type", Matchers.equalTo("unknown"))
				.body("message", Matchers.equalTo("344"));
						
	}
	
	
	

}