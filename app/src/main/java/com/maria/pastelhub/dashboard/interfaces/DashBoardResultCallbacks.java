package com.maria.pastelhub.dashboard.interfaces;

public interface DashBoardResultCallbacks {

    void onSuccess(String title, String message);
    void onError(String title, String message);

}
