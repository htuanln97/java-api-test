package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import constant.Endpoints;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import utils.JsonUtils;

public class E2ETest extends TestBase {
	
	@Test
	@Description("Test case: Login, Create, Update & Delete User")
	public void testCRUD() {
		// Login then parse token to test base
		JsonObject loginPayload = JsonUtils.readJsonAsObject("login.json");

		Response res = post(Endpoints.LOGIN, loginPayload);
		assertEquals(res.statusCode(), 200);
		assertNotNull(res);
		String token = res.jsonPath().getString("token");
		setToken(token);
		
		// Create user
		JsonObject createUserPayload = JsonUtils.readJsonAsObject("createUser.json");
		Response createRes = post(Endpoints.CREATE_USER, createUserPayload);
		assertEquals(createRes.statusCode(), 201, "Verify that create user not success");
		String userId = createRes.jsonPath().getString("id");

		// Update user
		Map<String, String> updateUserPayload = new HashMap<>();
		updateUserPayload.put("name", "Tester Updated");
		updateUserPayload.put("job", "QA Lead");
	    String updateEndpoint = Endpoints.UPDATE_USER.replace("{id}", userId);
		Response updateRes = put(updateEndpoint, updateUserPayload);
		assertEquals(updateRes.statusCode(), 200, "Verify that user infor can not be updated");

		// Delete user
		String deleteEndpoint = Endpoints.DELETE_USER.replace("{id}", userId);
		Response deleteRes = delete(deleteEndpoint);
		assertEquals(deleteRes.statusCode(), 204, "Verify that user can not be deleted");
	}
	
	
	@Test
	@Description("Test case: Register user, login with that user and retrieve infor")
	public void testResgisterLoginThenRetrieveUserList() {
		// Register a user
		JsonObject payload = JsonUtils.readJsonAsObject("register.json");
		Response registerRes = post(Endpoints.REGISTER, payload);
		
		// Verify that register user success
		System.out.println(registerRes.asPrettyString());
		assertEquals(registerRes.statusCode(), 200);
		String userId = registerRes.jsonPath().getString("id");
		String token = registerRes.jsonPath().getString("token");
		setToken(token);
		
		// Login with a user just registered
		Response loginRes = post(Endpoints.LOGIN, payload);
		assertEquals(loginRes.statusCode(), 200);
		assertNotNull(loginRes);
		System.out.println(loginRes.asPrettyString());
		
		
		// Retrieve user data base on id
		String endpoint = Endpoints.GET_USER.replace("{id}", userId);
		Response res = get(endpoint);
		String actualUserEmail = res.jsonPath().getString("data.email");

		// Verify
		System.out.println(res.asPrettyString());
		assertEquals(actualUserEmail, payload.get("email").getAsString());
		assertEquals(res.statusCode(), 200);
		assertNotNull(res);
	}
}
