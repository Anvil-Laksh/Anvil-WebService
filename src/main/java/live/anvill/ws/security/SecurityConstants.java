package live.anvill.ws.security;

import live.anvill.ws.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME=864000000;
    public static final String TOKEN_PREFIX = "bearer ";
    public static final String HEADER_STRING="Authorization";
    public static final String SIGN_UP_URL ="/index";


    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties)SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }

}
