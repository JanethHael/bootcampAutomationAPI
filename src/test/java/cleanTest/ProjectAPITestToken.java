package cleanTest;

import factoryRequest.FactoryRequest;
import factoryRequest.ParamRequest;
import io.restassured.response.Response;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ApiConfiguration;
import utils.GetProperties;

import static org.hamcrest.Matchers.equalTo;

public class ProjectAPITestToken {

    Response response;
    JSONObject body = new JSONObject();
    ParamRequest paramRequest = new ParamRequest();

    @BeforeEach
    public void verifyAuthenticated() {

        paramRequest.setUrl(ApiConfiguration.AUTHCHECK);
        paramRequest.setBody(body.toString());
        response = FactoryRequest.make("get").sendAuthToken(paramRequest);

        if (!response.getBody().as(Boolean.class)) {

            paramRequest.setUrl(ApiConfiguration.AUTHTOKEN);
            paramRequest.setBody(body.toString());
            response = FactoryRequest.make("get").send(paramRequest);
            response.then().body("UserEmail", equalTo(GetProperties.getInstance().getstrUser())).statusCode(200);

            GetProperties.getInstance().setstrAccessToken(
                    response.then().extract().path("TokenString")
            );
        }
    }

    @Test
    public void verifyCRUDProjectTest() {
        DateTime currentDate = DateTime.now();
        String datProjectName = "" + currentDate.getYear();
        datProjectName += currentDate.getMonthOfYear();
        datProjectName += currentDate.getDayOfMonth();
        datProjectName += "_TEST_API_MOJIX_BOOTCAMP";

        ///CREATE
        body.put("Content", datProjectName);
        paramRequest.setUrl(ApiConfiguration.CREATE);
        paramRequest.setBody(body.toString());
        response = FactoryRequest.make("post").sendAuthToken(paramRequest);
        response.then().body("Content", equalTo(body.get("Content"))).statusCode(200);
        int ProjectId = response.then().extract().path("Id");

        ///UPDATE
        datProjectName += "_UPDATED";
        body.put("Content", datProjectName);
        paramRequest.setUrl(String.format(ApiConfiguration.UPDATE, ProjectId));
        paramRequest.setBody(body.toString());
        response = FactoryRequest.make("put").sendAuthToken(paramRequest);
        response.then().body("Content", equalTo(body.get("Content"))).statusCode(200);

        ///GET
        paramRequest.setUrl(String.format(ApiConfiguration.GET, ProjectId));
        paramRequest.setBody(body.toString());
        response = FactoryRequest.make("get").sendAuthToken(paramRequest);
        response.then().body("Content", equalTo(body.get("Content"))).statusCode(200);

        ///DELETE
        paramRequest.setUrl(String.format(ApiConfiguration.DELETE, ProjectId));
        paramRequest.setBody(body.toString());
        response = FactoryRequest.make("delete").sendAuthToken(paramRequest);
        response.then().body("Content", equalTo(body.get("Content"))).statusCode(200);
    }
}
