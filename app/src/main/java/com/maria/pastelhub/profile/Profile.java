package com.maria.pastelhub.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.api.GetScore;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.api.SaveScore;
import com.maria.pastelhub.book_landing.adapter.ReviewAdapter;
import com.maria.pastelhub.book_landing.viewmodel.BookLandingViewModel;
import com.maria.pastelhub.dashboard.Dashboard;
import com.maria.pastelhub.login.Login;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.profile.adapter.ScoreAdapter;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    Intent intent;

    @BindView(R.id.cvLogout)
    CardView cvBack;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvContact)
    TextView tvContact;
    @BindView(R.id.llLogout)
    LinearLayout llLogout;
    String netStatus = "";
    Pref pref;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvScore)
    TextView tvScore;

    @BindView(R.id.list)
    RecyclerView list;

    private ScoreAdapter scoreAdapter;
    private List<GetScore.Data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profie);

        ButterKnife.bind(this);
        pref = new Pref(this);
        intent = getIntent();
        netStatus = intent.getStringExtra("status");
        tvName.setText(pref.getPreference("name"));
        tvContact.setText(pref.getPreference("contact"));
        tvEmail.setText(pref.getPreference("email"));

        list.setLayoutManager(new LinearLayoutManager(this));

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        tvDate.setText("Total Score for " + formattedDate);
        getScore();

        llLogout.setOnClickListener(this);
    }

    int score = 0;

    private void getScore() {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        JsonObject params = new JsonObject();
        params.addProperty("user_id", pref.getPreference("id"));
        params.addProperty("date", formattedDate);

        Call<GetScore> call = RetrofitClient.getInstance().getMyApi().getScore(params);
        call.enqueue(new Callback<GetScore>() {
            @Override
            public void onResponse(Call<GetScore> call, Response<GetScore> response) {
                GetScore r = response.body();
                if (r.getStatus() == 200) {

                    dataList = new ArrayList<>();

                    for (GetScore.Data d : r.getData()) {
                        score = score + Integer.parseInt(d.getScore());
                    }

                    dataList.addAll(r.getData());

                    tvScore.setText(String.valueOf(score));

                    setAdapter();

                } else {
                    new AlertClass(Profile.this, "Login", Profile.this, "Failed", r.getMessage(), 1);
                    tvScore.setText(String.valueOf(score));
                }
            }

            @Override
            public void onFailure(Call<GetScore> call, Throwable t) {
                Log.i(getClass().getName(), "=================error   " + t.getMessage());
                new AlertClass((Activity) Profile.this, "Login", Profile.this, "Failed", t.getMessage(), 1);
                tvScore.setText(String.valueOf(score));
            }
        });
    }

    private void setAdapter() {
        scoreAdapter = new ScoreAdapter( dataList, Profile.this);
        list.setAdapter(scoreAdapter);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.llLogout) {
            pref.clearPrefs();
            Intent loginscreen = new Intent(this, Login.class);
            loginscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(loginscreen);
            this.finish();
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
        SingleTon.setListener(this, null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Profile.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}