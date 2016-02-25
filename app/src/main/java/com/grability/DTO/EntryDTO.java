package com.grability.DTO;

import java.util.Vector;

/**
 * This class represents an Entry.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class EntryDTO {

    // Attributes
    private PriceDTO Price;
    private ContentTypeDTO ContentType;
    private LinkDTO Link;
    private IdDTO Id;
    private ArtistDTO Artist;
    private CategoryDTO Category;
    private ReleaseDateDTO ReleaseDate;
    private Vector<ImageDTO> Images;

    // Properties
    private String Name;
    private String Summary;
    private String Rights;
    private String Title;

    /**
     * Empty Constructor
     */
    public EntryDTO() {
        this.Images = new Vector<ImageDTO>();
    }

    // Gets and sets Price Attribute
    public PriceDTO getPrice() {
        return Price;
    }

    public void setPrice(PriceDTO price) {
        Price = price;
    }

    // Gets and sets ContentType Attribute
    public ContentTypeDTO getContentType() {
        return ContentType;
    }

    public void setContentType(ContentTypeDTO contentType) {
        ContentType = contentType;
    }

    // Gets and sets Link Attribute
    public LinkDTO getLink() {
        return Link;
    }

    public void setLink(LinkDTO link) {
        Link = link;
    }

    // Gets and sets Id Attribute
    public IdDTO getId() {
        return Id;
    }

    public void setId(IdDTO id) {
        Id = id;
    }

    // Gets and sets Artist Attribute
    public ArtistDTO getArtist() {
        return Artist;
    }

    public void setArtist(ArtistDTO artist) {
        Artist = artist;
    }

    // Gets and sets Category Attribute
    public CategoryDTO getCategory() {
        return Category;
    }

    public void setCategory(CategoryDTO category) {
        Category = category;
    }

    // Gets and sets ReleaseDate Attribute
    public ReleaseDateDTO getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(ReleaseDateDTO releaseDate) {
        ReleaseDate = releaseDate;
    }

    // Gets and sets Images Attribute
    public Vector<ImageDTO> getImages() {
        return Images;
    }

    public void setImages(Vector<ImageDTO> images) {
        Images = images;
    }

    // Gets and sets Name Attribute
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    // Gets and sets Summary Attribute
    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
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
}
