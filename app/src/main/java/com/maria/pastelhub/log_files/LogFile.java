package com.maria.pastelhub.log_files;

import android.util.Log;

public class LogFile {

    String tagName;
    String messagePass;

    public LogFile(String tagName, String messagePass) {
        this.tagName = tagName;
        this.messagePass = messagePass;
        LogFun(tagName, messagePass);
    }

    void LogFun(String tag, String message) {
        Log.d(tag, message);
    }

}
