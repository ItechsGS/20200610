
package com.org.godspeed.response_JsonS.TrainingProgramDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PillarExerciseStatus implements Serializable {

    @SerializedName("phase")
    @Expose
    private String phase;
    @SerializedName("pillar_image")
    @Expose
    private String pillarImage;
    @SerializedName("pillar_name")
    @Expose
    private String pillarName;
    @SerializedName("pillar")
    @Expose
    private String pillar;
    @SerializedName("week")
    @Expose
    private String week;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("total_excercise_completed")
    @Expose
    private String totalExcerciseCompleted;
    @SerializedName("total_excercise")
    @Expose
    private String totalExcercise;

    /**
     * No args constructor for use in serialization
     */
    public PillarExerciseStatus() {
    }

    /**
     * @param phase
     * @param pillarName
     * @param totalExcerciseCompleted
     * @param pillar
     * @param week
     * @param totalExcercise
     * @param pillarImage
     * @param day
     */
    public PillarExerciseStatus(String phase, String pillarImage, String pillarName, String pillar, String week, String day, String totalExcerciseCompleted, String totalExcercise) {
        super();
        this.phase = phase;
        this.pillarImage = pillarImage;
        this.pillarName = pillarName;
        this.pillar = pillar;
        this.week = week;
        this.day = day;
        this.totalExcerciseCompleted = totalExcerciseCompleted;
        this.totalExcercise = totalExcercise;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getPillarImage() {
        return pillarImage;
    }

    public void setPillarImage(String pillarImage) {
        this.pillarImage = pillarImage;
    }

    public String getPillarName() {
        return pillarName;
    }

    public void setPillarName(String pillarName) {
        this.pillarName = pillarName;
    }

    public String getPillar() {
        return pillar;
    }

    public void setPillar(String pillar) {
        this.pillar = pillar;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTotalExcerciseCompleted() {
        return totalExcerciseCompleted;
    }

    public void setTotalExcerciseCompleted(String totalExcerciseCompleted) {
        this.totalExcerciseCompleted = totalExcerciseCompleted;
    }

    public String getTotalExcercise() {
        return totalExcercise;
    }

    public void setTotalExcercise(String totalExcercise) {
        this.totalExcercise = totalExcercise;
    }

}
