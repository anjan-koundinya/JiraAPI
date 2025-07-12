package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utility {

    public static RequestSpecification reqBase;
    public RequestSpecification requestspecBase() throws IOException {
        if(reqBase == null) {
            PrintStream log = new PrintStream(new FileOutputStream("OutputLog.txt"));
            reqBase = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
                    .setContentType(ContentType.JSON)
                    .addHeader("Authorization", getApiToken("APITOKEN"))
                    //.addQueryParam("id",project_Id)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return reqBase;
        }
        return reqBase;
    }



    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fil = new FileInputStream("C:\\Users\\saipk\\IdeaProjects\\JiraAPIFramework\\src\\test\\java\\resources\\global.properties");
        prop.load(fil);
        return prop.getProperty(key);
    }

    public static String getApiToken(String token) throws IOException {
        Properties prop = new Properties();
        FileInputStream fil1 = new FileInputStream("C:\\Users\\saipk\\IdeaProjects\\JiraAPIFramework\\src\\test\\java\\resources\\global.properties");
        prop.load(fil1);
        return prop.getProperty(token);
    }

    public static String getJsonPaths(Response res, String key)
    {
        String resBody = res.asString();
        JsonPath js = new JsonPath(resBody);
        return js.get(key).toString();
    }
}

