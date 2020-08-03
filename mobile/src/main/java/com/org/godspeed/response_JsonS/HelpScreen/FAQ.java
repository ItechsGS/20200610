
package com.org.godspeed.response_JsonS.HelpScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FAQ implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("faq_question_id")
    @Expose
    private String faqQuestionId;
    @SerializedName("answer")
    @Expose
    private String answer;

    public FAQ(String question, String answer, String faqQuestionId) {
        this.question = question;
        this.answer = answer;
        this.faqQuestionId = faqQuestionId;

    }
    ////    //private final static long serialVersionUID= 8955006476912065567L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFaqQuestionId() {
        return faqQuestionId;
    }

    public void setFaqQuestionId(String faqQuestionId) {
        this.faqQuestionId = faqQuestionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
