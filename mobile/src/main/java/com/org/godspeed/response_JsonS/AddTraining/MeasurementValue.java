
package com.org.godspeed.response_JsonS.AddTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MeasurementValue implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("training_exercise_id")
    @Expose
    private String trainingExerciseId;
    @SerializedName("measurement_id")
    @Expose
    private String measurementId;
    @SerializedName("assign_program_id")
    @Expose
    private String assignProgramId;
    @SerializedName("measurement_value")
    @Expose
    private String measurementValue;
    @SerializedName("id_auto")
    @Expose
    private String idAuto;
    @SerializedName("training_exercise_auto_id")
    @Expose
    private String trainingExerciseAutoId;
    @SerializedName("athlete_custom_measurement_values_auto_id")
    @Expose
    private String athleteCustomMeasurementValuesAutoId;
    @SerializedName("custom_dose_coach_id_auto")
    @Expose
    private String customDoseCoachIdAuto;

    @SerializedName("workout_summary_count")
    @Expose
    private String workout_summary_count;


    //private final static long serialVersionUID = -6309548600335024942L;
    private String Set_Name = "";
    /**
     * No args constructor for use in serialization
     *
     */

    /**
     * @param id
     * @param trainingExerciseId
     * @param measurementId
     * @param measurementValue
     * @param assignProgramId
     * @param idAuto
     * @param trainingExerciseAutoId
     * @param athleteCustomMeasurementValuesAutoId
     * @param customDoseCoachIdAuto
     * @param workout_summary_count
     */
    public MeasurementValue(String id,
                            String trainingExerciseId,
                            String measurementId,
                            String measurementValue,
                            String assignProgramId,
                            String idAuto,
                            String trainingExerciseAutoId,

                            String athleteCustomMeasurementValuesAutoId,
                            String customDoseCoachIdAuto,
                            String workout_summary_count, String Set_Name) {
        super();
        this.id = id;
        this.trainingExerciseId = trainingExerciseId;
        this.measurementId = measurementId;
        this.assignProgramId = assignProgramId;
        this.measurementValue = measurementValue;
        this.idAuto = idAuto;
        this.trainingExerciseAutoId = trainingExerciseAutoId;
        this.athleteCustomMeasurementValuesAutoId = athleteCustomMeasurementValuesAutoId;
        this.customDoseCoachIdAuto = customDoseCoachIdAuto;
        this.workout_summary_count = workout_summary_count;
        this.Set_Name = Set_Name;
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

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public String getAssignProgramId() {
        return assignProgramId;
    }

    public void setAssignProgramId(String assignProgramId) {
        this.assignProgramId = assignProgramId;
    }

    public String getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(String measurementValue) {
        this.measurementValue = measurementValue;
    }

    public String getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(String idAuto) {
        this.idAuto = idAuto;
    }

    public String getTrainingExerciseAutoId() {
        return trainingExerciseAutoId;
    }

    public void setTrainingExerciseAutoId(String trainingExerciseAutoId) {
        this.trainingExerciseAutoId = trainingExerciseAutoId;
    }

    public String getAthleteCustomMeasurementValuesAutoId() {
        return athleteCustomMeasurementValuesAutoId;
    }

    public void setAthleteCustomMeasurementValuesAutoId(String athleteCustomMeasurementValuesAutoId) {
        this.athleteCustomMeasurementValuesAutoId = athleteCustomMeasurementValuesAutoId;
    }

    public String getCustomDoseCoachIdAuto() {
        return customDoseCoachIdAuto;
    }

    public void setCustomDoseCoachIdAuto(String customDoseCoachIdAuto) {
        this.customDoseCoachIdAuto = customDoseCoachIdAuto;
    }

    public String getSet_Name() {
        return Set_Name;
    }

    public void setSet_Name(String set_Name) {
        Set_Name = set_Name;
    }

    public String getWorkout_summary_count() {
        return workout_summary_count;
    }

    public void setWorkout_summary_count(String workout_summary_count) {
        this.workout_summary_count = workout_summary_count;
    }
}

