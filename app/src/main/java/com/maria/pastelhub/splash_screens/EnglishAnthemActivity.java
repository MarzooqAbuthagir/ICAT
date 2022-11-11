package com.maria.pastelhub.splash_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.dashboard.Dashboard;
import com.maria.pastelhub.login.Login;
import com.maria.pastelhub.prefference.AnthemPref;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;
import com.maria.pastelhub.videos.livevideos.LiveVideo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnglishAnthemActivity extends AppCompatActivity {
    @BindView(R.id.english_video)
    VideoView videoView;

    @BindView(R.id.tv_skip)
    TextView tv_skip;

    @BindView(R.id.tv_song_title)
    TextView tv_song_title;

    AnthemPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_anthem);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_english_anthem);

        ButterKnife.bind(this);

        pref = new AnthemPref(EnglishAnthemActivity.this);
//        int englishAnthemCount = pref.getEnglishAnthemCount();
//        if (englishAnthemCount < 1) tv_skip.setVisibility(View.GONE);
//        else tv_skip.setVisibility(View.VISIBLE);
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(getApplicationContext(), MusicService.class));
                pref.setEnglishAnthemCount(1);
                pref.setEnglishAnthemView(false);
                finish();
            }
        });

        tv_song_title.setText("Catechism Anthem");

        pref.setEnglishAnthemCount(1);
        pref.setEnglishAnthemView(false);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoPath("https://icatapp-tnbclc.in/icat/English_Anthem.mp4");
//        int rawId = getResources().getIdentifier("english_anthem", "raw", getPackageName());
//        String path = "android.resource://" + getPackageName() + "/" + rawId;
//        Uri uri = Uri.parse(path);
//        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnCompletionListener(mediaPlayer -> {
            startService(new Intent(getApplicationContext(), MusicService.class));
            pref.setEnglishAnthemCount(1);
            pref.setEnglishAnthemView(false);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        stopService(new Intent(EnglishAnthemActivity.this, MusicService.class));
    }

    @Override
    public void onBackPressed() {
    }
}