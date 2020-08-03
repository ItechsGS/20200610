package com.org.godspeed.response_JsonS.timezone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class timezoneList {
    @SerializedName("time_zone_id")
    @Expose
    private String TimeZoneId;
    @SerializedName("time_zone_name")
    @Expose
    private String TimeZoneName;

    public timezoneList(String timeZoneId, String timeZoneName) {
        TimeZoneId = timeZoneId;
        TimeZoneName = timeZoneName;
    }

    public String getTimeZoneId() {
        return TimeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        TimeZoneId = timeZoneId;
    }

    public String getTimeZoneName() {
        return TimeZoneName;
    }

    public void setTimeZoneName(String timeZoneName) {
        TimeZoneName = timeZoneName;
    }
}
