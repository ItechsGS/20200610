package com.org.godspeed.utility;

import java.io.Serializable;

/**
 * Created by Tanveer on 1/31/2018.
 */

public class QuestionnariesClass implements Serializable {


    private String questions = "", questionId = "", category_name = "";
    private String answerOne = "", answerTwo = "", answerThree = "", answerFour = "", answerFive = "";
    private String answerOneId = "", answerTwoId = "", answerThreeId = "", answerFourId = "", answerFiveId = "";
    private int selectedAnswer = 0;


    public QuestionnariesClass(String category_name, String question, String questionId, String ansOne, String ansTwo, String ansThree, String ansFour, String ansFive, String ansOneId, String ansTwoId, String ansThreeId, String ansFourId, String ansFiveId) {
        this.questions = question;
        this.category_name = category_name;
        this.questionId = questionId;
        this.answerOne = ansOne;
        this.answerTwo = ansTwo;
        this.answerThree = ansThree;
        this.answerFour = ansFour;
        this.answerFive = ansFive;

        this.answerOneId = ansOne;
        this.answerTwoId = ansTwo;
        this.answerThreeId = ansThree;
        this.answerFourId = ansFour;
        this.answerFiveId = ansFive;

    }


    public void setcategory_name(String category_name) {
        this.category_name = category_name;
    }


    public String getcategory_name() {
        return category_name;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String ansOne) {
        this.answerOne = ansOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String ansTwo) {
        this.answerTwo = ansTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String ansThree) {
        this.answerThree = ansThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String ansFour) {
        this.answerFour = ansFour;
    }

    public String getAnswerFive() {
        return answerFive;
    }

    public void setAnswerFive(String ansFive) {
        this.answerFive = ansFive;
    }

    public String getAnswerFiveId() {
        return answerFiveId;
    }

    public String getAnswerFourId() {
        return answerFourId;
    }

    public String getAnswerOneId() {
        return answerOneId;
    }

    public String getAnswerThreeId() {
        return answerThreeId;
    }

    public String getAnswerTwoId() {
        return answerTwoId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
