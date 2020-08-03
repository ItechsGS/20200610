package com.org.godspeed.response_JsonS.GetExerciseGraph;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TeamValue implements Serializable {

    private final static long serialVersionUID = 7874488603725002243L;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("reps_avg")
    @Expose
    private String repsAvg;
    @SerializedName("weight_avg")
    @Expose
    private String weightAvg;

    /**
     * No args constructor for use in serialization
     *
     */


    /**
     * @param date
     * @param repsAvg
     * @param weightAvg
     */
    public TeamValue(String date, String repsAvg, String weightAvg) {
        super();
        this.date = date;
        this.repsAvg = repsAvg;
        this.weightAvg = weightAvg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRepsAvg() {
        return repsAvg;
    }

    public void setRepsAvg(String repsAvg) {
        this.repsAvg = repsAvg;
    }

    public String getWeightAvg() {
        return weightAvg;
    }

    public void setWeightAvg(String weightAvg) {
        this.weightAvg = weightAvg;
    }

}