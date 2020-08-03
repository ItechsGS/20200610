
package com.org.godspeed.response_JsonS.exerciseTypeName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExerciseTypeName implements Serializable {

    @SerializedName("exercise_type_id")
    @Expose
    private String exerciseTypeId;
    @SerializedName("type_name")
    @Expose
    private String typeName;
    private boolean isSelected = false;

    /**
     * No args constructor for use in serialization
     *
     */
    /**
     * @param typeName
     * @param exerciseTypeId
     */
    public ExerciseTypeName(String exerciseTypeId, String typeName) {
        super();
        this.exerciseTypeId = exerciseTypeId;
        this.typeName = typeName;
    }

    public String getExerciseTypeId() {
        return exerciseTypeId;
    }

    public void setExerciseTypeId(String exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
