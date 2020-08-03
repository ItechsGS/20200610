
package com.org.godspeed.response_JsonS.ResponseOfAddExercise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseOfAddExercise implements Serializable {

    //private final static long serialVersionUID = 8982570452746695253L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("training_program_detail_id")
    @Expose
    private String trainingProgramDetailId;
    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("exercise_id")
    @Expose
    private String exerciseId;
    @SerializedName("phase_id")
    @Expose
    private String phaseId;
    @SerializedName("dose_id")
    @Expose
    private String doseId;
    @SerializedName("time_duration")
    @Expose
    private String timeDuration;
    @SerializedName("notes_id")
    @Expose
    private String notesId;
    @SerializedName("exercise_type_group_id")
    @Expose
    private String exerciseTypeGroupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrainingProgramDetailId() {
        return trainingProgramDetailId;
    }

    public void setTrainingProgramDetailId(String trainingProgramDetailId) {
        this.trainingProgramDetailId = trainingProgramDetailId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    public String getDoseId() {
        return doseId;
    }

    public void setDoseId(String doseId) {
        this.doseId = doseId;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getNotesId() {
        return notesId;
    }

    public void setNotesId(String notesId) {
        this.notesId = notesId;
    }

    public String getExerciseTypeGroupId() {
        return exerciseTypeGroupId;
    }

    public void setExerciseTypeGroupId(String exerciseTypeGroupId) {
        this.exerciseTypeGroupId = exerciseTypeGroupId;
    }

}
