
package com.org.godspeed.response_JsonS.ProgramsJSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Program implements Serializable {

    //    //private final static long serialVersionUID= -4048862611034156240L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("program_name")
    @Expose
    private String programName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("last_modify_time")
    @Expose
    private String lastModifyTime;
    @SerializedName("programplancat_id")
    @Expose
    private String programplancatId;

    /**
     * No args constructor for use in serialization
     */
    public Program() {
    }

    /**
     * @param id
     * @param price
     * @param programplancatId
     * @param description
     * @param video
     * @param lastModifyTime
     * @param programName
     */
    public Program(String id, String programName, String description, String price, String video, String lastModifyTime, String programplancatId) {
        super();
        this.id = id;
        this.programName = programName;
        this.description = description;
        this.price = price;
        this.video = video;
        this.lastModifyTime = lastModifyTime;
        this.programplancatId = programplancatId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getProgramplancatId() {
        return programplancatId;
    }

    public void setProgramplancatId(String programplancatId) {
        this.programplancatId = programplancatId;
    }

}
