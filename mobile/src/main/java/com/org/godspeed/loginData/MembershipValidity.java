
package com.org.godspeed.loginData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MembershipValidity implements Serializable {

    private final static long serialVersionUID = 3338328237810844865L;
    @SerializedName("last_updated_date")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("valid_till_date")
    @Expose
    private String validTillDate;
    @SerializedName("gym_account_id_auto")
    @Expose
    private String gymAccountIdAuto;

    /**
     * No args constructor for use in serialization
     */
    public MembershipValidity() {
    }

    /**
     * @param lastUpdatedDate
     * @param validTillDate
     * @param gymAccountIdAuto
     */
    public MembershipValidity(String lastUpdatedDate, String validTillDate, String gymAccountIdAuto) {
        super();
        this.lastUpdatedDate = lastUpdatedDate;
        this.validTillDate = validTillDate;
        this.gymAccountIdAuto = gymAccountIdAuto;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getValidTillDate() {
        return validTillDate;
    }

    public void setValidTillDate(String validTillDate) {
        this.validTillDate = validTillDate;
    }

    public String getGymAccountIdAuto() {
        return gymAccountIdAuto;
    }

    public void setGymAccountIdAuto(String gymAccountIdAuto) {
        this.gymAccountIdAuto = gymAccountIdAuto;
    }

}
