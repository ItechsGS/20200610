
package com.org.godspeed.response_JsonS.athleteData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectedAthleteGoal implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("goal_name")
    @Expose
    private String goalName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;
    @SerializedName("coach_id")
    @Expose
    private String coachId;
    @SerializedName("current_goal_id")
    @Expose
    private String currentGoalId;
    //private final static long serialVersionUID = 3459262745306136155L;

    /**
     * No args constructor for use in serialization
     */
    public SelectedAthleteGoal() {
    }

    /**
     * @param athleteId
     * @param position
     * @param id
     * @param coachId
     * @param image
     * @param currentGoalId
     * @param goalName
     */
    public SelectedAthleteGoal(String id, String goalName, String image, String position, String athleteId, String coachId, String currentGoalId) {
        super();
        this.id = id;
        this.goalName = goalName;
        this.image = image;
        this.position = position;
        this.athleteId = athleteId;
        this.coachId = coachId;
        this.currentGoalId = currentGoalId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getCurrentGoalId() {
        return currentGoalId;
    }

    public void setCurrentGoalId(String currentGoalId) {
        this.currentGoalId = currentGoalId;
    }

}
