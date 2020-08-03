
package com.org.godspeed.response_JsonS.SchoolData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetSchools implements Serializable {

    @SerializedName("school_id")
    @Expose
    private String schoolId;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("school_address")
    @Expose
    private String schoolAddress;

    private Boolean SelectedSchool = false;
    /**
     * No args constructor for use in serialization
     *
     */


    /**
     * @param schoolId
     * @param schoolAddress
     * @param schoolName
     */
    public GetSchools(String schoolId, String schoolName, String schoolAddress, Boolean SelectedSchool) {
        super();
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.schoolAddress = schoolAddress;
        this.SelectedSchool = SelectedSchool;
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

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public Boolean getSelectedSchool() {
        return SelectedSchool;
    }

    public void setSelectedSchool(Boolean selectedSchool) {
        SelectedSchool = selectedSchool;
    }
}
