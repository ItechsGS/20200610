package com.org.godspeed.response_JsonS.getVideoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LiveClassCoach implements Serializable {

    @SerializedName("live_class_coach_auto_id")
    @Expose
    private String live_class_coach_auto_id;

    @SerializedName("live_class_exercise_category_video_auto_id")
    @Expose
    private String live_class_exercise_category_video_auto_id;

    @SerializedName("coach_id")
    @Expose
    private String coach_id;

    @SerializedName("first_name")
    @Expose
    private String first_name;

    @SerializedName("last_name")
    @Expose
    private String last_name;

    @SerializedName("emailId")
    @Expose
    private String emailId;

    @SerializedName("user_image")
    @Expose
    private String user_image;


    public LiveClassCoach(String live_class_coach_auto_id, String live_class_exercise_category_video_auto_id, String coach_id, String first_name, String last_name, String emailId, String user_image) {
        this.live_class_coach_auto_id = live_class_coach_auto_id;
        this.live_class_exercise_category_video_auto_id = live_class_exercise_category_video_auto_id;
        this.coach_id = coach_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.emailId = emailId;
        this.user_image = user_image;
    }


    public String getLive_class_coach_auto_id() {
        return live_class_coach_auto_id;
    }

    public void setLive_class_coach_auto_id(String live_class_coach_auto_id) {
        this.live_class_coach_auto_id = live_class_coach_auto_id;
    }

    public String getLive_class_exercise_category_video_auto_id() {
        return live_class_exercise_category_video_auto_id;
    }

    public void setLive_class_exercise_category_video_auto_id(String live_class_exercise_category_video_auto_id) {
        this.live_class_exercise_category_video_auto_id = live_class_exercise_category_video_auto_id;
    }

    public String getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(String coach_id) {
        this.coach_id = coach_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

}

