package SASTechAuto.SASTechAuto;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.List;

/**
 * TestCase class contains test methods for various API related to Jira issue creation. This contains basic functionality for each end points
 */

public class TestCase {
	private ApiUtils apiUtils;
	private int JiraId;

	/**
	 * Setup method to initialize API utilities before running the tests. Runs once
	 * for the class, as indicated by @BeforeClass annotation.
	 */

	@BeforeClass
	public void setup() {
		apiUtils = new ApiUtils(AppConfig.getRequestSpec(), AppConfig.getResponseSpec());
	}
	/**
	 * Tests the POST request to create an issue  Verifies that the status
	 * code is 200 (OK) and that the returned Jira Id that is used for further call.
	 */

	@Test(priority = 1)
	
	public void testCreateissue() {
		String jsonPayload = "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"CPG\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"sample task \",\r\n"
				+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"id\": \"10001\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}";
		Response response = apiUtils.postRequest("rest/api/2/issue",jsonPayload);
		Assert.assertEquals(response.getStatusCode(), 201);
		JiraId=response.jsonPath().getInt("id");
	}
	/**
	 * Tests the PUT request to update summary field .
	 * Verifies that the status code is 204
	 */
	
		@Test(priority = 2)
	public void testUpdateIssue() {
		String jsonPayload ="{\r\n"
				+ "    \"update\": {\r\n"
				+ "        \"summary\":[\r\n"
				+ "            {\r\n"
				+ "                \"set\":\"Rest API Call\"\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }\r\n"
				+ "}";
		Response response = apiUtils.putRequest("rest/api/3/issue/"+JiraId, jsonPayload);
		Assert.assertEquals(response.getStatusCode(), 204);
		
	}

	/**
	 * Tests the GET request for validating summary field. Verifies that the status code is 200
	 * (OK) and that the response indicates success.
	 */
	@Test(priority = 3)
	public void testGetIssue() {
		Response response = apiUtils.getRequest("rest/api/3/issue/"+JiraId);
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Full JSON Payload: " + response.getBody().asString());
		Assert.assertEquals(response.jsonPath().getString("fields.summary"), "Rest API Call");
	}
	/**
	 * Tests the delete request delete jira id so.end of flow validates the error code.
	 */
	@Test(priority = 4)
	public void testDeleteIssue() {
		Response response = apiUtils.deleteRequest("rest/api/3/issue/"+JiraId);
		Assert.assertEquals(response.getStatusCode(), 204);
		
	}


}
