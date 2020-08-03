
package com.org.godspeed.response_JsonS.AddTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Exercise implements Serializable {


    private final static long serialVersionUID = -1651382012349554388L;
    /////////////////////////////////////////
    @SerializedName("wod_type")
    @Expose
    private String wodType;
    @SerializedName("wod_description")
    @Expose
    private String wodDescription;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("training_program_detail_id")
    @Expose
    private String trainingProgramDetailId;
    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("exercise_id")
    @Expose
    private String exerciseId;
    @SerializedName("exercise_type_group_id")
    @Expose
    private String exerciseTypeGroupId;
    @SerializedName("phase_id")
    @Expose
    private String phaseId;
    @SerializedName("dose_id")
    @Expose
    private String doseId;
    @SerializedName("type_name")
    @Expose
    private String typeName;
    @SerializedName("exercise_name")
    @Expose
    private String exerciseName;
    @SerializedName("abr")
    @Expose
    private String abr;
    @SerializedName("base_value")
    @Expose
    private String baseValue;
    @SerializedName("exercise_type_id")
    @Expose
    private String exerciseTypeId;
    @SerializedName("exercise_video_link")
    @Expose
    private String exerciseVideoLink;
    @SerializedName("time_duration")
    @Expose
    private String timeDuration;
    @SerializedName("exercise_percentage")
    @Expose
    private String exercisePercentage;
    @SerializedName("exercise_notes_detail")
    @Expose
    private String exerciseNotesDetail;
    @SerializedName("exercise_notes_date")
    @Expose
    private String exerciseNotesDate;
    @SerializedName("exercise_notes_id")
    @Expose
    private String exerciseNotesId;
    @SerializedName("type_group_id")
    @Expose
    private String typeGroupId;
    @SerializedName("measurement")
    @Expose
    private List<Measurement> measurement = null;
    @SerializedName("wod_dose_details")
    @Expose
    private List<WodDoseDetail> wodDoseDetails = null;
    @SerializedName("id_auto")
    @Expose
    private String idAuto;

    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("assign_program_id")
    @Expose
    private String assignProgramId;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;

    @SerializedName("notes_id")
    @Expose
    private String notesId;

    @SerializedName("exercise_type")
    @Expose
    private String exerciseType;
    @SerializedName("gym_account_id")
    @Expose
    private String gymAccountId;
    @SerializedName("last_modify_time")
    @Expose
    private String lastModifyTime;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("training_exercise_id_auto")
    @Expose
    private String trainingExerciseIdAuto;
    @SerializedName("work_duration")
    @Expose
    private String workDuration;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("training_exercise_unique_id")
    @Expose
    private String trainingExerciseUniqueId;

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

    @SerializedName("exercise_type_notes_id")
    @Expose
    private String exerciseTypeNotesId;
    @SerializedName("whiteboard_notes")
    @Expose
    private String whiteboardNotes;
    @SerializedName("whiteboard_notes_id")
    @Expose
    private String whiteboardNotesId;


    /**
     * No args constructor for use in serialization
     */
    public Exercise() {
    }


    /**
     * @param exerciseNotesDate
     * @param exerciseId
     * @param exerciseVideoLink
     * @param phaseId
     * @param typeName
     * @param exercisePercentage
     * @param exerciseNotesDetail
     * @param doseId
     * @param timeDuration
     * @param measurement
     * @param exerciseTypeId
     * @param abr
     * @param exerciseName
     * @param wodDoseDetails
     * @param trainingProgramDetailId
     * @param baseValue
     * @param typeGroupId
     * @param typeId
     * @param position
     * @param id
     * @param wodDescription
     * @param wodType
     * @param exerciseTypeGroupId
     * @param exerciseNotesId
     */
    public Exercise(String wodType, String wodDescription, String position, String id, String trainingProgramDetailId, String typeId, String exerciseId, String exerciseTypeGroupId, String phaseId, String doseId, String typeName, String exerciseName, String abr, String baseValue, String exerciseTypeId, String exerciseVideoLink, String timeDuration, String exercisePercentage, String exerciseNotesDetail, String exerciseNotesDate, String exerciseNotesId, String typeGroupId, List<Measurement> measurement, List<WodDoseDetail> wodDoseDetails,


                    String idAuto, String teamId, String assignProgramId, String athleteId, String notesId, String exerciseType, String gymAccountId, String lastModifyTime, String status, String trainingExerciseIdAuto, String workDuration, String type, String trainingExerciseUniqueId, String exerciseTypeNotesDetail, String exerciseTypeNotesDate, String notes, String trainingExerciseId, String exerciseTypeNotesId, String whiteboardNotes, String whiteboardNotesId, List<Measurement> meser) {
        super();
        this.wodType = wodType;
        this.wodDescription = wodDescription;
        this.position = position;
        this.id = id;
        this.trainingProgramDetailId = trainingProgramDetailId;
        this.typeId = typeId;
        this.exerciseId = exerciseId;
        this.exerciseTypeGroupId = exerciseTypeGroupId;
        this.phaseId = phaseId;
        this.doseId = doseId;
        this.typeName = typeName;
        this.exerciseName = exerciseName;
        this.abr = abr;
        this.baseValue = baseValue;
        this.exerciseTypeId = exerciseTypeId;
        this.exerciseVideoLink = exerciseVideoLink;
        this.timeDuration = timeDuration;
        this.exercisePercentage = exercisePercentage;
        this.exerciseNotesDetail = exerciseNotesDetail;
        this.exerciseNotesDate = exerciseNotesDate;
        this.exerciseNotesId = exerciseNotesId;
        this.typeGroupId = typeGroupId;
        this.measurement = measurement;
        this.wodDoseDetails = wodDoseDetails;
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
        this.measurement = meser;
    }
//    public Exercise(String trainingProgramDetailId, String typeId, String exerciseId, String phaseId, String doseId, String idAuto, String id, String teamId, String assignProgramId, String athleteId, String timeDuration, String notesId, String exerciseTypeGroupId, String exerciseTypeId, String typeName, String exerciseName, String exerciseType, String gymAccountId, String lastModifyTime, String exerciseVideoLink, String exercisePercentage, String status, String trainingExerciseIdAuto, String workDuration, String abr, String type, String exerciseNotesDate, String trainingExerciseUniqueId, String exerciseNotesDetail, String exerciseTypeNotesDetail, String exerciseTypeNotesDate, String notes, String trainingExerciseId, String exerciseNotesId, String exerciseTypeNotesId, String whiteboardNotes, String whiteboardNotesId, List<Measurement> meser) {
//        super();
//
//    }

    public String getWodType() {
        return wodType;
    }

    public void setWodType(String wodType) {
        this.wodType = wodType;
    }

    public String getWodDescription() {
        return wodDescription;
    }

    public void setWodDescription(String wodDescription) {
        this.wodDescription = wodDescription;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getExerciseTypeGroupId() {
        return exerciseTypeGroupId;
    }

    public void setExerciseTypeGroupId(String exerciseTypeGroupId) {
        this.exerciseTypeGroupId = exerciseTypeGroupId;
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

    public String getAbr() {
        return abr;
    }

    public void setAbr(String abr) {
        this.abr = abr;
    }

    public String getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(String baseValue) {
        this.baseValue = baseValue;
    }

    public String getExerciseTypeId() {
        return exerciseTypeId;
    }

    public void setExerciseTypeId(String exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
    }

    public String getExerciseVideoLink() {
        return exerciseVideoLink;
    }

    public void setExerciseVideoLink(String exerciseVideoLink) {
        this.exerciseVideoLink = exerciseVideoLink;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getExercisePercentage() {
        return exercisePercentage;
    }

    public void setExercisePercentage(String exercisePercentage) {
        this.exercisePercentage = exercisePercentage;
    }

    public String getExerciseNotesDetail() {
        return exerciseNotesDetail;
    }

    public void setExerciseNotesDetail(String exerciseNotesDetail) {
        this.exerciseNotesDetail = exerciseNotesDetail;
    }

    public String getExerciseNotesDate() {
        return exerciseNotesDate;
    }

    public void setExerciseNotesDate(String exerciseNotesDate) {
        this.exerciseNotesDate = exerciseNotesDate;
    }

    public String getExerciseNotesId() {
        return exerciseNotesId;
    }

    public void setExerciseNotesId(String exerciseNotesId) {
        this.exerciseNotesId = exerciseNotesId;
    }

    public String getTypeGroupId() {
        return typeGroupId;
    }

    public void setTypeGroupId(String typeGroupId) {
        this.typeGroupId = typeGroupId;
    }

    public List<Measurement> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(List<Measurement> measurement) {
        this.measurement = measurement;
    }

    public List<WodDoseDetail> getWodDoseDetails() {
        return wodDoseDetails;
    }

    public void setWodDoseDetails(List<WodDoseDetail> wodDoseDetails) {
        this.wodDoseDetails = wodDoseDetails;
    }


    public String getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(String idAuto) {
        this.idAuto = idAuto;
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


    public String getNotesId() {
        return notesId;
    }

    public void setNotesId(String notesId) {
        this.notesId = notesId;
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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getTrainingExerciseUniqueId() {
        return trainingExerciseUniqueId;
    }

    public void setTrainingExerciseUniqueId(String trainingExerciseUniqueId) {
        this.trainingExerciseUniqueId = trainingExerciseUniqueId;
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

}

//
//public class Exercise implements Serializable {
//
//    //private final static long serialVersionUID = -2235919777540408460L;
//    @SerializedName("id")
//    @Expose
//    private String id;
//    @SerializedName("training_program_detail_id")
//    @Expose
//    private String trainingProgramDetailId;
//    @SerializedName("type_id")
//    @Expose
//    private String typeId;
//    @SerializedName("exercise_id")
//    @Expose
//    private String exerciseId;
//    @SerializedName("phase_id")
//    @Expose
//    private String phaseId;
//    @SerializedName("dose_id")
//    @Expose
//    private String doseId;
//    @SerializedName("type_name")
//    @Expose
//    private String typeName;
//    @SerializedName("exercise_name")
//    @Expose
//    private String exerciseName;
//    @SerializedName("abr")
//    @Expose
//    private String abr;
//    @SerializedName("base_value")
//    @Expose
//    private String baseValue;
//    @SerializedName("exercise_type_id")
//    @Expose
//    private String exerciseTypeId;
//    @SerializedName("exercise_video_link")
//    @Expose
//    private String exerciseVideoLink;
//    @SerializedName("time_duration")
//    @Expose
//    private String timeDuration;
//    @SerializedName("exercise_percentage")
//    @Expose
//    private String exercisePercentage;
//    @SerializedName("exercise_notes_detail")
//    @Expose
//    private String exerciseNotesDetail;
//    @SerializedName("exercise_notes_date")
//    @Expose
//    private String exerciseNotesDate;
//    @SerializedName("exercise_notes_id")
//    @Expose
//    private String exerciseNotesId;
//    @SerializedName("dose_details")
//    @Expose
//    private List<DoseDetail> doseDetails = null;
//    @SerializedName("measurement")
//    @Expose
//    private List<Measurement> measurement = null;
//
//    /**
//     * No args constructor for use in serialization
//     */
//    public Exercise() {
//    }
//
//    /**
//     * @param exerciseNotesId
//     * @param exerciseTypeId
//     * @param trainingProgramDetailId
//     * @param doseId
//     * @param timeDuration
//     * @param doseDetails
//     * @param exerciseNotesDate
//     * @param phaseId
//     * @param exerciseVideoLink
//     * @param measurement
//     * @param id
//     * @param typeName
//     * @param abr
//     * @param exercisePercentage
//     * @param exerciseId
//     * @param exerciseName
//     * @param baseValue
//     * @param typeId
//     * @param exerciseNotesDetail
//     */
//    public Exercise(String id, String trainingProgramDetailId, String typeId, String exerciseId, String phaseId, String doseId, String typeName, String exerciseName, String abr, String baseValue, String exerciseTypeId, String exerciseVideoLink, String timeDuration, String exercisePercentage, String exerciseNotesDetail, String exerciseNotesDate, String exerciseNotesId, List<DoseDetail> doseDetails, List<Measurement> measurement) {
//        super();
//        this.id = id;
//        this.trainingProgramDetailId = trainingProgramDetailId;
//        this.typeId = typeId;
//        this.exerciseId = exerciseId;
//        this.phaseId = phaseId;
//        this.doseId = doseId;
//        this.typeName = typeName;
//        this.exerciseName = exerciseName;
//        this.abr = abr;
//        this.baseValue = baseValue;
//        this.exerciseTypeId = exerciseTypeId;
//        this.exerciseVideoLink = exerciseVideoLink;
//        this.timeDuration = timeDuration;
//        this.exercisePercentage = exercisePercentage;
//        this.exerciseNotesDetail = exerciseNotesDetail;
//        this.exerciseNotesDate = exerciseNotesDate;
//        this.exerciseNotesId = exerciseNotesId;
//        this.doseDetails = doseDetails;
//        this.measurement = measurement;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTrainingProgramDetailId() {
//        return trainingProgramDetailId;
//    }
//
//    public void setTrainingProgramDetailId(String trainingProgramDetailId) {
//        this.trainingProgramDetailId = trainingProgramDetailId;
//    }
//
//    public String getTypeId() {
//        return typeId;
//    }
//
//    public void setTypeId(String typeId) {
//        this.typeId = typeId;
//    }
//
//    public String getExerciseId() {
//        return exerciseId;
//    }
//
//    public void setExerciseId(String exerciseId) {
//        this.exerciseId = exerciseId;
//    }
//
//    public String getPhaseId() {
//        return phaseId;
//    }
//
//    public void setPhaseId(String phaseId) {
//        this.phaseId = phaseId;
//    }
//
//    public String getDoseId() {
//        return doseId;
//    }
//
//    public void setDoseId(String doseId) {
//        this.doseId = doseId;
//    }
//
//    public String getTypeName() {
//        return typeName;
//    }
//
//    public void setTypeName(String typeName) {
//        this.typeName = typeName;
//    }
//
//    public String getExerciseName() {
//        return exerciseName;
//    }
//
//    public void setExerciseName(String exerciseName) {
//        this.exerciseName = exerciseName;
//    }
//
//    public String getAbr() {
//        return abr;
//    }
//
//    public void setAbr(String abr) {
//        this.abr = abr;
//    }
//
//    public String getBaseValue() {
//        return baseValue;
//    }
//
//    public void setBaseValue(String baseValue) {
//        this.baseValue = baseValue;
//    }
//
//    public String getExerciseTypeId() {
//        return exerciseTypeId;
//    }
//
//    public void setExerciseTypeId(String exerciseTypeId) {
//        this.exerciseTypeId = exerciseTypeId;
//    }
//
//    public String getExerciseVideoLink() {
//        return exerciseVideoLink;
//    }
//
//    public void setExerciseVideoLink(String exerciseVideoLink) {
//        this.exerciseVideoLink = exerciseVideoLink;
//    }
//
//    public String getTimeDuration() {
//        return timeDuration;
//    }
//
//    public void setTimeDuration(String timeDuration) {
//        this.timeDuration = timeDuration;
//    }
//
//    public String getExercisePercentage() {
//        return exercisePercentage;
//    }
//
//    public void setExercisePercentage(String exercisePercentage) {
//        this.exercisePercentage = exercisePercentage;
//    }
//
//    public String getExerciseNotesDetail() {
//        return exerciseNotesDetail;
//    }
//
//    public void setExerciseNotesDetail(String exerciseNotesDetail) {
//        this.exerciseNotesDetail = exerciseNotesDetail;
//    }
//
//    public String getExerciseNotesDate() {
//        return exerciseNotesDate;
//    }
//
//    public void setExerciseNotesDate(String exerciseNotesDate) {
//        this.exerciseNotesDate = exerciseNotesDate;
//    }
//
//    public String getExerciseNotesId() {
//        return exerciseNotesId;
//    }
//
//    public void setExerciseNotesId(String exerciseNotesId) {
//        this.exerciseNotesId = exerciseNotesId;
//    }
//
//    public List<DoseDetail> getDoseDetails() {
//        return doseDetails;
//    }
//
//    public void setDoseDetails(List<DoseDetail> doseDetails) {
//        this.doseDetails = doseDetails;
//    }
//
//    public List<Measurement> getMeasurement() {
//        return measurement;
//    }
//
//    public void setMeasurement(List<Measurement> measurement) {
//        this.measurement = measurement;
//    }
//
//}
