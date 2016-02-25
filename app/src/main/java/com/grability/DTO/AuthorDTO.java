package com.grability.DTO;

/**
 * This class represents an Author.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class AuthorDTO {

    // properties
    private String Name;
    private String Uri;

    // Gets and sets Name Attribute
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    // Gets and sets Uri Attribute
    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }
}
