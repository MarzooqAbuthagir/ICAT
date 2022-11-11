package com.maria.pastelhub.activity.match;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonObject;
import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.api.SaveScore;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TamilLkgMatchActivtiy extends AppCompatActivity implements View.OnClickListener {

    NetworkStatusFinder networkStatusFinder;
    Pref pref;

    ArrayList<String> imgList = new ArrayList<String>();
    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.major_title)
    TextView major_title;
    String language = "";
    HashMap<String, String> list = new HashMap<>();
    int selectedPosition = 0;
    String lesson = "";
    String selectedClass ="", title ="", actName ="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_match_lkg);

        networkStatus();
        pref = new Pref(this);
        ButterKnife.bind(this);

        selectedPosition = getIntent().getIntExtra("Position", 0);
        clickEvent();
        getImages();
        Log.i(getClass().getName(), "=============777====" + selectedPosition);
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

        if (!networkStatusFinder.networkStatus(TamilLkgMatchActivtiy.this)) {
            Intent intent = new Intent(TamilLkgMatchActivtiy.this, EmptyStatus.class);
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
        String path = "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/TAMIL/Choose ";
        if (selectedPosition == 1) {
            String[] s1 = {"கபிரியேல் வானதூதர்", "அன்னை மரியா", " யோசேப்பு", "குழந்தை இயேசு", "இடையர்கள்", "மூன்று ஞானிகள்"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_1_2.png");
                list.put(s1[1], path + "the Correct Picture_1_1_6.png");
                list.put(s1[2], path + "the Correct Picture_1_1_5.png");
                list.put(s1[3], path + "the Correct Picture_1_1_1.png");
                list.put(s1[4], path + "the Correct Picture_1_1_3.png");
                list.put(s1[5], path + "the Correct Picture_1_1_4.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 2) {
            String[] s1 = {"இயேசு", "சீடர்கள்", " பெற்றோர்", "குழந்தைகள்", "மக்கள்"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_2_5.png");
                list.put(s1[1], path + "the Correct Picture_1_2_3.png");
                list.put(s1[2], path + "the Correct Picture_1_2_2.png");
                list.put(s1[3], path + "the Correct Picture_1_2_1.png");
                list.put(s1[4], path + "the Correct Picture_1_2_4.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 3) {
            String[] s1 = {"இயேசு", "இலாசர்", " மார்த்தா", "மரியா", "சீடர்கள்","யூதர்கள்"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_3_4.png");
                list.put(s1[1], path + "the Correct Picture_1_3_3.png");
                list.put(s1[2], path + "the Correct Picture_1_3_2.png");
                list.put(s1[3], path + "the Correct Picture_1_3_6.png");
                list.put(s1[4], path + "the Correct Picture_1_3_1.png");
                list.put(s1[5], path + "the Correct Picture_1_3_5.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 4) {
            String[] s1 = {"பன்னிரு சீடர்கள்", "சீமோன்", " யோவான்", "யாக்கோபு", "அந்திரேயா","மத்தேயு"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_4_5.png");
                list.put(s1[1], path + "the Correct Picture_1_4_4.png");
                list.put(s1[2], path + "the Correct Picture_1_4_1.png");
                list.put(s1[3], path + "the Correct Picture_1_4_2.png");
                list.put(s1[4], path + "the Correct Picture_1_4_6.png");
                list.put(s1[5], path + "the Correct Picture_1_4_3.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 5) {
            String[] s1 = {"உயிர்த்த இயேசு", "மகதலா மரியா", " எம்மாவு சீடர்கள்", "சீமோன் பேதுரு", "தோமா","சீடர்கள்"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_5_3.png");
                list.put(s1[1], path + "the Correct Picture_1_5_5.png");
                list.put(s1[2], path + "the Correct Picture_1_5_1.png");
                list.put(s1[3], path + "the Correct Picture_1_5_6.png");
                list.put(s1[4], path + "the Correct Picture_1_5_2.png");
                list.put(s1[5], path + "the Correct Picture_1_5_4.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 6) {
            String[] s1 = {"முடக்குவாதமுற்றவர்", "காது கேளாதவர்", " பார்வையற்ற பர்த்திமேயு", "கை சூம்பியவர்", "உடல் ஊனமுற்ற பெண்","உடல் நலமற்றவர்"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_6_3.png");
                list.put(s1[1], path + "the Correct Picture_1_6_5.png");
                list.put(s1[2], path + "the Correct Picture_1_6_6.png");
                list.put(s1[3], path + "the Correct Picture_1_6_2.png");
                list.put(s1[4], path + "the Correct Picture_1_6_4.png");
                list.put(s1[5], path + "the Correct Picture_1_6_1.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 7) {
            String[] s1 = {"எருசலேம் ஆலயம்", "சிறுவன் இயேசு", " யோசேப்பு", "மரியா", "போதகர்கள்","நாசரேத்து"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_7_3.png");
                list.put(s1[1], path + "the Correct Picture_1_7_5.png");
                list.put(s1[2], path + "the Correct Picture_1_7_6.png");
                list.put(s1[3], path + "the Correct Picture_1_7_1.png");
                list.put(s1[4], path + "the Correct Picture_1_7_2.png");
                list.put(s1[5], path + "the Correct Picture_1_7_4.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 8) {
            String[] s1 = {"செல்வர்", "ஏழை இலாசர்", " ஆபிரகாம்", "வானதூதர்கள்", "விண்ணகம்","பாதாளம்"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_8_3.png");
                list.put(s1[1], path + "the Correct Picture_1_8_4.png");
                list.put(s1[2], path + "the Correct Picture_1_8_6.png");
                list.put(s1[3], path + "the Correct Picture_1_8_1.png");
                list.put(s1[4], path + "the Correct Picture_1_8_2.png");
                list.put(s1[5], path + "the Correct Picture_1_8_5.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 9) {
            String[] s1 = {"இயேசு", "பிலிப்பு", " அந்திரேயா", "சிறுவன்", "ஐந்து அப்பங்களும், இரண்டு மீன்களும்","பன்னிரண்டு கூடைகள்"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_9_5.png");
                list.put(s1[1], path + "the Correct Picture_1_9_4.png");
                list.put(s1[2], path + "the Correct Picture_1_9_6.png");
                list.put(s1[3], path + "the Correct Picture_1_9_2.png");
                list.put(s1[4], path + "the Correct Picture_1_9_1.png");
                list.put(s1[5], path + "the Correct Picture_1_9_3.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 10) {
            String[] s1 = {"செபிக்கும் இயேசு", "தூங்கும் சீடர்கள்", "ஒலிவ மலை", "ஆண்டவரின் தூதர்", "இரத்த வியர்வை"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_10_3.png");
                list.put(s1[1], path + "the Correct Picture_1_10_4.png");
                list.put(s1[2], path + "the Correct Picture_1_10_1.png");
                list.put(s1[3], path + "the Correct Picture_1_10_5.png");
                list.put(s1[4], path + "the Correct Picture_1_10_2.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 11) {
            String[] s1 = {"இயேசு", "சீடர்கள்", "உடல் நலமற்றவர்", "பெத்சதா குளம்", "ஆண்டவரின் தூதர்","உடல்நலமற்றோர்"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_11_4.png");
                list.put(s1[1], path + "the Correct Picture_1_11_5.png");
                list.put(s1[2], path + "the Correct Picture_1_11_6.png");
                list.put(s1[3], path + "the Correct Picture_1_11_1.png");
                list.put(s1[4], path + "the Correct Picture_1_11_2.png");
                list.put(s1[5], path + "the Correct Picture_1_11_3.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 12) {
            String[] s1 = {"இயேசு", "சீடர்கள்", "பார்வையற்றவர்", "இயேசுவே உலகின் ஒளி", "சிலோவாம் குளம்","அக்கம் பக்கத்தார்"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_12_4.png");
                list.put(s1[1], path + "the Correct Picture_1_12_6.png");
                list.put(s1[2], path + "the Correct Picture_1_12_5.png");
                list.put(s1[3], path + "the Correct Picture_1_12_2.png");
                list.put(s1[4], path + "the Correct Picture_1_12_1.png");
                list.put(s1[5], path + "the Correct Picture_1_12_3.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 13) {
            String[] s1 = {"ஒளி", "வானம்", "நிலம்", "சூரியன்", "பறவைகள்", "மனிதர்கள்"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_13_1.png");
                list.put(s1[1], path + "the Correct Picture_1_13_2.png");
                list.put(s1[2], path + "the Correct Picture_1_13_3.png");
                list.put(s1[3], path + "the Correct Picture_1_13_4.png");
                list.put(s1[4], path + "the Correct Picture_1_13_5.png");
                list.put(s1[5], path + "the Correct Picture_1_13_6.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                 `           mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 14) {
            String[] s1 = {"தந்தைக் கடவுள்", "ஆதாம்", "ஏவாள்", "காயின்", "ஆபேல்"};
            for (int i = 0; i < s1.length; i++) {
                list.put(s1[0], path + "the Correct Picture_1_14_5.png");
                list.put(s1[1], path + "the Correct Picture_1_14_3.png");
                list.put(s1[2], path + "the Correct Picture_1_14_1.png");
                list.put(s1[3], path + "the Correct Picture_1_14_2.png");
                list.put(s1[4], path + "the Correct Picture_1_14_4.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 15) {
            String[] s1 = {"யோசேப்பு", "மரியா", "குழந்தை இயேசு", "ஞானிகள்", "விண்மீன்"};
            for (int i = 0; i < s1.length; i++) {

                list.put(s1[0], path + "the Correct Picture_1_15_3.png");
                list.put(s1[1], path + "the Correct Picture_1_15_5.png");
                list.put(s1[2], path + "the Correct Picture_1_15_1.png");
                list.put(s1[3], path + "the Correct Picture_1_15_4.png");
                list.put(s1[4], path + "the Correct Picture_1_15_2.png");
            }
        } else if (selectedPosition == 16) {
            String[] s1 = {"வழியோரம்", "பாறை நிலம்", "முட்செடிகள்", "நல்ல நிலம்"};
            for (int i = 0; i < s1.length; i++) {

                list.put(s1[0], path + "the Correct Picture_1_16_2.png");
                list.put(s1[1], path + "the Correct Picture_1_16_4.png");
                list.put(s1[2], path + "the Correct Picture_1_16_1.png");
                list.put(s1[3], path + "the Correct Picture_1_16_3.png");
                JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 17) {
            String[] s1 = {"தலைவர்", "ஒரு தாலந்து பெற்றவர்", "இரண்டு தாலந்து பெற்றவர்", "ஐந்து தாலந்து பெற்றவர்", "தாலந்து"};
            for (int i = 0; i < s1.length; i++) {

                list.put(s1[0], path + "the Correct Picture_1_17_4.png");
                list.put(s1[1], path + "the Correct Picture_1_17_1.png");
                list.put(s1[2], path + "the Correct Picture_1_17_5.png");
                list.put(s1[3], path + "the Correct Picture_1_17_2.png");
                list.put(s1[4], path + "the Correct Picture_1_17_3.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 18) {
            String[] s1 = {"கடவுள் தாமாக இருக்கிறார்.", "தொடக்கமும் முடிவும் இல்லாமல் இருக்கிறார்.", "உடலும் உருவமும் இல்லாமல் இருக்கிறார்.", "அனைத்து நன்மைகளுக்கும் ஊற்றாக இருக்கிறார்.", "எங்கும் நிறைந்து இருக்கிறார்.", "எல்லாவற்றிற்கும் முழு முதல் காரணமாய் இருக்கிறார்."};
            for (int i = 0; i < s1.length; i++) {

                list.put(s1[0], path + "the Correct Picture_1_18_4.png");
                list.put(s1[1], path + "the Correct Picture_1_18_3.png");
                list.put(s1[2], path + "the Correct Picture_1_18_6.png");
                list.put(s1[3], path + "the Correct Picture_1_18_1.png");
                list.put(s1[4], path + "the Correct Picture_1_18_2.png");
                list.put(s1[5], path + "the Correct Picture_1_18_5.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 19) {
            String[] s1 = {"வாழ்வு தரும் உணவு", "உலகின் ஒளி", "விண்ணக வாசல்", "உயிரும் உயிர்ப்பும்", "நல்ல ஆயன்", "வழியும் உண்மையும்", "திராட்சைச் செடி"};
            for (int i = 0; i < s1.length; i++) {

                list.put(s1[0], path + "the Correct Picture_1_19_7.png");
                list.put(s1[1], path + "the Correct Picture_1_19_3.png");
                list.put(s1[2], path + "the Correct Picture_1_19_2.png");
                list.put(s1[3], path + "the Correct Picture_1_19_6.png");
                list.put(s1[4], path + "the Correct Picture_1_19_1.png");
                list.put(s1[5], path + "the Correct Picture_1_19_4.png");
                list.put(s1[6], path + "the Correct Picture_1_19_5.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 20) {
            String[] s1 = {"ஏழையர் உள்ளத்தோர்", "துயரத்தில் இருப்போர்", "கனிவு கொண்டவர்", "நீதிவழி வாழ்பவர்", "இரக்கம் உடையோர்", "தூய உள்ளத்தோர்", "அமைதி தருபவர்", "நீதிக்காக துன்புறுவோர்"};
            for (int i = 0; i < s1.length; i++) {

                list.put(s1[0], path + "the Correct Picture_1_20_4.png");
                list.put(s1[1], path + "the Correct Picture_1_20_7.png");
                list.put(s1[2], path + "the Correct Picture_1_20_5.png");
                list.put(s1[3], path + "the Correct Picture_1_20_1.png");
                list.put(s1[4], path + "the Correct Picture_1_20_8.png");
                list.put(s1[5], path + "the Correct Picture_1_20_3.png");
                list.put(s1[6], path + "the Correct Picture_1_20_2.png");
                list.put(s1[7], path + "the Correct Picture_1_20_6.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 21) {
            String[] s1 = {"அன்பு", "நன்னயம்", "தன்னடக்கம்", "மகிழ்ச்சி", "பரிவு", "கனிவு", "அமைதி", "பொறுமை", "நம்பிக்கை"};
            for (int i = 0; i < s1.length; i++) {

                list.put(s1[0], path + "the Correct Picture_1_21_3.png");
                list.put(s1[1], path + "the Correct Picture_1_21_6.png");
                list.put(s1[2], path + "the Correct Picture_1_21_9.png");
                list.put(s1[3], path + "the Correct Picture_1_21_1.png");
                list.put(s1[4], path + "the Correct Picture_1_21_2.png");
                list.put(s1[5], path + "the Correct Picture_1_21_4.png");
                list.put(s1[6], path + "the Correct Picture_1_21_8.png");
                list.put(s1[7], path + "the Correct Picture_1_21_5.png");
                list.put(s1[8], path + "the Correct Picture_1_21_7.png");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
            }
        } else if (selectedPosition == 22) {
            String[] s1 = {"முதல் கட்டளை", "இரண்டாம் கட்டளை", "மூன்றாம் கட்டளை", "நான்காம் கட்டளை", "ஐந்தாம் கட்டளை", "ஆறாம் கட்டளை", "ஏழாம் கட்டளை", "எட்டாம் கட்டளை","ஓன்பதாம் கட்டளை","பத்தாம் கட்டளை"};
            for (int i = 0; i < s1.length; i++) {

                list.put(s1[0], "உன் ஆண்டவராகிய கடவுள் நாமே. நம்மை தவிர வேறு கடவுள் இல்லை.");
                list.put(s1[1], "கடவுளின் திருப்பெயரை வீணாகச் சொல்லாதே.");
                list.put(s1[2], "கடவுளின் நாள்களைப் புனிதமாக அனுசரி.");
                list.put(s1[3], "தாய் தந்தையை மதித்துப் பேணு.");
                list.put(s1[4], "கொலை செய்யாதே.");
                list.put(s1[5], "மோக பாவம் செய்யாதே.");
                list.put(s1[6], "களவு செய்யாதே.");
                list.put(s1[7], "பொய்சாட்சி சொல்லாதே.");
                list.put(s1[8], "பிறர் தாரத்தை விரும்பாதே.");
                list.put(s1[9], "பிறர் உடைமையை விரும்பாதே.");
//                        JSONArray mJSONArray = new JSONArray();
//                        for (String s : arrayStr) {
//                            list.put(s1[i], s);
//                            mJSONArray.put(imgList.add(s));
//                        }
            }
        }
        setRecyclerviewData();

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
            colors.add("#00B0FF");
            colors.add("#F17573");
            colors.add("#D8BB09");
            colors.add("#86D522");
            colors.add("#1A9ECC");
            matchBorder.add(R.drawable.match_border1);
            matchBorder.add(R.drawable.match_border2);
            matchBorder.add(R.drawable.match_border3);
            matchBorder.add(R.drawable.match_border4);
            matchBorder.add(R.drawable.match_border5);
            matchBorder.add(R.drawable.match_border6);
            matchBorder.add(R.drawable.match_border1);
            matchBorder.add(R.drawable.match_border2);
            matchBorder.add(R.drawable.match_border3);
            matchBorder.add(R.drawable.match_border4);
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
            if (selectedPosition == 22) {
                return new RecyclerView.ViewHolder(getLayoutInflater().inflate(R.layout.item_match_lkg_22, parent, false)) {
                    @Override
                    public String toString() {
                        return super.toString();
                    }
                };
            } else {
                return new RecyclerView.ViewHolder(getLayoutInflater().inflate(R.layout.item_match_lkg, parent, false)) {
                    @Override
                    public String toString() {
                        return super.toString();
                    }
                };
            }
        }

        int getLightColor(int color) {
            return ColorUtils.blendARGB(Color.WHITE, color, 0.5f);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            LinearLayout llMain = (LinearLayout) holder.itemView.findViewById(R.id.llMain);
            TextView button = (TextView) holder.itemView.findViewById(R.id.tv1);
            TextView buttonRight = (TextView) holder.itemView.findViewById(R.id.tv2);
            ImageView ivImg = (ImageView) holder.itemView.findViewById(R.id.ivImg);
            try {
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
                        if (selectedPosition==22){
                            buttonRight.setText(rightstr);
                            buttonRight.setVisibility(View.VISIBLE);
                            ivImg.setVisibility(View.GONE);

                        }else {
                            buttonRight.setVisibility(View.GONE);
                            ivImg.setVisibility(View.VISIBLE);

                            Glide.with(getApplicationContext())
                                    .load(rightstr)
                                    .apply(new RequestOptions().override(100, 100))
                                    .into(ivImg);

                        }

    //                    notifyDataSetChanged();
                    } else
                        llMain.setBackgroundResource(R.drawable.line_connection6);

                    String PACKAGE_NAME = getApplicationContext().getPackageName();
                    if (selectedPosition==22){
                        buttonRight.setText(rightstr);
                        buttonRight.setVisibility(View.VISIBLE);
                        ivImg.setVisibility(View.GONE);
                    }else {
                        buttonRight.setVisibility(View.GONE);
                        ivImg.setVisibility(View.VISIBLE);

                        Glide.with(getApplicationContext())
                                .load(rightstr)
                                .apply(new RequestOptions().override(100, 100))
                                .into(ivImg);

                    }

                    button.setVisibility(View.GONE);
                    llMain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (matchedans.contains(rightstr))
                                return;
                            if (current == -1) {
                                if (language.equals("1"))
                                    new AlertClass(TamilLkgMatchActivtiy.this, "Login", TamilLkgMatchActivtiy.this, "Incorrect!", "Choose the correct answer.", 3);
                                else
                                    new AlertClass(TamilLkgMatchActivtiy.this, "", TamilLkgMatchActivtiy.this, "தவறு!", "முதலில் கேள்வியைத் தேர்ந்தெடுக்கவும்", 3);
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
                                    new AlertClass(TamilLkgMatchActivtiy.this, "Login", TamilLkgMatchActivtiy.this, "Incorrect!", "Choose the correct answer.", 3);
                                else
                                    new AlertClass(TamilLkgMatchActivtiy.this, "", TamilLkgMatchActivtiy.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                            }

                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
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
                            new AlertClass(TamilLkgMatchActivtiy.this, "Two", TamilLkgMatchActivtiy.this, "Fantastic!",
                                    "You have successfully completed this activity.", 4);
                        else
                            new AlertClass(TamilLkgMatchActivtiy.this, "Two Start", TamilLkgMatchActivtiy.this, "மிக அருமை!",
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
                            new AlertClass(TamilLkgMatchActivtiy.this, "Two", TamilLkgMatchActivtiy.this, "Fantastic!",
                                    "You have successfully completed this activity.", 4);
                        else
                            new AlertClass(TamilLkgMatchActivtiy.this, "Two Start", TamilLkgMatchActivtiy.this, "மிக அருமை!",
                                    "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    } else
                        new AlertClass(TamilLkgMatchActivtiy.this, "Login", TamilLkgMatchActivtiy.this, "Failed", r.getMessage(), 1);
                }

                @Override
                public void onFailure(Call<SaveScore> call, Throwable t) {
                    Log.i(getClass().getName(), "=================error   " + t.getMessage());
                    new AlertClass(TamilLkgMatchActivtiy.this, "Login", TamilLkgMatchActivtiy.this, "Failed", t.getMessage(), 1);
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
