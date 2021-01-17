package StepDefFile;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import org.testng.Assert;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Utilities.ReadConfig;
//import static io.restassured.RestAssured.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import pojo.EntireResponse;
import pojo.Rates;


public class ForeignExchangeStepDefinition {
	
	ReadConfig readconfig = new ReadConfig();
	public String url = readconfig.getApplicationUrl();
	
	RequestSpecification rs;
	Response response;
	EntireResponse er;
	ObjectMapper objectMapper;
	File f;
	File fileObj;
	String resp;
	
	
	@Given("Rates API for latest Foreign Exhchange rates")
	public void rates_api_for_latest_foreign_exhchange_rates() {
		rs = given().baseUri(url);
	}

	@When("user calls latest Foreign Exchange Rates API {string} with GET http request")
	public void user_calls_latest_foreign_exchange_rates_api_with_get_http_request(String apiUrl) {
		response = rs.when().get(url+apiUrl);
	}

	@When("the API call is displayed with status code {int}")
	public void the_api_call_is_displayed_with_status_code(Integer expectedStatusCode) {
		Integer statusCode = response.getStatusCode();
		assertEquals(statusCode,expectedStatusCode);
	}

	@When("the response body is written to a json file")
	public void the_response_body_is_written_to_a_json_file() throws IOException {
		resp = response.asString();
		objectMapper = new ObjectMapper();				
//		fileObj = new File("response.json");
//		if(!fileObj.createNewFile()) {
//			FileWriter myWriter = new FileWriter("response.json");
//			myWriter.write(resp);
//			myWriter.close();
//		}
//		else 
//		{
//			System.out.println("Failed");
//		}
	}

	@When("json file is read")
	public void json_file_is_read() {
		
	}

	@Then("the response body should contain base value as {string}")
	public void the_response_body_should_contain_base_value_as(String baseExpectedValue) throws JsonParseException, JsonMappingException, IOException {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		er = objectMapper.readValue(resp, EntireResponse.class);
		//er = objectMapper.readValue(fileObj, EntireResponse.class);
		Assert.assertEquals(er.getBase(), baseExpectedValue); 
	}

	@Then("response body should contain date as {string}")
	public void response_body_should_contain_date_as(String dateExpectedValue) throws JsonParseException, JsonMappingException, IOException {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		er = objectMapper.readValue(resp, EntireResponse.class);
		Assert.assertEquals(er.getDate(), dateExpectedValue); 
	}

	@Then("response body should contain rates")
	public void response_body_should_contain_rates() throws JsonParseException, JsonMappingException, IOException {
		//objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		//float audrate = BigDecimal.valueOf(er.getRates().getAUD()).floatValue();
		//er = objectMapper.readValue(resp, EntireResponse.class);
		//float audrate = BigDecimal.valueOf(r.getAUD()).floatValue();	
		//er = response.as(EntireResponse.class);
		//System.out.println("Rates are: " +er.toString());
		Gson gson = new Gson();
		er = gson.fromJson(resp, EntireResponse.class);
		System.out.println("Rates are:"+er.getRates().getBGN());
		assertEquals(1.9558,er.getRates().getBGN(),0.001);
		
		
	}
	

}
