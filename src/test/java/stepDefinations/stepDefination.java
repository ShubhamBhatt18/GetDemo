package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo_classes.AddPlace;
import pojo_classes.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;


public class stepDefination extends Utility {

	public ResponseSpecification respSpec;

	public RequestSpecification res;
	public Response response;
	public RequestSpecification res1;
	
	public static String placeId;
	TestDataBuild data=new TestDataBuild();
	
	@Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_Place_Payload_with(String name, String language, String address) throws Throwable {
		

		  res = given().spec(requestSpecification(name)).body(data.addPlacePayLoad(name,language,address));
	}

	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String resource, String httpMethod) throws Throwable {

		if (httpMethod.equalsIgnoreCase("Post")) {

			// instantiating enum.with valueOf method,constructor will be called with the
			// value of resource which you pass

			APIResources resourceAPI = APIResources.valueOf(resource);

			String httpResource = resourceAPI.getResources();

			response = res.when().post(httpResource).then().spec(responseSpecification(200)).extract().response();
		} 
		else if (httpMethod.equalsIgnoreCase("Delete")) {
			APIResources resourceAPI = APIResources.valueOf(resource);

			String httpResource = resourceAPI.getResources();

			response = res.when().post(httpResource).then().spec(responseSpecification(200)).extract().response();
		
			System.out.println("Deleted--Response--->"+response.asString());
		}
		
		
		else if(httpMethod.equalsIgnoreCase("Get")) {
			APIResources resourceAPI=APIResources.valueOf(resource);
			
			String httpResource=resourceAPI.getResources();
			response = res.when().post(httpResource).then().spec(responseSpecification(200)).extract().response();
			
		}

	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_API_call_got_success_with_status_code(int arg1) throws Throwable {
	    int status=response.getStatusCode();
	   assertEquals(arg1,status	);
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String StatusorScope, String expectedValue) throws Throwable {
      
		 
		   
		  assertEquals(getJsonPath(response, StatusorScope),expectedValue);
	  
		   
	}
	
	@Then("^verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_Id_created_maps_to_using(String name, String resource) throws Throwable {
		
		
		 placeId=getJsonPath(response,"place_id");
		
	  res=given().spec(requestSpecification(name+"Get"))
	  .queryParam("place_id",placeId);
	  
	  user_calls_with_http_request(resource, "Get");
	  
	  
	  //checking name from get response is equal to name in parameter
	  assertEquals(getJsonPath(response,"name"),name);
	  
	}
	
//	@Then("^verify that existing place deleted for placeID that maps to \"([^\"]*)\" using \"([^\"]*)\"\"$")
//	public void verify_that_existing_place_deleted_for_placeID_that_maps_to_using(String name, String resource) throws Throwable {
//	    
//		 res = given().spec(requestSpecification(placeId+"-Delete")).body("{\n" + 
//			  		"\"place_id\":\""+placeId+"\"\n" + 
//			  		"\n" + 
//			  		"}");
//		 
//		  user_calls_with_http_request(resource, "Delete");
//		  
//		 System.out.println("Deleted--Response--->"+response.asString());
//
//		
//	}
	
	
	@Given("^Delete Place Payload$")
	public void delete_Place_Payload() throws Throwable {

		 res = given().spec(requestSpecification(placeId+"Delete")).body(data.deletePlacePayLoad(placeId));
		 
		// user_calls_with_http_request(resource, "Delete");
		 
		 System.out.println("Delete given statetment to push into git");
		 System.out.println("Delete given statetment to push into develop branch");

	 
	}
	    
}
