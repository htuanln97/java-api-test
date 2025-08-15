package tests;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import constant.Endpoints;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import utils.JsonUtils;

public class RegisterTest extends TestBase{
	
	@Test
	@Description("Test case: Register User - Success")
	public void testRegisterUserSuccess() {
		JsonObject payload = JsonUtils.readJsonAsObject("register.json");
		Response res = post(Endpoints.REGISTER, payload);
		
		//verify
		System.out.println(res.asPrettyString());
		assertEquals(res.statusCode(), 200);
	}
	
	@Test
	@Description("Test case: Register User with missing password")
	public void testRegisterWithMissingPassword() {
		Map<String, String> payload = new HashMap<>();
		payload.put("email", "user.not.exist@reqres.in");

		Response res = post(Endpoints.REGISTER, payload);
		String errorMessage = res.jsonPath().getString("error");

		
		//verify
		System.out.println(res.asPrettyString());
		assertEquals(res.statusCode(), 400);
		assertEquals(errorMessage, "Missing password");
	}
	
	@Test
	@Description("Test case: Register User with a user not defined")
	public void testRegisterWithUserNotDefined() {
		JsonObject payload = JsonUtils.readJsonAsObject("registerWithUserIsNotDefined.json");

		Response res = post(Endpoints.REGISTER, payload);
		String errorMessage = res.jsonPath().getString("error");

		
		//verify
		System.out.println(res.asPrettyString());
		assertEquals(res.statusCode(), 400);
		assertEquals(errorMessage, "Note: Only defined users succeed registration");
	}
}
