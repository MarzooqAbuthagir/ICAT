package com.maria.pastelhub.activity.choose.model;

public class ChooseAnswerUser {

    public String id;
    public String question;
    public String answer_1;
    public String answer_2;
    public String answer_3;
    public String answer_4;
    public String answer_switch_1;
    public String answer_switch_2;
    public String answer_switch_3;
    public String answer_switch_4;
    public String correct_answer;

    public ChooseAnswerUser() {
    }

    public ChooseAnswerUser(String id, String question, String answer_1,
                            String answer_2, String answer_3, String answer_4, String correct_answer, String answer_switch_1,
                            String answer_switch_2, String answer_switch_3, String answer_switch_4) {
        this.id = id;
        this.question = question;
        this.answer_1 = answer_1;
        this.answer_2 = answer_2;
        this.answer_3 = answer_3;
        this.answer_4 = answer_4;
        this.answer_switch_1 = answer_switch_1;
        this.answer_switch_2 = answer_switch_2;
        this.answer_switch_3 = answer_switch_3;
        this.answer_switch_4 = answer_switch_4;
        this.correct_answer = correct_answer;
    }

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

    public String getAnswer_1() {
        return answer_1;
    }

    public void setAnswer_1(String answer_1) {
        this.answer_1 = answer_1;
    }

    public String getAnswer_2() {
        return answer_2;
    }

    public void setAnswer_2(String answer_2) {
        this.answer_2 = answer_2;
    }

    public String getAnswer_3() {
        return answer_3;
    }

    public void setAnswer_3(String answer_3) {
        this.answer_3 = answer_3;
    }

    public String getAnswer_4() {
        return answer_4;
    }

    public void setAnswer_4(String answer_4) {
        this.answer_4 = answer_4;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getAnswer_switch_1() {
        return answer_switch_1;
    }

    public void setAnswer_switch_1(String answer_switch_1) {
        this.answer_switch_1 = answer_switch_1;
    }

    public String getAnswer_switch_2() {
        return answer_switch_2;
    }

    public void setAnswer_switch_2(String answer_switch_2) {
        this.answer_switch_2 = answer_switch_2;
    }

    public String getAnswer_switch_3() {
        return answer_switch_3;
    }

    public void setAnswer_switch_3(String answer_switch_3) {
        this.answer_switch_3 = answer_switch_3;
    }

    public String getAnswer_switch_4() {
        return answer_switch_4;
    }

    public void setAnswer_switch_4(String answer_switch_4) {
        this.answer_switch_4 = answer_switch_4;
    }
}
