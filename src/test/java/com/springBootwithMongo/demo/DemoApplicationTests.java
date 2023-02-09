package com.springBootwithMongo.demo;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@Autowired
	private TestRestTemplate template;
	String question_url = "http://localhost:8080/employee";
//	@Test
//	void testForGetRequest() throws JSONException {
//
//		ResponseEntity<String> responseEntity = template.getForEntity(question_url,String.class);
//		String expected_response = """
//        [{
//				        "employeeId": "63d9160dd5dd5f2f18bf902a"
//				    },
//				    {
//				        "employeeId": "63d93cf0c7fcc25e27120d54"
//				       },
//				    {
//				        "employeeId": "63d93da19886060c84d740df"
//				    },
//				    {
//				        "employeeId": "63d93e489886060c84d740e0"},
//				    {
//				        "employeeId": "63da090f91a0ae2e4dcaba0d"
//				    },
//				    {
//				        "employeeId": "63da2f8524a3325bab48f60e"},
//				    {
//				        "employeeId": "122443"
//				    }
//				]
//				""";
//
//		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
//		assertEquals("application/json",responseEntity.getHeaders().get("Content-Type").get(0));
//		JSONAssert.assertEquals(expected_response,responseEntity.getBody(),false);
//	}

	@Test
	public void testForPost(){

		String requestBody = """
				{"employeeName": "for_test",
				        "employeeDesignation": "test",
				        "employeeSalary": 25000,
				        "employeeDob": "2/10/1998"}
				""";

		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type","application/json");
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody,header);
		ResponseEntity<String> responseEntity = template.exchange(question_url, HttpMethod.POST,httpEntity,String.class);
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		System.out.println(responseEntity.getHeaders());
//		String loc = responseEntity.getHeaders().get("Location").get(0);
	}

}
