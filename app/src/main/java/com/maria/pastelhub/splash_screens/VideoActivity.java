package com.maria.pastelhub.splash_screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.dashboard.Dashboard;
import com.maria.pastelhub.login.Login;
import com.maria.pastelhub.prefference.Pref;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.intro_video)
    VideoView videoView;

    @BindView(R.id.tv_skip)
    TextView tv_skip;

    Pref pref;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_video);

        ButterKnife.bind(this);

        pref = new Pref(VideoActivity.this);
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pref.getLoginStatus() == 1) {
                    startActivity(new Intent(VideoActivity.this, Dashboard.class));
                    finish();
                    overridePendingTransition(R.anim.slide_in_right1, R.anim.slide_in_right);
                } else {
                    startActivity(new Intent(VideoActivity.this, Login.class));
                    finish();
                }
            }
        });

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.theivam_thantha));
        videoView.start();

        videoView.setOnCompletionListener(mediaPlayer -> {
            if (pref.getLoginStatus() == 1) {
                startActivity(new Intent(VideoActivity.this, Dashboard.class));
                finish();
                overridePendingTransition(R.anim.slide_in_right1, R.anim.slide_in_right);
            } else {
                startActivity(new Intent(VideoActivity.this, Login.class));
                finish();
            }
        });

    }


}