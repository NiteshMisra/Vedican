package com.student.model;

import com.google.gson.annotations.SerializedName;

public class Solutions {

    @SerializedName("id")
    private Integer id;

    @SerializedName("subject_id")
    private String subjectId;

    @SerializedName("passage_en")
    private String passageEn;

    @SerializedName("passage_hi")
    private String passageHi;

    @SerializedName("question_en")
    private String questionEn;

    @SerializedName("question_hi")
    private String questionHi;

    @SerializedName("option1")
    private String option1;

    @SerializedName("option2")
    private String option2;

    @SerializedName("option3")
    private String option3;

    @SerializedName("option4")
    private String option4;

    @SerializedName("option5")
    private String option5;

    @SerializedName("option_a")
    private String optionA;

    @SerializedName("option_b")
    private String optionB;

    @SerializedName("option_c")
    private String optionC;

    @SerializedName("option_d")
    private String optionD;

    @SerializedName("option_e")
    private String optionE;

    @SerializedName("correct_answer")
    private String correctAnswer;

    @SerializedName("weightage")
    private String weightage;

    @SerializedName("negative_marks")
    private String negativeMarks;

    @SerializedName("solution_en")
    private String solutionEn;

    @SerializedName("solution_hi")
    private String solutionHi;

    @SerializedName("status")
    private String status;

    @SerializedName("ip")
    private String ip;

    @SerializedName("date")
    private String date;

    @SerializedName("selected")
    private String selected;

    public Solutions(Integer id, String subjectId, String passageEn, String passageHi, String questionEn, String questionHi, String option1, String option2, String option3, String option4, String option5, String optionA, String optionB, String optionC, String optionD, String optionE, String correctAnswer, String weightage, String negativeMarks, String solutionEn, String solutionHi, String status, String ip, String date, String selected) {
        this.id = id;
        this.subjectId = subjectId;
        this.passageEn = passageEn;
        this.passageHi = passageHi;
        this.questionEn = questionEn;
        this.questionHi = questionHi;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.optionE = optionE;
        this.correctAnswer = correctAnswer;
        this.weightage = weightage;
        this.negativeMarks = negativeMarks;
        this.solutionEn = solutionEn;
        this.solutionHi = solutionHi;
        this.status = status;
        this.ip = ip;
        this.date = date;
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getPassageEn() {
        return passageEn;
    }

    public String getPassageHi() {
        return passageHi;
    }

    public String getQuestionEn() {
        return questionEn;
    }

    public String getQuestionHi() {
        return questionHi;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getOption5() {
        return option5;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getOptionE() {
        return optionE;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWeightage() {
        return weightage;
    }

    public String getNegativeMarks() {
        return negativeMarks;
    }

    public String getSolutionEn() {
        return solutionEn;
    }

    public String getSolutionHi() {
        return solutionHi;
    }

    public String getStatus() {
        return status;
    }

    public String getIp() {
        return ip;
    }

    public String getDate() {
        return date;
    }

    public String getSelected() {
        return selected;
    }
}
