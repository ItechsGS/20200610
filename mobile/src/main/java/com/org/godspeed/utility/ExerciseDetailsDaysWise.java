package com.org.godspeed.utility;

import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Tanveer on 1/31/2018.
 */

public class ExerciseDetailsDaysWise implements Serializable {
    private int exerciseId;
    private String exerciseName, description;
    private Vector<ExerciseMetrics> vectorExerciseMetrics = new Vector<ExerciseMetrics>();


    public ExerciseDetailsDaysWise(int exerciseId, String exerciseName) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;

    }

    public ExerciseDetailsDaysWise(int exerciseId, String exerciseName, String description) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.description = description;

    }

    public int getId() {
        return exerciseId;
    }

    public void setId(int id) {
        this.exerciseId = id;
    }

    public String getName() {
        return exerciseName;
    }

    public void setName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDiscription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vector<ExerciseMetrics> getExerciseList() {
        return vectorExerciseMetrics;
    }

    public void setExerciseList(Vector<ExerciseMetrics> vectorExerciseMetrics) {
        this.vectorExerciseMetrics = vectorExerciseMetrics;
    }


}
