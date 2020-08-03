
package com.org.godspeed.response_JsonS.getTeams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TeamCoach implements Serializable {

    private final static long serialVersionUID = 887592295975096870L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("school_id")
    @Expose
    private String schoolId;
    @SerializedName("team_name")
    @Expose
    private String teamName;

    /**
     * No args constructor for use in serialization
     */
    public TeamCoach() {
    }

    /**
     * @param teamName
     * @param firstName
     * @param lastName
     * @param teamId
     * @param schoolId
     * @param emailId
     * @param id
     * @param schoolName
     * @param userId
     */
    public TeamCoach(String id, String teamId, String emailId, String firstName, String lastName, String userId, String schoolName, String schoolId, String teamName) {
        super();
        this.id = id;
        this.teamId = teamId;
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.schoolName = schoolName;
        this.schoolId = schoolId;
        this.teamName = teamName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}
