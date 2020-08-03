
package com.org.godspeed.response_JsonS.athlete_level_pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AthleteLevelPojo {

    @SerializedName("selected_athlete_level")
    @Expose
    private List<SelectedAthleteLevel> selectedAthleteLevel = null;
    @SerializedName("selected_athlete_goal")
    @Expose
    private List<SelectedAthleteGoal> selectedAthleteGoal = null;
    @SerializedName("athlete_level")
    @Expose
    private List<AthleteLevel> athleteLevel = null;

    public List<SelectedAthleteLevel> getSelectedAthleteLevel() {
        return selectedAthleteLevel;
    }

    public void setSelectedAthleteLevel(List<SelectedAthleteLevel> selectedAthleteLevel) {
        this.selectedAthleteLevel = selectedAthleteLevel;
    }

    public List<SelectedAthleteGoal> getSelectedAthleteGoal() {
        return selectedAthleteGoal;
    }

    public void setSelectedAthleteGoal(List<SelectedAthleteGoal> selectedAthleteGoal) {
        this.selectedAthleteGoal = selectedAthleteGoal;
    }

    public List<AthleteLevel> getAthleteLevel() {
        return athleteLevel;
    }

    public void setAthleteLevel(List<AthleteLevel> athleteLevel) {
        this.athleteLevel = athleteLevel;
    }

}
