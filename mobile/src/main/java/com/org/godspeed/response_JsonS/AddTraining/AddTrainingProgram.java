
package com.org.godspeed.response_JsonS.AddTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AddTrainingProgram implements Serializable {

    //private final static long serialVersionUID = -3055845643973748879L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("program_name")
    @Expose
    private String programName;
    @SerializedName("create_folder_parent_id")
    @Expose
    private String createFolderParentId;
    @SerializedName("phase")
    @Expose
    private List<Phase> phase = null;


    @SerializedName("id_auto")
    @Expose
    private String idAuto;

    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("assign_program_id")
    @Expose
    private String assignProgramId;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;


    /**
     * No args constructor for use in serialization
     */

    /**
     * @param id
     * @param userId
     * @param phase
     * @param createFolderParentId
     * @param programName
     */
    public AddTrainingProgram(String id, String userId, String programName, String createFolderParentId, List<Phase> phase) {
        super();
        this.id = id;
        this.userId = userId;
        this.programName = programName;
        this.createFolderParentId = createFolderParentId;
        this.phase = phase;
    }

    public AddTrainingProgram(String userId, String programName, String idAuto, String id, String teamId, String assignProgramId, String athleteId, List<Phase> phase) {
        super();
        this.userId = userId;
        this.programName = programName;
        this.idAuto = idAuto;
        this.id = id;
        this.teamId = teamId;
        this.assignProgramId = assignProgramId;
        this.athleteId = athleteId;
        this.phase = phase;
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

    public String getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(String idAuto) {
        this.idAuto = idAuto;
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

    public String getAssignProgramId() {
        return assignProgramId;
    }

    public void setAssignProgramId(String assignProgramId) {
        this.assignProgramId = assignProgramId;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public List<Phase> getPhase() {
        return phase;
    }

    public void setPhase(List<Phase> phase) {
        this.phase = phase;
    }


}
