
package com.org.godspeed.response_JsonS.GetSchedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.org.godspeed.response_JsonS.getTeams.TeamSport;

import java.io.Serializable;
import java.util.List;

public class Team implements Serializable {


    @SerializedName("school_id")
    @Expose
    private String schoolId;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("team_color")
    @Expose
    private String teamColor;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("training_program_id")
    @Expose
    private String trainingProgramId;
    @SerializedName("assignprogram_date")
    @Expose
    private String assignprogramDate;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;
    @SerializedName("program_name")
    @Expose
    private String programName;
    @SerializedName("max_week")
    @Expose
    private String maxWeek;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("timing")
    @Expose
    private List<Timing> timing = null;


    @SerializedName("coach_class_timing")
    @Expose
    private List<Timing> coachClassTiming = null;

    @SerializedName("program_id")
    @Expose
    private String programId;
    @SerializedName("phase")
    @Expose
    private String phase;
    @SerializedName("week")
    @Expose
    private String week;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("assign_program_id")
    @Expose
    private String assignProgramId;


    @SerializedName("team_sports")
    @Expose
    private List<TeamSport> teamSports = null;
    /**
     * No args constructor for use in serialization
     *
     */

    /**
     * @param teamName
     * @param phase
     * @param lastName
     * @param week
     * @param endDate
     * @param timing
     * @param maxWeek
     * @param emailId
     * @param assignprogramDate
     * @param firstName
     * @param trainingProgramId
     * @param assignProgramId
     * @param athleteId
     * @param programName
     * @param schoolId
     * @param teamId
     * @param id
     * @param schoolName
     * @param day
     * @param startDate
     * @param programId
     * @param teamColor
     */
    public Team(String schoolId, String schoolName, String teamColor, String teamName, String emailId, String firstName, String lastName, String id, String teamId, String trainingProgramId, String assignprogramDate, String startDate, String athleteId, String programName, String maxWeek, String endDate, List<Timing> timing, String programId, String phase, String week, String day, String assignProgramId, List<TeamSport> teamSports) {
        super();
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.teamColor = teamColor;
        this.teamName = teamName;
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.teamId = teamId;
        this.trainingProgramId = trainingProgramId;
        this.assignprogramDate = assignprogramDate;
        this.startDate = startDate;
        this.athleteId = athleteId;
        this.programName = programName;
        this.maxWeek = maxWeek;
        this.endDate = endDate;
        this.timing = timing;
        this.programId = programId;
        this.phase = phase;
        this.week = week;
        this.day = day;
        this.assignProgramId = assignProgramId;
        this.teamSports = teamSports;
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

    public String getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getMaxWeek() {
        return maxWeek;
    }

    public void setMaxWeek(String maxWeek) {
        this.maxWeek = maxWeek;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Timing> getTiming() {
        return timing;
    }

    public void setTiming(List<Timing> timing) {
        this.timing = timing;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAssignProgramId() {
        return assignProgramId;
    }

    public void setAssignProgramId(String assignProgramId) {
        this.assignProgramId = assignProgramId;
    }


    public List<Timing> getCoachClassTiming() {
        return coachClassTiming;
    }

    public void setCoachClassTiming(List<Timing> coachClassTiming) {
        this.coachClassTiming = coachClassTiming;
    }

    public List<TeamSport> getTeamSports() {
        return teamSports;
    }

    public void setTeamSports(List<TeamSport> teamSports) {
        this.teamSports = teamSports;
    }
}
