package com.grability.DTO;

import java.util.Properties;

/**
 * This class represents an App Id.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class IdDTO {

    // Properties
    private String Label;
    private String BundleId;
    private String Id;

    /**
     * Constructor
     * @param label ID Label
     * @param bundleId ID Bundle
     * @param id ID
     */
    public IdDTO(String label, String bundleId, String id) {
        Label = label;
        BundleId = bundleId;
        Id = id;
    }

    // Gets and sets Label Attribute
    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    // Gets and sets BundleId Attribute
    public String getBundleId() {
        return BundleId;
    }

    public void setBundleId(String bundleId) {
        BundleId = bundleId;
    }

    // Gets and sets Id Attribute
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
