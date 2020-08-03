
package com.org.godspeed.response_JsonS.athlete_level_pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AthleteLevel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("athlete_level")
    @Expose
    private String athleteLevel;
    @SerializedName("athlete_level_image")
    @Expose
    private String athleteLevelImage;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("values")
    @Expose
    private List<Value> values = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

}