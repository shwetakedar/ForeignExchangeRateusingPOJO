package testcases;

import pojo.EntireResponse;
import pojo.Rates;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;

import io.restassured.RestAssured;
import io.restassured.internal.util.IOUtils;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class TestCases {
	
		
/*	@Test
	public void deserialTest() throws IOException
	{
		
		RestAssured.baseURI = "https://api.ratesapi.io/api";
		RequestSpecification httpRequest = RestAssured.given();
		Response resp = httpRequest.get("/latest");
		
		ResponseBody body = resp.getBody();
		System.out.println("Response Body is: "+body.asString());
		String b = body.asString();
		File fileObj = new File("response.json");

		if(fileObj.createNewFile()) {
			FileWriter myWriter = new FileWriter("./files/response.json");
			myWriter.write(b);
			myWriter.close();
		}else {
			System.out.println("Failed");
		}		
	
	}*/
	
	@Test
	public void test2() throws IOException
	{
		String response =given()
				.contentType("application/java")
				.baseUri("https://api.ratesapi.io/api")
				.log().uri()
			.when()
				.get("/latest")
			.then()
				.statusCode(200)
				.extract().asString();
		ObjectMapper objectMapper = new ObjectMapper();				
		File fileObj = new File("response.json");
		if(!fileObj.createNewFile()) {
			FileWriter myWriter = new FileWriter("response.json");
			myWriter.write(response);
			myWriter.close();
		}
		else 
		{
			System.out.println("Failed");
		}
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		EntireResponse er = objectMapper.readValue(fileObj, EntireResponse.class);
		Assert.assertEquals(er.getBase(), "EUR");
	}
}
