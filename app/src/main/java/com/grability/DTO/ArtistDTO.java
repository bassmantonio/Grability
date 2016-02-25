package com.grability.DTO;

/**
 * This class represents an Artist
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class ArtistDTO {

    // properties
    private String Name;
    private String HRef;

    /**
     * Constructor
     * @param name Artist Name
     * @param HRef Artist Web Site
     */
    public ArtistDTO(String name, String HRef) {
        Name = name;
        this.HRef = HRef;
    }

    // Gets and sets Name Attribute
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    // Gets and sets HRef Attribute
    public String getHRef() {
        return HRef;
    }

    public void setHRef(String HRef) {
        this.HRef = HRef;
    }
}
