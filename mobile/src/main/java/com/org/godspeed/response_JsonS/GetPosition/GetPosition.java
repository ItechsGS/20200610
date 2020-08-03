
package com.org.godspeed.response_JsonS.GetPosition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetPosition implements Serializable {

    private final static long serialVersionUID = -8865106293835972444L;
    @SerializedName("position_title_id")
    @Expose
    private String positionTitleId;
    @SerializedName("position_title_name")
    @Expose
    private String positionTitleName;
    @SerializedName("position")
    @Expose
    private String position;

    /**
     * No args constructor for use in serialization
     */
    public GetPosition() {
    }

    /**
     * @param positionTitleName
     * @param positionTitleId
     * @param position
     */
    public GetPosition(String positionTitleId, String positionTitleName, String position) {
        super();
        this.positionTitleId = positionTitleId;
        this.positionTitleName = positionTitleName;
        this.position = position;
    }

    public String getPositionTitleId() {
        return positionTitleId;
    }

    public void setPositionTitleId(String positionTitleId) {
        this.positionTitleId = positionTitleId;
    }

    public String getPositionTitleName() {
        return positionTitleName;
    }

    public void setPositionTitleName(String positionTitleName) {
        this.positionTitleName = positionTitleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
