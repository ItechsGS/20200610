
package com.org.godspeed.response_JsonS.AddTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Phase implements Serializable {

    //private final static long serialVersionUID = 1215319889520485175L;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("week")
    @Expose
    private List<Week> week = null;

    /**
     * No args constructor for use in serialization
     */
    public Phase() {
    }

    /**
     * @param number
     * @param week
     */
    public Phase(String number, List<Week> week) {
        super();
        this.number = number;
        this.week = week;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Week> getWeek() {
        return week;
    }

    public void setWeek(List<Week> week) {
        this.week = week;
    }

}
