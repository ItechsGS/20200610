
package com.org.godspeed.response_JsonS.getVideoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetVideoClas implements Serializable {

    private final static long serialVersionUID = -379936442544446313L;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("live_class_exercise_category")
    @Expose
    private List<LiveClassExerciseCategory> liveClassExerciseCategory = null;

    /**
     * No args constructor for use in serialization
     */
    public GetVideoClas() {
    }

    /**
     * @param date
     * @param liveClassExerciseCategory
     */
    public GetVideoClas(String date, List<LiveClassExerciseCategory> liveClassExerciseCategory) {
        super();
        this.date = date;
        this.liveClassExerciseCategory = liveClassExerciseCategory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<LiveClassExerciseCategory> getLiveClassExerciseCategory() {
        return liveClassExerciseCategory;
    }

    public void setLiveClassExerciseCategory(List<LiveClassExerciseCategory> liveClassExerciseCategory) {
        this.liveClassExerciseCategory = liveClassExerciseCategory;
    }

}
