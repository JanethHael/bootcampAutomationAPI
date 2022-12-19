package utils;

public class ApiConfiguration {
    // API TOKEN
    public static final String AUTHCHECK = GetProperties.getInstance().getstrHost() + "/api/authentication/isauthenticated.json";
    public static final String AUTHTOKEN = GetProperties.getInstance().getstrHost() + "/api/authentication/token.json";

    // API PROJECTS
    public static final String CREATE = GetProperties.getInstance().getstrHost() + "/api/projects.json";
    public static final String GET = GetProperties.getInstance().getstrHost() + "/api/projects/%s.json";
    public static final String UPDATE = GetProperties.getInstance().getstrHost() + "/api/projects/%s.json";
    public static final String DELETE= GetProperties.getInstance().getstrHost() + "/api/projects/%s.json";
}
