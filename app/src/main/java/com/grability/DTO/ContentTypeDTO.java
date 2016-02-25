package com.grability.DTO;

import java.util.Properties;

/**
 * This class represents a content type
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class ContentTypeDTO {

    // Properties
    private String Term;
    private String Label;

    /**
     * Constructor
     * @param term Content Type Term
     * @param label Content Type Name
     */
    public ContentTypeDTO(String term, String label) {
        Term = term;
        Label = label;
    }

    // Gets and sets Term Attribute
    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    // Gets and sets Label Attribute
    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
