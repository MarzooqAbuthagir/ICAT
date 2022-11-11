package com.maria.pastelhub.lessons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.activity.BaseActivityExtends;
import com.maria.pastelhub.R;
import com.maria.pastelhub.book_landing.BookLanding;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.emptystatus.EmptyView;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;
import com.maria.pastelhub.splash_screens.MainActivity;
import com.maria.pastelhub.videos.AudioVideoBean;
import com.maria.pastelhub.videos.livevideos.LiveVideo;
import com.maria.pastelhub.videos.livevideos.LiveVideo1;
import com.maria.pastelhub.videos.livevideos.viewmodel.VideoViewModel;
import com.maria.pastelhub.videos.playvideo.PlayVideo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LessonsActivity extends AppCompatActivity implements View.OnClickListener {

    NetworkStatusFinder networkStatusFinder;
    Pref pref;

    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.major_title)
    TextView major_title;

    @BindView(R.id.activity_one)
    CardView activity_one;
    @BindView(R.id.activity_intro)
    CardView activity_intro;
    @BindView(R.id.activity_con)
    CardView activity_con;

    @BindView(R.id.activity_two)
    CardView activity_two;

    @BindView(R.id.activity_three)
    CardView activity_three;

    @BindView(R.id.activity_four)
    CardView activity_four;

    @BindView(R.id.activity_five)
    CardView activity_five;

    @BindView(R.id.activity_six)
    CardView activity_six;

    @BindView(R.id.activity_seven)
    CardView activity_seven;

    @BindView(R.id.activity_eight)
    CardView activity_eight;

    @BindView(R.id.activity_nine)
    CardView activity_nine;

    @BindView(R.id.activity_eleven)
    CardView activity_eleven;
    @BindView(R.id.activity_twelve)
    CardView activity_twelve;
    @BindView(R.id.activity_thirteen)
    CardView activity_thirteen;
    @BindView(R.id.activity_fourteen)
    CardView activity_fourteen;
    @BindView(R.id.activity_fifteen)
    CardView activity_fifteen;
    @BindView(R.id.activity_sixteen)
    CardView activity_sixteen;
    @BindView(R.id.activity_seventeen)
    CardView activity_seventeen;
    @BindView(R.id.activity_eighteen)
    CardView activity_eighteen;
    @BindView(R.id.activity_nineteen)
    CardView activity_nineteen;
    @BindView(R.id.activity_twenty)
    CardView activity_twenty;
    @BindView(R.id.activity_twentyone)
    CardView activity_twentyone;
    @BindView(R.id.activity_twentytwo)
    CardView activity_twentytwo;
    @BindView(R.id.activity_ten)
    CardView activity_ten;

    ImageView sound_icon;
    String lanuage = "", selectedClass = "";
    String contentViews = "";
    int classnum;
    int position;
    String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        pref = new Pref(this);
        networkStatus();

        ButterKnife.bind(this);
        classnum = getIntent().getIntExtra("ID", 0);
        lanuage = getIntent().getStringExtra("language");
        selectedClass = getIntent().getStringExtra("class");
        contentViews = getIntent().getStringExtra("Views");
        if (lanuage == null)
            lanuage = "0";

        if (lanuage.equals("0") && (classnum == 5) && contentViews.equalsIgnoreCase("")) {
            activity_intro.setVisibility(View.VISIBLE);
            activity_con.setVisibility(View.VISIBLE);
            activity_eight.setVisibility(View.GONE);
        } else {
            activity_intro.setVisibility(View.GONE);
            activity_con.setVisibility(View.GONE);
            activity_eight.setVisibility(View.VISIBLE);
        }
        if (lanuage.equals("0") && (classnum == 5) && (contentViews.equalsIgnoreCase(getString(R.string.songs_tamil)) || contentViews.equalsIgnoreCase(getString(R.string.videos_tamil)))) {
            activity_eight.setVisibility(View.GONE);
        } else if (lanuage.equals("0") && (classnum == 5) && contentViews.equalsIgnoreCase(getString(R.string.activity_tamil))) {
            activity_eight.setVisibility(View.GONE);
        }
        if (lanuage.equals("1")) {
            if (getString(R.string.videos_tamil).equals(getIntent().getStringExtra("Views")))
                major_title.setText("Videos");
            else if (getString(R.string.songs_tamil).equals(getIntent().getStringExtra("Views")))
                major_title.setText("Songs");
            else if (("web").equals(getIntent().getStringExtra("from")))
                major_title.setText("Web Links");
            else if ("".equals(getIntent().getStringExtra("Views")))
                major_title.setText("Web Links");
            else
                major_title.setText("Activities");
            ((TextView) findViewById(R.id.tvAct1)).setText(getString(R.string.activity_1_eng));
            ((TextView) findViewById(R.id.tvAct2)).setText(getString(R.string.activity_2_eng));
            ((TextView) findViewById(R.id.tvAct3)).setText(getString(R.string.activity_3_eng));
            ((TextView) findViewById(R.id.tvAct4)).setText(getString(R.string.activity_4_eng));
            ((TextView) findViewById(R.id.tvAct5)).setText(getString(R.string.activity_5_eng));
            ((TextView) findViewById(R.id.tvAct6)).setText(getString(R.string.activity_6_eng));
            ((TextView) findViewById(R.id.tvAct7)).setText(getString(R.string.activity_7_eng));
            ((TextView) findViewById(R.id.tvAct8)).setText(getString(R.string.activity_8_eng));
//            if (classnum == 0 || classnum == 1)
//                activity_eight.setVisibility(View.GONE);
        } else {

            if (getString(R.string.videos_tamil).equals(getIntent().getStringExtra("Views")))
                major_title.setText(R.string.videos_tamil);
            else if (getString(R.string.songs_tamil).equals(getIntent().getStringExtra("Views")))
                major_title.setText(R.string.songs_tamil);
            else if ("".equals(getIntent().getStringExtra("Views")))
                major_title.setText(R.string.web_links_tamil);
            else
                major_title.setText(R.string.activity_tamil);
            if (classnum == 0 && (contentViews).equals("")) {
                activity_nine.setVisibility(View.VISIBLE);
                activity_ten.setVisibility(View.VISIBLE);
                activity_eleven.setVisibility(View.VISIBLE);
                activity_twelve.setVisibility(View.VISIBLE);
                activity_thirteen.setVisibility(View.VISIBLE);
                activity_fourteen.setVisibility(View.VISIBLE);
                activity_fifteen.setVisibility(View.VISIBLE);
                activity_sixteen.setVisibility(View.VISIBLE);
                activity_seventeen.setVisibility(View.VISIBLE);
                activity_eighteen.setVisibility(View.VISIBLE);
                activity_nineteen.setVisibility(View.VISIBLE);
                activity_twenty.setVisibility(View.VISIBLE);
                activity_twentyone.setVisibility(View.VISIBLE);
                activity_twentytwo.setVisibility(View.VISIBLE);
            } else if (contentViews.equals("tamilOne")) {
                activity_ten.setVisibility(View.VISIBLE);
                activity_eight.setVisibility(View.VISIBLE);
                activity_nine.setVisibility(View.VISIBLE);
                activity_eleven.setVisibility(View.VISIBLE);
                activity_twelve.setVisibility(View.VISIBLE);
                major_title.setText(R.string.activity_tamil);
                ((TextView) findViewById(R.id.tvAct1)).setText(getString(R.string.activity_1_tamil_lu));
                ((TextView) findViewById(R.id.tvAct2)).setText(getString(R.string.activity_2_tamil_lu));
                ((TextView) findViewById(R.id.tvAct3)).setText(getString(R.string.activity_3_tamil_lu));
                ((TextView) findViewById(R.id.tvAct4)).setText(getString(R.string.activity_4_tamil_lu));
                ((TextView) findViewById(R.id.tvAct5)).setText(getString(R.string.activity_5_tamil_lu));
                ((TextView) findViewById(R.id.tvAct6)).setText(getString(R.string.activity_6_tamil_lu));
                ((TextView) findViewById(R.id.tvAct7)).setText(getString(R.string.activity_7_tamil_lu));
                ((TextView) findViewById(R.id.tvAct8)).setText(getString(R.string.activity_8_tamil_lu));
                ((TextView) findViewById(R.id.tvAct9)).setText(getString(R.string.activity_9_tamil_lu));
                ((TextView) findViewById(R.id.tvAct10)).setText(getString(R.string.activity_10_tamil_lu));
                ((TextView) findViewById(R.id.tvAct11)).setText(getString(R.string.activity_11_tamil_lu));
                ((TextView) findViewById(R.id.tvAct12)).setText(getString(R.string.activity_12_tamil_lu));
            } else if (contentViews.equals("tamilTwo")) {
                activity_ten.setVisibility(View.VISIBLE);
                activity_eight.setVisibility(View.VISIBLE);
                activity_nine.setVisibility(View.VISIBLE);
                major_title.setText(R.string.activity_tamil);
                ((TextView) findViewById(R.id.tvAct1)).setText(getString(R.string.activity_1_tamil_l));
                ((TextView) findViewById(R.id.tvAct2)).setText(getString(R.string.activity_2_tamil_l));
                ((TextView) findViewById(R.id.tvAct3)).setText(getString(R.string.activity_3_tamil_l));
                ((TextView) findViewById(R.id.tvAct4)).setText(getString(R.string.activity_4_tamil_l));
                ((TextView) findViewById(R.id.tvAct5)).setText(getString(R.string.activity_5_tamil_l));
                ((TextView) findViewById(R.id.tvAct6)).setText(getString(R.string.activity_6_tamil_l));
                ((TextView) findViewById(R.id.tvAct7)).setText(getString(R.string.activity_7_tamil_l));
                ((TextView) findViewById(R.id.tvAct8)).setText(getString(R.string.activity_8_tamil_l));
                ((TextView) findViewById(R.id.tvAct9)).setText(getString(R.string.activity_9_tamil_l));
                ((TextView) findViewById(R.id.tvAct10)).setText(getString(R.string.activity_10_tamil_l));
            }
        }
        clickLisener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SingleTon.setListener(this, findViewById(R.id.sound_icon));
    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopService(new Intent(this,MusicService.class));
//    }

    private void clickLisener() {
        activity_intro.setOnClickListener(this);
        activity_con.setOnClickListener(this);
        activity_one.setOnClickListener(this);
        activity_two.setOnClickListener(this);
        activity_three.setOnClickListener(this);
        activity_four.setOnClickListener(this);
        activity_five.setOnClickListener(this);
        activity_six.setOnClickListener(this);
        activity_seven.setOnClickListener(this);
        activity_eight.setOnClickListener(this);
        activity_nine.setOnClickListener(this);
        activity_ten.setOnClickListener(this);
        activity_nineteen.setOnClickListener(this);
        activity_eleven.setOnClickListener(this);
        activity_twelve.setOnClickListener(this);
        activity_thirteen.setOnClickListener(this);
        activity_fourteen.setOnClickListener(this);
        activity_fifteen.setOnClickListener(this);
        activity_sixteen.setOnClickListener(this);
        activity_seventeen.setOnClickListener(this);
        activity_eighteen.setOnClickListener(this);
        activity_twenty.setOnClickListener(this);
        activity_twentyone.setOnClickListener(this);
        activity_twentytwo.setOnClickListener(this);
        back_major_header.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_major_header) {
            backClicked();
        } else if (view.getId() == R.id.back_major_header) {
            backClicked();
        } else if (view.getId() == R.id.activity_intro) {
            position = 11;
            callClass("intro", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_con) {
            position = 12;
            callClass("conclusion", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_one) {
            if (contentViews.equals("tamilTwo"))
                position = 13;
            else
                position = 1;

            callClass("lesson1", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_two) {
            if (contentViews.equals("tamilTwo"))
                position = 14;
            else
                position = 2;
            callClass("lesson2", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_three) {
            if (contentViews.equals("tamilTwo"))
                position = 15;
            else
                position = 3;
            callClass("lesson3", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_four) {
            if (contentViews.equals("tamilTwo"))
                position = 16;
            else
                position = 4;
            callClass("lesson4", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_five) {
            if (contentViews.equals("tamilTwo"))
                position = 17;
            else
                position = 5;
            callClass("lesson5", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_six) {
            if (contentViews.equals("tamilTwo"))
                position = 18;
            else
                position = 6;
            callClass("lesson6", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_seven) {
            if (contentViews.equals("tamilTwo"))
                position = 19;
            else
                position = 7;
            callClass("lesson7", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_eight) {
            if (contentViews.equals("tamilTwo"))
                position = 20;
            else
                position = 8;
//            if (lanuage.equals("1") && (classnum == 0 || classnum == 1))
//                startActivity(new Intent(LessonsActivity.this, EmptyStatus.class));
//            else
                callClass("lesson8", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_nine) {
            if (contentViews.equals("tamilTwo"))
                position = 21;
            else
                position = 9;
            callClass("lesson9", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_ten) {
            if (contentViews.equals("tamilTwo"))
                position = 22;
            else
                position = 10;
            callClass("lesson10", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_eleven) {
            position = 11;
            callClass("lesson11", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_twelve) {
            position = 12;
            callClass("lesson12", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_thirteen) {
            position = 13;
            callClass("lesson13", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_fourteen) {
            position = 14;
            callClass("lesson14", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_fifteen) {

            position = 15;
            callClass("lesson15", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_sixteen) {

            position = 16;
            callClass("lesson16", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_seventeen) {

            position = 17;
            callClass("lesson17", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_eighteen) {

            position = 18;
            callClass("lesson18", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_nineteen) {

            position = 19;
            callClass("lesson19", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_twenty) {
            position = 20;
            callClass("lesson20", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_twentyone) {
            position = 21;
            callClass("lesson21", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        } else if (view.getId() == R.id.activity_twentytwo) {
            position = 22;

            callClass("lesson12", "" + ((TextView) ((CardView) view).getChildAt(0)).getText().toString());
        }
    }


    private void callClass(String lesson, String title) {
        try {
            Log.i(getClass().getName(), "===================rightstr" + title);
//                Log.i("DEBUG","--Title ::"+((TextView)((CardView)view).getChildAt(0)).getText().toString());
//                Log.i("DEBUG","--ID ::"+getIntent().getIntExtra("ID",0));
            pref.setLesson(lesson);

            if (getString(R.string.videos_tamil).equals(contentViews)) {
                Intent intent = new Intent(LessonsActivity.this, LiveVideo.class);
                intent.putExtra("Title", title);
                intent.putExtra("language", lanuage);
                intent.putExtra("pos", position);
                intent.putExtra("ID", getIntent().getIntExtra("ID", 0));
                intent.putExtra("Views", contentViews);
//                    intent.putExtra("from", "live");
                startActivity(intent);
                overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
            } else if (contentViews.equals("")) {
                Intent intent = new Intent(LessonsActivity.this, LiveVideo1.class);
                intent.putExtra("Title", title);
                intent.putExtra("language", lanuage);
                intent.putExtra("pos", position);
                intent.putExtra("ID", getIntent().getIntExtra("ID", 0));
                intent.putExtra("Views", contentViews);
//                    intent.putExtra("from", "live");
                startActivity(intent);
                overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
            } else if (getString(R.string.songs_tamil).equals(contentViews)) {
//                    major_title.setText(R.string.songs_tamil);
                Intent intent = new Intent(LessonsActivity.this, LiveVideo.class);
                intent.putExtra("Title", title);
                intent.putExtra("language", lanuage);
                intent.putExtra("ID", getIntent().getIntExtra("ID", 0));
                intent.putExtra("Views", contentViews);
//                    intent.putExtra("from", "songs1");
                startActivity(intent);
                overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
            } else {
                Log.i(getClass().getName(), "=================== " + classnum);
                startActivity(new Intent(LessonsActivity.this, BaseActivityExtends.class)
                        .putExtra("language", "" + lanuage)
                        .putExtra("class", selectedClass)
                        .putExtra("Title", title)
                        .putExtra("pos", position)
                        .putExtra("Views", contentViews)
                        .putExtra("ID", getIntent().getIntExtra("ID", 0)));
                overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(LessonsActivity.this)) {
            Intent intent = new Intent(LessonsActivity.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
            intent.putExtra("from_page", "book_landing");
            startActivity(intent);
            finish();
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
}