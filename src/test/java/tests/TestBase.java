package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	protected Properties prop;
	protected static String token;

	@BeforeMethod
	@Parameters({"env"})
	@Step("Init properties file and get enviroment from xml file")
	public void setUp(@Optional("test") String env) {
		prop = initProperties(env);
	}

	@Step("Repare request header")
	protected RequestSpecification request() {
		String baseUrl = prop.getProperty("baseUrl");
		RequestSpecification req = RestAssured.given().baseUri(baseUrl).
				filter(new AllureRestAssured()).
				header("Content-Type", "application/json").
				header(prop.getProperty("apiHeaderKey"), prop.getProperty("apiHeaderValue"));

		if (token != null && !token.isEmpty()) {
			req.header("Authorization", "Bearer " + token);
		}
		 System.out.println("=== Request Headers ===");
		    req.log().headers();
		return req;
	}

	@Step("POST")
	protected Response post(String endpoint, Object body) {
		return request().body(body).post(endpoint);
	}

	@Step("GET")
	protected Response get(String endpoint) {
		return request().get(endpoint);
	}

	@Step("PUT")
	protected Response put(String endpoint, Object body) {
		return request().body(body).put(endpoint);
	}

	@Step("DELETE")
	protected Response delete(String endpoint) {
		return request().delete(endpoint);
	}

	@Step("Set token")
	public static void setToken(String yourToken) {
		token = yourToken;
	}

	/**
	 * This method is used to initialize the properties from config file
	 */
	public Properties initProperties(String env) {
		String filePath = "./src/test/resources/config/" + env + "-config.properties";
		try {
			FileInputStream ip = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}
}
