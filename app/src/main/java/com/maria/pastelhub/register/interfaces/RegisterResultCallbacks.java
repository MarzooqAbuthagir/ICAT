package com.maria.pastelhub.register.interfaces;

public interface RegisterResultCallbacks {

    void onSuccess(String title, String message);
    void onError(String title, String message);
    void onLocReq();

}
