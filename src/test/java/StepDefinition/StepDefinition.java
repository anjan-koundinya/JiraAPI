package StepDefinition;

import POJO.Fields;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utility {
    RequestSpecification response;
    Response GetProjectResponse;
    static String projectID;
    static String IssueType;
    TestDataBuild data = new TestDataBuild();

    @Given("Get Project {string}")
    public void get_project(String string) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        response = given().spec(requestspecBase());

    }

    @When("user calls {string} API with http {string} request")
    public void user_calls_api_with_http_request(String resource, String method) {
        // Write code here that turns the phrase above into concrete actions
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        if(method.equalsIgnoreCase("GET"))
        GetProjectResponse = response.when().get(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("POST")) {
            GetProjectResponse = response.when().post(resourceAPI.getResource());

        }

    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer value) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Integer.valueOf(GetProjectResponse.getStatusCode()),value);
    }

    @Then("To Get Project ID")
    public void to_get_project_id() {
        // Write code here that turns the phrase above into concrete actions

        projectID = getJsonPaths(GetProjectResponse, "values.id[0]");
        System.out.println(projectID);
    }

    @Given("Get Issue ID with queryParam")
    public void get_issue_id_with_query_param() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        //project = projectID;
        response = given().spec(requestspecBase()).queryParam("projectId", projectID);
    }

    @Then("To Get issue type ID")
    public void to_get_issue_type_id() {
        // Write code here that turns the phrase above into concrete actions
        IssueType = getJsonPaths(GetProjectResponse, "id[4]");
        System.out.println(IssueType);

    }
    @Given("The Create Issue payload {string}")
    public void the_create_issue_payload(String summary) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        response = given().spec(requestspecBase()).body(data.createIssuePayload(projectID,IssueType,summary));
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String id, String expectedID) {


        String IssueID = getJsonPaths(GetProjectResponse,id);
        System.out.println(IssueID);

    }
}

