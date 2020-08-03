
package com.org.godspeed.response_JsonS.AddTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DoseDetail implements Serializable {

    //private final static long serialVersionUID = 5823135278821705178L;
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
    @SerializedName("weight")
    @Expose
    private String weight;

    /**
     * No args constructor for use in serialization
     */
    public DoseDetail() {
    }

    /**
     * @param id
     * @param weight
     * @param repsPercent
     * @param repsValue
     * @param trainingExerciseId
     */
    public DoseDetail(String id, String trainingExerciseId, String repsValue, String repsPercent, String weight) {
        super();
        this.id = id;
        this.trainingExerciseId = trainingExerciseId;
        this.repsValue = repsValue;
        this.repsPercent = repsPercent;
        this.weight = weight;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
