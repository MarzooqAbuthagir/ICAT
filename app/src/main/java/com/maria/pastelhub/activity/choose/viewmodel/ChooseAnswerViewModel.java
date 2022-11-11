package com.maria.pastelhub.activity.choose.viewmodel;

import androidx.lifecycle.ViewModel;

import com.maria.pastelhub.activity.choose.model.ChooseAnswerUser;

public class ChooseAnswerViewModel extends ViewModel {

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

    public ChooseAnswerViewModel() {
    }

    public ChooseAnswerViewModel(ChooseAnswerUser chooseAnswerUser) {
        this.id = chooseAnswerUser.id;
        this.question = chooseAnswerUser.question;
        this.answer_1 = chooseAnswerUser.answer_1;
        this.answer_2 = chooseAnswerUser.answer_2;
        this.answer_3 = chooseAnswerUser.answer_3;
        this.answer_4 = chooseAnswerUser.answer_4;
        this.answer_switch_1 = chooseAnswerUser.answer_switch_1;
        this.answer_switch_2 = chooseAnswerUser.answer_switch_2;
        this.answer_switch_3 = chooseAnswerUser.answer_switch_3;
        this.answer_switch_4 = chooseAnswerUser.answer_switch_4;
        this.correct_answer = chooseAnswerUser.correct_answer;
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

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }
}
