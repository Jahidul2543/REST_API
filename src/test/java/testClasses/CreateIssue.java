package testClasses;

import com.easybix.utils.TestUtils;
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

import static io.restassured.RestAssured.given;

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

		// URI = BaseURI + Resource = http//:localhost:8081/rest/api/2/issue/
		String URI = URL.getEndPoint("/rest/api/2/issue/");

		// Request Body
		String createIssuePayLoad = PayloadGenerator.generatePayLoadString("CreateBug.json");

		response = RESTCalls.POSTRequest(URI, createIssuePayLoad, sessionID);

		BaseAssertion.verifyStatusCode(response, 201);

		String body = TestUtils.getResponseBody(response);
		log.info("statusMessage: "+ body);

		BaseAssertion.verifyResonseBodyByJsonPath( response,"key","RAT-43");

		log.info("****Issue created successfully****");
		log.info("****Test Passed****");


	}

}
