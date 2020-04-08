package com.qa.rest.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.rest.base.RestApiBase;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;

public class TC_04_Put_Employee_Record extends RestApiBase {
	
	
	
	@BeforeClass
	public void postemployeeorder() {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		JSONObject requestparams = new JSONObject();

		requestparams.put("name", "manan");
		requestparams.put("salary", "40000");
		requestparams.put("age", "98");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestparams.toJSONString());
		response = httpRequest.request(Method.PUT, "/update/"+empID);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
	}

	@Test(priority = 1)
	public void Responsebody() {

		String responsebody = response.getBody().asString();
		System.out.println(responsebody);

	}

	@Test(priority = 2)
	public void checkStatusCode() {
		int response_code = response.getStatusCode();
		System.out.println("Response Code ==> " + response_code);
		Assert.assertEquals(response_code, 200);
	}

	@Test(priority = 3)
	public void checkStatusTime() {
		Long response_time = response.getTime();
		System.out.println("Response time ==> " + response_time);
		Assert.assertTrue(response_time > 1000);
	}

	@Test(priority = 4)
	public void checkStatusLine() {
		String response_line = response.getStatusLine();
		System.out.println("Response line ==> " + response_line);
		Assert.assertEquals(response_line, "HTTP/1.1 200 OK");
	}

	@Test(priority = 5)
	public void checkheaders() {
		Headers headers = response.headers();
		for (Header header : headers) {

			String name = header.getName();
			String value = header.getValue();
			System.out.println(name + " =====>  " + value);
		}

	}



}
