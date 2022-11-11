package com.maria.pastelhub.login.model;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;

import java.util.regex.Pattern;

public class User extends BaseObservable {

    @NonNull
    private String password, mobile;

    public User() {
    }

    public User(@NonNull String mobile, @NonNull String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public void setMobile(@NonNull String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getMobile() {
        return mobile;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public int isValidData() {
        if (getMobile() == null && getPassword() == null) {
            return 0;
        } else if (getMobile().length() == 0) {
            return 1;
        } else if (getMobile().length() != 10) {
            return 2;
        } else if (getPassword() == null) {
            return 3;
        } else if (getPassword().length() < 5) {
            return 4;
        } else {
            return -1;
        }
    }

}
