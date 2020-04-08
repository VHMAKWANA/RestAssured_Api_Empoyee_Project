package com.qa.rest.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.rest.base.RestApiBase;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC_05_Delete_Employee_Record extends RestApiBase {

	@BeforeClass
	public void getallemployees() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		JsonPath jsonpath = response.jsonPath();
		String empID = jsonpath.get("[0].id");
		response=httpRequest.request(Method.DELETE, "/delete/" + empID);
	}

	@Test(priority = 1)
	public void checkResponseBody() {
		String responsbody = response.getBody().asString();

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
