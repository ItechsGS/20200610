
package com.org.godspeed.response_JsonS.GetWhiteBoardDatum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PillarDetail implements Serializable {

    private final static long serialVersionUID = -1605294296389462270L;
    @SerializedName("program_id")
    @Expose
    private String programId;
    @SerializedName("phase")
    @Expose
    private String phase;
    @SerializedName("week")
    @Expose
    private String week;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("pillar")
    @Expose
    private String pillar;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_auto")
    @Expose
    private String idAuto;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("assign_program_id")
    @Expose
    private String assignProgramId;
    @SerializedName("rpe")
    @Expose
    private String rpe;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;
    @SerializedName("piller_id")
    @Expose
    private String pillerId;
    @SerializedName("piller_name")
    @Expose
    private String pillerName;
    @SerializedName("pillar_image")
    @Expose
    private String pillarImage;

    /**
     * No args constructor for use in serialization
     */
    public PillarDetail() {
    }

    /**
     * @param phase
     * @param week
     * @param rpe
     * @param pillerName
     * @param idAuto
     * @param pillar
     * @param assignProgramId
     * @param athleteId
     * @param teamId
     * @param pillarImage
     * @param id
     * @param day
     * @param programId
     * @param pillerId
     */
    public PillarDetail(String programId, String phase, String week, String day, String pillar, String id, String idAuto, String teamId, String assignProgramId, String rpe, String athleteId, String pillerId, String pillerName, String pillarImage) {
        super();
        this.programId = programId;
        this.phase = phase;
        this.week = week;
        this.day = day;
        this.pillar = pillar;
        this.id = id;
        this.idAuto = idAuto;
        this.teamId = teamId;
        this.assignProgramId = assignProgramId;
        this.rpe = rpe;
        this.athleteId = athleteId;
        this.pillerId = pillerId;
        this.pillerName = pillerName;
        this.pillarImage = pillarImage;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPillar() {
        return pillar;
    }

    public void setPillar(String pillar) {
        this.pillar = pillar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(String idAuto) {
        this.idAuto = idAuto;
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

    public String getRpe() {
        return rpe;
    }

    public void setRpe(String rpe) {
        this.rpe = rpe;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getPillerId() {
        return pillerId;
    }

    public void setPillerId(String pillerId) {
        this.pillerId = pillerId;
    }

    public String getPillerName() {
        return pillerName;
    }

    public void setPillerName(String pillerName) {
        this.pillerName = pillerName;
    }

    public String getPillarImage() {
        return pillarImage;
    }

    public void setPillarImage(String pillarImage) {
        this.pillarImage = pillarImage;
    }

}
