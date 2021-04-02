package config;


import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    static final ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());


    //domain name
    public static String SOCIAL_PLACE_SERVER = config.getUrlCism();
    public static String SOCIAL_PLACE_CORE_SERVER = config.getUrlCore();

    // path
    public static String COSIAL_PLACE_PATH = "gql/";
    public static String Take_Token_Path = "oauth/token";

    // endPoint
    public static String COSIAL_PLACE_ENDPOINT_SEARCH = "auth";
    public static String COSIAL_PLACE_PATH_CORE = "graphql";


    public static String getBaseURL() {
        return ConfigHelper.SOCIAL_PLACE_SERVER;
    }

    public static String getBaseURLCore() {
        return ConfigHelper.SOCIAL_PLACE_CORE_SERVER;
    }
    public static String getPath() {
        return ConfigHelper.COSIAL_PLACE_PATH;
    }

    public static String getTokenPathCore() {
        return ConfigHelper.Take_Token_Path;
    }

    public static String getEndPoint() {
        return ConfigHelper.COSIAL_PLACE_ENDPOINT_SEARCH;
    }

    public static String getEndPointCore() {
        return ConfigHelper.COSIAL_PLACE_PATH_CORE;
    }


    // end point

}