package com.maria.pastelhub.splash_screens;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.maria.pastelhub.R;
import com.maria.pastelhub.dashboard.Dashboard;
import com.maria.pastelhub.log_files.LogFile;
import com.maria.pastelhub.prefference.Pref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.intro_video)
    VideoView videoView;
    Pref pref;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        pref = new Pref(MainActivity.this);

        pref.setSplashScreen("0");
        int rawId = getResources().getIdentifier("splash_video1", "raw", getPackageName());
        String path = "android.resource://" + getPackageName() + "/" + rawId;
        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnCompletionListener(mediaPlayer -> {
            startActivity(new Intent(MainActivity.this, JesusIntro.class));
            finish();
        });
    }


}