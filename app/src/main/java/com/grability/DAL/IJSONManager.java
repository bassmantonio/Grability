package com.grability.DAL;

/**
 * This interface is used to define Json Manager contract.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public interface IJSONManager {

    /**
     * Gets Json String of a service
     * @param url Service Url
     * @return Json String
     */
    String getJSONfromURL(String url);
}
