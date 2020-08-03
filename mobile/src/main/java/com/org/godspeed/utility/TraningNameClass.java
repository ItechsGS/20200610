package com.org.godspeed.utility;

/**
 * Created by Tanveer on 11/21/2017.
 */

public class TraningNameClass {
    public String name;
    public int noOfPhases = 0;
    public int trainingId;
    public Phase phaseInTraining;

    public class Phase {
        public int noOfWeeks;
        public String phaseName;
        public int phaseId, trainingId;
        public Exercise exerciseInPhase;
    }

    public class Exercise {
        public int phaseId, exerciseId;
        public boolean isWeight, isSets, isReps, isHeight, isMaxLift, isTime, isCalories, isRound, isDistance;
    }
}
