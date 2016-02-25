package com.grability.DTO;

import java.util.Properties;

/**
 * This class represents a Link.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class LinkDTO {

    //Properties
    private String Rel;
    private String Type;
    private String HRef;

    /**
     * Empty Constructor.
     */
    public LinkDTO() {
    }

    /**
     * Constructor
     * @param rel Link Rel
     * @param type Link type
     * @param HRef Link URL
     */
    public LinkDTO(String rel, String type, String HRef) {
        Rel = rel;
        Type = type;
        this.HRef = HRef;
    }

    // Gets and sets Rel Attribute
    public String getRel() {
        return Rel;
    }

    public void setRel(String rel) {
        Rel = rel;
    }

    // Gets and sets Type Attribute
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    // Gets and sets HRef Attribute
    public String getHRef() {
        return HRef;
    }

    public void setHRef(String HRef) {
        this.HRef = HRef;
    }
}
