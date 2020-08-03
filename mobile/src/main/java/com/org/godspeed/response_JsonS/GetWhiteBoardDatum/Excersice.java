
package com.org.godspeed.response_JsonS.GetWhiteBoardDatum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Excersice implements Serializable {

    private final static long serialVersionUID = -6006689958754265570L;
    @SerializedName("training_program_detail_id")
    @Expose
    private String trainingProgramDetailId;
    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("exercise_id")
    @Expose
    private String exerciseId;
    @SerializedName("phase_id")
    @Expose
    private String phaseId;
    @SerializedName("dose_id")
    @Expose
    private String doseId;
    @SerializedName("id_auto")
    @Expose
    private String idAuto;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("assign_program_id")
    @Expose
    private String assignProgramId;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;
    @SerializedName("time_duration")
    @Expose
    private String timeDuration;
    @SerializedName("notes_id")
    @Expose
    private String notesId;
    @SerializedName("exercise_type_group_id")
    @Expose
    private String exerciseTypeGroupId;
    @SerializedName("exercise_type_id")
    @Expose
    private String exerciseTypeId;
    @SerializedName("type_name")
    @Expose
    private String typeName;
    @SerializedName("exercise_name")
    @Expose
    private String exerciseName;
    @SerializedName("exercise_type")
    @Expose
    private String exerciseType;
    @SerializedName("gym_account_id")
    @Expose
    private String gymAccountId;
    @SerializedName("last_modify_time")
    @Expose
    private String lastModifyTime;
    @SerializedName("exercise_video_link")
    @Expose
    private String exerciseVideoLink;
    @SerializedName("exercise_percentage")
    @Expose
    private String exercisePercentage;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("training_exercise_id_auto")
    @Expose
    private String trainingExerciseIdAuto;
    @SerializedName("work_duration")
    @Expose
    private String workDuration;
    @SerializedName("abr")
    @Expose
    private String abr;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("exercise_notes_date")
    @Expose
    private String exerciseNotesDate;
    @SerializedName("training_exercise_unique_id")
    @Expose
    private String trainingExerciseUniqueId;
    @SerializedName("exercise_notes_detail")
    @Expose
    private String exerciseNotesDetail;
    @SerializedName("exercise_type_notes_detail")
    @Expose
    private String exerciseTypeNotesDetail;
    @SerializedName("exercise_type_notes_date")
    @Expose
    private String exerciseTypeNotesDate;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("training_exercise_id")
    @Expose
    private String trainingExerciseId;
    @SerializedName("exercise_notes_id")
    @Expose
    private String exerciseNotesId;
    @SerializedName("exercise_type_notes_id")
    @Expose
    private String exerciseTypeNotesId;
    @SerializedName("whiteboard_notes")
    @Expose
    private String whiteboardNotes;
    @SerializedName("whiteboard_notes_id")
    @Expose
    private String whiteboardNotesId;
    @SerializedName("Meser")
    @Expose
    private List<Meser> meser = null;

    /**
     * No args constructor for use in serialization
     *
     */


    /**
     * @param exerciseNotesDate
     * @param notes
     * @param exerciseId
     * @param exerciseVideoLink
     * @param exerciseType
     * @param phaseId
     * @param typeName
     * @param exercisePercentage
     * @param trainingExerciseUniqueId
     * @param exerciseNotesDetail
     * @param type
     * @param gymAccountId
     * @param assignProgramId
     * @param trainingProgramDetailId
     * @param notesId
     * @param id
     * @param trainingExerciseIdAuto
     * @param trainingExerciseId
     * @param exerciseNotesId
     * @param idAuto
     * @param meser
     * @param exerciseTypeNotesDate
     * @param exerciseTypeNotesId
     * @param whiteboardNotesId
     * @param doseId
     * @param timeDuration
     * @param exerciseTypeId
     * @param workDuration
     * @param abr
     * @param whiteboardNotes
     * @param exerciseName
     * @param athleteId
     * @param teamId
     * @param lastModifyTime
     * @param exerciseTypeNotesDetail
     * @param typeId
     * @param exerciseTypeGroupId
     * @param status
     */
    public Excersice(String trainingProgramDetailId, String typeId, String exerciseId, String phaseId, String doseId, String idAuto, String id, String teamId, String assignProgramId, String athleteId, String timeDuration, String notesId, String exerciseTypeGroupId, String exerciseTypeId, String typeName, String exerciseName, String exerciseType, String gymAccountId, String lastModifyTime, String exerciseVideoLink, String exercisePercentage, String status, String trainingExerciseIdAuto, String workDuration, String abr, String type, String exerciseNotesDate, String trainingExerciseUniqueId, String exerciseNotesDetail, String exerciseTypeNotesDetail, String exerciseTypeNotesDate, String notes, String trainingExerciseId, String exerciseNotesId, String exerciseTypeNotesId, String whiteboardNotes, String whiteboardNotesId, List<Meser> meser) {
        super();
        this.trainingProgramDetailId = trainingProgramDetailId;
        this.typeId = typeId;
        this.exerciseId = exerciseId;
        this.phaseId = phaseId;
        this.doseId = doseId;
        this.idAuto = idAuto;
        this.id = id;
        this.teamId = teamId;
        this.assignProgramId = assignProgramId;
        this.athleteId = athleteId;
        this.timeDuration = timeDuration;
        this.notesId = notesId;
        this.exerciseTypeGroupId = exerciseTypeGroupId;
        this.exerciseTypeId = exerciseTypeId;
        this.typeName = typeName;
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
        this.gymAccountId = gymAccountId;
        this.lastModifyTime = lastModifyTime;
        this.exerciseVideoLink = exerciseVideoLink;
        this.exercisePercentage = exercisePercentage;
        this.status = status;
        this.trainingExerciseIdAuto = trainingExerciseIdAuto;
        this.workDuration = workDuration;
        this.abr = abr;
        this.type = type;
        this.exerciseNotesDate = exerciseNotesDate;
        this.trainingExerciseUniqueId = trainingExerciseUniqueId;
        this.exerciseNotesDetail = exerciseNotesDetail;
        this.exerciseTypeNotesDetail = exerciseTypeNotesDetail;
        this.exerciseTypeNotesDate = exerciseTypeNotesDate;
        this.notes = notes;
        this.trainingExerciseId = trainingExerciseId;
        this.exerciseNotesId = exerciseNotesId;
        this.exerciseTypeNotesId = exerciseTypeNotesId;
        this.whiteboardNotes = whiteboardNotes;
        this.whiteboardNotesId = whiteboardNotesId;
        this.meser = meser;
    }

    public String getTrainingProgramDetailId() {
        return trainingProgramDetailId;
    }

    public void setTrainingProgramDetailId(String trainingProgramDetailId) {
        this.trainingProgramDetailId = trainingProgramDetailId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    public String getDoseId() {
        return doseId;
    }

    public void setDoseId(String doseId) {
        this.doseId = doseId;
    }

    public String getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(String idAuto) {
        this.idAuto = idAuto;
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

    public String getAssignProgramId() {
        return assignProgramId;
    }

    public void setAssignProgramId(String assignProgramId) {
        this.assignProgramId = assignProgramId;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getNotesId() {
        return notesId;
    }

    public void setNotesId(String notesId) {
        this.notesId = notesId;
    }

    public String getExerciseTypeGroupId() {
        return exerciseTypeGroupId;
    }

    public void setExerciseTypeGroupId(String exerciseTypeGroupId) {
        this.exerciseTypeGroupId = exerciseTypeGroupId;
    }

    public String getExerciseTypeId() {
        return exerciseTypeId;
    }

    public void setExerciseTypeId(String exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
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

    public String getExerciseVideoLink() {
        return exerciseVideoLink;
    }

    public void setExerciseVideoLink(String exerciseVideoLink) {
        this.exerciseVideoLink = exerciseVideoLink;
    }

    public String getExercisePercentage() {
        return exercisePercentage;
    }

    public void setExercisePercentage(String exercisePercentage) {
        this.exercisePercentage = exercisePercentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrainingExerciseIdAuto() {
        return trainingExerciseIdAuto;
    }

    public void setTrainingExerciseIdAuto(String trainingExerciseIdAuto) {
        this.trainingExerciseIdAuto = trainingExerciseIdAuto;
    }

    public String getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(String workDuration) {
        this.workDuration = workDuration;
    }

    public String getAbr() {
        return abr;
    }

    public void setAbr(String abr) {
        this.abr = abr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExerciseNotesDate() {
        return exerciseNotesDate;
    }

    public void setExerciseNotesDate(String exerciseNotesDate) {
        this.exerciseNotesDate = exerciseNotesDate;
    }

    public String getTrainingExerciseUniqueId() {
        return trainingExerciseUniqueId;
    }

    public void setTrainingExerciseUniqueId(String trainingExerciseUniqueId) {
        this.trainingExerciseUniqueId = trainingExerciseUniqueId;
    }

    public String getExerciseNotesDetail() {
        return exerciseNotesDetail;
    }

    public void setExerciseNotesDetail(String exerciseNotesDetail) {
        this.exerciseNotesDetail = exerciseNotesDetail;
    }

    public String getExerciseTypeNotesDetail() {
        return exerciseTypeNotesDetail;
    }

    public void setExerciseTypeNotesDetail(String exerciseTypeNotesDetail) {
        this.exerciseTypeNotesDetail = exerciseTypeNotesDetail;
    }

    public String getExerciseTypeNotesDate() {
        return exerciseTypeNotesDate;
    }

    public void setExerciseTypeNotesDate(String exerciseTypeNotesDate) {
        this.exerciseTypeNotesDate = exerciseTypeNotesDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTrainingExerciseId() {
        return trainingExerciseId;
    }

    public void setTrainingExerciseId(String trainingExerciseId) {
        this.trainingExerciseId = trainingExerciseId;
    }

    public String getExerciseNotesId() {
        return exerciseNotesId;
    }

    public void setExerciseNotesId(String exerciseNotesId) {
        this.exerciseNotesId = exerciseNotesId;
    }

    public String getExerciseTypeNotesId() {
        return exerciseTypeNotesId;
    }

    public void setExerciseTypeNotesId(String exerciseTypeNotesId) {
        this.exerciseTypeNotesId = exerciseTypeNotesId;
    }

    public String getWhiteboardNotes() {
        return whiteboardNotes;
    }

    public void setWhiteboardNotes(String whiteboardNotes) {
        this.whiteboardNotes = whiteboardNotes;
    }

    public String getWhiteboardNotesId() {
        return whiteboardNotesId;
    }

    public void setWhiteboardNotesId(String whiteboardNotesId) {
        this.whiteboardNotesId = whiteboardNotesId;
    }

    public List<Meser> getMeser() {
        return meser;
    }

    public void setMeser(List<Meser> meser) {
        this.meser = meser;
    }

}
