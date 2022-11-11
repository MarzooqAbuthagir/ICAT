package com.maria.pastelhub.activity.choose;

public class QAModel {
    String question;

    public QAModel(String question, String choice1, String choice2, String choice3, String choice4, int anspos) {
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.anspos = anspos;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public int getAnspos() {
        return anspos;
    }

    public void setAnspos(int anspos) {
        this.anspos = anspos;
    }

    public int getSelectedpos() {
        return selectedpos;
    }

    public void setSelectedpos(int selectedpos) {
        this.selectedpos = selectedpos;
    }

    String choice1;
    String choice2;
    String choice3;
    String choice4;
    int anspos;
    int selectedpos=-1;


}
