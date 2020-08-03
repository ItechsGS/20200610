
package com.org.godspeed.loginData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Value implements Serializable {

    //private final static long serialVersionUID = 2349721702110525334L;
    @SerializedName("exercise_parent_type_id")
    @Expose
    private String exerciseParentTypeId;
    @SerializedName("multiple")
    @Expose
    private String multiple;
    @SerializedName("exercise_multiplier_phases_id")
    @Expose
    private String exerciseMultiplierPhasesId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("perant_type_name")
    @Expose
    private String perantTypeName;
    @SerializedName("base_value")
    @Expose
    private String baseValue;
    @SerializedName("row_no")
    @Expose
    private String rowNo;
    @SerializedName("position")
    @Expose
    private String position;

    /**
     * No args constructor for use in serialization
     */
    public Value() {
    }

    /**
     * @param position
     * @param id
     * @param exerciseParentTypeId
     * @param exerciseMultiplierPhasesId
     * @param baseValue
     * @param rowNo
     * @param multiple
     * @param perantTypeName
     */
    public Value(String id, String exerciseParentTypeId, String multiple, String exerciseMultiplierPhasesId, String position, String perantTypeName, String baseValue, String rowNo) {
        super();
        this.id = id;
        this.exerciseParentTypeId = exerciseParentTypeId;
        this.multiple = multiple;
        this.exerciseMultiplierPhasesId = exerciseMultiplierPhasesId;
        this.position = position;
        this.perantTypeName = perantTypeName;
        this.baseValue = baseValue;
        this.rowNo = rowNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExerciseParentTypeId() {
        return exerciseParentTypeId;
    }

    public void setExerciseParentTypeId(String exerciseParentTypeId) {
        this.exerciseParentTypeId = exerciseParentTypeId;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public String getExerciseMultiplierPhasesId() {
        return exerciseMultiplierPhasesId;
    }

    public void setExerciseMultiplierPhasesId(String exerciseMultiplierPhasesId) {
        this.exerciseMultiplierPhasesId = exerciseMultiplierPhasesId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

}
