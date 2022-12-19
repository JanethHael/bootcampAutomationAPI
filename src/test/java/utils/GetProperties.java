package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {

    private static GetProperties instance = null;

    private String strHost;
    private String strUser;
    private String strPwd;
    private String strAccessToken;

    public String getstrHost() {
        return strHost;
    }

    public void setstrHost(String strHost) {
        this.strHost = strHost;
    }

    public String getstrUser() {
        return strUser;
    }

    public void setstrUser(String strUser) {
        this.strUser = strUser;
    }

    public String getstrPwd() {
        return strPwd;
    }

    public void setstrPwd(String strPwd) {
        this.strPwd = strPwd;
    }

    public String getstrAccessToken() {
        return strAccessToken;
    }

    public void setstrAccessToken(String strAccessToken) {
        this.strAccessToken = strAccessToken;
    }

    private GetProperties() {

        Properties properties = new Properties();
        String strNameFile = "conection.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(strNameFile);

        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        strHost = properties.getProperty("host");
        strUser = properties.getProperty("user");
        strPwd = properties.getProperty("pwd");
        strAccessToken = "";
    }

    public static GetProperties getInstance() {
        if (instance == null)
            instance = new GetProperties();
        return instance;
    }
}

