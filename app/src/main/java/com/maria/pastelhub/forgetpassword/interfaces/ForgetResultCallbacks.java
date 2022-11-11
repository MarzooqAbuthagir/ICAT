package com.maria.pastelhub.forgetpassword.interfaces;

public interface ForgetResultCallbacks {

    void onSuccess(String title, String message);
    void onError(String title, String message);

}
