package testClasses;

import com.easybix.RestAssuredCore.BaseAssertion;
import com.easybix.RestAssuredCore.BaseTest;
import com.easybix.RestAssuredCore.RESTCalls;
import com.easybix.utils.PayloadGenerator;
import com.easybix.utils.TestUtils;
import com.easybix.utils.URL;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditIssue {


    private String sessionID;
    Response response;

    private static Logger log = LogManager.getLogger(CreateIssue.class.getName());

    @BeforeMethod
    public void setUp() {
        sessionID = BaseTest.doLogin();
    }

    @Test
    public void editIssue() {
        log.info("Starting Create Issue Test");

        String URI = URL.getEndPoint("/rest/api/2/issue/RAT-43");

        String createIssuePayLaod = PayloadGenerator.generatePayLoadString("AssigningIssue.json");

        response = RESTCalls.PUTRequest(URI, createIssuePayLaod, sessionID);

        BaseAssertion.verifyStatusCode(response, 204);
        //String body = TestUtils.getResponseBody(response);
       // log.info("statusMessage: "+ body);


        log.info("****Issue edited successfully****");
        log.info("****Test Passed****");


    }

}
