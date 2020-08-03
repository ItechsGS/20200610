package com.org.godspeed.loginData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppMenu implements Serializable {

    private final static long serialVersionUID = -6031739258154419763L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("menu_icon")
    @Expose
    private String menuIcon;
    @SerializedName("menu_identifier")
    @Expose
    private String menuIdentifier;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("menu_sequence")
    @Expose
    private String menuSequence;
    @SerializedName("position")
    @Expose
    private String position;
    private int icon;
    /**
     * No args constructor for use in serialization
     *
     */


    /**
     * @param menuIcon
     * @param menuName
     * @param id
     * @param position
     * @param menuIdentifier
     * @param menuSequence
     * @param status
     */
    public AppMenu(String id, String menuName, String menuIcon, String menuIdentifier, String status, String menuSequence, String position) {
        super();
        this.id = id;
        this.menuName = menuName;
        this.menuIcon = menuIcon;
        this.menuIdentifier = menuIdentifier;
        this.status = status;
        this.menuSequence = menuSequence;
        this.position = position;
    }

    public AppMenu(String id, String menuName, int icon, String menuIdentifier, String status, String menuSequence, String position) {
        this.id = id;
        this.menuName = menuName;
        this.menuIcon = menuIcon;
        this.menuIdentifier = menuIdentifier;
        this.status = status;
        this.menuSequence = menuSequence;
        this.position = position;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuIdentifier() {
        return menuIdentifier;
    }

    public void setMenuIdentifier(String menuIdentifier) {
        this.menuIdentifier = menuIdentifier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMenuSequence() {
        return menuSequence;
    }

    public void setMenuSequence(String menuSequence) {
        this.menuSequence = menuSequence;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
