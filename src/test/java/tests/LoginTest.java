package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import utils.JsonUtils;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import constant.Endpoints;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class LoginTest extends TestBase {

	@Test
	@Description("Test case: Login - Success")
	public void testLoginSuccess() {
		//Here - support the below read json file using gson
		//String payload = JsonUtils.readJsonAsString("login.json");
		//Object payload = JsonUtils.readJsonAsObject("login.json");
		JsonObject payload = JsonUtils.readJsonAsObject("login.json");
		
		Response res = post(Endpoints.LOGIN, payload);
		String authToken = res.jsonPath().getString("token");
		setToken(authToken);
		
		//verify
		System.out.println(res.asPrettyString());
		assertEquals(res.statusCode(), 200);
		assertEquals(authToken, "QpwL5tke4Pnpja7X4");
	}

	@Test
	@Description("Test case: Login with missing providing password")
	public void testLoginWithMissingPassword() {
		Map<String, String> payload = new HashMap<>();
		payload.put("email", "test@qa.com");

		Response res = post(Endpoints.LOGIN, payload);
		
		System.out.println(res.asPrettyString());
		String errorMessage = res.jsonPath().getString("error");
		
		//verify
		assertEquals(res.statusCode(), 400);
		assertEquals(errorMessage, "Missing password");
	}

	@Test
	@Description("Test case: Login with invalid email")
	public void testLoginWithInvalidEmail() {
		JsonObject payload = JsonUtils.readJsonAsObject("loginInvalidEmail.json");

		Response res = post(Endpoints.LOGIN, payload);
		
		System.out.println(res.asPrettyString());
		String errorMessage = res.jsonPath().getString("error");
		
		//verify
		assertEquals(res.statusCode(), 400);
		assertEquals(errorMessage, "user not found");
	}
}
