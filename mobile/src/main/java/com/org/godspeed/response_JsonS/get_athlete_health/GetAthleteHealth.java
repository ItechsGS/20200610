
package com.org.godspeed.response_JsonS.get_athlete_health;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetAthleteHealth implements Serializable {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("unit_name")
    @Expose
    private String unitName;
    @SerializedName("health_image")
    @Expose
    private String healthTypeImage;

    /**
     * No args constructor for use in serialization
     *
     */

    /**
     * @param unitName
     * @param name
     * @param healthTypeImage
     * @param id
     * @param value
     */
    public GetAthleteHealth(String value, String name, String id, String unitName, String healthTypeImage) {
        super();
        this.value = value;
        this.name = name;
        this.id = id;
        this.unitName = unitName;
        this.healthTypeImage = healthTypeImage;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getHealthTypeImage() {
        return healthTypeImage;
    }

    public void setHealthTypeImage(String healthTypeImage) {
        this.healthTypeImage = healthTypeImage;
    }

}
