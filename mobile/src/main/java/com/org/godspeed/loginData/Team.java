
package com.org.godspeed.loginData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//public class Team implements Serializable
//{
//
//    @SerializedName("team_id")
//    @Expose
//    private String teamId;
//    @SerializedName("coach_id")
//    @Expose
//    private String coachId;
//    @SerializedName("team_name")
//    @Expose
//    private String teamName;
//    @SerializedName("teams_athlete_id")
//    @Expose
//    private String teamsAthleteId;
//    //private final static long serialVersionUID = 4573899033044635449L;
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public Team() {
//    }
//
//    /**
//     *
//     * @param teamName
//     * @param coachId
//     * @param teamId
//     * @param teamsAthleteId
//     */
//    public Team(String teamId, String coachId, String teamName, String teamsAthleteId) {
//        super();
//        this.teamId = teamId;
//        this.coachId = coachId;
//        this.teamName = teamName;
//        this.teamsAthleteId = teamsAthleteId;
//    }
//
//    public String getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(String teamId) {
//        this.teamId = teamId;
//    }
//
//    public String getCoachId() {
//        return coachId;
//    }
//
//    public void setCoachId(String coachId) {
//        this.coachId = coachId;
//    }
//
//    public String getTeamName() {
//        return teamName;
//    }
//
//    public void setTeamName(String teamName) {
//        this.teamName = teamName;
//    }
//
//    public String getTeamsAthleteId() {
//        return teamsAthleteId;
//    }
//
//    public void setTeamsAthleteId(String teamsAthleteId) {
//        this.teamsAthleteId = teamsAthleteId;
//    }
//
//}


public class Team implements Serializable {

    @SerializedName("sport_id")
    @Expose
    private String sportId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sport_name")
    @Expose
    private String sportName;
    @SerializedName("team_id")
    @Expose

    private String teamId;
    @SerializedName("coach_id")
    @Expose
    private String coachId;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("teams_athlete_id")
    @Expose
    private String teamsAthleteId;
    //private final static long serialVersionUID = -6236801093869423443L;

    /**
     * No args constructor for use in serialization
     */
    public Team() {
    }

    /**
     * @param id
     * @param sportId
     * @param sportName
     */
    public Team(String sportId, String id, String sportName) {
        super();
        this.sportId = sportId;
        this.id = id;
        this.sportName = sportName;
    }

    public Team(String teamId, String coachId, String teamName, String teamsAthleteId) {
        super();
        this.teamId = teamId;
        this.coachId = coachId;
        this.teamName = teamName;
        this.teamsAthleteId = teamsAthleteId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamsAthleteId() {
        return teamsAthleteId;
    }

    public void setTeamsAthleteId(String teamsAthleteId) {
        this.teamsAthleteId = teamsAthleteId;
    }

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }


}
