package com.grability.DTO;

import java.util.Vector;

/**
 * This class represents root Feed
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class FeedDTO {

    // Attributes
    private AuthorDTO Author;
    private Vector<EntryDTO> Entries;
    private Vector<LinkDTO> Links;

    // Properties
    private String Updated;
    private String Rights;
    private String Title;
    private String Icon;
    private String Id;

    /**
     * Singleton instance
      */
    private static FeedDTO instance = null;

    /**
     * Empty Constructor
     */
    public FeedDTO() {
        this.Entries = new Vector<EntryDTO>();
        this.Links = new Vector<LinkDTO>();
    }

    /**
     * Gets Singleton Instance.
     * @return Feed instance.
     */
    public static FeedDTO getInstance() {
        if(instance == null) {
            instance = new FeedDTO();
        }
        return instance;
    }

    /**
     * Sets Singleton Instance.
     * @param _instance Feed instance.
     */
    public static void setInstance(FeedDTO _instance) {
        if(instance == null) {
            instance = _instance;
        }
    }


    // Gets and sets Author Attribute
    public AuthorDTO getAuthor() {
        return Author;
    }

    public void setAuthor(AuthorDTO author) {
        Author = author;
    }

    // Gets and sets Entries Attribute
    public Vector<EntryDTO> getEntries() {
        return Entries;
    }

    public void setEntries(Vector<EntryDTO> entries) {
        Entries = entries;
    }

    // Gets and sets Links Attribute
    public Vector<LinkDTO> getLinks() {
        return Links;
    }

    public void setLinks(Vector<LinkDTO> links) {
        Links = links;
    }

    // Gets and sets Updated Attribute
    public String getUpdated() {
        return Updated;
    }

    public void setUpdated(String updated) {
        Updated = updated;
    }

    // Gets and sets Rights Attribute
    public String getRights() {
        return Rights;
    }

    public void setRights(String rights) {
        Rights = rights;
    }

    // Gets and sets Title Attribute
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    // Gets and sets Icon Attribute
    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    // Gets and sets Id Attribute
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
