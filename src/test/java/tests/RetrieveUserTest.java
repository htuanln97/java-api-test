package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import constant.Endpoints;
import io.qameta.allure.Description;
import io.restassured.response.Response;

public class RetrieveUserTest extends TestBase {

	@Test
	@Description("Test case: Retrieve a list of users")
	public void testRetrieveUserList() {
		Response res = get(Endpoints.GET_USER_LIST);

		// verify
		System.out.println(res.asPrettyString());
		assertEquals(res.statusCode(), 200);
		assertNotNull(res);
	}

	@Test
	@Description("Test case: Retrieve user data")
	public void testRetrieveWithGivenUser() {
		String userId = "10";
	    String endpoint = Endpoints.GET_USER.replace("{id}", userId);
	    
		Response res = get(endpoint);

		// verify
		System.out.println(res.asPrettyString());
		assertEquals(res.statusCode(), 200);
		assertNotNull(res);
	}
	
	@Test
	@Description("Test case: Retrieve user with providing invalid user id")
	public void testRetrieveWithInvalidUserID() {
		String userId = "20";
	    String endpoint = Endpoints.GET_USER.replace("{id}", userId);
	    
		Response res = get(endpoint);

		// verify
		System.out.println(res.asPrettyString());
		assertEquals(res.statusCode(), 404);
		assertEquals(res.getBody().asString(), "{}");
	}
}
