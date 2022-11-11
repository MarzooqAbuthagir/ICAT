package com.maria.pastelhub.emptystatus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.services.MusicService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyStatus extends AppCompatActivity implements View.OnClickListener {

    Intent intent;

    @BindView(R.id.load_back)
    CardView load_back;

    @BindView(R.id.empty_image)
    ImageView empty_image;

    @BindView(R.id.empty_title)
    TextView empty_title;

    @BindView(R.id.empty_subtitle)
    TextView empty_subtitle;

    String netStatus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_status);

        ButterKnife.bind(this);

        intent = getIntent();
        netStatus = intent.getStringExtra("status");

        if (intent.getStringExtra("book_id") != null) {
            //
        } else if (netStatus != null){
            if (netStatus.equals("no_net")) {
                empty_image.setImageResource(R.drawable.no_internet);
                empty_title.setText(R.string.no_internet);
                empty_subtitle.setText(R.string.no_internet_subtitle);
            } else if (netStatus.equals("something")) {
                empty_image.setImageResource(R.drawable.something_went_wrong);
                empty_title.setText(R.string.something_went_wrong);
                empty_subtitle.setText(R.string.something_went_wrong_subtitle);
            }
        }

        load_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.load_back) {
            onBackPressed();
        }
    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopService(new Intent(this, MusicService.class));
//    }
    @Override
    protected void onResume() {
        super.onResume();
        SingleTon.setListener(this,null);
    }

}