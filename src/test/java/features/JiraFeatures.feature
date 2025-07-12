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
    |BDD RestAssured Issue-1 |
    |BDD RestAssured Issue-2 |
    |BDD RestAssured Issue- New |


