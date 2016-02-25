package com.grability.DAL.Implementation;

import com.grability.BLL.ConfigurationKeys;
import com.grability.DAL.IJSONManager;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import org.json.*;
import java.io.*;
import org.apache.http.*;
import org.apache.http.client.*;

/**
 * This class is used to  manage the service Json.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class JSONManager implements IJSONManager{

    // Sting for exceptions
    public static String getException = "";

    /**
     * Empty Constructor
     */
    public JSONManager() {
    }

    /**
     * Gets Json String of a service
     * @param url Service Url
     * @return Json String
     */
    public String getJSONfromURL(String url){
        InputStream is = null;
        String result = "";
        JSONObject json = new JSONObject();
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            //HttpPost httppost = new HttpPost(url);
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }catch(Exception e){
            result = ConfigurationKeys.ERROR_GETTING_JSON;
        }

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            result=sb.toString();
        } catch(Exception e){
            result = ConfigurationKeys.ERROR_GETTING_JSON;
        }

        return result;
    }



}
