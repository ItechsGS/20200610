
package com.org.godspeed.response_JsonS.GetWhiteBoardDatum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ExcerciseAllType implements Serializable {

    private final static long serialVersionUID = -7472191592619388710L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("exercise_type_notes_id")
    @Expose
    private String exerciseTypeNotesId;
    @SerializedName("exercise_type_notes_date")
    @Expose
    private String exerciseTypeNotesDate;
    @SerializedName("exercise_type_notes_detail")
    @Expose
    private String exerciseTypeNotesDetail;
    @SerializedName("exercise_type_group_id")
    @Expose
    private String exerciseTypeGroupId;
    @SerializedName("excersice")
    @Expose
    private List<Excersice> excersice = null;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     */
    public ExcerciseAllType() {
    }

    /**
     * @param exerciseTypeNotesDetail
     * @param name
     * @param exerciseTypeNotesId
     * @param exerciseTypeNotesDate
     * @param id
     * @param exerciseTypeGroupId
     * @param excersice
     * @param status
     */
    public ExcerciseAllType(String id, String name, String exerciseTypeNotesId, String exerciseTypeNotesDate, String exerciseTypeNotesDetail, String exerciseTypeGroupId, List<Excersice> excersice, String status) {
        super();
        this.id = id;
        this.name = name;
        this.exerciseTypeNotesId = exerciseTypeNotesId;
        this.exerciseTypeNotesDate = exerciseTypeNotesDate;
        this.exerciseTypeNotesDetail = exerciseTypeNotesDetail;
        this.exerciseTypeGroupId = exerciseTypeGroupId;
        this.excersice = excersice;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExerciseTypeNotesId() {
        return exerciseTypeNotesId;
    }

    public void setExerciseTypeNotesId(String exerciseTypeNotesId) {
        this.exerciseTypeNotesId = exerciseTypeNotesId;
    }

    public String getExerciseTypeNotesDate() {
        return exerciseTypeNotesDate;
    }

    public void setExerciseTypeNotesDate(String exerciseTypeNotesDate) {
        this.exerciseTypeNotesDate = exerciseTypeNotesDate;
    }

    public String getExerciseTypeNotesDetail() {
        return exerciseTypeNotesDetail;
    }

    public void setExerciseTypeNotesDetail(String exerciseTypeNotesDetail) {
        this.exerciseTypeNotesDetail = exerciseTypeNotesDetail;
    }

    public String getExerciseTypeGroupId() {
        return exerciseTypeGroupId;
    }

    public void setExerciseTypeGroupId(String exerciseTypeGroupId) {
        this.exerciseTypeGroupId = exerciseTypeGroupId;
    }

    public List<Excersice> getExcersice() {
        return excersice;
    }

    public void setExcersice(List<Excersice> excersice) {
        this.excersice = excersice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
