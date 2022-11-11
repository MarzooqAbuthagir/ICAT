package com.maria.pastelhub.star_collecting.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.dashboard.Dashboard;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;
import com.maria.pastelhub.star_collecting.adapter.StarCollectingAdapter;
import com.maria.pastelhub.star_collecting.model.StarCollectingModel;

import java.util.ArrayList;

public class StarCollectingActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<StarCollectingModel> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_collecting);

        arrayList = new ArrayList<>();

        loadData();
        init();



    }


    @Override
    protected void onResume() {
        super.onResume();
        SingleTon.setListener(this,findViewById(R.id.sound_icon_header));
    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopService(new Intent(this,MusicService.class));
//    }


    private void loadData() {
        StarCollectingModel starCollectingModel = new StarCollectingModel();
        starCollectingModel.setTitle(getString(R.string.coloring_activity_tamil));
        arrayList.add(starCollectingModel);
        starCollectingModel = new StarCollectingModel();
        starCollectingModel.setTitle(getString(R.string.maze_tamil));
        arrayList.add(starCollectingModel);
        starCollectingModel = new StarCollectingModel();
        starCollectingModel.setTitle(getString(R.string.line_connector_tamil));
        arrayList.add(starCollectingModel);
        starCollectingModel = new StarCollectingModel();
        starCollectingModel.setTitle(getString(R.string.drag_drop_tamil));
        arrayList.add(starCollectingModel);
        starCollectingModel = new StarCollectingModel();
        starCollectingModel.setTitle(getString(R.string.word_search_tamil));
        arrayList.add(starCollectingModel);
        starCollectingModel = new StarCollectingModel();
        starCollectingModel.setTitle(getString(R.string.choose_tamil));
        arrayList.add(starCollectingModel);
    }

    private void init() {
        StarCollectingAdapter starCollectingAdapter = new StarCollectingAdapter(StarCollectingActivity.this, arrayList);
        rv = findViewById(R.id.rv);
        rv.setAdapter(starCollectingAdapter);
    }
}