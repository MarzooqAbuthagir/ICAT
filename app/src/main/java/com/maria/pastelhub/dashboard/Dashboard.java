package com.maria.pastelhub.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.dashboard.adapter.MyAdapter;
import com.maria.pastelhub.dashboard.viewmodel.DashboardViewModel;
import com.maria.pastelhub.prefference.AnthemPref;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.profile.Profile;
import com.maria.pastelhub.services.MusicService;
import com.maria.pastelhub.splash_screens.EnglishAnthemActivity;
import com.maria.pastelhub.splash_screens.TamilAnthemActivity;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dashboard extends AppCompatActivity implements View.OnClickListener, PaymentResultListener {

    private DashboardViewModel dashboardViewModel;
    private MyAdapter bookAdapter;

    @BindView(R.id.dashboard_book)
    RecyclerView dashboard_book;


    @BindView(R.id.english_medium_button)
    ImageView english_medium_button;

    @BindView(R.id.tamil_medium_button)
    ImageView tamil_medium_button;

    @BindView(R.id.heading_text)
    TextView heading_text;

    @BindView(R.id.book_select_title)
    TextView book_select_title;

    @BindView(R.id.navigation_icon)
    ImageView navigation_icon;

    int i = 0;


    ImageView sound_icon;

    Pref pref;
    AnthemPref anthemPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(Dashboard.this);

        anthemPref = new AnthemPref(this);
        pref = new Pref(this);

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        dashboard_book.setVisibility(View.INVISIBLE);

        loadTamilBook();

        english_medium_button.setOnClickListener(this);
        tamil_medium_button.setOnClickListener(this);
        navigation_icon.setOnClickListener(this);

        sound_icon = findViewById(R.id.sound_icon_header);

        Pref pref = new Pref(this);
        String musicVal = pref.getMusicval();
        if (pref.getSplashScreen().equals("0")) {
            pref.setSplashScreen("1");
            if (musicVal.equals("1"))
                getApplicationContext().startService(new Intent(this, MusicService.class));
        }

        System.out.println("dash " + anthemPref.getTamilAnthemView() + " ad " + anthemPref.getTamilAnthemCount());
        if (anthemPref.getTamilAnthemView())
            startActivity(new Intent(Dashboard.this, TamilAnthemActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        SingleTon.setListener(this, sound_icon);
    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopService(new Intent(this,MusicService.class));
//        }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.navigation_icon) {
            Intent intent = new Intent(Dashboard.this, Profile.class);
            startActivity(intent);
            finish();
        } else {
            if (i == 0) {
                loadEnglishBook();
                i += 1;
            } else {
                loadTamilBook();
                i = 0;
            }
        }
    }

    private void loadEnglishBook() {

        english_medium_button.setImageResource(R.drawable.english_selected);
        tamil_medium_button.setImageResource(R.drawable.tamil_non_selected);
        book_select_title.setText(R.string.select_book);
        heading_text.setText(R.string.let_the_little_children_come_to_me_mk_10_14);
        dashboard_book.setVisibility(View.VISIBLE);

        dashboardViewModel.getArrayListMutableLiveData().observe(this, dashboardViewModels -> {

            dashboard_book.setAdapter(null);

            bookAdapter = new MyAdapter(dashboardViewModels, Dashboard.this);
            dashboard_book.setAdapter(bookAdapter);


        });

        if (anthemPref.getEnglishAnthemView())
            startActivity(new Intent(Dashboard.this, EnglishAnthemActivity.class));
    }

    private void loadTamilBook() {

        english_medium_button.setImageResource(R.drawable.english_non_selected);
        tamil_medium_button.setImageResource(R.drawable.tamil_selected);
        book_select_title.setText(R.string.select_book_tamil);
        heading_text.setText(R.string.let_the_little_children_come_to_me_mk_10_14_tamil);
        dashboard_book.setVisibility(View.VISIBLE);

        dashboardViewModel.getArrayListMutableLiveData1().observe(this, dashboardViewModels -> {

            dashboard_book.setAdapter(null);
            dashboard_book.setLayoutManager(new GridLayoutManager(this, 3));
            bookAdapter = new MyAdapter(dashboardViewModels, Dashboard.this);
            dashboard_book.setAdapter(bookAdapter);


        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        pref.setPreference("is_subscription", "1");
    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
            JSONObject obj = new JSONObject(response);
            JSONObject json = obj.getJSONObject("error");
            String description = json.getString("description");

            Toast.makeText(Dashboard.this, "Payment failed: " + description, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(getClass().getName(), "Exception in onPaymentError", e);
        }
    }
}