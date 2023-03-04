package org.restTestNg;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.base.BaseClass;

import io.restassured.response.Response;

public class RestassureedTestNg extends BaseClass{
	
	//GET
	@Test(priority=1)
	public void get_01() {
		requestObject("https://reqres.in/");
		queryParameter("page", "2");
		Response getResponse = responseObject("GET", "api/users");
		int responseCode = responseCode(getResponse);
		System.out.println(responseCode);
		validationOfCode(200, getResponse);
		responseBody(getResponse);
	}
	
	@Test(priority=2)
	public void get_02() {
		requestObject("https://reqres.in/");
		Response getResponse = responseObject("GET", "api/unknown/2");
		int responseCode = responseCode(getResponse);
		System.out.println(responseCode);
		validationOfCode(200, getResponse);
		responseBody(getResponse);
	}
	
	//POST
	@Test(priority=3)
	public void post_01() {
		requestObject("https://reqres.in/");
		JSONObject js = new JSONObject();
		js.put("name", "Arun");
		js.put("age", "24");
		requestSpecification.body(js.toJSONString());
		Response postResponse = responseObject("POST", "api/users");
		int responseCode = responseCode(postResponse);
		System.out.println(responseCode);
		responseBody(postResponse);
		validationOfCode(201, postResponse);
	}
	
	//PUT
	@Test(priority=4)
	public void put_tc01() {
		requestObject("https://reqres.in/");
		JSONObject js = new JSONObject();
		js.put("name", "Nagu");
		js.put("job", "Business");
		requestSpecification.body(js.toJSONString());
		Response putResponse = responseObject("PUT", "api/users/2");
		int responseCode = responseCode(putResponse);
		System.out.println(responseCode);
		validationOfCode(200, putResponse);
		responseBody(putResponse);
	}

	//DELETE
	@Test(priority=5)
	public void delete_tc01() {
		requestObject("https://reqres.in/");
		Response deleeteResponse = responseObject("DELETE", "api/users/2");
		int responseCode = responseCode(deleeteResponse);
		System.out.println(responseCode);
		validationOfCode(204, deleeteResponse);
	}
}
