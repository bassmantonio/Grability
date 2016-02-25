package com.grability.BLL;

/**
 * This class has many configurations keys and values, used to change App behavior
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class ConfigurationKeys {

    // Configuration keys

    // SplashScreen Keys
    public static final long SPLASH_SCREEN_DELAY = 3000;

    // SharedPreferences Keys
    public static final String SHARED_PREFERENCES_STRING = "GrabilityPreferences";
    public static final String FIRST_TIME_KEY = "FirstTimeKey";
    public static final String JSON_STRING_KEY = "JSONString";

    // JSON Manage Keys
    public static final String ERROR_GETTING_JSON = "ERROR";
    public static final String URL_SERVICE_JSON = "https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json";

    // Bundle Keys
    public static final String APP_CATEGORY = "Category";
    public static final String APP_ID = "AppId";
}
