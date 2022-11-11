package com.maria.pastelhub.forgetpassword.model;

import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;

public class ForgetUser extends BaseObservable {

    @NonNull
    String mail;

    public ForgetUser(){

    }

    public ForgetUser(@NonNull String mail) {
        this.mail = mail;
    }

    @NonNull
    public String getMail() {
        return mail;
    }

    public void setMail(@NonNull String mail) {
        this.mail = mail;
    }

    public int isValidateMail() {
        if (getMail() == null)
            return 1;
        else if (!Patterns.EMAIL_ADDRESS.matcher(getMail()).matches())
            return 2;
        else
            return -1;
    }

}
