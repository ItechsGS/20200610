
package com.org.godspeed.response_JsonS.AddTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Pillar implements Serializable {

    //private final static long serialVersionUID = -2135935269387818946L;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("types")
    @Expose
    private List<Type> types = null;

    /**
     * No args constructor for use in serialization
     */
    public Pillar() {
    }

    /**
     * @param number
     * @param types
     */
    public Pillar(String number, List<Type> types) {
        super();
        this.number = number;
        this.types = types;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

}
