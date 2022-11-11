package com.maria.pastelhub.activity.match;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.activity.choose.ChooseAnswerActivity;
import com.maria.pastelhub.activity.colordrawing.MainActivity;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.api.Results;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.api.SaveScore;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LkgMatchActivtiy extends AppCompatActivity implements View.OnClickListener {

    NetworkStatusFinder networkStatusFinder;
    Pref pref;

    ArrayList<String> imgList = new ArrayList<String>();
    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.major_title)
    TextView major_title;
    String language = "";
    HashMap<String, String> list = new HashMap<>();

    String lesson = "";
    String selectedClass ="", title ="", actName ="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_match_lkg);

        networkStatus();
        pref = new Pref(this);
        ButterKnife.bind(this);

        clickEvent();

        Log.i(getClass().getName(), "=============777====" + pref.getLesson());
        getImages();

        if (getIntent().getStringExtra("language").equals("1")) {
            major_title.setText("Choose the correct picture - Creation");
        } else {
            major_title.setText(R.string.line_connector_tamil);
        }
        language = getIntent().getStringExtra("language");
        title = getIntent().getStringExtra("title");
        selectedClass = getIntent().getStringExtra("selectedClass");
        actName = getIntent().getStringExtra("actName");
        System.out.println("value--------------"+ language);
        System.out.println("value--------------"+ selectedClass);
        System.out.println("value--------------"+ title);
        System.out.println("value--------------"+ actName);

    }

    private void setRecyclerviewData() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MatchAdapter(this, list));

    }

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(LkgMatchActivtiy.this)) {
            Intent intent = new Intent(LkgMatchActivtiy.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
            startActivity(intent);
            finish();
        }
    }

    private void clickEvent() {

        back_major_header.setOnClickListener(this);
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
    public void onClick(View v) {
        if (v.getId() == R.id.back_major_header) {
            backClicked();
        }
    }

    private void getImages() {
        Call<Results> call = RetrofitClient.getInstance().getMyApi().getImages();
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                Results r = response.body();
//                List<Results> myheroList = response.body();
//                String[] oneHeroes = new String[myheroList.size()];
                Log.i(getClass().getName(), "=================" + pref.getLesson());
                if (pref.getLesson().equalsIgnoreCase("lesson1")) {
                    String[] s1 = {"Adam", "Eve", "Serpent", "Tree", "Fruit"};
                    for (int i = 0; i < r.getData().getLesson1().size(); i++) {
                        String[] arrayStr = r.getData().getLesson1().get(i).split(",");
                        list.put(s1[0], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson1/Choose the Correct Picture_1_1_2.png");
                        list.put(s1[1], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson1/Choose the Correct Picture_1_1_4.png");
                        list.put(s1[2], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson1/Choose the Correct Picture_1_1_1.png");
                        list.put(s1[3], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson1/Choose the Correct Picture_1_1_5.png");
                        list.put(s1[4], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson1/Choose the Correct Picture_1_1_3.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
                    }
                } else if (pref.getLesson().equalsIgnoreCase("lesson2")) {
                    String[] s1 = {"Jesus", "Peter", "John", "James", "Andrew"};
                    for (int i = 0; i < r.getData().getLesson2().size(); i++) {
                        Log.i(getClass().getName(), "onResourceReady:outer " + r.getData().getLesson2().get(i));
                        String[] arrayStr = r.getData().getLesson2().get(i).split(",");
                        list.put(s1[0], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson2/Choose the Correct Picture_1_2_3.png");
                        list.put(s1[1], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson2/Choose the Correct Picture_1_2_1.png");
                        list.put(s1[2], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson2/Choose the Correct Picture_1_2_5.png");
                        list.put(s1[3], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson2/Choose the Correct Picture_1_2_2.png");
                        list.put(s1[4], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson2/Choose the Correct Picture_1_2_4.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
                    }
                } else if (pref.getLesson().equalsIgnoreCase("lesson3")) {
                    String[] s1 = {"Joseph", "Mary", "Infant Jesus", "Star", "Angel"};
                    for (int i = 0; i < r.getData().getLesson3().size(); i++) {
                        String[] arrayStr = r.getData().getLesson3().get(i).split(",");
                        list.put(s1[0], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson3/Choose the Correct Picture_1_3_3.png");
                        list.put(s1[1], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson3/Choose the Correct Picture_1_3_1.png");
                        list.put(s1[2], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson3/Choose the Correct Picture_1_3_5.png");
                        list.put(s1[3], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson3/Choose the Correct Picture_1_3_2.png");
                        list.put(s1[4], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson3/Choose the Correct Picture_1_3_4.png");
                    }
                } else if (pref.getLesson().equalsIgnoreCase("lesson4")) {
                    String[] s1 = {"Joseph", "Mary", "Boy Jesus", "Sheep", "Axe"};
                    for (int i = 0; i < r.getData().getLesson4().size(); i++) {
                        String[] arrayStr = r.getData().getLesson4().get(i).split(",");
                        list.put(s1[0], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson4/Choose the Correct Picture_1_4_5.png");
                        list.put(s1[1], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson4/Choose the Correct Picture_1_4_3.png");
                        list.put(s1[2], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson4/Choose the Correct Picture_1_4_2.png");
                        list.put(s1[3], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson4/Choose the Correct Picture_1_4_1.png");
                        list.put(s1[4], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson4/Choose the Correct Picture_1_4_4.png");
                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
                    }
                } else if (pref.getLesson().equalsIgnoreCase("lesson5")) {
                    String[] s1 = {"Noah", "Ark", "Rainbow", "Elephant", "Monkey"};
                    for (int i = 0; i < r.getData().getLesson5().size(); i++) {
                        String[] arrayStr = r.getData().getLesson5().get(i).split(",");
                        list.put(s1[0], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson5/Choose the Correct Picture_1_5_4.png");
                        list.put(s1[1], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson5/Choose the Correct Picture_1_5_1.png");
                        list.put(s1[2], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson5/Choose the Correct Picture_1_5_5.png");
                        list.put(s1[3], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson5/Choose the Correct Picture_1_5_2.png");
                        list.put(s1[4], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson5/Choose the Correct Picture_1_5_3.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
                    }
                } else if (pref.getLesson().equalsIgnoreCase("lesson6")) {
                    String[] s1 = {"Risen Jesus", "Mary Magdalene", "Tomb", "Stone", "Tree"};
                    for (int i = 0; i < r.getData().getLesson6().size(); i++) {
                        String[] arrayStr = r.getData().getLesson6().get(i).split(",");
                        list.put(s1[0], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson6/Choose the Correct Picture_1_6_2.png");
                        list.put(s1[1], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson6/Choose the Correct Picture_1_6_3.png");
                        list.put(s1[2], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson6/Choose the Correct Picture_1_6_5.png");
                        list.put(s1[3], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson6/Choose the Correct Picture_1_6_1.png");
                        list.put(s1[4], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson6/Choose the Correct Picture_1_6_4.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
                    }
                } else if (pref.getLesson().equalsIgnoreCase("lesson7")) {
                    String[] s1 = {"Jesus", "Veronica", "Cross", "Soldiers", "Crowd"};
                    for (int i = 0; i < r.getData().getLesson7().size(); i++) {
                        String[] arrayStr = r.getData().getLesson7().get(i).split(",");
                        list.put(s1[0], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson7/Choose the Correct Picture_1_7_3.png");
                        list.put(s1[1], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson7/Choose the Correct Picture_1_7_5.png");
                        list.put(s1[2], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson7/Choose the Correct Picture_1_7_2.png");
                        list.put(s1[3], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson7/Choose the Correct Picture_1_7_1.png");
                        list.put(s1[4], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson7/Choose the Correct Picture_1_7_4.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
                    }
                } else if (pref.getLesson().equalsIgnoreCase("lesson8")) {
                    String[] s1 = {"Jesus", "Zacchaeus", "Disciples", "Tree", "People"};
                    for (int i = 0; i < r.getData().getLesson8().size(); i++) {
                        String[] arrayStr = r.getData().getLesson8().get(i).split(",");
                        list.put(s1[0], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson8/Choose the Correct Picture_1_8_5.png");
                        list.put(s1[1], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson8/Choose the Correct Picture_1_8_3.png");
                        list.put(s1[2], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson8/Choose the Correct Picture_1_8_4.png");
                        list.put(s1[3], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson8/Choose the Correct Picture_1_8_2.png");
                        list.put(s1[4], "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/LKG/lesson8/Choose the Correct Picture_1_8_1.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
                    }
                }
                setRecyclerviewData();
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.i(getClass().getName(), "=================error   " + t.getMessage());
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void leesonCall() {

    }

    private class MatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        HashMap<String, String> list;
        ArrayList<String> left, right;
        int current = -1;
        ArrayList<String> matchedstr = new ArrayList<>();
        ArrayList<String> matchedans = new ArrayList<>();
        ArrayList<String> colors = new ArrayList<>();
        Context context;
        ArrayList<Integer> matchBorder = new ArrayList<>();

        public MatchAdapter(Context context, HashMap<String, String> matchData) {
            list = matchData;
            left = new ArrayList<>(matchData.keySet());
            right = new ArrayList<>(matchData.values());
            this.context = context;
            Collections.shuffle(right);
            Collections.shuffle(left);
            colors.add("#F17573");
            colors.add("#D8BB09");
            colors.add("#86D522");
            colors.add("#1A9ECC");
            colors.add("#6927E1");
            matchBorder.add(R.drawable.match_border1);
            matchBorder.add(R.drawable.match_border2);
            matchBorder.add(R.drawable.match_border3);
            matchBorder.add(R.drawable.match_border4);
            matchBorder.add(R.drawable.match_border5);
            int diff = left.size() - colors.size();
            if (diff != 0) {
                Random random = new Random();
                while (diff > 0) {
                    String colorCode = String.format("#%06x", random.nextInt(0xffffff + 1)).toUpperCase();
                    if (!colors.contains(colorCode) && !colorCode.equals("#FFFFFF")) {
                        colors.add(colorCode);
                        diff--;
                    }
                }
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(getLayoutInflater().inflate(R.layout.item_match_lkg, parent, false)) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }

        int getLightColor(int color) {
            return ColorUtils.blendARGB(Color.WHITE, color, 0.5f);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            LinearLayout llMain = (LinearLayout) holder.itemView.findViewById(R.id.llMain);
            TextView button = (TextView) holder.itemView.findViewById(R.id.tv1);
            ImageView ivImg = (ImageView) holder.itemView.findViewById(R.id.ivImg);
            final String leftstr = left.get(position / 2);
            final String rightstr = right.get(position / 2);
            if (position % 2 == 0) {
//                holder.itemView.getLayoutParams().height = 120;
                if (position == current) {
                    llMain.setBackgroundResource(matchBorder.get(position / 2));
                } else {
                    int color = Color.parseColor(colors.get(position / 2));
                    GradientDrawable drawable = new GradientDrawable();
                    drawable.setColor(getLightColor(color));
                    drawable.setCornerRadius(Math.round(TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics())));
                    drawable.setShape(GradientDrawable.RECTANGLE);
                    drawable.setStroke(2, color);
                    llMain.setBackgroundDrawable(drawable);

                }
                button.setVisibility(View.VISIBLE);
                ivImg.setVisibility(View.GONE);
                button.setText(leftstr);
                llMain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(getClass().getName(), "===================" + leftstr);
                        if (matchedstr.contains(leftstr))
                            return;
                        if (current == position)
                            current = -1;
                        else
                            current = position;
                        notifyDataSetChanged();
                    }
                });

            } else {
//                holder.itemView.getLayoutParams().height = 250;
                if (matchedans.contains(rightstr)) {
                    int color = Color.parseColor(colors.get((left.indexOf(getOriginal(rightstr)))));
                    GradientDrawable drawable = new GradientDrawable();
                    drawable.setColor(getLightColor(color));
                    drawable.setCornerRadius(Math.round(TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics())));
                    drawable.setShape(GradientDrawable.RECTANGLE);
                    drawable.setStroke(2, color);
                    llMain.setBackground(drawable);
                    Glide.with(getApplicationContext())
                            .load(rightstr)
                            .fitCenter()
                            .into(ivImg);

//                    notifyDataSetChanged();
                } else
                    llMain.setBackgroundResource(R.drawable.line_connection6);

                String PACKAGE_NAME = getApplicationContext().getPackageName();
                Glide.with(getApplicationContext())
                        .load(rightstr)
                        .fitCenter()
                        .into(ivImg);

                button.setVisibility(View.GONE);
                ivImg.setVisibility(View.VISIBLE);
                llMain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(getClass().getName(), "===================rightstr" + rightstr);
                        if (matchedans.contains(rightstr))
                            return;
                        if (current == -1) {
                            if (language.equals("1"))
                                new AlertClass(LkgMatchActivtiy.this, "Login", LkgMatchActivtiy.this, "Incorrect!", "Choose the correct answer.", 3);
                            else
                                new AlertClass(LkgMatchActivtiy.this, "", LkgMatchActivtiy.this, "தவறு!", "முதலில் கேள்வியைத் தேர்ந்தெடுக்கவும்", 3);
                            return;
                        }
                        if (list.get(left.get(current / 2)).equals(rightstr)) {
                            matchedstr.add(left.get(current / 2));
                            matchedans.add(rightstr);
                            notifyDataSetChanged();
                            current = -1;
                            if (matchedstr.size() == left.size()) {
                                callAlert(pref.getLesson());
//                                new AlertClass(LkgMatchActivtiy.this, "Two Start", LkgMatchActivtiy.this, "மிக அருமை!", "நீங்கள் மூன்றாம்" +
//                                        " நிலையை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                                notifyDataSetChanged();
                            }

                        } else {
                            if (language.equals("1"))
                                new AlertClass(LkgMatchActivtiy.this, "Login", LkgMatchActivtiy.this, "Incorrect!", "Choose the correct answer.", 3);
                            else
                                new AlertClass(LkgMatchActivtiy.this, "", LkgMatchActivtiy.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                        }

                    }
                });
            }
        }

        void callAlert(String position) {
            Log.i(getClass().getName(), "===================" + position);
            switch (position) {
                case "lesson1":
                case "lesson2":
                case "lesson3":
                case "lesson4":
                case "lesson5":
                case "lesson6":
                case "lesson7":
                case "lesson8":
                case "lesson9":
                case "lesson10":
                case "lesson11":
                case "lesson12":
                    if (selectedClass.equalsIgnoreCase("LKG & UKG")) {
                        selectedClass = pref.getPreference("regClass");
                    }
                    if (pref.getPreference("regClass").equalsIgnoreCase(selectedClass)) {
                        saveScore();
                    } else {
                        if (language.equals("1"))
                            new AlertClass(LkgMatchActivtiy.this, "Two", LkgMatchActivtiy.this, "Fantastic!",
                                    "You have successfully completed this activity.", 4);
                        else
                            new AlertClass(LkgMatchActivtiy.this, "Two Start", LkgMatchActivtiy.this, "மிக அருமை!",
                                    "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    }
                    break;

            }
        }

        private void saveScore() {
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String formattedDate = df.format(c);

            JsonObject params = new JsonObject();
            params.addProperty("user_id", pref.getPreference("id"));
            params.addProperty("date", formattedDate);
            params.addProperty("medium", (language.equals("0") ? "Tamil" : "English"));
            params.addProperty("class", selectedClass);
            params.addProperty("session", title);
            params.addProperty("activity_name", actName);
            params.addProperty("score", 2);

            Call<SaveScore> call = RetrofitClient.getInstance().getMyApi().saveScore(params);
            call.enqueue(new Callback<SaveScore>() {
                @Override
                public void onResponse(Call<SaveScore> call, Response<SaveScore> response) {
                    SaveScore r = response.body();
                    if (r.getStatus() == 200) {
                        if (language.equals("1"))
                            new AlertClass(LkgMatchActivtiy.this, "Two", LkgMatchActivtiy.this, "Fantastic!",
                                    "You have successfully completed this activity.", 4);
                        else
                            new AlertClass(LkgMatchActivtiy.this, "Two Start", LkgMatchActivtiy.this, "மிக அருமை!",
                                    "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    } else
                        new AlertClass(LkgMatchActivtiy.this, "Login", LkgMatchActivtiy.this, "Failed", r.getMessage(), 1);
                }

                @Override
                public void onFailure(Call<SaveScore> call, Throwable t) {
                    Log.i(getClass().getName(), "=================error   " + t.getMessage());
                    new AlertClass(LkgMatchActivtiy.this, "Login", LkgMatchActivtiy.this, "Failed", t.getMessage(), 1);
                }
            });
        }

        private String getOriginal(String rightstr) {
            for (String s : list.keySet())
                if (list.get(s).equals(rightstr))
                    return s;
            return "";
        }

        private int getImageName(String str, Context cx) {
            int id = getResources().getIdentifier(str, "drawable", getPackageName());
            return id;
        }

        @Override
        public int getItemCount() {
            return list.size() * 2;
        }
    }
}
