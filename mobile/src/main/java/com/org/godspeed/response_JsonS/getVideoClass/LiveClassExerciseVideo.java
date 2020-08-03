
package com.org.godspeed.response_JsonS.getVideoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LiveClassExerciseVideo implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("live_exercise_class_category_id_auto")
    @Expose
    private String liveExerciseClassCategoryIdAuto;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("video_name")
    @Expose
    private String videoName;

    @SerializedName("is_live")
    @Expose
    private String is_live;


    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("thumbnail_image")
    @Expose
    private String thumbnail_image;

    @SerializedName("video_meeting_title")
    @Expose
    private String video_meeting_title;

    @SerializedName("start_time")
    @Expose
    private String start_time;

    @SerializedName("end_time")
    @Expose
    private String end_time;

    @SerializedName("liveclass_checkin")
    @Expose
    private List<LiveClassCheckIn> liveClassCheckInList;


    @SerializedName("live_class_coach")
    @Expose
    private List<LiveClassCoach> liveClassCoachList;


    /**
     * No args constructor for use in serialization
     *
     */

    /**
     * @param id
     * @param liveExerciseClassCategoryIdAuto
     * @param date
     * @param videoName
     * @param is_live
     * @param password
     * @param thumbnail_image
     * @param video_meeting_title
     * @param start_time
     * @param end_time
     * @param liveClassCheckInList
     * @param liveClassCoachList
     */
    public LiveClassExerciseVideo(String id, String liveExerciseClassCategoryIdAuto, String date, String videoName, String is_live, String password, String thumbnail_image, String video_meeting_title, String start_time, String end_time, List<LiveClassCheckIn> liveClassCheckInList, List<LiveClassCoach> liveClassCoachList) {
        super();
        this.id = id;
        this.liveExerciseClassCategoryIdAuto = liveExerciseClassCategoryIdAuto;
        this.date = date;
        this.videoName = videoName;
        this.is_live = is_live;
        this.password = password;
        this.thumbnail_image = thumbnail_image;
        this.video_meeting_title = video_meeting_title;
        this.start_time = start_time;
        this.end_time = end_time;
        this.liveClassCheckInList = liveClassCheckInList;
        this.liveClassCoachList = liveClassCoachList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiveExerciseClassCategoryIdAuto() {
        return liveExerciseClassCategoryIdAuto;
    }

    public void setLiveExerciseClassCategoryIdAuto(String liveExerciseClassCategoryIdAuto) {
        this.liveExerciseClassCategoryIdAuto = liveExerciseClassCategoryIdAuto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getIs_live() {
        return is_live;
    }

    public void setIs_live(String is_live) {
        this.is_live = is_live;
    }

    public String getThumbnail_image() {
        return thumbnail_image;
    }

    public void setThumbnail_image(String thumbnail_image) {
        this.thumbnail_image = thumbnail_image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVideo_meeting_title() {
        return video_meeting_title;
    }

    public void setVideo_meeting_title(String video_meeting_title) {
        this.video_meeting_title = video_meeting_title;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public List<LiveClassCheckIn> getLiveClassCheckInList() {
        return liveClassCheckInList;
    }

    public void setLiveClassCheckInList(List<LiveClassCheckIn> liveClassCheckInList) {
        this.liveClassCheckInList = liveClassCheckInList;
    }

    public List<LiveClassCoach> getLiveClassCoachList() {
        return liveClassCoachList;
    }

    public void setLiveClassCoachList(List<LiveClassCoach> liveClassCoachList) {
        this.liveClassCoachList = liveClassCoachList;
    }
}
