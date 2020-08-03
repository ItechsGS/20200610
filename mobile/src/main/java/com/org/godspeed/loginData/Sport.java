package com.org.godspeed.loginData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sport implements Serializable {

    //private final static long serialVersionUID = -6236801093869423443L;
    @SerializedName("sport_id")
    @Expose
    private String sportId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sport_name")
    @Expose
    private String sportName;

    /**
     * No args constructor for use in serialization
     */
    public Sport() {
    }

    /**
     * @param id
     * @param sportId
     * @param sportName
     */
    public Sport(String sportId, String id, String sportName) {
        super();
        this.sportId = sportId;
        this.id = id;
        this.sportName = sportName;
    }

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }


}
//
//public class Sport implements Serializable
//{
//
//    //private final static long serialVersionUID = -7199789624721974893L;
//    @SerializedName("sport_id")
//    @Expose
//    private String sportId;
//    @SerializedName("id")
//    @Expose
//    private String id;
//    @SerializedName("user_id")
//    @Expose
//    private String userId;
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public Sport() {
//    }
//
//    /**
//     *
//     * @param id
//     * @param sportId
//     * @param userId
//     */
//    public Sport(String sportId, String id, String userId) {
//        super();
//        this.sportId = sportId;
//        this.id = id;
//        this.userId = userId;
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
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
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
//}

