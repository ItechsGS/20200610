
package com.org.godspeed.response_JsonS.messageJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SenderDetail implements Serializable {

    private final static long serialVersionUID = -2675931585215866884L;
    @SerializedName("sender_user_id")
    @Expose
    private String senderUserId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("user_image")
    @Expose
    private String userImage;

    /**
     * No args constructor for use in serialization
     *
     */


    /**
     * @param firstName
     * @param lastName
     * @param userImage
     * @param senderUserId
     * @param emailId
     */
    public SenderDetail(String senderUserId, String firstName, String lastName, String emailId, String userImage) {
        super();
        this.senderUserId = senderUserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.userImage = userImage;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

}
