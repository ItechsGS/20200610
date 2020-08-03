package com.org.godspeed.loginData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PillarMenuDetail implements Serializable {

    private final static long serialVersionUID = 1862231107196046810L;
    @SerializedName("piller_id")
    @Expose
    private String pillerId;
    @SerializedName("piller_name")
    @Expose
    private String pillerName;
    @SerializedName("pillar_image")
    @Expose
    private String pillarImage;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */


    /**
     * @param pillerName
     * @param pillarImage
     * @param pillerId
     * @param status
     */
    public PillarMenuDetail(String pillerId, String pillerName, String pillarImage, String status) {
        super();
        this.pillerId = pillerId;
        this.pillerName = pillerName;
        this.pillarImage = pillarImage;
        this.status = status;
    }

    public String getPillerId() {
        return pillerId;
    }

    public void setPillerId(String pillerId) {
        this.pillerId = pillerId;
    }

    public String getPillerName() {
        return pillerName;
    }

    public void setPillerName(String pillerName) {
        this.pillerName = pillerName;
    }

    public String getPillarImage() {
        return pillarImage;
    }

    public void setPillarImage(String pillarImage) {
        this.pillarImage = pillarImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
