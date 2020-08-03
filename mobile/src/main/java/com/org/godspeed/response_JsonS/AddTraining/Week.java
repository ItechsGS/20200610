
package com.org.godspeed.response_JsonS.AddTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Week implements Serializable {

    //private final static long serialVersionUID = -2150421294029616339L;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("day")
    @Expose
    private List<Day> day = null;

    /**
     * No args constructor for use in serialization
     */
    public Week() {
    }

    /**
     * @param number
     * @param day
     */
    public Week(String number, List<Day> day) {
        super();
        this.number = number;
        this.day = day;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Day> getDay() {
        return day;
    }

    public void setDay(List<Day> day) {
        this.day = day;
    }

}
