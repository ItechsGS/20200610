
package com.org.godspeed.utility;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Training_program_detail implements Serializable {

    //private final static long serialVersionUID = 7651143229094419695L;
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
    @SerializedName("assign_program_id")
    @Expose
    private String assignProgramId;

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

    public String getAssignProgramId() {
        return assignProgramId;
    }

    public void setAssignProgramId(String assignProgramId) {
        this.assignProgramId = assignProgramId;
    }

}
