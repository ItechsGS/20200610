
package com.org.godspeed.response_JsonS.AthleteClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Option implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("athlete_class_coach_id_auto")
    @Expose
    private String athleteClassCoachIdAuto;
    //private final static long serialVersionUID = 162997506579471667L;

    /**
     * No args constructor for use in serialization
     */
    public Option() {
    }

    /**
     * @param to
     * @param id
     * @param from
     * @param athleteClassCoachIdAuto
     */
    public Option(String id, String from, String to, String athleteClassCoachIdAuto) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.athleteClassCoachIdAuto = athleteClassCoachIdAuto;
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

    public String getAthleteClassCoachIdAuto() {
        return athleteClassCoachIdAuto;
    }

    public void setAthleteClassCoachIdAuto(String athleteClassCoachIdAuto) {
        this.athleteClassCoachIdAuto = athleteClassCoachIdAuto;
    }

}
