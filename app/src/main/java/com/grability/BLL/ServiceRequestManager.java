package com.grability.BLL;

import org.json.JSONException;
import com.grability.DAL.IJSONManager;
import com.grability.DAL.Implementation.JSONManager;
import com.grability.DTO.FeedDTO;
import com.grability.Activities.ActSplashScreen;

/**
 * This class is used to  manage the service Json request.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class ServiceRequestManager{

    // SplashScreen Instance
    ActSplashScreen activity;

    // Interface JSONManager
    IJSONManager objJson;

    // JSONManager instance.
    private JSONManager jsonManager;

    // Runnable Instance
    private Runnable threadService;

    /**
     * Constructor for initialize private properties
     * @param _activity; Sets the activity instance
     */
    public ServiceRequestManager(ActSplashScreen _activity) {
        this.activity = _activity;
    }

    /**
     * Method used for run a sub process for calling JSON Service
     */
    public void runService() throws JSONException{
        this.threadService = new Runnable() {
            @Override
            public void run() {
                try {
                    callJsonService();
                }
                catch (Exception e) {

                }
            }
        };
        Thread hilo = new Thread(threadService);
        hilo.start();

    }

    /**
     * This method calling Json Service and save the result in Shared Preferences
     */
    private void callJsonService() throws JSONException{
        this.objJson = new JSONManager();
        String request = objJson.getJSONfromURL(ConfigurationKeys.URL_SERVICE_JSON);
        activity.setJasonOnPreferences(request);
    }



}
