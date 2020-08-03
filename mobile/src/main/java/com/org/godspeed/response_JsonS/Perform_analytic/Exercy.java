
package com.org.godspeed.response_JsonS.Perform_analytic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Exercy implements Serializable {

    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("type_name")
    @Expose
    private String typeName;
    @SerializedName("exercise_id")
    @Expose
    private String exerciseId;
    @SerializedName("exercise_name")
    @Expose
    private String exerciseName;


    /**
     * @param exerciseName
     * @param exerciseId
     * @param typeName
     * @param typeId
     */
    public Exercy(String typeId, String typeName, String exerciseId, String exerciseName) {
        super();
        this.typeId = typeId;
        this.typeName = typeName;
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

}
