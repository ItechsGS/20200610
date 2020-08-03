
package com.org.godspeed.loginData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectedAthleteLevel implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;
    @SerializedName("coach_id")
    @Expose
    private String coachId;
    @SerializedName("current_level")
    @Expose
    private String currentLevel;
    @SerializedName("athlete_level")
    @Expose
    private String athleteLevel;
    @SerializedName("athlete_level_image")
    @Expose
    private String athleteLevelImage;
    @SerializedName("position")
    @Expose
    private String position;
    //private final static long serialVersionUID = -8910112415288686656L;

    /**
     * No args constructor for use in serialization
     */
    public SelectedAthleteLevel() {
    }

    /**
     * @param position
     * @param athleteId
     * @param id
     * @param athleteLevel
     * @param coachId
     * @param athleteLevelImage
     * @param currentLevel
     */
    public SelectedAthleteLevel(String id, String athleteId, String coachId, String currentLevel, String athleteLevel, String athleteLevelImage, String position) {
        super();
        this.id = id;
        this.athleteId = athleteId;
        this.coachId = coachId;
        this.currentLevel = currentLevel;
        this.athleteLevel = athleteLevel;
        this.athleteLevelImage = athleteLevelImage;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getAthleteLevel() {
        return athleteLevel;
    }

    public void setAthleteLevel(String athleteLevel) {
        this.athleteLevel = athleteLevel;
    }

    public String getAthleteLevelImage() {
        return athleteLevelImage;
    }

    public void setAthleteLevelImage(String athleteLevelImage) {
        this.athleteLevelImage = athleteLevelImage;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
