
package com.org.godspeed.response_JsonS.athleteData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Team implements Serializable {

    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("coache_id")
    @Expose
    private String coacheId;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("background_image")
    @Expose
    private String backgroundImage;
    @SerializedName("gym_account_id")
    @Expose
    private String gymAccountId;
    @SerializedName("last_modify_time")
    @Expose
    private String lastModifyTime;
    @SerializedName("sport_id")
    @Expose
    private String sportId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("teams_athlete_id")
    @Expose
    private String teamsAthleteId;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;
    @SerializedName("coach_id")
    @Expose
    private String coachId;
    //private final static long serialVersionUID = 2735403838917717697L;

    /**
     * No args constructor for use in serialization
     */
    public Team() {
    }

    /**
     * @param athleteId
     * @param teamName
     * @param gymAccountId
     * @param logo
     * @param sportId
     * @param coachId
     * @param coacheId
     * @param userId
     * @param backgroundImage
     * @param teamId
     * @param teamsAthleteId
     * @param lastModifyTime
     */
    public Team(String teamId, String teamName, String coacheId, String logo, String backgroundImage, String gymAccountId, String lastModifyTime, String sportId, String userId, String teamsAthleteId, String athleteId, String coachId) {
        super();
        this.teamId = teamId;
        this.teamName = teamName;
        this.coacheId = coacheId;
        this.logo = logo;
        this.backgroundImage = backgroundImage;
        this.gymAccountId = gymAccountId;
        this.lastModifyTime = lastModifyTime;
        this.sportId = sportId;
        this.userId = userId;
        this.teamsAthleteId = teamsAthleteId;
        this.athleteId = athleteId;
        this.coachId = coachId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCoacheId() {
        return coacheId;
    }

    public void setCoacheId(String coacheId) {
        this.coacheId = coacheId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getGymAccountId() {
        return gymAccountId;
    }

    public void setGymAccountId(String gymAccountId) {
        this.gymAccountId = gymAccountId;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeamsAthleteId() {
        return teamsAthleteId;
    }

    public void setTeamsAthleteId(String teamsAthleteId) {
        this.teamsAthleteId = teamsAthleteId;
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

}
