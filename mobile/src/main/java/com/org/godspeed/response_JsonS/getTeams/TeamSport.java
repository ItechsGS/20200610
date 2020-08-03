
package com.org.godspeed.response_JsonS.getTeams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TeamSport implements Serializable {

    private final static long serialVersionUID = 7128712918310091501L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("team_team_id")
    @Expose
    private String teamTeamId;
    @SerializedName("sport_id")
    @Expose
    private String sportId;
    @SerializedName("sport_name")
    @Expose
    private String sportName;

    /**
     * No args constructor for use in serialization
     */
    public TeamSport() {
    }

    /**
     * @param teamTeamId
     * @param sportId
     * @param sportName
     * @param id
     */
    public TeamSport(String id, String teamTeamId, String sportId, String sportName) {
        super();
        this.id = id;
        this.teamTeamId = teamTeamId;
        this.sportId = sportId;
        this.sportName = sportName;
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

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

}
