
package com.org.godspeed.response_JsonS.HelpScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Setting implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("bg_color")
    @Expose
    private String bgColor;
    @SerializedName("navbar_color")
    @Expose
    private String navbarColor;
    @SerializedName("text_color")
    @Expose
    private String textColor;
    @SerializedName("section_color")
    @Expose
    private String sectionColor;
    @SerializedName("user_id")
    @Expose
    private String userId;
    //  //    //private final static long serialVersionUID= 6593764379935334186L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getNavbarColor() {
        return navbarColor;
    }

    public void setNavbarColor(String navbarColor) {
        this.navbarColor = navbarColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getSectionColor() {
        return sectionColor;
    }

    public void setSectionColor(String sectionColor) {
        this.sectionColor = sectionColor;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
