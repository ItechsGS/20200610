
package com.org.godspeed.response_JsonS.goalArray;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GoalArray implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("goal_name")
    @Expose
    private String goalName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("position")
    @Expose
    private String position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
