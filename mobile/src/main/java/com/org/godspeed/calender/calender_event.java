package com.org.godspeed.calender;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.org.godspeed.response_JsonS.GetSchedules.Timing;
import com.org.godspeed.response_JsonS.getTeams.TeamSport;
import com.org.godspeed.response_JsonS.getVideoClass.LiveClassExerciseVideo;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Hugo Andrade on 25/03/2018.
 */

public class calender_event implements Parcelable {

    public static final Creator<calender_event> CREATOR = new Creator<calender_event>() {
        @Override
        public calender_event createFromParcel(Parcel in) {
            return new calender_event(in);
        }

        @Override
        public calender_event[] newArray(int size) {
            return new calender_event[size];
        }
    };
    private String mID;
    private String mTitle;
    private Calendar mDate;
    private int mColor;
    private boolean isCompleted;
    private List<Timing> timingList;
    private List<TeamSport> teamSports = null;
    private String teamName;
    private String school_id;
    private String emailId;
    private String firstName;
    private String lastName;
    private String programName;
    private String id;
    private String teamId;
    private String trainingProgramId;
    private String assignprogramDate;
    private String startDate;
    private String athleteId;
    private String endDate;
    private String assignProgramId;

    @SerializedName("Timingid")
    @Expose
    private String Timingid;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;


    /*FOR VIDEO CLASSES*/
    private String category_name;
    private String category_image;
    private List<LiveClassExerciseVideo> liveClassExerciseVideoList;

    public calender_event(Calendar date, String id, String category_name, String category_image, List<LiveClassExerciseVideo> liveClassExerciseVideoList) {
        this.id = id;
        this.liveClassExerciseVideoList = liveClassExerciseVideoList;
        this.category_image = category_image;
        this.category_name = category_name;

        mDate = date;
    }
    /*FOR VIDEO CLASSES*/

    public calender_event(
            String teamName,
            String emailId,
            String firstName,
            String lastName,
            String programName,
            String id,
            String teamId,
            String assignProgramId,
            String trainingProgramId,
            String assignprogramDate,
            String startDate,
            String athleteId,
            String endDate,
            List<Timing> timingList,
            Calendar date, int color, boolean isCompleted, String title,
            String school_id,
            List<TeamSport> teamSports,
            String Timingid, String from, String to
    ) {
        mID = id;
        this.emailId = emailId;
        this.teamName = teamName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.programName = programName;
        this.id = id;
        this.assignProgramId = assignProgramId;
        this.isCompleted = isCompleted;
        this.timingList = timingList;
        this.teamId = teamId;
        this.trainingProgramId = trainingProgramId;
        this.assignprogramDate = assignprogramDate;
        this.startDate = startDate;
        this.athleteId = athleteId;
        this.endDate = endDate;
        mTitle = title;
        mDate = date;
        mColor = color;
        this.school_id = school_id;
        this.teamSports = teamSports;
        this.Timingid = Timingid;
        this.from = from;
        this.to = to;
    }


    protected calender_event(Parcel in) {
        mID = in.readString();
        mTitle = in.readString();
        mColor = in.readInt();
        mDate = (Calendar) in.readSerializable();
        isCompleted = in.readByte() != 0;
    }

    public String getAssignProgramId() {
        return assignProgramId;
    }

    public void setAssignProgramId(String assignProgramId) {
        this.assignProgramId = assignProgramId;
    }

    public String getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public Calendar getDate() {
        return mDate;
    }

    public int getColor() {
        return mColor;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mID);
        dest.writeString(mTitle);
        dest.writeInt(mColor);
        dest.writeSerializable(mDate);
        dest.writeByte((byte) (isCompleted ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<Timing> getTimingList() {
        return timingList;
    }

    public void setTimingList(List<Timing> timingList) {
        this.timingList = timingList;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTrainingProgramId() {
        return trainingProgramId;
    }

    public void setTrainingProgramId(String trainingProgramId) {
        this.trainingProgramId = trainingProgramId;
    }

    public String getAssignprogramDate() {
        return assignprogramDate;
    }

    public void setAssignprogramDate(String assignprogramDate) {
        this.assignprogramDate = assignprogramDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public List<TeamSport> getTeamSports() {
        return teamSports;
    }

    public void setTeamSports(List<TeamSport> teamSports) {
        this.teamSports = teamSports;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTimingid() {
        return Timingid;
    }

    public void setTimingid(String timingid) {
        Timingid = timingid;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public List<LiveClassExerciseVideo> getLiveClassExerciseVideoList() {
        return liveClassExerciseVideoList;
    }

    public void setLiveClassExerciseVideoList(List<LiveClassExerciseVideo> liveClassExerciseVideoList) {
        this.liveClassExerciseVideoList = liveClassExerciseVideoList;
    }
}
