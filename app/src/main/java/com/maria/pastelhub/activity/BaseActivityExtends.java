package com.maria.pastelhub.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.activity.choose.ChooseAnswerActivity;
import com.maria.pastelhub.activity.colordrawing.MainActivity;
import com.maria.pastelhub.activity.draganddrop.DragandDrop;
import com.maria.pastelhub.activity.draganddrop.DragandDroplu;
import com.maria.pastelhub.activity.draganddrop.DragandDropluTamil;
import com.maria.pastelhub.activity.match.LkgMatchActivtiy;
import com.maria.pastelhub.activity.match.MatchActivtiy;
import com.maria.pastelhub.activity.match.TamilLkgMatchActivtiy;
import com.maria.pastelhub.activity.match.UkgMatchActivtiy;
import com.maria.pastelhub.activity.maze.MazeActivity;
import com.maria.pastelhub.activity.wordsearch.WordSearch;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.lessons.LessonsActivity;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.services.MusicService;
import com.maria.pastelhub.videos.livevideos.LiveVideo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivityExtends extends AppCompatActivity implements View.OnClickListener {

    NetworkStatusFinder networkStatusFinder;

    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.major_title)
    TextView major_title;

    @BindView(R.id.coloring)
    CardView coloring;

    @BindView(R.id.maze)
    CardView maze;

    @BindView(R.id.choose)
    CardView choose;

    @BindView(R.id.line)
    CardView line;

    @BindView(R.id.drag_drop)
    CardView drag_drop;

    @BindView(R.id.word)
    CardView word;

    String language = "", selectedClass = "";
    int position = 0, classnum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_extends);

        networkStatus();

        ButterKnife.bind(this);

        clickListener();

        String s;
        major_title.setText(s = getIntent().getStringExtra("Title"));
        classnum = getIntent().getIntExtra("ID", 0);
        language = getIntent().getStringExtra("language");
        selectedClass = getIntent().getStringExtra("class");
        if (language.equals("0") && classnum == 0)
            position = getIntent().getIntExtra("pos", 0);
        else
            position = (Integer.parseInt(s.split(" ")[2]));
        Log.i(getClass().getName(), "==================  position " + s + "   " + language + "   " + position);
        if (getIntent().getStringExtra("language").equals("1")) {
            ((TextView) findViewById(R.id.tvCc)).setText(getString(R.string.celebrating_colouring));
            ((TextView) findViewById(R.id.tvStw)).setText(getString(R.string.show_the_way));
            ((TextView) findViewById(R.id.tvEM)).setText(getString(R.string.enjoy_matching));
            ((TextView) findViewById(R.id.tvRjw)).setText(getString(R.string.r_word));
            TextView tvPh = ((TextView) findViewById(R.id.tvPh));
            tvPh.setText(getString(R.string.prayer_hunt));
            ;
            TextView tvCca = ((TextView) findViewById(R.id.tvCCa));
            tvCca.setText(getString(R.string.choose_the_correct_ans));
            if (classnum == 0 || classnum == 1) {
                ((CardView) findViewById(R.id.word)).setVisibility(View.GONE);
                ((CardView) findViewById(R.id.choose)).setVisibility(View.GONE);
                ((TextView) findViewById(R.id.tvEM)).setText("Choose the correct picture - Creation");
                ((TextView) findViewById(R.id.tvRjw)).setText("Arrange the Jumbled Bible names");
            }
        } else {
            if (classnum == 0) {
                ((CardView) findViewById(R.id.word)).setVisibility(View.GONE);
                ((CardView) findViewById(R.id.choose)).setVisibility(View.GONE);
            }
            if (selectedClass.equalsIgnoreCase("LKG & UKG")) {
                ((TextView) findViewById(R.id.tvRjw)).setText("விவிலியப் பெயர்களை வரிசைப்படுத்து");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SingleTon.setListener(this, findViewById(R.id.sound_icon));
    }

    private void clickListener() {
        coloring.setOnClickListener(this);
        maze.setOnClickListener(this);
        choose.setOnClickListener(this);
        line.setOnClickListener(this);
        drag_drop.setOnClickListener(this);
        word.setOnClickListener(this);
        back_major_header.setOnClickListener(this);
    }

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(BaseActivityExtends.this)) {
            Intent intent = new Intent(BaseActivityExtends.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_major_header) {
            backClicked();
        } else if (view.getId() == R.id.coloring) {
            String actName = ((TextView) findViewById(R.id.tvCc)).getText().toString();
            startActivity(new Intent(BaseActivityExtends.this, MainActivity.class).putExtra("Position", position).putExtra("class", classnum).putExtra("language", "" + language).putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);

        } else if (view.getId() == R.id.maze) {
            MazeActivity.COLS = 10; // hided by Abu + position / 2;
            MazeActivity.ROWS = 10; // hided by Abu + position / 2;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            MazeActivity.widthref = displayMetrics.widthPixels;
            String actName = ((TextView) findViewById(R.id.tvStw)).getText().toString();
            startActivity(new Intent(BaseActivityExtends.this, MazeActivity.class).putExtra("Position", position).putExtra("class", classnum).putExtra("language", "" + language).putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
        } else if (view.getId() == R.id.choose) {
            String actName = ((TextView) findViewById(R.id.tvCCa)).getText().toString();
            startActivity(new Intent(BaseActivityExtends.this, ChooseAnswerActivity.class).putExtra("Position", position).putExtra("class", classnum).putExtra("language", "" + language).putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);

        } else if (view.getId() == R.id.line)
            if (language.equalsIgnoreCase("1")) {
                String actName = ((TextView) findViewById(R.id.tvEM)).getText().toString();
                if (classnum == 0) {
                    startActivity(new Intent(BaseActivityExtends.this, LkgMatchActivtiy.class).putExtra("Position", position).putExtra("class", classnum).putExtra("language", "" + language).putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
                } else if (classnum == 1) {
                    startActivity(new Intent(BaseActivityExtends.this, UkgMatchActivtiy.class).putExtra("Position", position).putExtra("class", classnum).putExtra("language", "" + language).putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
                } else
                    startActivity(new Intent(BaseActivityExtends.this, MatchActivtiy.class).putExtra("Position", position).putExtra("class", classnum).putExtra("language", "" + language).putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
                overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
            } else {
                String actName = ((TextView) findViewById(R.id.tvEM)).getText().toString();
                if (classnum == 0)
                    startActivity(new Intent(BaseActivityExtends.this, TamilLkgMatchActivtiy.class).putExtra("Position", position).putExtra("class", classnum).putExtra("language", "" + language).putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
                else
                    startActivity(new Intent(BaseActivityExtends.this, MatchActivtiy.class).putExtra("Position", position).putExtra("class", classnum).putExtra("language", "" + language).putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
                overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);

            }
        else if (view.getId() == R.id.drag_drop) {
            if (language.equals("1") && (classnum == 0 || classnum == 1)) {
                String actName = ((TextView) findViewById(R.id.tvRjw)).getText().toString();
                startActivity(new Intent(BaseActivityExtends.this, DragandDroplu.class)
                        .putExtra("Position", position)
                        .putExtra("class", classnum)
                        .putExtra("language", "" + language)
                        .putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
            } else if (classnum == 0) {
                String actName = ((TextView) findViewById(R.id.tvRjw)).getText().toString();
                startActivity(new Intent(BaseActivityExtends.this, DragandDropluTamil.class)
                        .putExtra("Position", position)
                        .putExtra("class", classnum)
                        .putExtra("language", "" + language)
                        .putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
            } else {
                String actName = ((TextView) findViewById(R.id.tvRjw)).getText().toString();
                startActivity(new Intent(BaseActivityExtends.this, DragandDrop.class).putExtra("Position", position).putExtra("class", classnum).putExtra("language", "" + language).putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
            }
                overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);

        } else if (view.getId() == R.id.word) {
            String actName = ((TextView) findViewById(R.id.tvPh)).getText().toString();
            startActivity(new Intent(BaseActivityExtends.this, WordSearch.class).putExtra("Position", position).putExtra("class", classnum).putExtra("language", "" + language).putExtra("selectedClass", selectedClass).putExtra("title", getIntent().getStringExtra("Title")).putExtra("actName", actName));
            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);

        }
    }
}