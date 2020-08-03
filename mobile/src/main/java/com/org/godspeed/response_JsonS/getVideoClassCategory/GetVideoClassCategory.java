
package com.org.godspeed.response_JsonS.getVideoClassCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetVideoClassCategory implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("category_icon")
    @Expose
    private String categoryIcon;
    @SerializedName("short_title")
    @Expose
    private String shortTitle;


    /**
     * @param categoryImage
     * @param categoryIcon
     * @param id
     * @param shortTitle
     * @param categoryName
     */
    public GetVideoClassCategory(String id, String categoryName, String categoryImage, String categoryIcon, String shortTitle) {
        super();
        this.id = id;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.categoryIcon = categoryIcon;
        this.shortTitle = shortTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

}
