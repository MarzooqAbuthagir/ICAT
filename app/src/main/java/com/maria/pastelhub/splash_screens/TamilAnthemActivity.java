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
import com.maria.pastelhub.prefference.AnthemPref;
import com.maria.pastelhub.services.MusicService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TamilAnthemActivity extends AppCompatActivity {
    @BindView(R.id.tamil_video)
    VideoView videoView;

    @BindView(R.id.tv_skip)
    TextView tv_skip;

    @BindView(R.id.tv_song_title)
    TextView tv_song_title;

    AnthemPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamil_anthem);


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_tamil_anthem);

        ButterKnife.bind(this);

        pref = new AnthemPref(TamilAnthemActivity.this);
//        int tamilAnthemCount = pref.getTamilAnthemCount();
//        if (tamilAnthemCount < 1) tv_skip.setVisibility(View.GONE);
//        else tv_skip.setVisibility(View.VISIBLE);
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(getApplicationContext(), MusicService.class));
                pref.setTamilAnthemCount(1);
                pref.setTamilAnthemView(false);
                finish();
            }
        });

        tv_song_title.setText("மறைக்கல்வி கீதம்");

        pref.setTamilAnthemCount(1);
        pref.setTamilAnthemView(false);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoPath("https://icatapp-tnbclc.in/icat/Tamil_Anthem.mp4");
//        int rawId = getResources().getIdentifier("tamil_anthem", "raw", getPackageName());
//        String path = "android.resource://" + getPackageName() + "/" + rawId;
//        Uri uri = Uri.parse(path);
//        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnCompletionListener(mediaPlayer -> {
            startService(new Intent(getApplicationContext(), MusicService.class));
            pref.setTamilAnthemCount(1);
            pref.setTamilAnthemView(false);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        stopService(new Intent(TamilAnthemActivity.this, MusicService.class));
    }

    @Override
    public void onBackPressed() {
    }
}