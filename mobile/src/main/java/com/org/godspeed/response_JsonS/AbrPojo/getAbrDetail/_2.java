
package com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class _2 implements Serializable {

    //private final static long serialVersionUID = 7061586854130814138L;
    @SerializedName("abr")
    @Expose
    private String abr;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("day1_value")
    @Expose
    private String day1Value;
    @SerializedName("day1_percent")
    @Expose
    private String day1Percent;
    @SerializedName("day2_value")
    @Expose
    private String day2Value;
    @SerializedName("day2_percent")
    @Expose
    private String day2Percent;
    @SerializedName("day3_value")
    @Expose
    private String day3Value;
    @SerializedName("day3_percent")
    @Expose
    private String day3Percent;
    @SerializedName("day4_value")
    @Expose
    private String day4Value;
    @SerializedName("day4_percent")
    @Expose
    private String day4Percent;
    @SerializedName("day5_value")
    @Expose
    private String day5Value;
    @SerializedName("day5_percent")
    @Expose
    private String day5Percent;

    /**
     * No args constructor for use in serialization
     */
    public _2() {
    }

    /**
     * @param id
     * @param abr
     * @param day2Value
     * @param day5Percent
     * @param day3Percent
     * @param day4Value
     * @param day5Value
     * @param day2Percent
     * @param day1Value
     * @param day4Percent
     * @param day1Percent
     * @param day3Value
     */
    public _2(String abr, String id, String day1Value, String day1Percent, String day2Value, String day2Percent, String day3Value, String day3Percent, String day4Value, String day4Percent, String day5Value, String day5Percent) {
        super();
        this.abr = abr;
        this.id = id;
        this.day1Value = day1Value;
        this.day1Percent = day1Percent;
        this.day2Value = day2Value;
        this.day2Percent = day2Percent;
        this.day3Value = day3Value;
        this.day3Percent = day3Percent;
        this.day4Value = day4Value;
        this.day4Percent = day4Percent;
        this.day5Value = day5Value;
        this.day5Percent = day5Percent;
    }

    public String getAbr() {
        return abr;
    }

    public void setAbr(String abr) {
        this.abr = abr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay1Value() {
        return day1Value;
    }

    public void setDay1Value(String day1Value) {
        this.day1Value = day1Value;
    }

    public String getDay1Percent() {
        return day1Percent;
    }

    public void setDay1Percent(String day1Percent) {
        this.day1Percent = day1Percent;
    }

    public String getDay2Value() {
        return day2Value;
    }

    public void setDay2Value(String day2Value) {
        this.day2Value = day2Value;
    }

    public String getDay2Percent() {
        return day2Percent;
    }

    public void setDay2Percent(String day2Percent) {
        this.day2Percent = day2Percent;
    }

    public String getDay3Value() {
        return day3Value;
    }

    public void setDay3Value(String day3Value) {
        this.day3Value = day3Value;
    }

    public String getDay3Percent() {
        return day3Percent;
    }

    public void setDay3Percent(String day3Percent) {
        this.day3Percent = day3Percent;
    }

    public String getDay4Value() {
        return day4Value;
    }

    public void setDay4Value(String day4Value) {
        this.day4Value = day4Value;
    }

    public String getDay4Percent() {
        return day4Percent;
    }

    public void setDay4Percent(String day4Percent) {
        this.day4Percent = day4Percent;
    }

    public String getDay5Value() {
        return day5Value;
    }

    public void setDay5Value(String day5Value) {
        this.day5Value = day5Value;
    }

    public String getDay5Percent() {
        return day5Percent;
    }

    public void setDay5Percent(String day5Percent) {
        this.day5Percent = day5Percent;
    }

}
