
package com.org.godspeed.response_JsonS.athleteData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssingProgramDetail implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("training_program_id")
    @Expose
    private String trainingProgramId;
    @SerializedName("assignprogram_date")
    @Expose
    private String assignprogramDate;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("program_name")
    @Expose
    private String programName;
    @SerializedName("create_folder_parent_id")
    @Expose
    private String createFolderParentId;
    @SerializedName("training_program_status")
    @Expose
    private String trainingProgramStatus;
    @SerializedName("gym_account_id")
    @Expose
    private String gymAccountId;
    @SerializedName("assign_program_id")
    @Expose
    private String assignProgramId;
    //private final static long serialVersionUID = 2127816012147434735L;

    /**
     * No args constructor for use in serialization
     */
    public AssingProgramDetail() {
    }

    /**
     * @param athleteId
     * @param id
     * @param gymAccountId
     * @param startDate
     * @param trainingProgramId
     * @param assignProgramId
     * @param userId
     * @param trainingProgramStatus
     * @param assignprogramDate
     * @param createFolderParentId
     * @param teamId
     * @param programName
     */
    public AssingProgramDetail(String id, String teamId, String trainingProgramId, String assignprogramDate, String startDate, String athleteId, String userId, String programName, String createFolderParentId, String trainingProgramStatus, String gymAccountId, String assignProgramId) {
        super();
        this.id = id;
        this.teamId = teamId;
        this.trainingProgramId = trainingProgramId;
        this.assignprogramDate = assignprogramDate;
        this.startDate = startDate;
        this.athleteId = athleteId;
        this.userId = userId;
        this.programName = programName;
        this.createFolderParentId = createFolderParentId;
        this.trainingProgramStatus = trainingProgramStatus;
        this.gymAccountId = gymAccountId;
        this.assignProgramId = assignProgramId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTrainingProgramId() {
        return trainingProgramId;
    }

    public void setTrainingProgramId(String trainingProgramId) {
        this.trainingProgramId = trainingProgramId;
    }

    public String getAssignprogramDate() {
        return assignprogramDate;
    }

    public void setAssignprogramDate(String assignprogramDate) {
        this.assignprogramDate = assignprogramDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getCreateFolderParentId() {
        return createFolderParentId;
    }

    public void setCreateFolderParentId(String createFolderParentId) {
        this.createFolderParentId = createFolderParentId;
    }

    public String getTrainingProgramStatus() {
        return trainingProgramStatus;
    }

    public void setTrainingProgramStatus(String trainingProgramStatus) {
        this.trainingProgramStatus = trainingProgramStatus;
    }

    public String getGymAccountId() {
        return gymAccountId;
    }

    public void setGymAccountId(String gymAccountId) {
        this.gymAccountId = gymAccountId;
    }

    public String getAssignProgramId() {
        return assignProgramId;
    }

    public void setAssignProgramId(String assignProgramId) {
        this.assignProgramId = assignProgramId;
    }

}
