
package com.org.godspeed.response_JsonS.addIntoAthleteArray;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArrayofnewSetExersise implements Serializable {

    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("data")
    @Expose
    private Data data;
    //    //private final static long serialVersionUID= 2875358005234147134L;

    /**
     * No args constructor for use in serialization
     */
    public ArrayofnewSetExersise() {
    }

    /**
     * @param responseMessage
     * @param responseCode
     * @param data
     */
    public ArrayofnewSetExersise(Integer responseCode, String responseMessage, Data data) {
        super();
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
