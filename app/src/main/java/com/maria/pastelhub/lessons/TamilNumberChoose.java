package com.maria.pastelhub.lessons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.activity.BaseActivityExtends;
import com.maria.pastelhub.activity.lineconnector.LineConnector;
import com.maria.pastelhub.application.ICatApplication;
import com.maria.pastelhub.login.Login;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;
import com.maria.pastelhub.videos.livevideos.LiveVideo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TamilNumberChoose extends AppCompatActivity implements View.OnClickListener {

    Intent intent;

    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.sound_icon)
    ImageView sound_icon;

    @BindView(R.id.major_title)
    TextView major_title;
    @BindView(R.id.cvOne)
    CardView cvOne;
    @BindView(R.id.cvTwo)
    CardView cvTwo;

    String netStatus = "";
    Pref pref;
    String lanuage = "0", selectedClass = "";
    String contentView = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tamil_number_page);

        ButterKnife.bind(this);
        pref = new Pref(this);
        intent = getIntent();
        netStatus = intent.getStringExtra("status");
        contentView = intent.getStringExtra("Views");
        selectedClass = intent.getStringExtra("class");
        major_title.setText("");
        cvOne.setOnClickListener(this);
        cvTwo.setOnClickListener(this);
        sound_icon.setOnClickListener(this);
        back_major_header.setOnClickListener(this);

    }

    String volval;

    public void musiccontrol() {

        volval = pref.getMusicval();

        sound_icon = findViewById(R.id.sound_icon);


        sound_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (volval.equals("1")) {
                    volval = "0";
                    sound_icon.setImageResource(R.drawable.sound_mute);
                    pref.setMusicVal("0");
                    if (!ICatApplication.isMusicServiceRunning) {
                        stopService(new Intent(TamilNumberChoose.this, MusicService.class));
                    }
                } else if (volval.equals("0")) {
                    volval = "1";
                    sound_icon.setImageResource(R.drawable.sound_on);
                    pref.setMusicVal("1");
                    startService(new Intent(TamilNumberChoose.this, MusicService.class));
                } else {
                    volval = "1";
                    sound_icon.setImageResource(R.drawable.sound_on);
                    pref.setMusicVal("1");
                    if (ICatApplication.isMusicServiceRunning) {
                        startService(new Intent(TamilNumberChoose.this, MusicService.class));
                    }
                }
            }
        });

        if (volval != null) {
            if (volval.equals("1")) {
                sound_icon.setImageResource(R.drawable.sound_on);
                if (ICatApplication.isMusicServiceRunning) {
                    startService(new Intent(this, MusicService.class));
                }
            } else {
                sound_icon.setImageResource(R.drawable.sound_mute);
                if (ICatApplication.isMusicServiceRunning) {
                    stopService(new Intent(this, MusicService.class));
                }
            }
        }

    }

    String title = "";

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_major_header) {
            backClicked();
        } else if (view.getId() == R.id.sound_icon) {
            musiccontrol();
        } else if (view.getId() == R.id.cvOne) {
            if (contentView.equals(getString(R.string.videos_tamil)) || contentView.equals(getString(R.string.songs_tamil))) {
                if (contentView.equalsIgnoreCase(getString(R.string.videos_tamil)))
                    title = getString(R.string.videos_tamil);
                else title = getString(R.string.songs_tamil);
                startActivity(new Intent(TamilNumberChoose.this, TamilLkgUkgActivity.class) // hided by Abu LiveVideo.class)
                        .putExtra("language", "" + lanuage)
                        .putExtra("Views", contentView)
                        .putExtra("Title", title)
                        .putExtra("choice", "tamilOne")
                        .putExtra("ID", getIntent().getIntExtra("ID", 0)));
            } else {
                startActivity(new Intent(TamilNumberChoose.this, LessonsActivity.class)
                        .putExtra("language", "" + lanuage)
                        .putExtra("class", selectedClass)
                        .putExtra("Views", "tamilOne")
                        .putExtra("ID", getIntent().getIntExtra("ID", 0)));
            }
            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
        } else if (view.getId() == R.id.cvTwo) {
            if (contentView.equals(getString(R.string.videos_tamil)) || contentView.equals(getString(R.string.songs_tamil))) {
                if (contentView.equalsIgnoreCase(getString(R.string.videos_tamil)))
                    title = getString(R.string.videos_tamil);
                else title = getString(R.string.songs_tamil);
                startActivity(new Intent(TamilNumberChoose.this, TamilLkgUkgActivity.class) // hided by Abu LiveVideo.class)
                        .putExtra("language", "" + lanuage)
                        .putExtra("Views", contentView)
                        .putExtra("Title", title)
                        .putExtra("choice", "tamilTwo")
                        .putExtra("ID", getIntent().getIntExtra("ID", 0)));
            } else {
                startActivity(new Intent(TamilNumberChoose.this, LessonsActivity.class)
                        .putExtra("language", "" + lanuage)
                        .putExtra("class", selectedClass)
                        .putExtra("Views", "tamilTwo")
                        .putExtra("ID", getIntent().getIntExtra("ID", 0)));
            }
            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
        }
    }

    @Override
    public void onBackPressed() {
        backClicked();
    }

    private void backClicked() {
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SingleTon.setListener(this, null);
    }

}