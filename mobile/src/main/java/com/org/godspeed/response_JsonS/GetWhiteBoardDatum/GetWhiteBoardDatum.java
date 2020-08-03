
package com.org.godspeed.response_JsonS.GetWhiteBoardDatum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetWhiteBoardDatum implements Serializable {

    private final static long serialVersionUID = 4122295890357779442L;
    @SerializedName("0")
    @Expose
    private List<com.org.godspeed.response_JsonS.GetWhiteBoardDatum._0> _0 = null;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("user_image")
    @Expose
    private String userImage;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("exercise_athlete_level_users_id")
    @Expose
    private String exerciseAthleteLevelUsersId;
    @SerializedName("sports")
    @Expose
    private List<Sport> sports = null;
    @SerializedName("selected_athlete_level")
    @Expose
    private List<SelectedAthleteLevel> selectedAthleteLevel = null;

    /**
     * No args constructor for use in serialization
     */
    public GetWhiteBoardDatum() {
    }

    /**
     * @param lastName
     * @param gender
     * @param sports
     * @param exerciseAthleteLevelUsersId
     * @param weight
     * @param emailId
     * @param userName
     * @param userId
     * @param _0
     * @param firstName
     * @param selectedAthleteLevel
     * @param userImage
     * @param userType
     */
    public GetWhiteBoardDatum(List<com.org.godspeed.response_JsonS.GetWhiteBoardDatum._0> _0, String userId, String userName, String firstName, String lastName, String emailId, String gender, String userType, String userImage, String weight, String exerciseAthleteLevelUsersId, List<Sport> sports, List<SelectedAthleteLevel> selectedAthleteLevel) {
        super();
        this._0 = _0;
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.gender = gender;
        this.userType = userType;
        this.userImage = userImage;
        this.weight = weight;
        this.exerciseAthleteLevelUsersId = exerciseAthleteLevelUsersId;
        this.sports = sports;
        this.selectedAthleteLevel = selectedAthleteLevel;
    }

    public List<com.org.godspeed.response_JsonS.GetWhiteBoardDatum._0> get0() {
        return _0;
    }

    public void set0(List<com.org.godspeed.response_JsonS.GetWhiteBoardDatum._0> _0) {
        this._0 = _0;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getExerciseAthleteLevelUsersId() {
        return exerciseAthleteLevelUsersId;
    }

    public void setExerciseAthleteLevelUsersId(String exerciseAthleteLevelUsersId) {
        this.exerciseAthleteLevelUsersId = exerciseAthleteLevelUsersId;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    public List<SelectedAthleteLevel> getSelectedAthleteLevel() {
        return selectedAthleteLevel;
    }

    public void setSelectedAthleteLevel(List<SelectedAthleteLevel> selectedAthleteLevel) {
        this.selectedAthleteLevel = selectedAthleteLevel;
    }

}
