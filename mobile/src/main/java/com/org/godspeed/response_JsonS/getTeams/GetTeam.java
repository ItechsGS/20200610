
package com.org.godspeed.response_JsonS.getTeams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetTeam implements Serializable {

    private final static long serialVersionUID = -6859252820496100L;
    @SerializedName("addedby_userid")
    @Expose
    private String addedbyUserid;
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
    @SerializedName("school_id")
    @Expose
    private String schoolId;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("Training_program_detail")
    @Expose
    private List<TrainingProgramDetail> trainingProgramDetail = null;
    @SerializedName("coach_class_timing")
    @Expose
    private List<CoachClassTiming> coachClassTiming = null;
    @SerializedName("team_sports")
    @Expose
    private List<TeamSport> teamSports = null;
    @SerializedName("team_color")
    @Expose
    private String teamColor;
    @SerializedName("team_coaches")
    @Expose
    private List<TeamCoach> teamCoaches = null;

    /**
     * No args constructor for use in serialization
     */
    public GetTeam() {
    }

    /**
     * @param teamName
     * @param backgroundImage
     * @param emailId
     * @param userId
     * @param gymAccountId
     * @param sportId
     * @param teamCoaches
     * @param coacheId
     * @param addedbyUserid
     * @param teamId
     * @param lastModifyTime
     * @param schoolId
     * @param logo
     * @param trainingProgramDetail
     * @param schoolName
     * @param teamSports
     * @param coachClassTiming
     * @param teamColor
     */
    public GetTeam(String addedbyUserid, String emailId, String teamId, String teamName, String coacheId, String logo, String backgroundImage, String gymAccountId, String lastModifyTime, String sportId, String userId, String schoolId, String teamColor, String schoolName, List<TrainingProgramDetail> trainingProgramDetail, List<CoachClassTiming> coachClassTiming, List<TeamSport> teamSports, List<TeamCoach> teamCoaches) {
        super();
        this.addedbyUserid = addedbyUserid;
        this.emailId = emailId;
        this.teamId = teamId;
        this.teamName = teamName;
        this.coacheId = coacheId;
        this.logo = logo;
        this.backgroundImage = backgroundImage;
        this.gymAccountId = gymAccountId;
        this.lastModifyTime = lastModifyTime;
        this.sportId = sportId;
        this.userId = userId;
        this.schoolId = schoolId;
        this.teamColor = teamColor;
        this.schoolName = schoolName;
        this.trainingProgramDetail = trainingProgramDetail;
        this.coachClassTiming = coachClassTiming;
        this.teamSports = teamSports;
        this.teamCoaches = teamCoaches;
    }

    public String getAddedbyUserid() {
        return addedbyUserid;
    }

    public void setAddedbyUserid(String addedbyUserid) {
        this.addedbyUserid = addedbyUserid;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<TrainingProgramDetail> getTrainingProgramDetail() {
        return trainingProgramDetail;
    }

    public void setTrainingProgramDetail(List<TrainingProgramDetail> trainingProgramDetail) {
        this.trainingProgramDetail = trainingProgramDetail;
    }

    public List<CoachClassTiming> getCoachClassTiming() {
        return coachClassTiming;
    }

    public void setCoachClassTiming(List<CoachClassTiming> coachClassTiming) {
        this.coachClassTiming = coachClassTiming;
    }

    public List<TeamSport> getTeamSports() {
        return teamSports;
    }

    public void setTeamSports(List<TeamSport> teamSports) {
        this.teamSports = teamSports;
    }

    public List<TeamCoach> getTeamCoaches() {
        return teamCoaches;
    }

    public void setTeamCoaches(List<TeamCoach> teamCoaches) {
        this.teamCoaches = teamCoaches;
    }

}
