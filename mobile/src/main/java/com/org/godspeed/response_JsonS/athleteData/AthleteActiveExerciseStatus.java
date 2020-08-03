
package com.org.godspeed.response_JsonS.athleteData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AthleteActiveExerciseStatus implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("training_program_id")
    @Expose
    private Integer trainingProgramId;
    @SerializedName("phase")
    @Expose
    private Integer phase;
    @SerializedName("week")
    @Expose
    private Integer week;
    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("pillar")
    @Expose
    private Integer pillar;
    @SerializedName("athlete_id")
    @Expose
    private Integer athleteId;
    @SerializedName("exercise_type_id")
    @Expose
    private Integer exerciseTypeId;
    @SerializedName("exercise_id")
    @Expose
    private Integer exerciseId;
    @SerializedName("exercise_complete_status")
    @Expose
    private String exerciseCompleteStatus;
    //private final static long serialVersionUID = -8494372810546392097L;

    /**
     * No args constructor for use in serialization
     */
    public AthleteActiveExerciseStatus() {
    }

    /**
     * @param athleteId
     * @param id
     * @param exerciseTypeId
     * @param exerciseId
     * @param trainingProgramId
     * @param pillar
     * @param day
     * @param phase
     * @param week
     */
    public AthleteActiveExerciseStatus(Integer id, Integer trainingProgramId, Integer phase, Integer week, Integer day, Integer pillar, Integer athleteId, Integer exerciseTypeId, Integer exerciseId, String exerciseCompleteStatus) {
        super();
        this.id = id;
        this.trainingProgramId = trainingProgramId;
        this.phase = phase;
        this.week = week;
        this.day = day;
        this.pillar = pillar;
        this.athleteId = athleteId;
        this.exerciseTypeId = exerciseTypeId;
        this.exerciseId = exerciseId;
        this.exerciseCompleteStatus = exerciseCompleteStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainingProgramId() {
        return trainingProgramId;
    }

    public void setTrainingProgramId(Integer trainingProgramId) {
        this.trainingProgramId = trainingProgramId;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getPillar() {
        return pillar;
    }

    public void setPillar(Integer pillar) {
        this.pillar = pillar;
    }

    public Integer getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Integer athleteId) {
        this.athleteId = athleteId;
    }

    public Integer getExerciseTypeId() {
        return exerciseTypeId;
    }

    public void setExerciseTypeId(Integer exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseCompleteStatus() {
        return exerciseCompleteStatus;
    }

    public void setExerciseCompleteStatus(String exerciseCompleteStatus) {
        this.exerciseCompleteStatus = exerciseCompleteStatus;
    }
}
