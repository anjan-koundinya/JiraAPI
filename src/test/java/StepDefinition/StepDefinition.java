package StepDefinition;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StepDefinition extends Utility {
    private static final Log log = LogFactory.getLog(StepDefinition.class);
    RequestSpecification response;
    Response GetProjectResponse;
    static String projectID;
    static String IssueType;
    TestDataBuild data = new TestDataBuild();
    static String IssueID;
    String IssueKey;
    int size;

    @Given("Get Project {string}")
    public void get_project(String string) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        response = given().spec(requestspecBase());

    }

    @When("user calls {string} API with http {string} request")
    public void user_calls_api_with_http_request(String resource, String method) {
        // Write code here that turns the phrase above into concrete actions
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(method + resourceAPI.getResource());
        if(method.equalsIgnoreCase("GET"))
        GetProjectResponse = response.when().get(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("POST"))
            GetProjectResponse = response.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("DELETE"))
            GetProjectResponse = response.when().delete(resourceAPI.getResource());




    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer value) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Integer.valueOf(GetProjectResponse.getStatusCode()),value);
    }

    @Then("To Get Project ID")
    public void to_get_project_id() {
        // Write code here that turns the phrase above into concrete actions

        //projectID = getJsonPaths(GetProjectResponse, "values.id[0]");

        size = Integer.parseInt(getJsonPaths(GetProjectResponse, "values.size()"));
        for(int i=0;i<= size;i++) {
            if (getJsonPaths(GetProjectResponse, "values.name[" + i + "]").equalsIgnoreCase("API Test 1")) {
            /*{
                projectID = getJsonPaths(GetProjectResponse, "values.id[i]");
            }*/

                projectID = getJsonPaths(GetProjectResponse, "values.id[" + i + "]");
                break;
            }
        }

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
        //IssueType = getJsonPaths(GetProjectResponse, "id[4]");
        //System.out.println(IssueType);

        //for(int i=0;i< size;i++) {
            //if (getJsonPaths(GetProjectResponse, "[" + i + "].name").toString())) {
            /*{
                projectID = getJsonPaths(GetProjectResponse, "values.id[i]");
            }*/
                //System.out.println(getJsonPaths(GetProjectResponse, "[" + i + "].name"));
                /*System.out.println(getJsonPaths(GetProjectResponse, "id[" + i + "]"));
                break;*/

        //int size = getJsonPaths(GetProjectResponse.asString(), "").size();

        for (int i = 0;; i++) {
            String name = getJsonPaths(GetProjectResponse, "[" + i + "].name");
            if (name != null && name.equalsIgnoreCase("Bug")) {
                IssueType = getJsonPaths(GetProjectResponse, "[" + i + "].id");
                break;
            }

        }
    }
    @Given("The Create Issue payload {string}")
    public void the_create_issue_payload(String summary) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        response = given().spec(requestspecBase()).body(data.createIssuePayload(projectID,IssueType,summary));
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String id, String expectedID) {


        IssueID = getJsonPaths(GetProjectResponse,"id");
        IssueKey = getJsonPaths(GetProjectResponse,"key");
        GetProjectResponse.then().assertThat().body(matchesJsonSchemaInClasspath("user-schema.json"));

    }

    @Given("Get Created {string}")
    public void get_created(String Issue) throws IOException, InterruptedException {
        // Write code here that turns the phrase above into concrete actions
       // Thread.sleep(5000);
        response = given().spec(requestspecBase()).pathParams("issueIdOrKey",IssueID);

    }

    @Then("Successfully Deleted with status code {int}")
    public void successfully_deleted_with_status_code(Integer code) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions

        assertEquals(Integer.valueOf(GetProjectResponse.getStatusCode()),code);
       // Thread.sleep(3000);
        System.out.println(GetProjectResponse.getStatusCode());
        //assertEquals(Integer.valueOf(GetProjectResponse.getStatusCode()),code);
    }
}

