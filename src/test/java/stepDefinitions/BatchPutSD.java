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
import model.BatchResponse;

import org.json.simple.JSONObject;
import org.testng.Assert;
import Base.baseclass;
import java.io.IOException;


public class BatchPutSD extends baseclass{
	String uri;
	public RequestSpecification request;
	Response response;
	BatchResponse batch;
	
	
	

	
	
	@Then("User Receives 200 status code with response body")
	public void verify_status_code_and_response_body() {
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		response.then().statusCode(200);
		
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
	
	@When("User creates new batch for this test and gets batch details from {string} and {string} and {string} and {string} and {string} and {string}")
	public void create_new_batch_get_batch_details(String sheetName, String bName, String bDesc, String bStatus, String bNoC, String bPgId) throws IOException {
		this.uri = Config.BASE_URL + "/" + Config.PostBatch_SUB_URL;
	    System.out.println(this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
	    
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
		Response resp = this.request.post(this.uri);
		resp.then().log().all();
		
		this.batch = new BatchResponse();
		JsonPath jsonPathEvaluator = resp.jsonPath();
		
		this.batch.batchId = jsonPathEvaluator.get("batchId");
		this.batch.batchDescription = jsonPathEvaluator.get("batchDescription");
		this.batch.batchName = jsonPathEvaluator.get("batchName");
		this.batch.batchNoOfClasses = jsonPathEvaluator.get("batchNoOfClasses");
		this.batch.batchStatus = jsonPathEvaluator.get("batchStatus");
		this.batch.programId = jsonPathEvaluator.get("programId");
		this.batch.programName = jsonPathEvaluator.get("programName");

		
	   
	}
	
	@When("User sends HTTPS PUT Request and request Body with all field from {string} and {string}")
	public void update_new_batch_get_batch_details(String sheetName, String bDesc) throws IOException {
		this.uri = Config.BASE_URL + "/" + Config.PutBatch_SUB_URL + "/" + this.batch.batchId;
	    System.out.println(this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
	    
		
		String batchDesc = excelDataValue(sheetName, bDesc);
		
		JSONObject body = new JSONObject(); 
		Random r = new Random();
		
		body.put("batchId", this.batch.batchId); 
		body.put("batchDescription", batchDesc); 
		body.put("batchName", this.batch.batchName); 
		body.put("batchNoOfClasses", this.batch.batchNoOfClasses); 
		body.put("batchStatus", this.batch.batchStatus); 
		body.put("programId", this.batch.programId); 
		
		System.out.println(body.toString());
		
		this.request.body(body);
		this.response = this.request.put(this.uri);
		this.response.then().log().all();
		
		//this.batch = new BatchResponse();
		JsonPath jsonPathEvaluator = this.response.jsonPath();
		
		this.batch.batchId = jsonPathEvaluator.get("batchId");
		this.batch.batchDescription = jsonPathEvaluator.get("batchDescription");
		this.batch.batchName = jsonPathEvaluator.get("batchName");
		this.batch.batchNoOfClasses = jsonPathEvaluator.get("batchNoOfClasses");
		this.batch.batchStatus = jsonPathEvaluator.get("batchStatus");
		this.batch.programId = jsonPathEvaluator.get("programId");
		this.batch.programName = jsonPathEvaluator.get("programName");

		
	   
	}
	
	@When("User sends HTTPS PUT Request with missing fields in request Body")
	public void update_new_batch_with_missing_batch_details() throws IOException {
		this.uri = Config.BASE_URL + "/" + Config.PutBatch_SUB_URL + "/" + this.batch.batchId;
	    System.out.println(this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
	    
		
		//String batchDesc = excelDataValue(sheetName, bDesc);
		
		JSONObject body = new JSONObject(); 
		Random r = new Random();
		
		//body.put("batchId", this.batch.batchId); 
		//body.put("batchDescription", batchDesc); 
		//body.put("batchName", this.batch.batchName); 
		body.put("batchNoOfClasses", this.batch.batchNoOfClasses); 
		body.put("batchStatus", this.batch.batchStatus); 
		body.put("programId", this.batch.programId); 
		
		System.out.println(body.toString());
		
		this.request.body(body);
		this.response = this.request.put(this.uri);
		this.response.then().log().all();
		
			   
	}
	
	@When("User sends HTTPS PUT Request with invalid batchid in request Body")
	public void update_new_batch_with_invalid_batchid_details() throws IOException {
		this.uri = Config.BASE_URL + "/" + Config.PutBatch_SUB_URL + "/" + "99999999";
	    System.out.println(this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
	    
		
		JSONObject body = new JSONObject(); 
		Random r = new Random();
		
		//body.put("batchId", 999999999); 
		body.put("batchDescription", this.batch.batchDescription); 
		body.put("batchName", this.batch.batchName); 
		body.put("batchNoOfClasses", this.batch.batchNoOfClasses); 
		body.put("batchStatus", this.batch.batchStatus); 
		body.put("programId", this.batch.programId); 
		
		System.out.println(body.toString());
		
		this.request.body(body);
		this.response = this.request.put(this.uri);
		this.response.then().log().all();
		
			   
	}
	
	@Then("Verify put batches API returns 400 status code and with batch missing fields error")
	public void verify_batches_API_returns_400_status_code_and_with_missing_field_message() {
				response.then().statusCode(400);
				
				JsonPath jsonPathEvaluator = response.jsonPath();
				String mes = jsonPathEvaluator.get("message");
				Assert.assertEquals(mes.contains("mandatory"),true);
				Boolean suc= jsonPathEvaluator.get("success");
				Assert.assertFalse(suc);
	}
	
	@Then("Verify put batches API returns 404 status code and not found")
	public void verify_batches_API_returns_404_status_code_and_not_found() {
				response.then().statusCode(404);
				
				JsonPath jsonPathEvaluator = response.jsonPath();
				String mes = jsonPathEvaluator.get("message");
				Assert.assertEquals(mes.contains("not found"),true);
				Boolean suc= jsonPathEvaluator.get("success");
				Assert.assertFalse(suc);
	}
	
	
	
}