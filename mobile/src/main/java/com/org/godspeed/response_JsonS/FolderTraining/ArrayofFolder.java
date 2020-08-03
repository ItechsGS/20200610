
package com.org.godspeed.response_JsonS.FolderTraining;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArrayofFolder implements Serializable {

    private final static long serialVersionUID = -7886066844584169301L;
    @SerializedName("gym_account_id")
    @Expose
    private String gymAccountId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("background_image")
    @Expose
    private String backgroundImage;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("background_video")
    @Expose
    private String backgroundVideo;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("creation_time")
    @Expose
    private String creationTime;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("is_delete")
    @Expose
    private String isDelete;
    @SerializedName("program_name")
    @Expose
    private String programName;
    @SerializedName("create_folder_parent_id")
    @Expose
    private String createFolderParentId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("folder_name")
    @Expose
    private String folderName;
    @SerializedName("created_folder_user_id")
    @Expose
    private String createdFolderUserId;
    @SerializedName("training_program_status")
    @Expose
    private String trainingProgramStatus;
    @SerializedName("sub_folder_id")
    @Expose
    private String subFolderId;

    /**
     * No args constructor for use in serialization
     */
    public ArrayofFolder() {
    }

    /**
     * @param country
     * @param address
     * @param creationTime
     * @param city
     * @param backgroundImage
     * @param isDelete
     * @param subFolderId
     * @param emailId
     * @param backgroundVideo
     * @param userId
     * @param gymAccountId
     * @param createFolderParentId
     * @param programName
     * @param name
     * @param logo
     * @param state
     * @param id
     * @param folderName
     * @param createdFolderUserId
     * @param trainingProgramStatus
     */
    public ArrayofFolder(String gymAccountId, String name, String logo, String backgroundImage, String backgroundVideo, String address, String emailId, String country, String state, String city, String creationTime, String isDelete, String id, String userId, String programName, String createFolderParentId, String trainingProgramStatus, String folderName, String createdFolderUserId, String subFolderId) {
        super();
        this.gymAccountId = gymAccountId;
        this.name = name;
        this.logo = logo;
        this.backgroundImage = backgroundImage;
        this.backgroundVideo = backgroundVideo;
        this.address = address;
        this.emailId = emailId;
        this.country = country;
        this.state = state;
        this.city = city;
        this.creationTime = creationTime;
        this.isDelete = isDelete;
        this.id = id;
        this.userId = userId;
        this.programName = programName;
        this.createFolderParentId = createFolderParentId;
        this.trainingProgramStatus = trainingProgramStatus;
        this.folderName = folderName;
        this.createdFolderUserId = createdFolderUserId;
        this.subFolderId = subFolderId;
    }

    public String getGymAccountId() {
        return gymAccountId;
    }

    public void setGymAccountId(String gymAccountId) {
        this.gymAccountId = gymAccountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getBackgroundVideo() {
        return backgroundVideo;
    }

    public void setBackgroundVideo(String backgroundVideo) {
        this.backgroundVideo = backgroundVideo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getCreateFolderParentId() {
        return createFolderParentId;
    }

    public void setCreateFolderParentId(String createFolderParentId) {
        this.createFolderParentId = createFolderParentId;
    }

    public String getTrainingProgramStatus() {
        return trainingProgramStatus;
    }

    public void setTrainingProgramStatus(String trainingProgramStatus) {
        this.trainingProgramStatus = trainingProgramStatus;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getCreatedFolderUserId() {
        return createdFolderUserId;
    }

    public void setCreatedFolderUserId(String createdFolderUserId) {
        this.createdFolderUserId = createdFolderUserId;
    }

    public String getSubFolderId() {
        return subFolderId;
    }

    public void setSubFolderId(String subFolderId) {
        this.subFolderId = subFolderId;
    }

}
