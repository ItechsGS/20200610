package com.org.godspeed.utility;

import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Tanveer on 1/31/2018.
 */

public class DaysClass implements Serializable {
    private int phaseId, daysId;
    private String daysName;
    private Vector<ExerciseDetailsDaysWise> vectorExerciseDetails = new Vector<ExerciseDetailsDaysWise>();

    public DaysClass(int id, int phaseId, String daysName) {
        this.phaseId = phaseId;
        this.daysId = id;
        this.daysName = daysName;
    }

    public int getId() {
        return daysId;
    }

    public void setId(int id) {
        this.daysId = id;
    }

    public String getName() {
        return daysName;
    }

    public void setName(String name) {
        this.daysName = name;
    }

    public int getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(int phaseId) {
        this.phaseId = phaseId;
    }

    public Vector<ExerciseDetailsDaysWise> getExerciseList() {
        return vectorExerciseDetails;
    }

    public void setExerciseList(Vector<ExerciseDetailsDaysWise> itemList) {
        this.vectorExerciseDetails = itemList;
    }
}
