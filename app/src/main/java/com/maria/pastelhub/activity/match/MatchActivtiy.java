package com.maria.pastelhub.activity.match;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.activity.BaseActivityExtends;
import com.maria.pastelhub.activity.choose.ChooseAnswerActivity;
import com.maria.pastelhub.activity.colordrawing.MainActivity;
import com.maria.pastelhub.activity.lineconnector.LineConnector;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.api.SaveScore;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchActivtiy extends AppCompatActivity implements View.OnClickListener {

    NetworkStatusFinder networkStatusFinder;


    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.major_title)
    TextView major_title;
    String language = "";
    Pref pref;
    String selectedClass ="", title ="", actName ="";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_match);

        networkStatus();
        pref = new Pref(getApplicationContext());

        ButterKnife.bind(this);

        clickEvent();

        if (getIntent().getStringExtra("language").equals("1"))
            major_title.setText(R.string.enjoy_matching);
        else
            major_title.setText(R.string.line_connector_tamil);
        language = getIntent().getStringExtra("language");
        title = getIntent().getStringExtra("title");
        selectedClass = getIntent().getStringExtra("selectedClass");
        actName = getIntent().getStringExtra("actName");
        System.out.println("value--------------"+ language);
        System.out.println("value--------------"+ selectedClass);
        System.out.println("value--------------"+ title);
        System.out.println("value--------------"+ actName);
        setRecyclerviewData();

    }

    private void setRecyclerviewData() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        Log.i(getClass().getName(), "=====================  " + getIntent().getIntExtra("class", 0));
        recyclerView.setAdapter(new MatchAdapter(this, SingleTon.getMatchData(this, getIntent().getIntExtra("class", 0), getIntent().getIntExtra("Position", 0), language)));

    }

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(MatchActivtiy.this)) {
            Intent intent = new Intent(MatchActivtiy.this, EmptyStatus.class);
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
            return new RecyclerView.ViewHolder(getLayoutInflater().inflate(R.layout.item_match, parent, false)) {
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
            Button button = (Button) holder.itemView;
            final String leftstr = left.get(position / 2);
            final String rightstr = right.get(position / 2);


            if (position % 2 == 0) {
                if (position == current) {
                    button.setBackgroundResource(matchBorder.get(position / 2));
                } else {
                    int color = Color.parseColor(colors.get(position / 2));
                    GradientDrawable drawable = new GradientDrawable();
                    drawable.setColor(getLightColor(color));
                    drawable.setCornerRadius(Math.round(TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics())));
                    drawable.setShape(GradientDrawable.RECTANGLE);
                    drawable.setStroke(2, color);
                    button.setBackground(drawable);

                }
                button.setText(leftstr);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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

                if (matchedans.contains(rightstr)) {
                    int color = Color.parseColor(colors.get((left.indexOf(getOriginal(rightstr)))));
                    GradientDrawable drawable = new GradientDrawable();
                    drawable.setColor(getLightColor(color));
                    drawable.setCornerRadius(Math.round(TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics())));
                    drawable.setShape(GradientDrawable.RECTANGLE);
                    drawable.setStroke(2, color);
                    button.setBackground(drawable);
//                    notifyDataSetChanged();
                } else
                    button.setBackgroundResource(R.drawable.line_connection6);
                button.setText(rightstr);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (matchedans.contains(rightstr))
                            return;
                        if (current == -1) {
                            if (language.equals("1"))
                                new AlertClass(MatchActivtiy.this, "Login", MatchActivtiy.this, "Incorrect!", "Choose the correct answer.", 3);
                            else
                                new AlertClass(MatchActivtiy.this, "", MatchActivtiy.this, "தவறு!", "முதலில் கேள்வியைத் தேர்ந்தெடுக்கவும்", 3);
                            return;
                        }
                        if (list.get(left.get(current / 2)).equals(rightstr)) {
                            matchedstr.add(left.get(current / 2));
                            matchedans.add(rightstr);
                            notifyDataSetChanged();
                            current = -1;
                            if (matchedstr.size() == left.size()) {
                                callAlert(pref.getLesson());
//                                new AlertClass(MatchActivtiy.this, "Two Start", MatchActivtiy.this, "மிக அருமை!", "நீங்கள் மூன்றாம்" +
//                                        " நிலையை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                                notifyDataSetChanged();
                            }

                        } else {
                            if (language.equals("1"))
                                new AlertClass(MatchActivtiy.this, "Login", MatchActivtiy.this, "Incorrect!", "Choose the correct answer.", 3);
                            else
                                new AlertClass(MatchActivtiy.this, "", MatchActivtiy.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
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
                            new AlertClass(MatchActivtiy.this, "Two", MatchActivtiy.this, "Fantastic!",
                                    "You have successfully completed this activity.", 4);
                        else
                            new AlertClass(MatchActivtiy.this, "Two Start", MatchActivtiy.this, "மிக அருமை!",
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
                            new AlertClass(MatchActivtiy.this, "Two", MatchActivtiy.this, "Fantastic!",
                                    "You have successfully completed this activity.", 4);
                        else
                            new AlertClass(MatchActivtiy.this, "Two Start", MatchActivtiy.this, "மிக அருமை!",
                                    "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    } else
                        new AlertClass(MatchActivtiy.this, "Login", MatchActivtiy.this, "Failed", r.getMessage(), 1);
                }

                @Override
                public void onFailure(Call<SaveScore> call, Throwable t) {
                    Log.i(getClass().getName(), "=================error   " + t.getMessage());
                    new AlertClass(MatchActivtiy.this, "Login", MatchActivtiy.this, "Failed", t.getMessage(), 1);
                }
            });
        }

        private String getOriginal(String rightstr) {
            for (String s : list.keySet())
                if (list.get(s).equals(rightstr))
                    return s;
            return "";
        }

        @Override
        public int getItemCount() {
            return list.size() * 2;
        }
    }
}
