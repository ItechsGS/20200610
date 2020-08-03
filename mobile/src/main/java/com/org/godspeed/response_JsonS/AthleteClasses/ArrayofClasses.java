
package com.org.godspeed.response_JsonS.AthleteClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ArrayofClasses implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("coach_id")
    @Expose
    private String coachId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;
    //private final static long serialVersionUID = 7564043313183157590L;

    /**
     * No args constructor for use in serialization
     */
    public ArrayofClasses() {
    }

    /**
     * @param id
     * @param lastName
     * @param coachId
     * @param firstName
     * @param options
     * @param userType
     */
    public ArrayofClasses(String id, String coachId, String firstName, String lastName, String userType, List<Option> options) {
        super();
        this.id = id;
        this.coachId = coachId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
