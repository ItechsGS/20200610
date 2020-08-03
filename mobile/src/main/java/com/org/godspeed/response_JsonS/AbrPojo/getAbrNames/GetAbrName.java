
package com.org.godspeed.response_JsonS.AbrPojo.getAbrNames;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class GetAbrName implements Serializable {

    @SerializedName("abr")
    @Expose
    private String abr;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("phase")
    @Expose
    private String phase;
    //private final static long serialVersionUID = 2016727014666285268L;

    /**
     * No args constructor for use in serialization
     *
     */

    /**
     * @param id
     * @param abr
     * @param type
     * @param phase
     */
    public GetAbrName(String abr, String id, String type, String phase) {
        super();
        this.abr = abr;
        this.id = id;
        this.type = type;
        this.phase = phase;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }


}
