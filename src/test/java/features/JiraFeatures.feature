Feature: To Validate JIRA API's
@GetProject @Reg @Pos
Scenario: To Get all Project Details
  Given Get Project "BaseURI"
  When user calls "GetProjects" API with http "GET" request
  Then the API call got success with status code 200
  And To Get Project ID
  #And "<key>" is equal to "<value>"

@GetIssueType @Reg @Pos
Scenario: To Get all issue type
  Given Get Issue ID with queryParam
  When user calls "GetIssueTypes" API with http "GET" request
  Then the API call got success with status code 200
  And To Get issue type ID


@CreateIssue @Reg @Pos
Scenario: To Create an Issue
  Given The Create Issue payload "summary"
  When user calls "CreateIssue" API with http "POST" request
  Then the API call got success with status code 201
  Then "id" in response body is "issueID"

  @CreateIssue @Reg @Negative
  Scenario: To Create an Issue
    Given To Create Issue without summary
    When user calls "CreateIssue" API with http "POST" request
    Then the API call returns with status code 400




  @GetIssue @Reg @Pos
  Scenario: To Get Issue Details
    Given Get Created "Issue ID"
    When user calls "GetIssue" API with http "GET" request
    Then the API call got success with status code 200

   @DeleteIssue @Reg @Pos
   Scenario: To Delete an issue
    Given Get Created "Issue ID"
    When user calls "DeleteIssue" API with http "DELETE" request
    Then the API call got success with status code 204

  @DeleteIssue @Reg @Negative
  Scenario: To Delete an issue
    Given Get Created "Issue ID"
    When user calls "DeleteIssue" API with http "DELETE" request
    Then the API call got success with status code 404

  @GetIssue @Reg @Negative
  Scenario: To Get Issue Details
    Given Get Created "Issue ID"
    When user calls "GetIssue" API with http "GET" request
    Then the API call got success with status code 404

#Future Features:
  #1 Add Feature to Update issue
  #2 Change Priority, Status, Assigne, Watcher




