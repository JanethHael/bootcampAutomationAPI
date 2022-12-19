package factoryRequest;

public class FactoryRequest {
    public static RequestD make(String vStrType) {
        RequestD request;

        switch (vStrType) {
            case "post":
                request = new PostRequest();
                break;
            case "put":
                request = new PutRequest();
                break;
            case "delete":
                request = new DeleteRequest();
                break;
            default:
                request = new GetRequest();
                break;
        }

        return request;
    }
}
