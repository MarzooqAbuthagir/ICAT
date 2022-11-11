package com.maria.pastelhub.emptystatus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.dashboard.Dashboard;
import com.maria.pastelhub.splash_screens.VideoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyView extends AppCompatActivity implements View.OnClickListener {

    Intent intent;

    @BindView(R.id.cvBack)
    CardView cvBack;
//    String netStatus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_view);

        ButterKnife.bind(this);

        intent = getIntent();
//        netStatus = intent.getStringExtra("status");


        cvBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cvBack) {
//            onBackPressed();
            startActivity(new Intent(EmptyView.this, Dashboard.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(EmptyView.this, Dashboard.class));
        finish();
    }

    //    @Override
//    protected void onPause() {
//        super.onPause();
//        stopService(new Intent(this, MusicService.class));
//    }
    @Override
    protected void onResume() {
        super.onResume();
        SingleTon.setListener(this, null);
    }

}