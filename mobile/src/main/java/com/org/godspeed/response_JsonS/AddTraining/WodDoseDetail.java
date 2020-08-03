
package com.org.godspeed.response_JsonS.AddTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WodDoseDetail implements Serializable {

    private final static long serialVersionUID = -2416717251971838218L;
    @SerializedName("training_exercise_id_auto")
    @Expose
    private String trainingExerciseIdAuto;
    @SerializedName("wod_dose_values")
    @Expose
    private String wodDoseValues;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     */
    public WodDoseDetail() {
    }

    /**
     * @param wodDoseValues
     * @param name
     * @param trainingExerciseIdAuto
     */
    public WodDoseDetail(String trainingExerciseIdAuto, String wodDoseValues, String name) {
        super();
        this.trainingExerciseIdAuto = trainingExerciseIdAuto;
        this.wodDoseValues = wodDoseValues;
        this.name = name;
    }

    public String getTrainingExerciseIdAuto() {
        return trainingExerciseIdAuto;
    }

    public void setTrainingExerciseIdAuto(String trainingExerciseIdAuto) {
        this.trainingExerciseIdAuto = trainingExerciseIdAuto;
    }

    public String getWodDoseValues() {
        return wodDoseValues;
    }

    public void setWodDoseValues(String wodDoseValues) {
        this.wodDoseValues = wodDoseValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
