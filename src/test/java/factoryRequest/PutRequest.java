package factoryRequest;

import io.restassured.response.Response;
import utils.GetProperties;

import static io.restassured.RestAssured.given;

public class PutRequest implements RequestD {
    @Override
    public Response send(ParamRequest requestInfo) {
        Response response = given()
                .auth()
                .preemptive()
                .basic(GetProperties.getInstance().getstrUser(), GetProperties.getInstance().getstrPwd())
                .body(requestInfo.getBody())
                .log().all()
                .when()
                .put(requestInfo.getUrl());

        response.then().log().all();

        return response;
    }

    @Override
    public Response sendAuthToken(ParamRequest requestInfo) {
        Response response = given()
                .header("token", GetProperties.getInstance().getstrAccessToken())
                .body(requestInfo.getBody())
                .log().all()
                .when()
                .put(requestInfo.getUrl());

        response.then().log().all();

        return response;
    }
}
