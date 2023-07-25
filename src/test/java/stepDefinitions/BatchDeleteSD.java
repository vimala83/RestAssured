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


public class BatchDeleteSD extends baseclass{
	String uri;
	public RequestSpecification request;
	Response response;
	BatchResponse batch;
	
	@Then("User Receives 200 status code for delete")
	public void verify_status_code_and_response_body() {
		
		
		
		response.then().statusCode(200);
		
	}
	
	@Then("User Receives 404 status code for delete")
	public void verify_404_status_code_and_response_body() {
		
		
		response.then().statusCode(404);
		
	}
	
	@When("User creates new batch for this delete test and gets batch details from {string} and {string} and {string} and {string} and {string} and {string}")
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
	
	@When("User sends HTTPS DELETE Request for same batch")
	public void delete_new_batch_get_batch_details() throws IOException {
		this.uri = Config.BASE_URL + "/" + Config.PutBatch_SUB_URL + "/" + this.batch.batchId;
	    System.out.println(this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
		this.response = this.request.delete(this.uri);
		this.response.then().log().all();
	   
	}
	
	@When("User sends HTTPS DELETE Request for invalid batchid")
	public void delete_batch_with_invalid_id() throws IOException {
		this.uri = Config.BASE_URL + "/" + Config.PutBatch_SUB_URL + "/" + "99999999";
	    System.out.println(this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
		this.response = this.request.delete(this.uri);
		this.response.then().log().all();
	   
	}
	
	
	
	
	
	
	
	
}