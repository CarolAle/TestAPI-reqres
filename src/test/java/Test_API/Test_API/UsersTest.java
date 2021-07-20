package Test_API.Test_API;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.http.ContentType;

public class UsersTest extends LoginTest {
	
	@Test
	public void testListUsers() {
		given()
		.when()
			.get("/users")
		.then()
			.log().all()
			.statusCode(200)
			.body("page", equalTo(1))
			.body("data.email[0]", equalTo("george.bluth@reqres.in"))
			.body("data.email[5]", equalTo("tracey.ramos@reqres.in"));
	}
	
	@Test
	public void testCreateUser() {
		 given()
			.body("{\n"
					+ "    \"name\": \"User Test\",\n"
					+ "    \"job\": \"leader\"\n"
					+ "}")
			.contentType(ContentType.JSON)
		.when()
			.post("/users")
		.then()
			.log().all()
			.statusCode(201)
			.body("name", equalTo("User Test"));
	}
	
	@Test
	public void testEditUser() {
		 given()
			.body("{\n"
					+ "    \"name\": \"User Test Edit\",\n"
					+ "    \"job\": \"leader\"\n"
					+ "}")
			.contentType(ContentType.JSON)
		.when()
			.put("/users/2")
		.then()
			.log().all()
			.statusCode(200)
			.body("name", equalTo("User Test Edit"));
	}
	
	@Test
	public void testDeletUser() {
		 given()
		.when()
			.delete("/users/2")
		.then()
			.log().all()
			.statusCode(204);
	}

}
