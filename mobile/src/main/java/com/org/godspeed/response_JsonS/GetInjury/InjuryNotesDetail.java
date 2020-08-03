
package com.org.godspeed.response_JsonS.GetInjury;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InjuryNotesDetail implements Serializable {

    //private final static long serialVersionUID = 3730566528298010689L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("injury_id")
    @Expose
    private String injuryId;
    @SerializedName("note_date")
    @Expose
    private String noteDate;
    @SerializedName("notes")
    @Expose
    private String notes;

    /**
     * No args constructor for use in serialization
     */

    public InjuryNotesDetail(String id, String injuryId, String noteDate, String notes) {
        super();
        this.id = id;
        this.injuryId = injuryId;
        this.noteDate = noteDate;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInjuryId() {
        return injuryId;
    }

    public void setInjuryId(String injuryId) {
        this.injuryId = injuryId;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
