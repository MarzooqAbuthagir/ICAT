package com.maria.pastelhub.videos.playvideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maria.pastelhub.R;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.services.MusicService;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayVideo extends YouTubeBaseActivity implements View.OnClickListener {

    @BindView(R.id.youtube_player_view)
    YouTubePlayerView youtube_player_view;

    NetworkStatusFinder networkStatusFinder;

    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.major_title)
    TextView major_title;

    Intent intent;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        ButterKnife.bind(this);
        stopService(new Intent(this, MusicService.class));
        networkStatus();

        ButterKnife.bind(this);

        clickEvent();

        major_title.setText("");
        findViewById(R.id.sound_icon).setVisibility(View.INVISIBLE);

        intent = getIntent();

        if (intent.getStringExtra("path") != null) {
            path = intent.getStringExtra("path");
//            new LogFile("youtube", " " + path);

            youtube_player_view.initialize(PlayVideo.this.getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {
//                        youTubePlayer.loadVideo("XfP31eWXli4");
                        youTubePlayer.loadVideo(path, 0);
                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                        youTubePlayer.setPlaylistEventListener(new YouTubePlayer.PlaylistEventListener() {
                            @Override
                            public void onPrevious() {

                            }

                            @Override
                            public void onNext() {

                            }

                            @Override
                            public void onPlaylistEnded() {
                                youTubePlayer.seekToMillis(1000);
                                youTubePlayer.pause();
                            }
                        });
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                    Toast.makeText(PlayVideo.this, "Unable to play video", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(PlayVideo.this)) {
            Intent intent = new Intent(PlayVideo.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
            intent.putExtra("from_page", "line_connector");
            startActivity(intent);
            finish();
        }

    }

    private void clickEvent() {
        back_major_header.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_major_header) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_left1, R.anim.slide_in_left);
    }
}