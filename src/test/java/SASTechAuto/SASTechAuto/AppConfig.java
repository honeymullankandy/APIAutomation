package SASTechAuto.SASTechAuto;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AppConfig {
	// PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();

	public static RequestSpecification getRequestSpec() {
		PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
		String userName = System.getenv("BASIC_AUTH_USERNAME");
		String password = System.getenv("BASIC_AUTH_PASSWORD");
		basicAuth.setUserName(userName);
		basicAuth.setPassword(password);
		return new RequestSpecBuilder().setBaseUri("https://honeytech.atlassian.net/").setAuth(basicAuth)
				.setContentType(ContentType.JSON).build();
	}

	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
	}
}
