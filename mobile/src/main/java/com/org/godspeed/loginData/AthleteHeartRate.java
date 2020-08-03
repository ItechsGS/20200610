
package com.org.godspeed.loginData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AthleteHeartRate implements Serializable {

    //private final static long serialVersionUID = 5882779593788821021L;
    @SerializedName("avg_heart_rate")
    @Expose
    private String avgHeartRate;
    @SerializedName("min_heart_rate")
    @Expose
    private String minHeartRate;
    @SerializedName("max_heart_rate")
    @Expose
    private String maxHeartRate;

    /**
     * No args constructor for use in serialization
     */
    public AthleteHeartRate() {
    }

    /**
     * @param minHeartRate
     * @param avgHeartRate
     * @param maxHeartRate
     */
    public AthleteHeartRate(String avgHeartRate, String minHeartRate, String maxHeartRate) {
        super();
        this.avgHeartRate = avgHeartRate;
        this.minHeartRate = minHeartRate;
        this.maxHeartRate = maxHeartRate;
    }

    public String getAvgHeartRate() {
        return avgHeartRate;
    }

    public void setAvgHeartRate(String avgHeartRate) {
        this.avgHeartRate = avgHeartRate;
    }

    public String getMinHeartRate() {
        return minHeartRate;
    }

    public void setMinHeartRate(String minHeartRate) {
        this.minHeartRate = minHeartRate;
    }

    public String getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(String maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

}
