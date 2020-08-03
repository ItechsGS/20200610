
package com.org.godspeed.loginData;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.org.godspeed.response_JsonS.athleteData.AssingProgramDetail;

import java.io.Serializable;
import java.util.List;

public class Login implements Serializable {
    @SerializedName("10")
    @Expose
    private String _10;
    @SerializedName("20")
    @Expose
    private String _20;
    @SerializedName("60")
    @Expose
    private String _60;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("password_type")
    @Expose
    private String passwordType;
    @SerializedName("school_id")
    @Expose
    private String schoolId;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("position_title_id")
    @Expose
    private String positionTitleId;
    @SerializedName("position_title_name")
    @Expose
    private String positionTitleName;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("background_image")
    @Expose
    private String backgroundImage;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("language_id")
    @Expose
    private String languageId;
    @SerializedName("language_name")
    @Expose
    private String languageName;
    @SerializedName("suit")
    @Expose
    private String suit;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("school_address")
    @Expose
    private String schoolAddress;
    @SerializedName("sport_id")
    @Expose
    private String sportId;
    @SerializedName("sport_name")
    @Expose
    private String sportName;
    @SerializedName("social_head")
    @Expose
    private String socialHead;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("athlete_heart_rate")
    @Expose
    private List<AthleteHeartRate> athleteHeartRate = null;
    @SerializedName("sports")
    @Expose
    private List<Sport> sports = null;
    @SerializedName("athlete_photo")
    @Expose
    private String athletePhoto;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("sport_level")
    @Expose
    private String sportLevel;
    @SerializedName("sport_field")
    @Expose
    private String sportField;
    @SerializedName("social_media")
    @Expose
    private String socialMedia;
    @SerializedName("music")
    @Expose
    private String music;
    @SerializedName("user_image")
    @Expose
    private String userImage;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("height_unit")
    @Expose
    private String heightUnit;
    @SerializedName("weight_unit")
    @Expose
    private String weightUnit;
    @SerializedName("neck")
    @Expose
    private String neck;
    @SerializedName("bicep")
    @Expose
    private String bicep;
    @SerializedName("chest")
    @Expose
    private String chest;
    @SerializedName("waist")
    @Expose
    private String waist;
    @SerializedName("hips")
    @Expose
    private String hips;
    @SerializedName("thigh")
    @Expose
    private String thigh;
    @SerializedName("smm")
    @Expose
    private String smm;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("body_fat")
    @Expose
    private String bodyFat;
    @SerializedName("gym_account_id")
    @Expose
    private String gymAccountId;
    @SerializedName("athlete_refcoach_id")
    @Expose
    private String athleteRefcoachId;
    @SerializedName("coach_referby")
    @Expose
    private String coachReferby;

    @SerializedName("membership_status")
    @Expose
    private String membership_status;

    @SerializedName("questionnaire")
    @Expose
    private String questionnaire;
    @SerializedName("sets")
    @Expose
    private String sets;
    @SerializedName("membership_validity")
    @Expose
    private MembershipValidity membershipValidity;
    @SerializedName("assing_program_details")
    @Expose
    private List<AssingProgramDetail> assingProgramDetails = null;
    @SerializedName("selected_athlete_level")
    @Expose
    private List<SelectedAthleteLevel> selectedAthleteLevel = null;
    @SerializedName("selected_athlete_goal")
    @Expose
    private List<SelectedAthleteGoal> selectedAthleteGoal = null;

    @SerializedName("app_menu")
    @Expose
    private List<AppMenu> appMenu = null;

    @SerializedName("athlete_level")
    @Expose
    private List<AthleteLevel> athleteLevel = null;

    @SerializedName("pillar_detail")
    @Expose
    private List<PillarMenuDetail> pillarDetail = null;


    @SerializedName("time_zone")
    @Expose
    private String timezone = null;


    /**
     * No args constructor for use in serialization
     */

//
    public Login(String timezone, String membership_status, String _10, String _20, String _60, String userId, String emailId, String passwordType, String schoolId, String schoolName, String positionTitleId, String positionTitleName, String userType, String backgroundImage, String firstName, String lastName, String userName, String dob, String gender, String deviceToken, String languageId, String languageName, String suit, String street, String zip, String country, String state, String city, String schoolAddress, String sportId, String sportName, String socialHead, List<Team> teams, String teamName, List<AthleteHeartRate> athleteHeartRate, List<Sport> sports, String athletePhoto, String address, String age, String sportLevel, String sportField, String socialMedia, String music, String userImage, String height, String weight, String heightUnit, String weightUnit, String neck, String bicep, String chest, String waist, String hips, String thigh, String smm, String paymentStatus, String bodyFat, String gymAccountId, String athleteRefcoachId, String coachReferby, String questionnaire, String sets, MembershipValidity membershipValidity, List<AssingProgramDetail> assingProgramDetails, List<SelectedAthleteLevel> selectedAthleteLevel, List<SelectedAthleteGoal> selectedAthleteGoal, List<AthleteLevel> athleteLevel,
                 List<AppMenu> appMenu, List<PillarMenuDetail> pillarMenuDetailList) {
        super();
        this._10 = _10;
        this.timezone = timezone;
        this.pillarDetail = pillarMenuDetailList;
        this.appMenu = appMenu;
        this.membership_status = membership_status;
        this._20 = _20;
        this._60 = _60;
        this.userId = userId;
        this.emailId = emailId;
        this.passwordType = passwordType;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.positionTitleId = positionTitleId;
        this.positionTitleName = positionTitleName;
        this.userType = userType;
        this.backgroundImage = backgroundImage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.dob = dob;
        this.gender = gender;
        this.deviceToken = deviceToken;
        this.languageId = languageId;
        this.languageName = languageName;
        this.suit = suit;
        this.street = street;
        this.zip = zip;
        this.country = country;
        this.state = state;
        this.city = city;
        this.schoolAddress = schoolAddress;
        this.sportId = sportId;
        this.sportName = sportName;
        this.socialHead = socialHead;
        this.teams = teams;
        this.teamName = teamName;
        this.athleteHeartRate = athleteHeartRate;
        this.sports = sports;
        this.athletePhoto = athletePhoto;
        this.address = address;
        this.age = age;
        this.sportLevel = sportLevel;
        this.sportField = sportField;
        this.socialMedia = socialMedia;
        this.music = music;
        this.userImage = userImage;
        this.height = height;
        this.weight = weight;
        this.heightUnit = heightUnit;
        this.weightUnit = weightUnit;
        this.neck = neck;
        this.bicep = bicep;
        this.chest = chest;
        this.waist = waist;
        this.hips = hips;
        this.thigh = thigh;
        this.smm = smm;
        this.paymentStatus = paymentStatus;
        this.bodyFat = bodyFat;
        this.gymAccountId = gymAccountId;
        this.athleteRefcoachId = athleteRefcoachId;
        this.coachReferby = coachReferby;
        this.questionnaire = questionnaire;
        this.sets = sets;
        this.membershipValidity = membershipValidity;
        this.assingProgramDetails = assingProgramDetails;
        this.selectedAthleteLevel = selectedAthleteLevel;
        this.selectedAthleteGoal = selectedAthleteGoal;
        this.athleteLevel = athleteLevel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSocialHead() {
        return socialHead;
    }

    public void setSocialHead(String socialHead) {
        this.socialHead = socialHead;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<AthleteHeartRate> getAthleteHeartRate() {
        return athleteHeartRate;
    }

    public void setAthleteHeartRate(List<AthleteHeartRate> athleteHeartRate) {
        this.athleteHeartRate = athleteHeartRate;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    public String getAthletePhoto() {
        return athletePhoto;
    }

    public void setAthletePhoto(String athletePhoto) {
        this.athletePhoto = athletePhoto;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSportLevel() {
        return sportLevel;
    }

    public void setSportLevel(String sportLevel) {
        this.sportLevel = sportLevel;
    }

    public String getSportField() {
        return sportField;
    }

    public void setSportField(String sportField) {
        this.sportField = sportField;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getBicep() {
        return bicep;
    }

    public void setBicep(String bicep) {
        this.bicep = bicep;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHips() {
        return hips;
    }

    public void setHips(String hips) {
        this.hips = hips;
    }

    public String getThigh() {
        return thigh;
    }

    public void setThigh(String thigh) {
        this.thigh = thigh;
    }

    public String getSmm() {
        return smm;
    }

    public void setSmm(String smm) {
        this.smm = smm;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(String bodyFat) {
        this.bodyFat = bodyFat;
    }

    public String getGymAccountId() {
        return gymAccountId;
    }

    public void setGymAccountId(String gymAccountId) {
        this.gymAccountId = gymAccountId;
    }

    public String getAthleteRefcoachId() {
        return athleteRefcoachId;
    }

    public void setAthleteRefcoachId(String athleteRefcoachId) {
        this.athleteRefcoachId = athleteRefcoachId;
    }

    public String getCoachReferby() {
        return coachReferby;
    }

    public void setCoachReferby(String coachReferby) {
        this.coachReferby = coachReferby;
    }

    public String getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public MembershipValidity getMembershipValidity() {
        return membershipValidity;
    }

    public void setMembershipValidity(MembershipValidity membershipValidity) {
        this.membershipValidity = membershipValidity;
    }

    public List<AssingProgramDetail> getAssingProgramDetails() {
        return assingProgramDetails;
    }

    public void setAssingProgramDetails(List<AssingProgramDetail> assingProgramDetails) {
        this.assingProgramDetails = assingProgramDetails;
    }

    public List<SelectedAthleteLevel> getSelectedAthleteLevel() {
        return selectedAthleteLevel;
    }

    public void setSelectedAthleteLevel(List<SelectedAthleteLevel> selectedAthleteLevel) {
        this.selectedAthleteLevel = selectedAthleteLevel;
    }

    public List<SelectedAthleteGoal> getSelectedAthleteGoal() {
        return selectedAthleteGoal;
    }

    public void setSelectedAthleteGoal(List<SelectedAthleteGoal> selectedAthleteGoal) {
        this.selectedAthleteGoal = selectedAthleteGoal;
    }

    public List<AthleteLevel> getAthleteLevel() {
        return athleteLevel;
    }

    public void setAthleteLevel(List<AthleteLevel> athleteLevel) {
        this.athleteLevel = athleteLevel;
    }

    public String get_10() {
        return _10;
    }

    public void set_10(String _10) {
        this._10 = _10;
    }

    public String get_60() {
        return _60;
    }

    public void set_60(String _60) {
        this._60 = _60;
    }

    public String get_20() {
        return _20;
    }

    public void set_20(String _20) {
        this._20 = _20;
    }

    public String getMembership_status() {
        return membership_status;
    }

    public void setMembership_status(String membership_status) {
        this.membership_status = membership_status;
    }

    public List<AppMenu> getAppMenu() {
        return appMenu;
    }

    public void setAppMenu(List<AppMenu> appMenu) {
        this.appMenu = appMenu;
    }

    public List<PillarMenuDetail> getPillarDetail() {
        return pillarDetail;
    }

    public void setPillarDetail(List<PillarMenuDetail> pillarDetail) {
        this.pillarDetail = pillarDetail;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}

//
//public class Login implements Serializable {
//
//    @SerializedName("10")
//    @Expose
//    private String _10;
//    @SerializedName("20")
//    @Expose
//    private String _20;
//    @SerializedName("60")
//    @Expose
//    private String _60;
//    @SerializedName("user_id")
//    @Expose
//    private String userId;
//    @SerializedName("user_name")
//    @Expose
//    private String userName;
//    @SerializedName("first_name")
//    @Expose
//    private String firstName;
//    @SerializedName("last_name")
//    @Expose
//    private String lastName;
//    @SerializedName("emailId")
//    @Expose
//    private String emailId;
//    @SerializedName("background_image")
//    @Expose
//    private String backgroundImage;
//    @SerializedName("gender")
//    @Expose
//    private String gender;
//    @SerializedName("user_type")
//    @Expose
//    private String userType;
//    @SerializedName("parent_id")
//    @Expose
//    private String parentId;
//    @SerializedName("device_id")
//    @Expose
//    private String deviceId;
//    @SerializedName("device_token")
//    @Expose
//    private String deviceToken;
//    @SerializedName("token")
//    @Expose
//    private String token;
//    @SerializedName("link_expire")
//    @Expose
//    private String linkExpire;
//    @SerializedName("status")
//    @Expose
//    private String status;
//    @SerializedName("creationTime")
//    @Expose
//    private String creationTime;
//    @SerializedName("school_id")
//    @Expose
//    private String schoolId;
//    @SerializedName("position_title_id")
//    @Expose
//    private String positionTitleId;
//    @SerializedName("dob")
//    @Expose
//    private String dob;
//    @SerializedName("language_id")
//    @Expose
//    private String languageId;
//    @SerializedName("suit")
//    @Expose
//    private String suit;
//    @SerializedName("street")
//    @Expose
//    private String street;
//    @SerializedName("zip")
//    @Expose
//    private String zip;
//    @SerializedName("country")
//    @Expose
//    private String country;
//    @SerializedName("state")
//    @Expose
//    private String state;
//    @SerializedName("city")
//    @Expose
//    private String city;
//    @SerializedName("school_address")
//    @Expose
//    private String schoolAddress;
//    @SerializedName("sport_id")
//    @Expose
//    private String sportId;
//    @SerializedName("social_head")
//    @Expose
//    private String socialHead;
//    @SerializedName("team_id")
//    @Expose
//    private String teamId;
//    @SerializedName("athlete_photo")
//    @Expose
//    private String athletePhoto;
//    @SerializedName("address")
//    @Expose
//    private String address;
//    @SerializedName("age")
//    @Expose
//    private String age;
//    @SerializedName("sports")
//    @Expose
//    private List<com.org.godspeed.response_JsonS.athleteData.Sport> sports = null;
//
//    @SerializedName("sport_level")
//    @Expose
//    private String sportLevel;
//    @SerializedName("sport_field")
//    @Expose
//    private String sportField;
//    @SerializedName("social_media")
//    @Expose
//    private String socialMedia;
//    @SerializedName("music")
//    @Expose
//    private String music;
//    @SerializedName("user_image")
//    @Expose
//    private String userImage;
//    @SerializedName("weight")
//    @Expose
//    private String weight;
//    @SerializedName("smm")
//    @Expose
//    private String smm;
//    @SerializedName("height")
//    @Expose
//    private String height;
//    @SerializedName("body_fat")
//    @Expose
//    private String bodyFat;
//    @SerializedName("gym_account_id")
//    @Expose
//    private String gymAccountId;
//    @SerializedName("last_modify_time")
//    @Expose
//    private String lastModifyTime;
//    @SerializedName("athlete_refcoach_id")
//    @Expose
//    private String athleteRefcoachId;
//    @SerializedName("coach_referby")
//    @Expose
//    private String coachReferby;
//    @SerializedName("athlete_attendance")
//    @Expose
//    private String athleteAttendance;
//    @SerializedName("weight_unit")
//    @Expose
//    private String weightUnit;
//    @SerializedName("height_unit")
//    @Expose
//    private String heightUnit;
//    @SerializedName("password_type")
//    @Expose
//    private String passwordType;
//    @SerializedName("payment_status")
//    @Expose
//    private String paymentStatus;
//    @SerializedName("tshirt")
//    @Expose
//    private String tshirt;
//    @SerializedName("evaluation_date")
//    @Expose
//    private String evaluationDate;
//    @SerializedName("overhead_squat")
//    @Expose
//    private String overheadSquat;
//    @SerializedName("linear_lunge")
//    @Expose
//    private String linearLunge;
//    @SerializedName("lateral_lunge")
//    @Expose
//    private String lateralLunge;
//    @SerializedName("shoulder_group")
//    @Expose
//    private String shoulderGroup;
//    @SerializedName("reaction")
//    @Expose
//    private String reaction;
//    @SerializedName("half_pro")
//    @Expose
//    private String halfPro;
//    @SerializedName("pro")
//    @Expose
//    private String pro;
//    @SerializedName("t_drill")
//    @Expose
//    private String tDrill;
//    @SerializedName("vertical_jump")
//    @Expose
//    private String verticalJump;
//    @SerializedName("ppo")
//    @Expose
//    private String ppo;
//    @SerializedName("rsi")
//    @Expose
//    private String rsi;
//    @SerializedName("impulse")
//    @Expose
//    private String impulse;
//    @SerializedName("broad_jump")
//    @Expose
//    private String broadJump;
//    @SerializedName("3_broad_jump")
//    @Expose
//    private String _3BroadJump;
//    @SerializedName("punch_right")
//    @Expose
//    private String punchRight;
//    @SerializedName("punch_left")
//    @Expose
//    private String punchLeft;
//    @SerializedName("row_Right")
//    @Expose
//    private String rowRight;
//    @SerializedName("row_Left")
//    @Expose
//    private String rowLeft;
//    @SerializedName("login")
//    @Expose
//    private String login;
//    @SerializedName("language_name")
//    @Expose
//    private String languageName;
//    @SerializedName("school_name")
//    @Expose
//    private String schoolName;
//    @SerializedName("sport_name")
//    @Expose
//    private String sportName;
//    @SerializedName("team_name")
//    @Expose
//    private String teamName;
//    @SerializedName("exercise_athlete_level_users_id")
//    @Expose
//    private String exerciseAthleteLevelUsersId;
//    @SerializedName("teams")
//    @Expose
//    private List<com.org.godspeed.response_JsonS.athleteData.Team> teams = null;
//    @SerializedName("athlete_heart_rate")
//    @Expose
//    private List<com.org.godspeed.response_JsonS.athleteData.AthleteHeartRate> athleteHeartRate = null;
//    @SerializedName("selected_athlete_level")
//    @Expose
//    private List<com.org.godspeed.response_JsonS.athleteData.SelectedAthleteLevel> selectedAthleteLevel = null;
//    @SerializedName("assing_program_details")
//    @Expose
//    private List<com.org.godspeed.response_JsonS.athleteData.AssingProgramDetail> assingProgramDetails = null;
//    @SerializedName("selected_athlete_goal")
//    @Expose
//    private List<com.org.godspeed.response_JsonS.athleteData.SelectedAthleteGoal> selectedAthleteGoal = null;
//    @SerializedName("athlete_active_exercise_status")
//    @Expose
//    private AthleteActiveExerciseStatus athleteActiveExerciseStatus;
//    @SerializedName("athlete_level")
//    @Expose
//    private List<com.org.godspeed.response_JsonS.athleteData.AthleteLevel> athleteLevel = null;
//    //private final static long serialVersionUID = 4869648231090998919L;
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public Login() {
//    }
//
//    /**
//     *
//     * @param teamName
//     * @param passwordType
//     * @param selectedAthleteGoal
//     * @param athleteHeartRate
//     * @param rsi
//     * @param schoolName
//     * @param lateralLunge
//     * @param street
//     * @param sports
//     * @param userImage
//     * @param sportLevel
//     * @param bodyFat
//     * @param rowRight
//     * @param linkExpire
//     * @param userType
//     * @param city
//     * @param gymAccountId
//     * @param emailId
//     * @param height
//     * @param athleteRefcoachId
//     * @param userId
//     * @param age
//     * @param userName
//     * @param gender
//     * @param login
//     * @param creationTime
//     * @param athleteAttendance
//     * @param deviceId
//     * @param _3BroadJump
//     * @param rowLeft
//     * @param lastName
//     * @param verticalJump
//     * @param linearLunge
//     * @param status
//     * @param sportName
//     * @param broadJump
//     * @param tDrill
//     * @param backgroundImage
//     * @param paymentStatus
//     * @param evaluationDate
//     * @param athletePhoto
//     * @param _60
//     * @param languageName
//     * @param country
//     * @param teams
//     * @param _20
//     * @param ppo
//     * @param coachReferby
//     * @param punchRight
//     * @param reaction
//     * @param athleteLevel
//     * @param positionTitleId
//     * @param weight
//     * @param punchLeft
//     * @param socialMedia
//     * @param state
//     * @param socialHead
//     * @param assingProgramDetails
//     * @param teamId
//     * @param impulse
//     * @param parentId
//     * @param sportId
//     * @param overheadSquat
//     * @param token
//     * @param heightUnit
//     * @param _10
//     * @param tshirt
//     * @param firstName
//     * @param sportField
//     * @param exerciseAthleteLevelUsersId
//     * @param music
//     * @param zip
//     * @param weightUnit
//     * @param smm
//     * @param shoulderGroup
//     * @param athleteActiveExerciseStatus
//     * @param selectedAthleteLevel
//     * @param schoolAddress
//     * @param languageId
//     * @param halfPro
//     * @param address
//     * @param deviceToken
//     * @param dob
//     * @param pro
//     * @param suit
//     * @param schoolId
//     * @param lastModifyTime
//     */
//    public Login(String _10, String _20, String _60, String userId, String userName, String firstName, String lastName, String emailId, String backgroundImage, String gender, String userType, String parentId, String deviceId, String deviceToken, String token, String linkExpire, String status, String creationTime, String schoolId, String positionTitleId, String dob, String languageId, String suit, String street, String zip, String country, String state, String city, String schoolAddress, String sportId, String socialHead, String teamId, String athletePhoto, String address, String age, List<com.org.godspeed.response_JsonS.athleteData.Sport> sports, String sportLevel, String sportField, String socialMedia, String music, String userImage, String weight, String smm, String height, String bodyFat, String gymAccountId, String lastModifyTime, String athleteRefcoachId, String coachReferby, String athleteAttendance, String weightUnit, String heightUnit, String passwordType, String paymentStatus, String tshirt, String evaluationDate, String overheadSquat, String linearLunge, String lateralLunge, String shoulderGroup, String reaction, String halfPro, String pro, String tDrill, String verticalJump, String ppo, String rsi, String impulse, String broadJump, String _3BroadJump, String punchRight, String punchLeft, String rowRight, String rowLeft, String login, String languageName, String schoolName, String sportName, String teamName, String exerciseAthleteLevelUsersId, List<com.org.godspeed.response_JsonS.athleteData.Team> teams, List<com.org.godspeed.response_JsonS.athleteData.AthleteHeartRate> athleteHeartRate, List<com.org.godspeed.response_JsonS.athleteData.SelectedAthleteLevel> selectedAthleteLevel, List<com.org.godspeed.response_JsonS.athleteData.AssingProgramDetail> assingProgramDetails, List<com.org.godspeed.response_JsonS.athleteData.SelectedAthleteGoal> selectedAthleteGoal, AthleteActiveExerciseStatus athleteActiveExerciseStatus, List<com.org.godspeed.response_JsonS.athleteData.AthleteLevel> athleteLevel) {
//        super();
//        this._10 = _10;
//        this._20 = _20;
//        this._60 = _60;
//        this.userId = userId;
//        this.userName = userName;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.emailId = emailId;
//        this.backgroundImage = backgroundImage;
//        this.gender = gender;
//        this.userType = userType;
//        this.parentId = parentId;
//        this.deviceId = deviceId;
//        this.deviceToken = deviceToken;
//        this.token = token;
//        this.linkExpire = linkExpire;
//        this.status = status;
//        this.creationTime = creationTime;
//        this.schoolId = schoolId;
//        this.positionTitleId = positionTitleId;
//        this.dob = dob;
//        this.languageId = languageId;
//        this.suit = suit;
//        this.street = street;
//        this.zip = zip;
//        this.country = country;
//        this.state = state;
//        this.city = city;
//        this.schoolAddress = schoolAddress;
//        this.sportId = sportId;
//        this.socialHead = socialHead;
//        this.teamId = teamId;
//        this.athletePhoto = athletePhoto;
//        this.address = address;
//        this.age = age;
//        this.sports = sports;
//        this.sportLevel = sportLevel;
//        this.sportField = sportField;
//        this.socialMedia = socialMedia;
//        this.music = music;
//        this.userImage = userImage;
//        this.weight = weight;
//        this.smm = smm;
//        this.height = height;
//        this.bodyFat = bodyFat;
//        this.gymAccountId = gymAccountId;
//        this.lastModifyTime = lastModifyTime;
//        this.athleteRefcoachId = athleteRefcoachId;
//        this.coachReferby = coachReferby;
//        this.athleteAttendance = athleteAttendance;
//        this.weightUnit = weightUnit;
//        this.heightUnit = heightUnit;
//        this.passwordType = passwordType;
//        this.paymentStatus = paymentStatus;
//        this.tshirt = tshirt;
//        this.evaluationDate = evaluationDate;
//        this.overheadSquat = overheadSquat;
//        this.linearLunge = linearLunge;
//        this.lateralLunge = lateralLunge;
//        this.shoulderGroup = shoulderGroup;
//        this.reaction = reaction;
//        this.halfPro = halfPro;
//        this.pro = pro;
//        this.tDrill = tDrill;
//        this.verticalJump = verticalJump;
//        this.ppo = ppo;
//        this.rsi = rsi;
//        this.impulse = impulse;
//        this.broadJump = broadJump;
//        this._3BroadJump = _3BroadJump;
//        this.punchRight = punchRight;
//        this.punchLeft = punchLeft;
//        this.rowRight = rowRight;
//        this.rowLeft = rowLeft;
//        this.login = login;
//        this.languageName = languageName;
//        this.schoolName = schoolName;
//        this.sportName = sportName;
//        this.teamName = teamName;
//        this.exerciseAthleteLevelUsersId = exerciseAthleteLevelUsersId;
//        this.teams = teams;
//        this.athleteHeartRate = athleteHeartRate;
//        this.selectedAthleteLevel = selectedAthleteLevel;
//        this.assingProgramDetails = assingProgramDetails;
//        this.selectedAthleteGoal = selectedAthleteGoal;
//        this.athleteActiveExerciseStatus = athleteActiveExerciseStatus;
//        this.athleteLevel = athleteLevel;
//    }
//
//    public String get10() {
//        return _10;
//    }
//
//    public void set10(String _10) {
//        this._10 = _10;
//    }
//
//    public String get20() {
//        return _20;
//    }
//
//    public void set20(String _20) {
//        this._20 = _20;
//    }
//
//    public String get60() {
//        return _60;
//    }
//
//    public void set60(String _60) {
//        this._60 = _60;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmailId() {
//        return emailId;
//    }
//
//    public void setEmailId(String emailId) {
//        this.emailId = emailId;
//    }
//
//    public String getBackgroundImage() {
//        return backgroundImage;
//    }
//
//    public void setBackgroundImage(String backgroundImage) {
//        this.backgroundImage = backgroundImage;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getUserType() {
//        return userType;
//    }
//
//    public void setUserType(String userType) {
//        this.userType = userType;
//    }
//
//    public String getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(String parentId) {
//        this.parentId = parentId;
//    }
//
//    public String getDeviceId() {
//        return deviceId;
//    }
//
//    public void setDeviceId(String deviceId) {
//        this.deviceId = deviceId;
//    }
//
//    public String getDeviceToken() {
//        return deviceToken;
//    }
//
//    public void setDeviceToken(String deviceToken) {
//        this.deviceToken = deviceToken;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public String getLinkExpire() {
//        return linkExpire;
//    }
//
//    public void setLinkExpire(String linkExpire) {
//        this.linkExpire = linkExpire;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getCreationTime() {
//        return creationTime;
//    }
//
//    public void setCreationTime(String creationTime) {
//        this.creationTime = creationTime;
//    }
//
//    public String getSchoolId() {
//        return schoolId;
//    }
//
//    public void setSchoolId(String schoolId) {
//        this.schoolId = schoolId;
//    }
//
//    public String getPositionTitleId() {
//        return positionTitleId;
//    }
//
//    public void setPositionTitleId(String positionTitleId) {
//        this.positionTitleId = positionTitleId;
//    }
//
//    public String getDob() {
//        return dob;
//    }
//
//    public void setDob(String dob) {
//        this.dob = dob;
//    }
//
//    public String getLanguageId() {
//        return languageId;
//    }
//
//    public void setLanguageId(String languageId) {
//        this.languageId = languageId;
//    }
//
//    public String getSuit() {
//        return suit;
//    }
//
//    public void setSuit(String suit) {
//        this.suit = suit;
//    }
//
//    public String getStreet() {
//        return street;
//    }
//
//    public void setStreet(String street) {
//        this.street = street;
//    }
//
//    public String getZip() {
//        return zip;
//    }
//
//    public void setZip(String zip) {
//        this.zip = zip;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getSchoolAddress() {
//        return schoolAddress;
//    }
//
//    public void setSchoolAddress(String schoolAddress) {
//        this.schoolAddress = schoolAddress;
//    }
//
//    public String getSportId() {
//        return sportId;
//    }
//
//    public void setSportId(String sportId) {
//        this.sportId = sportId;
//    }
//
//    public String getSocialHead() {
//        return socialHead;
//    }
//
//    public void setSocialHead(String socialHead) {
//        this.socialHead = socialHead;
//    }
//
//    public String getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(String teamId) {
//        this.teamId = teamId;
//    }
//
//    public String getAthletePhoto() {
//        return athletePhoto;
//    }
//
//    public void setAthletePhoto(String athletePhoto) {
//        this.athletePhoto = athletePhoto;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getAge() {
//        return age;
//    }
//
//    public void setAge(String age) {
//        this.age = age;
//    }
//
//    public List<com.org.godspeed.response_JsonS.athleteData.Sport> getSports() {
//        return sports;
//    }
//
//    public void setSports(List<Sport> sports) {
//        this.sports = sports;
//    }
//
//    public String getSportLevel() {
//        return sportLevel;
//    }
//
//    public void setSportLevel(String sportLevel) {
//        this.sportLevel = sportLevel;
//    }
//
//    public String getSportField() {
//        return sportField;
//    }
//
//    public void setSportField(String sportField) {
//        this.sportField = sportField;
//    }
//
//    public String getSocialMedia() {
//        return socialMedia;
//    }
//
//    public void setSocialMedia(String socialMedia) {
//        this.socialMedia = socialMedia;
//    }
//
//    public String getMusic() {
//        return music;
//    }
//
//    public void setMusic(String music) {
//        this.music = music;
//    }
//
//    public String getUserImage() {
//        return userImage;
//    }
//
//    public void setUserImage(String userImage) {
//        this.userImage = userImage;
//    }
//
//    public String getWeight() {
//        return weight;
//    }
//
//    public void setWeight(String weight) {
//        this.weight = weight;
//    }
//
//    public String getSmm() {
//        return smm;
//    }
//
//    public void setSmm(String smm) {
//        this.smm = smm;
//    }
//
//    public String getHeight() {
//        return height;
//    }
//
//    public void setHeight(String height) {
//        this.height = height;
//    }
//
//    public String getBodyFat() {
//        return bodyFat;
//    }
//
//    public void setBodyFat(String bodyFat) {
//        this.bodyFat = bodyFat;
//    }
//
//    public String getGymAccountId() {
//        return gymAccountId;
//    }
//
//    public void setGymAccountId(String gymAccountId) {
//        this.gymAccountId = gymAccountId;
//    }
//
//    public String getLastModifyTime() {
//        return lastModifyTime;
//    }
//
//    public void setLastModifyTime(String lastModifyTime) {
//        this.lastModifyTime = lastModifyTime;
//    }
//
//    public String getAthleteRefcoachId() {
//        return athleteRefcoachId;
//    }
//
//    public void setAthleteRefcoachId(String athleteRefcoachId) {
//        this.athleteRefcoachId = athleteRefcoachId;
//    }
//
//    public String getCoachReferby() {
//        return coachReferby;
//    }
//
//    public void setCoachReferby(String coachReferby) {
//        this.coachReferby = coachReferby;
//    }
//
//    public String getAthleteAttendance() {
//        return athleteAttendance;
//    }
//
//    public void setAthleteAttendance(String athleteAttendance) {
//        this.athleteAttendance = athleteAttendance;
//    }
//
//    public String getWeightUnit() {
//        return weightUnit;
//    }
//
//    public void setWeightUnit(String weightUnit) {
//        this.weightUnit = weightUnit;
//    }
//
//    public String getHeightUnit() {
//        return heightUnit;
//    }
//
//    public void setHeightUnit(String heightUnit) {
//        this.heightUnit = heightUnit;
//    }
//
//    public String getPasswordType() {
//        return passwordType;
//    }
//
//    public void setPasswordType(String passwordType) {
//        this.passwordType = passwordType;
//    }
//
//    public String getPaymentStatus() {
//        return paymentStatus;
//    }
//
//    public void setPaymentStatus(String paymentStatus) {
//        this.paymentStatus = paymentStatus;
//    }
//
//    public String getTshirt() {
//        return tshirt;
//    }
//
//    public void setTshirt(String tshirt) {
//        this.tshirt = tshirt;
//    }
//
//    public String getEvaluationDate() {
//        return evaluationDate;
//    }
//
//    public void setEvaluationDate(String evaluationDate) {
//        this.evaluationDate = evaluationDate;
//    }
//
//    public String getOverheadSquat() {
//        return overheadSquat;
//    }
//
//    public void setOverheadSquat(String overheadSquat) {
//        this.overheadSquat = overheadSquat;
//    }
//
//    public String getLinearLunge() {
//        return linearLunge;
//    }
//
//    public void setLinearLunge(String linearLunge) {
//        this.linearLunge = linearLunge;
//    }
//
//    public String getLateralLunge() {
//        return lateralLunge;
//    }
//
//    public void setLateralLunge(String lateralLunge) {
//        this.lateralLunge = lateralLunge;
//    }
//
//    public String getShoulderGroup() {
//        return shoulderGroup;
//    }
//
//    public void setShoulderGroup(String shoulderGroup) {
//        this.shoulderGroup = shoulderGroup;
//    }
//
//    public String getReaction() {
//        return reaction;
//    }
//
//    public void setReaction(String reaction) {
//        this.reaction = reaction;
//    }
//
//    public String getHalfPro() {
//        return halfPro;
//    }
//
//    public void setHalfPro(String halfPro) {
//        this.halfPro = halfPro;
//    }
//
//    public String getPro() {
//        return pro;
//    }
//
//    public void setPro(String pro) {
//        this.pro = pro;
//    }
//
//    public String getTDrill() {
//        return tDrill;
//    }
//
//    public void setTDrill(String tDrill) {
//        this.tDrill = tDrill;
//    }
//
//    public String getVerticalJump() {
//        return verticalJump;
//    }
//
//    public void setVerticalJump(String verticalJump) {
//        this.verticalJump = verticalJump;
//    }
//
//    public String getPpo() {
//        return ppo;
//    }
//
//    public void setPpo(String ppo) {
//        this.ppo = ppo;
//    }
//
//    public String getRsi() {
//        return rsi;
//    }
//
//    public void setRsi(String rsi) {
//        this.rsi = rsi;
//    }
//
//    public String getImpulse() {
//        return impulse;
//    }
//
//    public void setImpulse(String impulse) {
//        this.impulse = impulse;
//    }
//
//    public String getBroadJump() {
//        return broadJump;
//    }
//
//    public void setBroadJump(String broadJump) {
//        this.broadJump = broadJump;
//    }
//
//    public String get3BroadJump() {
//        return _3BroadJump;
//    }
//
//    public void set3BroadJump(String _3BroadJump) {
//        this._3BroadJump = _3BroadJump;
//    }
//
//    public String getPunchRight() {
//        return punchRight;
//    }
//
//    public void setPunchRight(String punchRight) {
//        this.punchRight = punchRight;
//    }
//
//    public String getPunchLeft() {
//        return punchLeft;
//    }
//
//    public void setPunchLeft(String punchLeft) {
//        this.punchLeft = punchLeft;
//    }
//
//    public String getRowRight() {
//        return rowRight;
//    }
//
//    public void setRowRight(String rowRight) {
//        this.rowRight = rowRight;
//    }
//
//    public String getRowLeft() {
//        return rowLeft;
//    }
//
//    public void setRowLeft(String rowLeft) {
//        this.rowLeft = rowLeft;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getLanguageName() {
//        return languageName;
//    }
//
//    public void setLanguageName(String languageName) {
//        this.languageName = languageName;
//    }
//
//    public String getSchoolName() {
//        return schoolName;
//    }
//
//    public void setSchoolName(String schoolName) {
//        this.schoolName = schoolName;
//    }
//
//    public String getSportName() {
//        return sportName;
//    }
//
//    public void setSportName(String sportName) {
//        this.sportName = sportName;
//    }
//
//    public String getTeamName() {
//        return teamName;
//    }
//
//    public void setTeamName(String teamName) {
//        this.teamName = teamName;
//    }
//
//    public String getExerciseAthleteLevelUsersId() {
//        return exerciseAthleteLevelUsersId;
//    }
//
//    public void setExerciseAthleteLevelUsersId(String exerciseAthleteLevelUsersId) {
//        this.exerciseAthleteLevelUsersId = exerciseAthleteLevelUsersId;
//    }
//
//    public List<com.org.godspeed.response_JsonS.athleteData.Team> getTeams() {
//        return teams;
//    }
//
//    public void setTeams(List<Team> teams) {
//        this.teams = teams;
//    }
//
//    public List<com.org.godspeed.response_JsonS.athleteData.AthleteHeartRate> getAthleteHeartRate() {
//        return athleteHeartRate;
//    }
//
//    public void setAthleteHeartRate(List<AthleteHeartRate> athleteHeartRate) {
//        this.athleteHeartRate = athleteHeartRate;
//    }
//
//    public List<com.org.godspeed.response_JsonS.athleteData.SelectedAthleteLevel> getSelectedAthleteLevel() {
//        return selectedAthleteLevel;
//    }
//
//    public void setSelectedAthleteLevel(List<SelectedAthleteLevel> selectedAthleteLevel) {
//        this.selectedAthleteLevel = selectedAthleteLevel;
//    }
//
//    public List<com.org.godspeed.response_JsonS.athleteData.AssingProgramDetail> getAssingProgramDetails() {
//        return assingProgramDetails;
//    }
//
//    public void setAssingProgramDetails(List<AssingProgramDetail> assingProgramDetails) {
//        this.assingProgramDetails = assingProgramDetails;
//    }
//
//    public List<com.org.godspeed.response_JsonS.athleteData.SelectedAthleteGoal> getSelectedAthleteGoal() {
//        return selectedAthleteGoal;
//    }
//
//    public void setSelectedAthleteGoal(List<SelectedAthleteGoal> selectedAthleteGoal) {
//        this.selectedAthleteGoal = selectedAthleteGoal;
//    }
//
//    public AthleteActiveExerciseStatus getAthleteActiveExerciseStatus() {
//        return athleteActiveExerciseStatus;
//    }
//
//    public void setAthleteActiveExerciseStatus(AthleteActiveExerciseStatus athleteActiveExerciseStatus) {
//        this.athleteActiveExerciseStatus = athleteActiveExerciseStatus;
//    }
//
//    public List<com.org.godspeed.response_JsonS.athleteData.AthleteLevel> getAthleteLevel() {
//        return athleteLevel;
//    }
//
//    public void setAthleteLevel(List<AthleteLevel> athleteLevel) {
//        this.athleteLevel = athleteLevel;
//    }
////{
////
////    @SerializedName("user_id")
////    @Expose
////    private String userId;
////    @SerializedName("emailId")
////    @Expose
////    private String emailId;
////    @SerializedName("password_type")
////    @Expose
////    private String passwordType;
////    @SerializedName("school_id")
////    @Expose
////    private String schoolId;
////    @SerializedName("school_name")
////    @Expose
////    private String schoolName;
////    @SerializedName("position_title_id")
////    @Expose
////    private String positionTitleId;
////    @SerializedName("position_title_name")
////    @Expose
////    private String positionTitleName;
////    @SerializedName("user_type")
////    @Expose
////    private String userType;
////    @SerializedName("background_image")
////    @Expose
////    private String backgroundImage;
////    @SerializedName("first_name")
////    @Expose
////    private String firstName;
////    @SerializedName("last_name")
////    @Expose
////    private String lastName;
////    //private final static long serialVersionUID = 1062112176857328541L;
////    @SerializedName("dob")
////    @Expose
////    private String dob;
////    @SerializedName("gender")
////    @Expose
////    private String gender;
////    @SerializedName("device_token")
////    @Expose
////    private String deviceToken;
////    @SerializedName("language_id")
////    @Expose
////    private String languageId;
////    @SerializedName("language_name")
////    @Expose
////    private String languageName;
////    @SerializedName("suit")
////    @Expose
////    private String suit;
////    @SerializedName("street")
////    @Expose
////    private String street;
////    @SerializedName("zip")
////    @Expose
////    private String zip;
////    @SerializedName("country")
////    @Expose
////    private String country;
////    @SerializedName("state")
////    @Expose
////    private String state;
////    @SerializedName("city")
////    @Expose
////    private String city;
////    @SerializedName("school_address")
////    @Expose
////    private String schoolAddress;
////    @SerializedName("sport_id")
////    @Expose
////    private String sportId;
////    @SerializedName("sport_name")
////    @Expose
////    private String sportName;
////    @SerializedName("social_head")
////    @Expose
////    private String socialHead;
////    @SerializedName("teams")
////    @Expose
////    private List<Team> teams = null;
////    @SerializedName("team_name")
////    @Expose
////    private String teamName;
////    @SerializedName("user_name")
////    @Expose
////    private String userName;
////    @SerializedName("athlete_heart_rate")
////    @Expose
////    private List<AthleteHeartRate> athleteHeartRate = null;
////    @SerializedName("athlete_photo")
////    @Expose
////    private String athletePhoto;
////    @SerializedName("address")
////    @Expose
////    private String address;
////    @SerializedName("age")
////    @Expose
////    private String age;
////    @SerializedName("sport_level")
////    @Expose
////    private String sportLevel;
////    @SerializedName("sport_field")
////    @Expose
////    private String sportField;
////    @SerializedName("social_media")
////    @Expose
////    private String socialMedia;
////    @SerializedName("music")
////    @Expose
////    private String music;
////    @SerializedName("user_image")
////    @Expose
////    private String userImage;
////    @SerializedName("height")
////    @Expose
////    private String height;
////    @SerializedName("weight")
////    @Expose
////    private String weight;
////    @SerializedName("height_unit")
////    @Expose
////    private String heightUnit;
////    @SerializedName("weight_unit")
////    @Expose
////    private String weightUnit;
////    @SerializedName("smm")
////    @Expose
////    private String smm;
////    @SerializedName("sports")
////    @Expose
////    private List<Sport> sports = null;
////    @SerializedName("body_fat")
////    @Expose
////    private String bodyFat;
////    @SerializedName("gym_account_id")
////    @Expose
////    private String gymAccountId;
////    @SerializedName("athlete_refcoach_id")
////    @Expose
////    private String athleteRefcoachId;
////    @SerializedName("coach_referby")
////    @Expose
////    private String coachReferby;
////    @SerializedName("questionnaire")
////    @Expose
////    private String questionnaire;
////    @SerializedName("sets")
////    @Expose
////    private String sets;
////    @SerializedName("assing_program_details")
////    @Expose
////    private List<AssingProgramDetail> assingProgramDetails = null;
////    @SerializedName("selected_athlete_level")
////    @Expose
////    private List<SelectedAthleteLevel> selectedAthleteLevel = null;
////    @SerializedName("selected_athlete_goal")
////    @Expose
////    private List<SelectedAthleteGoal> selectedAthleteGoal = null;
////    @SerializedName("athlete_level")
////    @Expose
////    private List<AthleteLevel> athleteLevel = null;
////    @SerializedName("payment_status")
////    @Expose
////    private String paymentStatus;
////
////    /**
////     * No args constructor for use in serialization
////     *
////     */
////    public Login() {
////    }
////
////    /**
////     *
////     * @param teamName
////     * @param passwordType
////     * @param selectedAthleteGoal
////     * @param athleteHeartRate
////     * @param schoolName
////     * @param street
////     * @param sports
////     * @param userImage
////     * @param sportLevel
////     * @param bodyFat
////     * @param city
////     * @param userType
////     * @param gymAccountId
////     * @param questionnaire
////     * @param emailId
////     * @param height
////     * @param athleteRefcoachId
////     * @param userId
////     * @param age
////     * @param sets
////     * @param gender
////     * @param userName
////     * @param lastName
////     * @param sportName
////     * @param backgroundImage
////     * @param paymentStatus
////     * @param athletePhoto
////     * @param languageName
////     * @param country
////     * @param teams
////     * @param coachReferby
////     * @param athleteLevel
////     * @param positionTitleId
////     * @param weight
////     * @param socialMedia
////     * @param state
////     * @param socialHead
////     * @param assingProgramDetails
////     * @param sportId
////     * @param heightUnit
////     * @param firstName
////     * @param positionTitleName
////     * @param sportField
////     * @param music
////     * @param zip
////     * @param weightUnit
////     * @param smm
////     * @param selectedAthleteLevel
////     * @param schoolAddress
////     * @param languageId
////     * @param address
////     * @param deviceToken
////     * @param dob
////     * @param suit
////     * @param schoolId
////     */
////    public Login(String userId, String emailId, String passwordType, String schoolId, String schoolName, String positionTitleId, String positionTitleName, String userType, String backgroundImage, String firstName, String lastName, String userName, String dob, String gender, String deviceToken, String languageId, String languageName, String suit, String street, String zip, String country, String state, String city, String schoolAddress, String sportId, String sportName, String socialHead, List<Team> teams, String teamName, List<AthleteHeartRate> athleteHeartRate, List<Sport> sports, String athletePhoto, String address, String age, String sportLevel, String sportField, String socialMedia, String music, String userImage, String height, String weight, String heightUnit, String weightUnit, String smm, String paymentStatus, String bodyFat, String gymAccountId, String athleteRefcoachId, String coachReferby, String questionnaire, String sets, List<AssingProgramDetail> assingProgramDetails, List<SelectedAthleteLevel> selectedAthleteLevel, List<SelectedAthleteGoal> selectedAthleteGoal, List<AthleteLevel> athleteLevel) {
////        super();
////        this.userId = userId;
////        this.emailId = emailId;
////        this.passwordType = passwordType;
////        this.schoolId = schoolId;
////        this.schoolName = schoolName;
////        this.positionTitleId = positionTitleId;
////        this.positionTitleName = positionTitleName;
////        this.userType = userType;
////        this.backgroundImage = backgroundImage;
////        this.firstName = firstName;
////        this.lastName = lastName;
////        this.userName = userName;
////        this.dob = dob;
////        this.gender = gender;
////        this.deviceToken = deviceToken;
////        this.languageId = languageId;
////        this.languageName = languageName;
////        this.suit = suit;
////        this.street = street;
////        this.zip = zip;
////        this.country = country;
////        this.state = state;
////        this.city = city;
////        this.schoolAddress = schoolAddress;
////        this.sportId = sportId;
////        this.sportName = sportName;
////        this.socialHead = socialHead;
////        this.teams = teams;
////        this.teamName = teamName;
////        this.athleteHeartRate = athleteHeartRate;
////        this.sports = sports;
////        this.athletePhoto = athletePhoto;
////        this.address = address;
////        this.age = age;
////        this.sportLevel = sportLevel;
////        this.sportField = sportField;
////        this.socialMedia = socialMedia;
////        this.music = music;
////        this.userImage = userImage;
////        this.height = height;
////        this.weight = weight;
////        this.heightUnit = heightUnit;
////        this.weightUnit = weightUnit;
////        this.smm = smm;
////        this.paymentStatus = paymentStatus;
////        this.bodyFat = bodyFat;
////        this.gymAccountId = gymAccountId;
////        this.athleteRefcoachId = athleteRefcoachId;
////        this.coachReferby = coachReferby;
////        this.questionnaire = questionnaire;
////        this.sets = sets;
////        this.assingProgramDetails = assingProgramDetails;
////        this.selectedAthleteLevel = selectedAthleteLevel;
////        this.selectedAthleteGoal = selectedAthleteGoal;
////        this.athleteLevel = athleteLevel;
////    }
////
////    public String getUserId() {
////        return userId;
////    }
////
////    public void setUserId(String userId) {
////        this.userId = userId;
////    }
////
////    public String getEmailId() {
////        return emailId;
////    }
////
////    public void setEmailId(String emailId) {
////        this.emailId = emailId;
////    }
////
////    public String getPasswordType() {
////        return passwordType;
////    }
////
////    public void setPasswordType(String passwordType) {
////        this.passwordType = passwordType;
////    }
////
////    public String getSchoolId() {
////        return schoolId;
////    }
////
////    public void setSchoolId(String schoolId) {
////        this.schoolId = schoolId;
////    }
////
////    public String getSchoolName() {
////        return schoolName;
////    }
////
////    public void setSchoolName(String schoolName) {
////        this.schoolName = schoolName;
////    }
////
////    public String getPositionTitleId() {
////        return positionTitleId;
////    }
////
////    public void setPositionTitleId(String positionTitleId) {
////        this.positionTitleId = positionTitleId;
////    }
////
////    public String getPositionTitleName() {
////        return positionTitleName;
////    }
////
////    public void setPositionTitleName(String positionTitleName) {
////        this.positionTitleName = positionTitleName;
////    }
////
////    public String getUserType() {
////        return userType;
////    }
////
////    public void setUserType(String userType) {
////        this.userType = userType;
////    }
////
////    public String getBackgroundImage() {
////        return backgroundImage;
////    }
////
////    public void setBackgroundImage(String backgroundImage) {
////        this.backgroundImage = backgroundImage;
////    }
////
////    public String getFirstName() {
////        return firstName;
////    }
////
////    public void setFirstName(String firstName) {
////        this.firstName = firstName;
////    }
////
////    public String getLastName() {
////        return lastName;
////    }
////
////    public void setLastName(String lastName) {
////        this.lastName = lastName;
////    }
////
////    public String getUserName() {
////        return userName;
////    }
////
////    public void setUserName(String userName) {
////        this.userName = userName;
////    }
////
////    public String getDob() {
////        return dob;
////    }
////
////    public void setDob(String dob) {
////        this.dob = dob;
////    }
////
////    public String getGender() {
////        return gender;
////    }
////
////    public void setGender(String gender) {
////        this.gender = gender;
////    }
////
////    public String getDeviceToken() {
////        return deviceToken;
////    }
////
////    public void setDeviceToken(String deviceToken) {
////        this.deviceToken = deviceToken;
////    }
////
////    public String getLanguageId() {
////        return languageId;
////    }
////
////    public void setLanguageId(String languageId) {
////        this.languageId = languageId;
////    }
////
////    public String getLanguageName() {
////        return languageName;
////    }
////
////    public void setLanguageName(String languageName) {
////        this.languageName = languageName;
////    }
////
////    public String getSuit() {
////        return suit;
////    }
////
////    public void setSuit(String suit) {
////        this.suit = suit;
////    }
////
////    public String getStreet() {
////        return street;
////    }
////
////    public void setStreet(String street) {
////        this.street = street;
////    }
////
////    public String getZip() {
////        return zip;
////    }
////
////    public void setZip(String zip) {
////        this.zip = zip;
////    }
////
////    public String getCountry() {
////        return country;
////    }
////
////    public void setCountry(String country) {
////        this.country = country;
////    }
////
////    public String getState() {
////        return state;
////    }
////
////    public void setState(String state) {
////        this.state = state;
////    }
////
////    public String getCity() {
////        return city;
////    }
////
////    public void setCity(String city) {
////        this.city = city;
////    }
////
////    public String getSchoolAddress() {
////        return schoolAddress;
////    }
////
////    public void setSchoolAddress(String schoolAddress) {
////        this.schoolAddress = schoolAddress;
////    }
////
////    public String getSportId() {
////        return sportId;
////    }
////
////    public void setSportId(String sportId) {
////        this.sportId = sportId;
////    }
////
////    public String getSportName() {
////        return sportName;
////    }
////
////    public void setSportName(String sportName) {
////        this.sportName = sportName;
////    }
////
////    public String getSocialHead() {
////        return socialHead;
////    }
////
////    public void setSocialHead(String socialHead) {
////        this.socialHead = socialHead;
////    }
////
////    public List<Team> getTeams() {
////        return teams;
////    }
////
////    public void setTeams(List<Team> teams) {
////        this.teams = teams;
////    }
////
////    public String getTeamName() {
////        return teamName;
////    }
////
////    public void setTeamName(String teamName) {
////        this.teamName = teamName;
////    }
////
////    public List<AthleteHeartRate> getAthleteHeartRate() {
////        return athleteHeartRate;
////    }
////
////    public void setAthleteHeartRate(List<AthleteHeartRate> athleteHeartRate) {
////        this.athleteHeartRate = athleteHeartRate;
////    }
////
////    public List<Sport> getSports() {
////        return sports;
////    }
////
////
////    public void setSports(List<Sport> sports) {
////        this.sports = sports;
////    }
////
////    public String getAthletePhoto() {
////        return athletePhoto;
////    }
////
////    public void setAthletePhoto(String athletePhoto) {
////        this.athletePhoto = athletePhoto;
////    }
////
////    public String getAddress() {
////        return address;
////    }
////
////    public void setAddress(String address) {
////        this.address = address;
////    }
////
////    public String getAge() {
////        return age;
////    }
////
////    public void setAge(String age) {
////        this.age = age;
////    }
////
////    public String getSportLevel() {
////        return sportLevel;
////    }
////
////    public void setSportLevel(String sportLevel) {
////        this.sportLevel = sportLevel;
////    }
////
////    public String getSportField() {
////        return sportField;
////    }
////
////    public void setSportField(String sportField) {
////        this.sportField = sportField;
////    }
////
////    public String getSocialMedia() {
////        return socialMedia;
////    }
////
////    public void setSocialMedia(String socialMedia) {
////        this.socialMedia = socialMedia;
////    }
////
////    public String getMusic() {
////        return music;
////    }
////
////    public void setMusic(String music) {
////        this.music = music;
////    }
////
////    public String getUserImage() {
////        return userImage;
////    }
////
////    public void setUserImage(String userImage) {
////        this.userImage = userImage;
////    }
////
////    public String getHeight() {
////        return height;
////    }
////
////    public void setHeight(String height) {
////        this.height = height;
////    }
////
////    public String getWeight() {
////        return weight;
////    }
////
////    public void setWeight(String weight) {
////        this.weight = weight;
////    }
////
////    public String getHeightUnit() {
////        return heightUnit;
////    }
////
////    public void setHeightUnit(String heightUnit) {
////        this.heightUnit = heightUnit;
////    }
////
////    public String getWeightUnit() {
////        return weightUnit;
////    }
////
////    public void setWeightUnit(String weightUnit) {
////        this.weightUnit = weightUnit;
////    }
////
////    public String getSmm() {
////        return smm;
////    }
////
////    public void setSmm(String smm) {
////        this.smm = smm;
////    }
////
////    public String getPaymentStatus() {
////        return paymentStatus;
////    }
////
////    public void setPaymentStatus(String paymentStatus) {
////        this.paymentStatus = paymentStatus;
////    }
////
////    public String getBodyFat() {
////        return bodyFat;
////    }
////
////    public void setBodyFat(String bodyFat) {
////        this.bodyFat = bodyFat;
////    }
////
////    public String getGymAccountId() {
////        return gymAccountId;
////    }
////
////    public void setGymAccountId(String gymAccountId) {
////        this.gymAccountId = gymAccountId;
////    }
////
////    public String getAthleteRefcoachId() {
////        return athleteRefcoachId;
////    }
////
////    public void setAthleteRefcoachId(String athleteRefcoachId) {
////        this.athleteRefcoachId = athleteRefcoachId;
////    }
////
////    public String getCoachReferby() {
////        return coachReferby;
////    }
////
////    public void setCoachReferby(String coachReferby) {
////        this.coachReferby = coachReferby;
////    }
////
////    public String getQuestionnaire() {
////        return questionnaire;
////    }
////
////    public void setQuestionnaire(String questionnaire) {
////        this.questionnaire = questionnaire;
////    }
////
////    public String getSets() {
////        return sets;
////    }
////
////    public void setSets(String sets) {
////        this.sets = sets;
////    }
////
////    public List<AssingProgramDetail> getAssingProgramDetails() {
////        return assingProgramDetails;
////    }
////
////    public void setAssingProgramDetails(List<AssingProgramDetail> assingProgramDetails) {
////        this.assingProgramDetails = assingProgramDetails;
////    }
////
////    public List<SelectedAthleteLevel> getSelectedAthleteLevel() {
////        return selectedAthleteLevel;
////    }
////
////    public void setSelectedAthleteLevel(List<SelectedAthleteLevel> selectedAthleteLevel) {
////        this.selectedAthleteLevel = selectedAthleteLevel;
////    }
////
////    public List<SelectedAthleteGoal> getSelectedAthleteGoal() {
////        return selectedAthleteGoal;
////    }
////
////    public void setSelectedAthleteGoal(List<SelectedAthleteGoal> selectedAthleteGoal) {
////        this.selectedAthleteGoal = selectedAthleteGoal;
////    }
////
////    public List<AthleteLevel> getAthleteLevel() {
////        return athleteLevel;
////    }
////
////    public void setAthleteLevel(List<AthleteLevel> athleteLevel) {
////        this.athleteLevel = athleteLevel;
////    }
//
//}
