
package com.org.godspeed.response_JsonS.GetInjury;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetInjury implements Serializable {

    //private final static long serialVersionUID = 8721846039443965261L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("athlete_id")
    @Expose
    private String athleteId;
    @SerializedName("injury_reported_on")
    @Expose
    private String injuryReportedOn;
    @SerializedName("injury_recovered_on")
    @Expose
    private String injuryRecoveredOn;
    @SerializedName("injury_part_name")
    @Expose
    private String injuryPartName;
    @SerializedName("reported_by")
    @Expose
    private String reportedBy;
    @SerializedName("injury_notes_detail")
    @Expose
    private List<InjuryNotesDetail> injuryNotesDetail = null;

    public GetInjury(String id, String athleteId, String injuryReportedOn, String injuryRecoveredOn, String injuryPartName, String reportedBy, List<InjuryNotesDetail> injuryNotesDetail) {
        super();
        this.id = id;
        this.athleteId = athleteId;
        this.injuryReportedOn = injuryReportedOn;
        this.injuryRecoveredOn = injuryRecoveredOn;
        this.injuryPartName = injuryPartName;
        this.reportedBy = reportedBy;
        this.injuryNotesDetail = injuryNotesDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getInjuryReportedOn() {
        return injuryReportedOn;
    }

    public void setInjuryReportedOn(String injuryReportedOn) {
        this.injuryReportedOn = injuryReportedOn;
    }

    public String getInjuryRecoveredOn() {
        return injuryRecoveredOn;
    }

    public void setInjuryRecoveredOn(String injuryRecoveredOn) {
        this.injuryRecoveredOn = injuryRecoveredOn;
    }

    public String getInjuryPartName() {
        return injuryPartName;
    }

    public void setInjuryPartName(String injuryPartName) {
        this.injuryPartName = injuryPartName;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public List<InjuryNotesDetail> getInjuryNotesDetail() {
        return injuryNotesDetail;
    }

    public void setInjuryNotesDetail(List<InjuryNotesDetail> injuryNotesDetail) {
        this.injuryNotesDetail = injuryNotesDetail;
    }

}
