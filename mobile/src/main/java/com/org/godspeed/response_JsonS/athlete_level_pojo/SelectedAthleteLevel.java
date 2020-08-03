
package com.org.godspeed.response_JsonS.athlete_level_pojo;

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

    public SelectedAthleteLevel(String context, String athleteID, String coachID, String athleteLevel, String athleteLevelImage) {
        this.position = athleteLevel;
        this.athleteId = athleteID;
        this.coachId = coachID;
        this.athleteLevel = athleteLevel;
        this.athleteLevelImage = athleteLevelImage;
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
