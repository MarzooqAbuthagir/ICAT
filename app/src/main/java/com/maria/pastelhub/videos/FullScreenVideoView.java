package com.maria.pastelhub.videos;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.maria.pastelhub.R;

public class FullScreenVideoView extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_full_screen_video_view);

            VideoView videoView = findViewById(R.id.video_view);
            RelativeLayout  rl_progress = findViewById(R.id.rl_progress);
            rl_progress.setVisibility(View.VISIBLE);

            Uri videoUri = Uri.parse(getIntent().getStringExtra("videoUrl"));
            videoView.setVideoURI(videoUri);

            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.seekTo((getIntent().getIntExtra("duration",0)));
            videoView.start();

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    rl_progress.setVisibility(View.GONE);
                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                            videoView.setMediaController(mediaController);
                            mediaController.setAnchorView(videoView);
                        }
                    });
                }
            });
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                    Toast.makeText(FullScreenVideoView.this, "Videos completed", Toast.LENGTH_SHORT).show();
                    rl_progress.setVisibility(View.GONE);
                }
            });

            videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Log.d("DEBUG", "What " + what + " extra " + extra);
                    rl_progress.setVisibility(View.GONE);
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
