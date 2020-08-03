
package com.org.godspeed.response_JsonS.GetSchedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Timing implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;

    /**
     * No args constructor for use in serialization
     *
     */

    /**
     * @param from
     * @param id
     * @param to
     */
    public Timing(String id, String from, String to) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
