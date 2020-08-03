
package com.org.godspeed.response_JsonS.athleteData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sport implements Serializable {

    @SerializedName("sport_id")
    @Expose
    private String sportId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sport_name")
    @Expose
    private String sportName;
    //private final static long serialVersionUID = 35845855662958214L;

    /**
     * No args constructor for use in serialization
     */
    public Sport() {
    }

    /**
     * @param id
     * @param sportId
     * @param sportName
     */
    public Sport(String sportId, String id, String sportName) {
        super();
        this.sportId = sportId;
        this.id = id;
        this.sportName = sportName;
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
