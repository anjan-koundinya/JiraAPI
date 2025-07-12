package resources;

import POJO.AllFields;
import POJO.Fields;
import POJO.IssueType;
import POJO.ProjectID;

public class TestDataBuild {

    public AllFields createIssuePayload(String project, String IssueType, String summary) {

        AllFields fin = new AllFields();
        Fields data = new Fields();
        IssueType Itype = new IssueType();
        ProjectID pType = new ProjectID();

        Itype.setId(IssueType);
        pType.setId(project);
        data.setProject(pType);
        data.setIssuetype(Itype);
        data.setSummary(summary);
        fin.setFields(data);
        return fin;


    }
}
