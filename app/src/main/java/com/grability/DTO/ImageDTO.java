package com.grability.DTO;

/**
 * This class represents an Image Information.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class ImageDTO {

    // Properties
    private String URL;
    private String Height;

    /**
     * Constructor
     * @param URL Image URL
     * @param height Image height.
     */
    public ImageDTO(String URL, String height) {
        this.URL = URL;
        Height = height;
    }

    // Gets and sets URL Attribute
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    // Gets and sets Height Attribute
    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }
}
