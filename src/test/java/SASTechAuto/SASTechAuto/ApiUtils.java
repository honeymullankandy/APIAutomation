package SASTechAuto.SASTechAuto;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiUtils {

    private final RequestSpecification requestSpec;
    private final ResponseSpecification responseSpec;

    public ApiUtils(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        this.requestSpec = requestSpec;
        this.responseSpec = responseSpec;
    }

    public Response getRequest(String endpoint) {
        return given().spec(requestSpec).when().get(endpoint).then().spec(responseSpec).extract().response();
    }

    public Response putRequest(String endpoint, String jsonPayload) {
        return given().spec(requestSpec).body(jsonPayload).when().put(endpoint).then().spec(responseSpec).extract().response();
    }

    public Response postRequest(String endpoint,String jsonPayload) {
        return given().spec(requestSpec).body(jsonPayload).when().post(endpoint).then().spec(responseSpec).extract().response();
    }
    public Response deleteRequest(String endpoint) {
   	 return given().spec(requestSpec).when().delete(endpoint).then().spec(responseSpec).extract().response();
     
   }
}

