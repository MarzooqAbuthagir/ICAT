package com.maria.pastelhub.splash_screens;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.maria.pastelhub.dashboard.Dashboard;
import com.maria.pastelhub.login.Login;
import com.maria.pastelhub.R;
import com.maria.pastelhub.log_files.LogFile;
import com.maria.pastelhub.prefference.Pref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JesusIntro extends AppCompatActivity {

    @BindView(R.id.jesus_video)
    VideoView jesus_video;
    Pref pref;

    @BindView(R.id.enter_button)
    Button enter_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_jesus_intro);
        ButterKnife.bind(this);

        pref = new Pref(JesusIntro.this);
        try {
            int rawId = getResources().getIdentifier("jesus_children_animation", "raw", getPackageName());
            String path = "android.resource://" + getPackageName() + "/" + rawId;
            Uri uri = Uri.parse(path);
            jesus_video.setVideoURI(uri);
            jesus_video.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextPage() {

            startActivity(new Intent(JesusIntro.this, VideoActivity.class));
            finish();

    }

    public void enterClicked(View view) {
        new LogFile("tag", "button pressed");
        nextPage();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}