package tests;

import static org.testng.Assert.assertEquals;


import org.testng.annotations.Test;

import constant.Endpoints;
import io.qameta.allure.Description;
import io.restassured.response.Response;

public class DeleteUserTest extends TestBase {

	@Test
	@Description("Test case: Delete user")
	public void testDeleteUser() {
		String userId = "2";
		String endpoint = Endpoints.DELETE_USER.replace("{id}", userId);

		Response res = delete(endpoint);

		// verify
		assertEquals(res.statusCode(), 204);

	}

	@Test
	@Description("Test case: Delete user and expected invalid code")
	public void testDeleteUserAndExpectInvalidStatusCode() {
		String userId = "100";
		String endpoint = Endpoints.DELETE_USER.replace("{id}", userId);

		Response res = delete(endpoint);

		// verify
		assertEquals(res.statusCode(), 400);

	}

	@Test
	@Description("Test case: Delete user - missing user id")
	public void testDeleteUserWithoutUserID() {
		Response res = delete("/api/users/");

		// verify
		assertEquals(res.statusCode(), 400);

	}
}
