package tests;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import constant.Endpoints;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateUserTest extends TestBase{

	@Test
	@Description("Test case: Updated user infor")
	public void testUpdateUserInfo() {
		String userId = "2";
	    String endpoint = Endpoints.UPDATE_USER.replace("{id}", userId);
	    Map<String, String> payload = new HashMap<>();
		payload.put("job", "test");
		
	    
		Response res = put(endpoint, payload);

		// verify
		System.out.println(res.asPrettyString());
		assertEquals(res.statusCode(), 200);
		assertEquals(res.jsonPath().getString("job"), payload.get("job"));
		
	}
	
	@Test
	@Description("Test case: Update infor and expect invalid status code")
	public void testUpdateUserInfoAndExpectIncorrectValue() {
		String userId = "2";
	    String endpoint = Endpoints.UPDATE_USER.replace("{id}", userId);
	    Map<String, String> payload = new HashMap<>();
		payload.put("job", "test");
		
	    
		Response res = put(endpoint, payload);

		// verify
		System.out.println(res.asPrettyString());
		assertEquals(res.statusCode(), 200);
		assertEquals(res.jsonPath().getString("job"), "tester123");
		
	}
	
	@Test
	@Description("Test case: Update user infor with missing api key")
	public void testUpdateUserInfoWithMissingAPIKey() {
		String userId = "2";
	    String endpoint = Endpoints.UPDATE_USER.replace("{id}", userId);
	    Map<String, String> payload = new HashMap<>();
	    payload.put("name", "test");
		
	    Response res = RestAssured.given()
	            .baseUri(prop.getProperty("baseUrl"))
	            .header("Content-Type", "application/json")
	            .body(payload).put(endpoint);
	           


		// verify
		System.out.println(res.asPrettyString());
		// makes this case failed
		assertEquals(res.statusCode(), 200);
		
	}
}
