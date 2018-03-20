package com.easybix.Issue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.easybix.RestAssuredCore.BaseAssertion;
import com.easybix.RestAssuredCore.BaseTest;
import com.easybix.RestAssuredCore.RESTCalls;
import com.easybix.utils.PayloadGenerator;
import com.easybix.utils.URL;

import io.restassured.response.Response;

public class CreateIssue {

	private String sessionID;
	Response response;

	private static Logger log = LogManager.getLogger(CreateIssue.class.getName());

	@BeforeMethod
	public void setUp() {
		sessionID = BaseTest.doLogin();
	}

	@Test
	public void createIssue() {
		log.info("Starting Create Issue Test");
		String URI = URL.getEndPoint("/rest/api/2/issue/");
		String createIssuePayLaod = PayloadGenerator.generatePayLoadString("CreateBug.json");
		response = RESTCalls.POSTRequest(URI, createIssuePayLaod, sessionID);
		BaseAssertion.verifyStatusCode(response, 201);

	}

}
