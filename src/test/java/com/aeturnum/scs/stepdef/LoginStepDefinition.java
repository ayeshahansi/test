package com.aeturnum.scs.stepdef;

import static io.restassured.RestAssured.given;

//import org.testng.Assert;

//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;



public class LoginStepDefinition {
	
	
	@When("^loging call is sent$")
	public void loging_call_is_sent() throws Throwable {
		
		 String token;

		System.out.println("Login test start");
		Response response = given().contentType("application/x-www-form-urlencoded; charset")
				.formParam("grant_type", "password").formParam("username", "peter")
				.formParam("password", "Testing.123").when().post("http://192.168.103.101:83/api/security/token")
				.andReturn();
		
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());

		
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody().asString());



		System.out.println("Login test end ");
	    throw new PendingException();
	}

	@Then("^success message is displayed$")
	public void success_message_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

}
