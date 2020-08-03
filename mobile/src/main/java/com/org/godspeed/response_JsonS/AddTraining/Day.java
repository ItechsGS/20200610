
package com.org.godspeed.response_JsonS.AddTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Day implements Serializable {

    //private final static long serialVersionUID = 7501013986942881343L;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("pillar")
    @Expose
    private List<Pillar> pillar = null;

    /**
     * No args constructor for use in serialization
     */
    public Day() {
    }

    /**
     * @param pillar
     * @param number
     */
    public Day(String number, List<Pillar> pillar) {
        super();
        this.number = number;
        this.pillar = pillar;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Pillar> getPillar() {
        return pillar;
    }

    public void setPillar(List<Pillar> pillar) {
        this.pillar = pillar;
    }

}
