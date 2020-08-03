
package com.org.godspeed.response_JsonS.ExerciseNameList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Set implements Serializable {

    private final static long serialVersionUID = 286200361540632018L;
    @SerializedName("exercise_sets_id")
    @Expose
    private String exerciseSetsId;
    @SerializedName("exercise_id")
    @Expose
    private String exerciseId;
    @SerializedName("set_name")
    @Expose
    private String setName;
    @SerializedName("tempo")
    @Expose
    private String tempo;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("reps")
    @Expose
    private String reps;
    @SerializedName("gym_account_id")
    @Expose
    private String gymAccountId;
    @SerializedName("last_modify_time")
    @Expose
    private String lastModifyTime;

    /**
     * No args constructor for use in serialization
     */
    public Set() {
    }

    /**
     * @param setName
     * @param exerciseSetsId
     * @param reps
     * @param exerciseId
     * @param lastModifyTime
     * @param tempo
     * @param weight
     * @param gymAccountId
     */
    public Set(String exerciseSetsId, String exerciseId, String setName, String tempo, String weight, String reps, String gymAccountId, String lastModifyTime) {
        super();
        this.exerciseSetsId = exerciseSetsId;
        this.exerciseId = exerciseId;
        this.setName = setName;
        this.tempo = tempo;
        this.weight = weight;
        this.reps = reps;
        this.gymAccountId = gymAccountId;
        this.lastModifyTime = lastModifyTime;
    }

    public String getExerciseSetsId() {
        return exerciseSetsId;
    }

    public void setExerciseSetsId(String exerciseSetsId) {
        this.exerciseSetsId = exerciseSetsId;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getGymAccountId() {
        return gymAccountId;
    }

    public void setGymAccountId(String gymAccountId) {
        this.gymAccountId = gymAccountId;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

}
