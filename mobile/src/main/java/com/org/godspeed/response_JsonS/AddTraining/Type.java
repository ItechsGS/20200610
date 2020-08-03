package com.org.godspeed.response_JsonS.AddTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Type implements Serializable {

    @SerializedName("wod_description")
    @Expose
    private String wodDescription;
    @SerializedName("wod_type")
    @Expose
    private String wodType;

    //private final static long serialVersionUID = 1372295598575540016L;
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
    @SerializedName("exercise_time_duration")
    @Expose
    private String exerciseTimeDuration;
    @SerializedName("exercise_type_group_id")
    @Expose
    private String exerciseTypeGroupId;
    @SerializedName("exercises")
    @Expose
    private List<Exercise> exercises = null;
    @SerializedName("type_group_id")
    @Expose
    private String typeGroupId;


    public Type(String wodDescription, String wodType, String exerciseTypeGroupId, String id, String name, String exerciseTypeNotesId, String exerciseTypeNotesDate, String exerciseTypeNotesDetail, String exerciseTimeDuration, String typeGroupId, List<Exercise> exercises) {
        super();
        this.wodDescription = wodDescription;
        this.wodType = wodType;
        this.exerciseTypeGroupId = exerciseTypeGroupId;
        this.id = id;
        this.name = name;
        this.exerciseTypeNotesId = exerciseTypeNotesId;
        this.exerciseTypeNotesDate = exerciseTypeNotesDate;
        this.exerciseTypeNotesDetail = exerciseTypeNotesDetail;
        this.exerciseTimeDuration = exerciseTimeDuration;
        this.typeGroupId = typeGroupId;
        this.exercises = exercises;
    }

    public String getWodDescription() {
        return wodDescription;
    }

    public void setWodDescription(String wodDescription) {
        this.wodDescription = wodDescription;
    }

    public String getWodType() {
        return wodType;
    }

    public void setWodType(String wodType) {
        this.wodType = wodType;
    }

    public String getExerciseTypeGroupId() {
        return exerciseTypeGroupId;
    }

    public void setExerciseTypeGroupId(String exerciseTypeGroupId) {
        this.exerciseTypeGroupId = exerciseTypeGroupId;
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

    public String getExerciseTimeDuration() {
        return exerciseTimeDuration;
    }

    public void setExerciseTimeDuration(String exerciseTimeDuration) {
        this.exerciseTimeDuration = exerciseTimeDuration;
    }

    public String getTypeGroupId() {
        return typeGroupId;
    }

    public void setTypeGroupId(String typeGroupId) {
        this.typeGroupId = typeGroupId;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getExerciseTypeNotesId() {
//        return exerciseTypeNotesId;
//    }
//
//    public void setExerciseTypeNotesId(String exerciseTypeNotesId) {
//        this.exerciseTypeNotesId = exerciseTypeNotesId;
//    }
//
//    public String getExerciseTypeNotesDate() {
//        return exerciseTypeNotesDate;
//    }
//
//    public void setExerciseTypeNotesDate(String exerciseTypeNotesDate) {
//        this.exerciseTypeNotesDate = exerciseTypeNotesDate;
//    }
//
//    public String getExerciseTypeNotesDetail() {
//        return exerciseTypeNotesDetail;
//    }
//
//    public void setExerciseTypeNotesDetail(String exerciseTypeNotesDetail) {
//        this.exerciseTypeNotesDetail = exerciseTypeNotesDetail;
//    }
//
//    public String getExerciseTimeDuration() {
//        return exerciseTimeDuration;
//    }
//
//    public void setExerciseTimeDuration(String exerciseTimeDuration) {
//        this.exerciseTimeDuration = exerciseTimeDuration;
//    }
//
//    public String getTypeGroupId() {
//        return typeGroupId;
//    }
//
//    public void setTypeGroupId(String typeGroupId) {
//        this.typeGroupId = typeGroupId;
//    }
//
//    public List<Exercise> getExercises() {
//        return exercises;
//    }
//
//    public void setExercises(List<Exercise> exercises) {
//        this.exercises = exercises;
//    }

}
