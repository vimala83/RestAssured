package stepDefinitions;

import io.restassured.RestAssured;
import utilities.Config;
import utilities.Logger;

import java.io.IOException;
import java.util.List;

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

import org.testng.Assert;

import Base.baseclass;


public class BatchGetSD extends baseclass {
	String uri;
	public RequestSpecification request;
	Response response;

	@Given("Get all batch module url is set with valid url")
	public void batch_module_base_url_is_set_with_valid_get_batch_url() {
	    this.uri = Config.BASE_URL + "/" + Config.GetAllBatch_SUB_URL;
	    this.request = RestAssured.given().header("Content-type","application/json");
	}
	
	@When("User sends GET All batches request")
	public void user_sends_get_request() {
		response = this.request.get(this.uri);	
		//response.then().log().all();
	}
	
	@Then("Make sure batches api returns success 200 status code and verify collection schema")
	public void verify_response_status_code_is_200_return_collection_() {
				response.then().statusCode(200); // checking return status code
				JsonPath jsonPathEvaluator = response.jsonPath();
				Assert.assertTrue(jsonPathEvaluator.getList("$").size()>0); // checking response has at least one batch
				List<BatchResponse> resp = jsonPathEvaluator.getList("$", BatchResponse.class); // deserialze into object to validate schema 
				for (BatchResponse item: resp) {
					//System.out.println(item.batchId + ","+ item.batchName + "," + item.batchDescription+ "," + item.batchNoOfClasses+ "," + item.programId+ "," + item.programName+ "," + item.batchStatus );
					Assert.assertTrue(item.batchId> 0);
					Assert.assertTrue(item.batchName.length() > 0);
					//Assert.assertTrue(item.batchDescription.length()> 0);
					//Assert.assertTrue(item.batchNoOfClasses > 0);
					Assert.assertTrue(item.programId> 0);
					//Assert.assertTrue(item.programName.length()> 0);
					//Assert.assertTrue(item.batchStatus.length()> 0);
				}
				
	}
	
	@Given("User sets request for Batch module with valid base URL and valid path")
	public void user_sets_request_for_batch_module_with_valid_base_URL_and_valid_path() {
			    this.uri = Config.BASE_URL + "/" + Config.GETBatch_by_BatchID_URL;
			    System.out.println(this.uri);
			    this.request = RestAssured.given().header("Content-type","application/json");
			}
	
	@When("User sends GET request with valid batchID from {string} and {string}")
	public void user_sends_get_request_with_valid_batchID(String sheetname, String testcase) throws IOException  {
		String vaildBatchID = excelDataValue(sheetname, testcase);
		response = this.request.get(this.uri+vaildBatchID);	
		response.then().log().all();
	}
	
	@Then("Make sure batches api returns single batch success 200 status code and verify schema")
	public void make_sure_batches_api_returns_single_batch_success_200_status_code_and_verify_schema() {
				response.then().statusCode(200);
				
				JsonPath jsonPathEvaluator = response.jsonPath();
				int bID = jsonPathEvaluator.get("batchId");
				Assert.assertEquals(bID,6481, "valid batchId");
				String bDis = jsonPathEvaluator.get("batchDescription");
				Assert.assertEquals(bDis,"Newest", "valid batchDescription");
				String bName = jsonPathEvaluator.get("batchName");
				Assert.assertEquals(bName,"BumbbleBee", "valid batchName");
				int bNoofClass = jsonPathEvaluator.get("batchNoOfClasses");
				Assert.assertEquals(bNoofClass,10, "valid batchNoOfClasses");
				String bSt = jsonPathEvaluator.get("batchStatus");
				Assert.assertEquals(bSt,"Active", "valid batchStatus");
				int pId = jsonPathEvaluator.get("programId");
				Assert.assertEquals(pId,10942, "valid programId");
				String pName = jsonPathEvaluator.get("programName");
				Assert.assertEquals(pName,"ccccgh", "valid programName");
				
	}			
	
	
	@When("User sends GET request with invalid batchID")
	public void user_sends_get_request_with_invalid_batchID() {
		String invaildBatchID = randomestring();
		response = this.request.get(this.uri+invaildBatchID);	
		response.then().log().all();
	}
	
	@Then("Verify batches API returns 404 status code and with message not found")
	public void verify_batches_API_returns_404_status_code_and_with_message_not_found() {
				response.then().statusCode(404);
				
				JsonPath jsonPathEvaluator = response.jsonPath();
				String mes = jsonPathEvaluator.get("message");
				Assert.assertEquals(mes.contains("Batch not found with Id"),true);
				Boolean suc= jsonPathEvaluator.get("success");
				Assert.assertFalse(suc);
	}			
	
	@Given("User sets request for BatchName in Batch module with valid base URL and valid Path")
	public void user_sets_request_for_batchname_in_Batch_module_with_valid_base_URL_and_valid_path() {
			    this.uri = Config.BASE_URL + "/" + Config.GETBatch_by_BatchName_URL;
	
			    this.request = RestAssured.given().header("Content-type","application/json");
			}		
				
	
	@When("User sends GET request with valid batchName from {string} and {string}")
	public void user_sends_get_request_with_valid_batchName(String sheetname, String testcase) throws IOException  {
		String validBatchName = excelDataValue(sheetname, testcase);
		System.out.println(this.uri+validBatchName);
		response = this.request.get(this.uri+validBatchName);	
		response.then().log().all();
	}	
	
	@Then("Verify batches api returns single batchname success 200 status code and verify schema")
	public void verify_batches_api_returns_single_batchname_success_200_status_code_and_verify_schema() {
				response.then().statusCode(200);
				
			JsonPath jsonPathEvaluator = response.jsonPath();
			
			Assert.assertTrue(jsonPathEvaluator.getList("$").size()>0); // checking response has at least one batch
			List<BatchResponse> resp = jsonPathEvaluator.getList("$", BatchResponse.class); // deserialze into object to validate schema 
		
				BatchResponse batch = resp.get(0);
				
				Assert.assertEquals(batch.batchId,6481);
				Assert.assertEquals(batch.batchName,"BumbbleBee");
				Assert.assertEquals(batch.batchDescription,"Newest");
				Assert.assertEquals(batch.batchNoOfClasses,10);
				Assert.assertEquals(batch.programId,10942);
				Assert.assertEquals(batch.programName,"ccccgh");
				Assert.assertEquals(batch.batchStatus,"Active");
	}			
		
	
	@When("User sends GET request with invalid batchName")
	public void user_sends_get_request_with_invalid_batchName() {
		String invaildBatchName = randomestring();
		response = this.request.get(this.uri+invaildBatchName);	
		response.then().log().all();
	}
	
	@Given("User sets request for ProgramId in Batch module with valid base URL and valid Path")
	public void user_sets_request_for_ProgramId_in_Batch_module_with_valid_base_URL_and_valid_path() {
			    this.uri = Config.BASE_URL + "/" + Config.GETBatch_by_ProgramId_URL;
	
			    this.request = RestAssured.given().header("Content-type","application/json");
			}		
				
	@When("User sends GET request with valid ProgramId from {string} and {string}")
	public void user_sends_Get_request_with_valid_ProgramId (String sheetname, String testcase) throws IOException  {
		String validProgramId = excelDataValue(sheetname, testcase);
		System.out.println(this.uri+validProgramId);
		response = this.request.get(this.uri+validProgramId);	
		response.then().log().all();
	}	
	
	@When("User sends GET request with invalid ProgramId")
	public void user_sends_get_request_with_invalid_ProgramId() {
		String invaildProgramId = randomestring();
		response = this.request.get(this.uri+invaildProgramId);	
		response.then().log().all();
	}
	
	

}