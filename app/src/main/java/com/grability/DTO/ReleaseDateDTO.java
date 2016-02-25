package com.grability.DTO;

import java.util.Properties;

/**
 * This class represents application release date.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class ReleaseDateDTO {

    // Properties
    private String Label;
    private String formalDate;

    /**
     * Constructor
     * @param label Release Label
     * @param formalDate Release Formal Date.
     */
    public ReleaseDateDTO(String label, String formalDate) {
        Label = label;
        this.formalDate = formalDate;
    }

    // Gets and sets Label Attribute
    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    // Gets and sets FormalDate Attribute
    public String getFormalDate() {
        return formalDate;
    }

    public void setFormalDate(String formalDate) {
        this.formalDate = formalDate;
    }
}
