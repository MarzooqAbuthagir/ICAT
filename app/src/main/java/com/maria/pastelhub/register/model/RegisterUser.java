package com.maria.pastelhub.register.model;

import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;

public class RegisterUser extends BaseObservable {

    @NonNull
    private String name, email, mobile, username, password,country="India",state="Tamil", regClass ="Select Class";

    public RegisterUser() {
    }

    public RegisterUser(@NonNull String name, String email, String mobile, String username, String password) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    public String getCountry() {
        return country;
    }

    public void setCountry(@NonNull String country) {
        this.country = country;
    }

    @NonNull
    public String getState() {
        return state;
    }

    public void setState(@NonNull String state) {
        this.state = state;
    }

    @NonNull
    public String getRegClass() {
        return regClass;
    }

    public void setRegClass(@NonNull String regClass) {
        this.regClass = regClass;
    }

    public int isValidRegister() {
        if (getName() == null)
            return 1;
        else if (getName().length() < 3)
            return 2;
        if (getEmail() == null)
            return 3;
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
            return 4;
        else if (getMobile() == null)
            return 5;
        else if (getMobile().length() != 10)
            return 6;
//        else if (getUsername() == null)
//            return 7;
//        else if (getUsername().length() < 4)
//            return 8;
//        else if (getPassword() == null)
//            return 9;
//        else if (getPassword().length() < 6)
//            return 10;
        else if (getRegClass().equalsIgnoreCase("Select Class"))
            return 11;
        else
            return 0;
    }

}
