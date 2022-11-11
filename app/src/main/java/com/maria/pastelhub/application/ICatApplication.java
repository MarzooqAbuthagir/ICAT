package com.maria.pastelhub.application;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;

public class ICatApplication extends Application implements LifecycleObserver {

    protected String volval;

    public static boolean isMusicServiceRunning = false;
    SharedPreferences sharedPreferences;
    protected Pref pref;
    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        pref = new Pref(this);
        sharedPreferences = this.getSharedPreferences("SharedPrefManager", 0);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppBackgrounded() {
//        Log.e("onAppBackgrounded", "Entered");
        Log.e("onAppBackgrounded", " is Music Running : "+isMusicServiceRunning+" :: SPLASH SCREEN LOADING "+volval+" :: is Music On :"+pref.getMusicval());
        if (isMusicServiceRunning)
            stopService(new Intent(getApplicationContext(), MusicService.class));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onAppForegrounded() {
        volval = sharedPreferences.getString("SPLASH_SCREEN_LOADING", "0");
        Log.e("onAppForegrounded", " is Music Running : "+isMusicServiceRunning+" :: SPLASH SCREEN LOADING "+volval+" :: is Music On :"+pref.getMusicval());

        if (volval != null) {
            if (volval.equals("0")) {
                if (isMusicServiceRunning) {
                    stopService(new Intent(getApplicationContext(), MusicService.class));
                }
            } else if (volval.equals("1")) {
                if (!isMusicServiceRunning && pref.getMusicval().equals("1")) {
                    startService(new Intent(getApplicationContext(), MusicService.class));
                }
            }
        } else {
            pref.setMusicVal("1");
            volval = pref.getMusicval();
            if (!isMusicServiceRunning) {
                startService(new Intent(getApplicationContext(), MusicService.class));
            }
        }
    }

}
