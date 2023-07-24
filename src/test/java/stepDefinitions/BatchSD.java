package stepDefinitions;

import io.restassured.RestAssured;
import utilities.Config;
import utilities.Logger;

import java.util.List;
import java.util.Random;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.Assert;
import Base.baseclass;
import java.io.IOException;


public class BatchSD extends baseclass{
	String uri;
	public RequestSpecification request;
	Response response;

	
	@Given("User creates POST Request for the LMS API endpoint")
	public void batch_module_base_url_is_set_with_valid_post_batch() {
	    this.uri = Config.BASE_URL + "/" + Config.PostBatch_SUB_URL;
	    System.out.println(this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
	    Logger.info(this.uri);
	}
	
	@When("User sends HTTPS Request and  request Body with all field from {string} and {string} and {string} and {string} and {string} and {string}")
	public void valid_data_is_passed_as_request_body(String sheetName, String bName, String bDesc, String bStatus, String bNoC, String bPgId) throws IOException {
		String batchName = excelDataValue(sheetName, bName);
		String batchDesc = excelDataValue(sheetName, bDesc);
		String batchStatus = excelDataValue(sheetName, bStatus);
		String batchNoC = excelDataValue(sheetName, bNoC);
		String batchPgId = excelDataValue(sheetName, bPgId);
		
		JSONObject body = new JSONObject(); 
		Random r = new Random();
		
		body.put("batchDescription", batchDesc); 
		body.put("batchName", batchName + r.nextInt(1000)); 
		body.put("batchNoOfClasses", batchNoC); 
		body.put("batchStatus", batchStatus); 
		body.put("programId", batchPgId); 
		
		System.out.println(body.toString());
		
		this.request.body(body);
		this.response = this.request.post(this.uri);
		this.response.then().log().all();
	    Logger.info("User sends HTTPS Request and  request Body with all field");
	}
	
	
	@Then("User Receives 201 created status with response body")
	public void verify_status_code_and_response_body() {
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		response.then().statusCode(201);
		
		ResponseBody body = response.getBody();
		
		String bodyAsString = body.asString();
		Assert.assertTrue(bodyAsString.contains("batchDescription"));
		Assert.assertTrue(bodyAsString.contains("batchId"));
		Assert.assertTrue(bodyAsString.contains("batchName"));
		Assert.assertTrue(bodyAsString.contains("batchNoOfClasses"));
		Assert.assertTrue(bodyAsString.contains("batchStatus"));
		Assert.assertTrue(bodyAsString.contains("programId"));
		Assert.assertTrue(bodyAsString.contains("programName"));
		
		Logger.info(jsonPathEvaluator.get("batchId").toString());
	}
	
	
	
	
	
	
}