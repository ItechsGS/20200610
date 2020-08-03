
package com.org.godspeed.response_JsonS.getVideoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LiveClassExerciseCategory implements Serializable {

    private final static long serialVersionUID = 4568787093369125939L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("live_class_exercise_video")
    @Expose
    private List<LiveClassExerciseVideo> liveClassExerciseVideo = null;

    /**
     * No args constructor for use in serialization
     */
    public LiveClassExerciseCategory() {
    }

    /**
     * @param categoryImage
     * @param liveClassExerciseVideo
     * @param id
     * @param categoryName
     */
    public LiveClassExerciseCategory(String id, String categoryName, String categoryImage, List<LiveClassExerciseVideo> liveClassExerciseVideo) {
        super();
        this.id = id;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.liveClassExerciseVideo = liveClassExerciseVideo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public List<LiveClassExerciseVideo> getLiveClassExerciseVideo() {
        return liveClassExerciseVideo;
    }

    public void setLiveClassExerciseVideo(List<LiveClassExerciseVideo> liveClassExerciseVideo) {
        this.liveClassExerciseVideo = liveClassExerciseVideo;
    }

}
