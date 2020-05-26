package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utility {
	public ResponseSpecification respSpec;
	public RequestSpecification reqSpec;
	public 	String res;
	
	public String extractProperty(String key) throws IOException {
		
		Properties prop=new Properties();
	FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/java/resources/global.properties"));
	
	prop.load(file);
	
	return prop.getProperty(key);
	
	}
	//parameter name is used so that logs of each data set is created dynamically if parameter is not given logs of first data set is erased and only last data set logs are output stream
	public RequestSpecification requestSpecification(String name) throws IOException {
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";


    PrintStream log=new PrintStream(new FileOutputStream(System.getProperty("user.dir")+"/logs/"+name+"logging.txt"));
	     reqSpec = new RequestSpecBuilder().setBaseUri(extractProperty("baseURI"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
	     
	     return reqSpec;
	     
		
		
	}
	
	public ResponseSpecification responseSpecification(int statusCode) {
		
		respSpec=new ResponseSpecBuilder().expectStatusCode(statusCode)
				.expectContentType(ContentType.JSON).build();
		
		return respSpec;
		
	}
	
	public String getJsonPath(Response response,String key) {
		
		
		 res=response.asString();
		
		JsonPath js=new JsonPath(res);
		return(js.get(key).toString());
		
	}
}
