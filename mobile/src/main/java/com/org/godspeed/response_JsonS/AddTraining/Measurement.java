
package com.org.godspeed.response_JsonS.AddTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Measurement implements Serializable {

    //private final static long serialVersionUID = -6000646193268638918L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("measurement_id")
    @Expose
    private String measurementId;
    @SerializedName("training_exercise_id")
    @Expose
    private String trainingExerciseId;
    @SerializedName("measurement_name")
    @Expose
    private String measurementName;
    @SerializedName("dose_id")
    @Expose
    private String doseId;
    @SerializedName("measurement_value")
    @Expose
    private List<MeasurementValue> measurementValue = null;

    /**
     * No args constructor for use in serialization
     */

    /**
     * @param id
     * @param doseId
     * @param measurementId
     * @param trainingExerciseId
     * @param measurementName
     * @param measurementValue
     */
    public Measurement(String id, String measurementId, String trainingExerciseId, String measurementName, String doseId, List<MeasurementValue> measurementValue) {
        super();
        this.id = id;
        this.measurementId = measurementId;
        this.trainingExerciseId = trainingExerciseId;
        this.measurementName = measurementName;
        this.doseId = doseId;
        this.measurementValue = measurementValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public String getTrainingExerciseId() {
        return trainingExerciseId;
    }

    public void setTrainingExerciseId(String trainingExerciseId) {
        this.trainingExerciseId = trainingExerciseId;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public String getDoseId() {
        return doseId;
    }

    public void setDoseId(String doseId) {
        this.doseId = doseId;
    }

    public List<MeasurementValue> getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(List<MeasurementValue> measurementValue) {
        this.measurementValue = measurementValue;
    }

}

//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//import java.io.Serializable;
//import java.util.List;
//
//public class Measurement implements Serializable {
//
//    //private final static long serialVersionUID = -2071019646433581968L;
//    @SerializedName("id")
//    @Expose
//    private String id;
//    @SerializedName("training_exercise_id")
//    @Expose
//    private String trainingExerciseId;
//    @SerializedName("measurement_id")
//    @Expose
//    private String measurementId;
//    @SerializedName("measurement_name")
//    @Expose
//    private String measurementName;
//    @SerializedName("measurement_value")
//    @Expose
//    private List<MeasurementValue> measurementValue = null;
//
//    /**
//     * No args constructor for use in serialization
//     */
//    public Measurement() {
//    }
//
//    /**
//     * @param id
//     * @param measurementId
//     * @param trainingExerciseId
//     * @param measurementName
//     * @param measurementValue
//     */
//    public Measurement(String id, String trainingExerciseId, String measurementId, String measurementName, List<MeasurementValue> measurementValue) {
//        super();
//        this.id = id;
//        this.trainingExerciseId = trainingExerciseId;
//        this.measurementId = measurementId;
//        this.measurementName = measurementName;
//        this.measurementValue = measurementValue;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTrainingExerciseId() {
//        return trainingExerciseId;
//    }
//
//    public void setTrainingExerciseId(String trainingExerciseId) {
//        this.trainingExerciseId = trainingExerciseId;
//    }
//
//    public String getMeasurementId() {
//        return measurementId;
//    }
//
//    public void setMeasurementId(String measurementId) {
//        this.measurementId = measurementId;
//    }
//
//    public String getMeasurementName() {
//        return measurementName;
//    }
//
//    public void setMeasurementName(String measurementName) {
//        this.measurementName = measurementName;
//    }
//
//    public List<MeasurementValue> getMeasurementValue() {
//        return measurementValue;
//    }
//
//    public void setMeasurementValue(List<MeasurementValue> measurementValue) {
//        this.measurementValue = measurementValue;
//    }
//
//}
