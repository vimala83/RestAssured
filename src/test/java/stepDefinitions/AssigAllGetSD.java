package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonObject;

import Base.baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Config;
import utilities.Excelreader;

public class AssigAllGetSD extends baseclass {

	String uri;
	public RequestSpecification request;
	JsonObject jsonObject;
	String jsonAsString;
	Response response;

	@Given("User set GET Request for the LMS API endpoint")
	public void user_set_get_request_for_the_lms_api_endpoint() {

		this.uri = Config.Getallassignment_URL;
		System.out.println("URL= " + uri);
		this.request = RestAssured.given().header("Content-Type", "application/json");

	}

	@When("User set Https Request")
	public void user_set_https_request() {
		response = this.request.get(this.uri);
		System.out.println("Response is= " + response.asString());
		response.then().log().all();
	}

	@Then("User {string} OK Status .")
	public void user_ok_status(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		System.out.println("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request all Assignment data is successfull");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request unsuccessful");
			throw new io.cucumber.java.PendingException();
		}

	}

	@Given("User send GET Request for the LMS API endpoint with valid Assignment ID")
	public void user_send_get_request_for_the_lms_api_endpoint_with_valid_assignment_id() {
		this.uri = Config.Getallassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User create GET request with valid AssignmentID from {string} and {string}")
	public void user_create_get_request_with_valid_assignment_id_from_and(String string, String string2)
			throws IOException {
		System.out.println("TestCase Name= " + string);

		String assignmentId = excelDataValue(string, string2);

		response = this.request.when().get(Config.Getallassignment_URL + "/" + assignmentId).then().log().all()
				.extract().response();
	}

	@Then("User receives {string} OK Status with response body.")
	public void user_receives_ok_status_with_response_body(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		System.out.println("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request all Assignment data is successfull");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request unsuccessful");
			throw new io.cucumber.java.PendingException();
		  }
		}

	@Given("User send GET Request for the LMS API endpoint with invalid AssignmentID")
	public void user_send_get_request_for_the_lms_api_endpoint_with_invalid_assignment_id() {
		this.uri = Config.Getallassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User create GET request with invalid AssignmentID from {string} and {string}")
	public void user_create_get_request_with_invalid_assignment_id_from_and(String string, String string2)
			throws IOException {
		System.out.println("TestCase Name= " + string);
		
				response = this.request
					.when()
					.get(Config.Getallassignment_URL + "/" + randomestring())
					.then()
					.log().all().extract().response();
					}

	@Then("User {string} Not Found Status.")
	public void user_not_found_status(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		System.out.println("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request all Assignment data is successfull");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request unsuccessful");
				}
	}

	@Given("User creates GET Request for the LMS API endpoint with valid BatchId")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_batch_id() {
		this.uri = Config.Getassignment_URL_BatchID;
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User create GET request with valid batch ID from {string} and {string}")
	public void user_create_get_request_with_valid_batch_id_from_and(String string, String string2) throws IOException {
		System.out.println("TestCase Name= " + string);
		
		String VaildBatchID = excelDataValue(string, string2);
		response = this.request.when().get(Config.Getassignment_URL_BatchID + "/" + VaildBatchID).then().log().all()
				.extract().response();
	}

	@Then("User get status {string} OK")
	public void user_get_status_ok(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		System.out.println("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request all Assignment data is successfull");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request unsuccessful");
			throw new io.cucumber.java.PendingException();
		}

	}

	@Given("User GET Request for the LMS API endpoint with invalid BatchId")
	public void user_get_request_for_the_lms_api_endpoint_with_invalid_batch_id() {
		this.uri = Config.Getassignment_URL_BatchID;
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User create GET request with invalid batchID from {string} and {string}")
	public void user_create_get_request_with_invalid_batch_id_from_and(String string, String string2) throws IOException {
		//String InVaildBatchID = excelDataValue(string, string2);
		//response = this.request.when().get(Config.Getassignment_URL_BatchID + "/" + InVaildBatchID).then().log().all()
			//	.extract().response();
		response = this.request
				.when()
				.get(Config.Getassignment_URL_BatchID + "/" + randomestring())
				.then()
				.log().all().extract().response();

	}

	@Then("User create {string} Not Found Status with message and boolean success details")
	public void user_create_not_found_status_with_message_and_boolean_success_details(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		System.out.println("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request all Assignment data is successfull");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request unsuccessful");
					}

	}

}
