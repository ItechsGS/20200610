
package com.org.godspeed.response_JsonS.ExerciseNameList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Exercise implements Serializable {

    @SerializedName("exercise_id")
    @Expose
    private String exerciseId;
    @SerializedName("exercise_name")
    @Expose
    private String exerciseName;
    @SerializedName("exercise_type")
    @Expose
    private String exerciseType;
    @SerializedName("gym_account_id")
    @Expose
    private String gymAccountId;
    @SerializedName("last_modify_time")
    @Expose
    private String lastModifyTime;
    @SerializedName("exercise_type_id")
    @Expose
    private String exerciseTypeId;
    @SerializedName("exercise_video_link")
    @Expose
    private String exerciseVideoLink;
    @SerializedName("exercise_percentage")
    @Expose
    private String exercisePercentage;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("perant_type_name")
    @Expose
    private String perantTypeName;
    @SerializedName("base_value")
    @Expose
    private String baseValue;
    @SerializedName("sets")
    @Expose
    private List<Set> sets = null;


    private Boolean selectedExercise = false;

    /**
     * No args constructor for use in serialization
     *
     */


    /**
     * @param exerciseName
     * @param exerciseId
     * @param exerciseVideoLink
     * @param baseValue
     * @param sets
     * @param exerciseType
     * @param lastModifyTime
     * @param exercisePercentage
     * @param perantTypeName
     * @param id
     * @param gymAccountId
     * @param exerciseTypeId
     */
    public Exercise(String exerciseId,
                    String exerciseName,
                    String exerciseType,
                    String gymAccountId,
                    String lastModifyTime,
                    String exerciseTypeId,
                    String exerciseVideoLink,
                    String exercisePercentage,
                    String id,
                    String perantTypeName,
                    String baseValue,
                    Boolean selectedExercise,
                    List<Set> sets) {
        super();
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
        this.gymAccountId = gymAccountId;
        this.lastModifyTime = lastModifyTime;
        this.exerciseTypeId = exerciseTypeId;
        this.exerciseVideoLink = exerciseVideoLink;
        this.exercisePercentage = exercisePercentage;
        this.id = id;
        this.perantTypeName = perantTypeName;
        this.baseValue = baseValue;
        this.sets = sets;
        this.selectedExercise = selectedExercise;
    }

    public Exercise(String exerciseId, String exerciseName, String exerciseType, String gymAccountId, String lastModifyTime, String exerciseTypeId, String exerciseVideoLink, String id, String perantTypeName, String baseValue, Boolean selectedExercise, List<Set> sets) {
        super();
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
        this.gymAccountId = gymAccountId;
        this.lastModifyTime = lastModifyTime;
        this.exerciseTypeId = exerciseTypeId;
        this.exerciseVideoLink = exerciseVideoLink;
        this.exercisePercentage = exercisePercentage;
        this.id = id;
        this.perantTypeName = perantTypeName;
        this.baseValue = baseValue;
        this.sets = sets;
        this.selectedExercise = selectedExercise;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
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

    public String getExerciseTypeId() {
        return exerciseTypeId;
    }

    public void setExerciseTypeId(String exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
    }

    public String getExerciseVideoLink() {
        return exerciseVideoLink;
    }

    public void setExerciseVideoLink(String exerciseVideoLink) {
        this.exerciseVideoLink = exerciseVideoLink;
    }

    public String getExercisePercentage() {
        return exercisePercentage;
    }

    public void setExercisePercentage(String exercisePercentage) {
        this.exercisePercentage = exercisePercentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerantTypeName() {
        return perantTypeName;
    }

    public void setPerantTypeName(String perantTypeName) {
        this.perantTypeName = perantTypeName;
    }

    public String getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(String baseValue) {
        this.baseValue = baseValue;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public Boolean getSelectedExercise() {
        return selectedExercise;
    }

    public void setSelectedExercise(Boolean selectedExercise) {
        this.selectedExercise = selectedExercise;
    }
}
