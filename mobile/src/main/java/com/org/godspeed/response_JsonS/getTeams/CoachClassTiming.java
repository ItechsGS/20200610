
package com.org.godspeed.response_JsonS.getTeams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CoachClassTiming implements Serializable {

    private final static long serialVersionUID = 8298942292894988080L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("team_team_id")
    @Expose
    private String teamTeamId;
    @SerializedName("coach_id")
    @Expose
    private String coachId;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;

    /**
     * No args constructor for use in serialization
     */
    public CoachClassTiming() {
    }

    /**
     * @param teamTeamId
     * @param from
     * @param id
     * @param to
     * @param coachId
     */
    public CoachClassTiming(String id, String teamTeamId, String coachId, String from, String to) {
        super();
        this.id = id;
        this.teamTeamId = teamTeamId;
        this.coachId = coachId;
        this.from = from;
        this.to = to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamTeamId() {
        return teamTeamId;
    }

    public void setTeamTeamId(String teamTeamId) {
        this.teamTeamId = teamTeamId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
