package com.maria.pastelhub.review;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.activity.maze.MazeActivity;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    ImageView back_major_header;
    TextView major_title;
    RatingBar rb;
    ImageView sound_icon;
    Button btn;
    EditText review_desc;
    Pref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        pref = new Pref(ReviewActivity.this);

        init();
        if (pref.getPreferenceFloat(Pref.REVIEW_RATING) != (float)0) {
            rb.setRating(pref.getPreferenceFloat(Pref.REVIEW_RATING));

            if (pref.getPreference(Pref.REVIEW_DESC) != null) {
                review_desc.setText(pref.getPreference(Pref.REVIEW_DESC));
            }
        }

    }

    private void init() {
        back_major_header = findViewById(R.id.back_major_header);
        major_title = findViewById(R.id.major_title);
        rb = findViewById(R.id.rb);
        review_desc = findViewById(R.id.review_desc);
        btn = findViewById(R.id.btn);


        major_title.setText(getResources().getString(R.string.write_your_review));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref.setPreference(Pref.REVIEW_RATING, rb.getRating());
                pref.setPreference(Pref.REVIEW_DESC, review_desc.getText().toString());
                onBackPressed();
            }
        });

        back_major_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sound_icon=findViewById(R.id.sound_icon);
        SingleTon.setListener(this,sound_icon);
    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopService(new Intent(this,MusicService.class));
//    }
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}