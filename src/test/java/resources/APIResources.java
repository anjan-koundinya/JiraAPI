package resources;

public enum APIResources {

    GetProjects("/rest/api/2/project/search"),
    GetIssueTypes("/rest/api/2/issuetype/project"),
    CreateIssue("/rest/api/2/issue");
    private String resource;
    APIResources(String resource) {

        this.resource  = resource;
    }

    public String getResource()
    {
        return resource;
    }
}

