
package com.org.godspeed.response_JsonS.ExerciseNameList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ExerciseName implements Serializable {

    private final static long serialVersionUID = -7346105989359421674L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("perant_type_name")
    @Expose
    private String perantTypeName;
    @SerializedName("base_value")
    @Expose
    private String baseValue;
    @SerializedName("exercise")
    @Expose
    private List<Exercise> exercise = null;

    /**
     * No args constructor for use in serialization
     */
    public ExerciseName() {
    }

    /**
     * @param baseValue
     * @param exercise
     * @param perantTypeName
     * @param id
     */
    public ExerciseName(String id, String perantTypeName, String baseValue, List<Exercise> exercise) {
        super();
        this.id = id;
        this.perantTypeName = perantTypeName;
        this.baseValue = baseValue;
        this.exercise = exercise;
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

    public List<Exercise> getExercise() {
        return exercise;
    }

    public void setExercise(List<Exercise> exercise) {
        this.exercise = exercise;
    }

}
