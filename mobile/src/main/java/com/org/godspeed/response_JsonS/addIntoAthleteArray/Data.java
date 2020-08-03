
package com.org.godspeed.response_JsonS.addIntoAthleteArray;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {

    @SerializedName("id_auto")
    @Expose
    private String idAuto;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("training_exercise_id")
    @Expose
    private String trainingExerciseId;
    @SerializedName("reps_value")
    @Expose
    private String repsValue;
    @SerializedName("reps_percent")
    @Expose
    private String repsPercent;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("training_exercise_auto_id")
    @Expose
    private String trainingExerciseAutoId;
    @SerializedName("assign_program_id")
    @Expose
    private String assignProgramId;
    @SerializedName("training_program_id")
    @Expose
    private String trainingProgramId;
    @SerializedName("training_exercise_dose_id_auto")
    @Expose
    private String trainingExerciseDoseIdAuto;
    //    //private final static long serialVersionUID= 2658351198068303606L;

    /**
     * No args constructor for use in serialization
     */
    public Data() {
    }

    /**
     * @param athleteId
     * @param id
     * @param weight
     * @param trainingExerciseDoseIdAuto
     * @param trainingProgramId
     * @param assignProgramId
     * @param repsPercent
     * @param repsValue
     * @param trainingExerciseId
     * @param idAuto
     * @param trainingExerciseAutoId
     */
    public Data(String idAuto, String id, String trainingExerciseId, String repsValue, String repsPercent, String athleteId, String weight, String trainingExerciseAutoId, String assignProgramId, String trainingProgramId, String trainingExerciseDoseIdAuto) {
        super();
        this.idAuto = idAuto;
        this.id = id;
        this.trainingExerciseId = trainingExerciseId;
        this.repsValue = repsValue;
        this.repsPercent = repsPercent;
        this.athleteId = athleteId;
        this.weight = weight;
        this.trainingExerciseAutoId = trainingExerciseAutoId;
        this.assignProgramId = assignProgramId;
        this.trainingProgramId = trainingProgramId;
        this.trainingExerciseDoseIdAuto = trainingExerciseDoseIdAuto;
    }

    public String getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(String idAuto) {
        this.idAuto = idAuto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrainingExerciseId() {
        return trainingExerciseId;
    }

    public void setTrainingExerciseId(String trainingExerciseId) {
        this.trainingExerciseId = trainingExerciseId;
    }

    public String getRepsValue() {
        return repsValue;
    }

    public void setRepsValue(String repsValue) {
        this.repsValue = repsValue;
    }

    public String getRepsPercent() {
        return repsPercent;
    }

    public void setRepsPercent(String repsPercent) {
        this.repsPercent = repsPercent;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTrainingExerciseAutoId() {
        return trainingExerciseAutoId;
    }

    public void setTrainingExerciseAutoId(String trainingExerciseAutoId) {
        this.trainingExerciseAutoId = trainingExerciseAutoId;
    }

    public String getAssignProgramId() {
        return assignProgramId;
    }

    public void setAssignProgramId(String assignProgramId) {
        this.assignProgramId = assignProgramId;
    }

    public String getTrainingProgramId() {
        return trainingProgramId;
    }

    public void setTrainingProgramId(String trainingProgramId) {
        this.trainingProgramId = trainingProgramId;
    }

    public String getTrainingExerciseDoseIdAuto() {
        return trainingExerciseDoseIdAuto;
    }

    public void setTrainingExerciseDoseIdAuto(String trainingExerciseDoseIdAuto) {
        this.trainingExerciseDoseIdAuto = trainingExerciseDoseIdAuto;
    }

}
