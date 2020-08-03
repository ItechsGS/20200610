package com.org.godspeed.response_JsonS.getVideoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LiveClassCheckIn implements Serializable {
    @SerializedName("checkin_id")
    @Expose
    private String checkinid;

    @SerializedName("video_auto_id")
    @Expose
    private String video_auto_id;
    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("last_name")
    @Expose
    private String last_name;

    @SerializedName("first_name")
    @Expose
    private String first_name;

    @SerializedName("user_image")
    @Expose
    private String user_image;


    public LiveClassCheckIn(String checkinid, String video_auto_id, String user_id, String last_name, String first_name, String user_image) {
        this.checkinid = checkinid;
        this.video_auto_id = video_auto_id;
        this.user_id = user_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.user_image = user_image;
    }

    public String getCheckinid() {
        return checkinid;
    }

    public void setCheckinid(String checkinid) {
        this.checkinid = checkinid;
    }

    public String getVideo_auto_id() {
        return video_auto_id;
    }

    public void setVideo_auto_id(String video_auto_id) {
        this.video_auto_id = video_auto_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }
}
