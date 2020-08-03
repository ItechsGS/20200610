
package com.org.godspeed.response_JsonS.GetSport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetSport implements Serializable {

    private final static long serialVersionUID = 4067795770717287113L;
    @SerializedName("sport_id")
    @Expose
    private String sportId;
    @SerializedName("sport_name")
    @Expose
    private String sportName;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     * No args constructor for use in serialization
     */
    public GetSport() {
    }

    /**
     * @param date
     * @param sportId
     * @param sportName
     */
    public GetSport(String sportId, String sportName, String date) {
        super();
        this.sportId = sportId;
        this.sportName = sportName;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
