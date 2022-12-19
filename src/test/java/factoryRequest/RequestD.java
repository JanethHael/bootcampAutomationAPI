package factoryRequest;

import io.restassured.response.Response;

public interface RequestD {

    Response send(ParamRequest requestInfo);
    Response sendAuthToken(ParamRequest requestInfo);
}
