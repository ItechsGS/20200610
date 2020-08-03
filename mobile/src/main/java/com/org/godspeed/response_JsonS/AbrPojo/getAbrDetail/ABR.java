package com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ABR implements Serializable {

    //private final static long serialVersionUID = -3621987572263078035L;
    @SerializedName("1")
    @Expose
    private List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._1> _1 = null;
    @SerializedName("2")
    @Expose
    private List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._2> _2 = null;
    @SerializedName("3")
    @Expose
    private List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._3> _3 = null;
    @SerializedName("4")
    @Expose
    private List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._4> _4 = null;

    /**
     * No args constructor for use in serialization
     */
    public ABR() {
    }

    /**
     * @param _3
     * @param _4
     * @param _1
     * @param _2
     */
    public ABR(List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._1> _1, List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._2> _2, List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._3> _3, List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._4> _4) {
        super();
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
    }

    public List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._1> get1() {
        return _1;
    }

    public void set1(List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._1> _1) {
        this._1 = _1;
    }

    public List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._2> get2() {
        return _2;
    }

    public void set2(List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._2> _2) {
        this._2 = _2;
    }

    public List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._3> get3() {
        return _3;
    }

    public void set3(List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._3> _3) {
        this._3 = _3;
    }

    public List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._4> get4() {
        return _4;
    }

    public void set4(List<com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail._4> _4) {
        this._4 = _4;
    }

}
