package Test_API.Test_API;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class LoginTest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "/api";
	}
	
	@Test
	public void testLoginSuccessful() {
		 given()
			.body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/login")
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test
	public void testLoginUnsuccessful() {
		 given()
			.body("{\"email\": \"eve.holt@reqres.in\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/login")
		.then()
			.log().all()
			.body("error", containsString("Missing password"))
			.statusCode(400);
	}
	
}
