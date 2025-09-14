Feature: To Validate JIRA API's
@GetProject @Reg
Scenario: To Get all Project Details
  Given Get Project "BaseURI"
  When user calls "GetProjects" API with http "GET" request
  Then the API call got success with status code 200
  And To Get Project ID
  #And "<key>" is equal to "<value>"

@GetIssueType @Reg
Scenario: To Get all issue type
  Given Get Issue ID with queryParam
  When user calls "GetIssueTypes" API with http "GET" request
  Then the API call got success with status code 200
  And To Get issue type ID


@CreateIssue @Reg
Scenario Outline: To Create an Issue
  Given The Create Issue payload "<summary>"
  When user calls "CreateIssue" API with http "POST" request
  Then the API call got success with status code 201
  Then "id" in response body is "issueID"

  Examples:

    |summary	|
    |BDD RestAssured Issue-Project API |

  @DeleteIssue
  Scenario: To Delete an issue
    Given Get Created "Issue ID"
    When user calls "DeleteIssue" API with http "DELETE" request
    Then the API call got success with status code 204


  @GetIssue
  Scenario: To Get Issue Details
    Given Get Created "Issue ID"
    When user calls "GetIssue" API with http "GET" request
    Then the API call got success with status code 404





