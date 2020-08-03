
package com.org.godspeed.response_JsonS.messageJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MessageScreenData implements Serializable {

    private final static long serialVersionUID = 2512981202658012143L;
    @SerializedName("sender_user_id")
    @Expose
    private String senderUserId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("teams_athlete_id")
    @Expose
    private String teamsAthleteId;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;
    @SerializedName("gym_account_id")
    @Expose
    private String gymAccountId;
    @SerializedName("last_modify_time")
    @Expose
    private String lastModifyTime;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("push_notification_datetime")
    @Expose
    private String pushNotificationDatetime;
    @SerializedName("sender_detail")
    @Expose
    private SenderDetail senderDetail;

    /**
     * No args constructor for use in serialization
     *
     */

    /**
     * @param teamName
     * @param lastName
     * @param teamsAthleteId
     * @param senderUserId
     * @param emailId
     * @param message
     * @param userId
     * @param gymAccountId
     * @param firstName
     * @param athleteId
     * @param teamId
     * @param lastModifyTime
     * @param id
     * @param senderDetail
     * @param pushNotificationDatetime
     */
    public MessageScreenData(String senderUserId, String id, String teamsAthleteId, String teamId, String athleteId, String gymAccountId, String lastModifyTime, String userId, String firstName, String lastName, String emailId, String teamName, String message, String pushNotificationDatetime, SenderDetail senderDetail) {
        super();
        this.senderUserId = senderUserId;
        this.id = id;
        this.teamsAthleteId = teamsAthleteId;
        this.teamId = teamId;
        this.athleteId = athleteId;
        this.gymAccountId = gymAccountId;
        this.lastModifyTime = lastModifyTime;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.teamName = teamName;
        this.message = message;
        this.pushNotificationDatetime = pushNotificationDatetime;
        this.senderDetail = senderDetail;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamsAthleteId() {
        return teamsAthleteId;
    }

    public void setTeamsAthleteId(String teamsAthleteId) {
        this.teamsAthleteId = teamsAthleteId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getGymAccountId() {
        return gymAccountId;
    }

    public void setGymAccountId(String gymAccountId) {
        this.gymAccountId = gymAccountId;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPushNotificationDatetime() {
        return pushNotificationDatetime;
    }

    public void setPushNotificationDatetime(String pushNotificationDatetime) {
        this.pushNotificationDatetime = pushNotificationDatetime;
    }

    public SenderDetail getSenderDetail() {
        return senderDetail;
    }

    public void setSenderDetail(SenderDetail senderDetail) {
        this.senderDetail = senderDetail;
    }

}
