package com.grability.DTO;

/**
 * This class represents a Category
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class CategoryDTO {

    // properties
    private String Id;
    private String Term;
    private String Scheme;
    private String Label;

    /**
     * Constructor
     * @param id Category Id
     * @param term Category term
     * @param scheme Category scheme
     * @param label Category name
     */
    public CategoryDTO(String id, String term, String scheme, String label) {
        Id = id;
        Term = term;
        Scheme = scheme;
        Label = label;
    }

    // Gets and sets Id Attribute
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    // Gets and sets Term Attribute
    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    // Gets and sets Scheme Attribute
    public String getScheme() {
        return Scheme;
    }

    public void setScheme(String scheme) {
        Scheme = scheme;
    }

    // Gets and sets Label Attribute
    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
