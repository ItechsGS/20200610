
package com.org.godspeed.response_JsonS.GetSchedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetSchedules implements Serializable {

    private final static long serialVersionUID = 8150570468216015508L;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("Team")
    @Expose
    private List<Team> team = null;

    /**
     * No args constructor for use in serialization
     *
     */

    /**
     * @param team
     * @param startDate
     */
    public GetSchedules(String startDate, List<Team> team) {
        super();
        this.startDate = startDate;
        this.team = team;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }

}
