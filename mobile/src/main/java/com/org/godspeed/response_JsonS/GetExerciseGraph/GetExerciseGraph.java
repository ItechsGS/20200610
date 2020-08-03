
package com.org.godspeed.response_JsonS.GetExerciseGraph;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetExerciseGraph implements Serializable {

    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("team_values")
    @Expose
    private List<TeamValue> teamValues = null;

    @SerializedName("team_name")
    @Expose
    private String teamName;

    /**
     * No args constructor for use in serialization
     */

    public GetExerciseGraph(String teamId, List<TeamValue> teamValues, String teamName) {
        super();
        this.teamId = teamId;
        this.teamValues = teamValues;
        this.teamName = teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public List<TeamValue> getTeamValues() {
        return teamValues;
    }

    public void setTeamValues(List<TeamValue> teamValues) {
        this.teamValues = teamValues;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
