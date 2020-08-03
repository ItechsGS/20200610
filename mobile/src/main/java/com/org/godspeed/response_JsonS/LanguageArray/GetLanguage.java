
package com.org.godspeed.response_JsonS.LanguageArray;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetLanguage implements Serializable {

    private final static long serialVersionUID = 6860674118194004939L;
    @SerializedName("language_id")
    @Expose
    private String languageId;
    @SerializedName("language_name")
    @Expose
    private String languageName;

    /**
     * No args constructor for use in serialization
     */
    public GetLanguage() {
    }

    /**
     * @param languageId
     * @param languageName
     */
    public GetLanguage(String languageId, String languageName) {
        super();
        this.languageId = languageId;
        this.languageName = languageName;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

}
