package com.maria.pastelhub.videos.livevideos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.activity.BaseActivityExtends;
import com.maria.pastelhub.activity.wordsearch.WordSearch;
import com.maria.pastelhub.book_landing.BookLanding;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;
import com.maria.pastelhub.videos.livevideos.adpater.VideoAdapter;
import com.maria.pastelhub.videos.livevideos.viewmodel.VideoViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.maria.pastelhub.application.ICatApplication.isMusicServiceRunning;

public class LiveVideo1 extends AppCompatActivity {

    NetworkStatusFinder networkStatusFinder;

    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.major_title)
    TextView major_title;

    private VideoAdapter videoAdapter;
    private List<VideoViewModel> videoViewModelsList;

    @BindView(R.id.video_list)
    RecyclerView video_list;

    Intent intent;
    Pref pref;
    int classnum;
    int position;
    String language = "";

    //    @Override
//    protected void onResume() {
//        super.onResume();
//        SingleTon.setListener(this,findViewById(R.id.sound_icon));
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopService(new Intent(this,MusicService.class));
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_video);

        networkStatus();

        ButterKnife.bind(this);

        video_list.setLayoutManager(new LinearLayoutManager(this));

        videoViewModelsList = new ArrayList<>();

        intent = getIntent();
        classnum = getIntent().getIntExtra("ID", 0);
        position = getIntent().getIntExtra("pos", 0);
        language = getIntent().getStringExtra("language");
        major_title.setText(getIntent().getStringExtra("Title"));
        setReviewAdapter();
        pref = new Pref(LiveVideo1.this);

//        Toast.makeText(this, " " + pref.getMusicval(), Toast.LENGTH_SHORT).show();

        stopService(new Intent(LiveVideo1.this, MusicService.class));

        back_major_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        SharedPreferences preferences = getSharedPreferences("SharedPrefManager", Context.MODE_PRIVATE);

        ImageView ivSound = findViewById(R.id.sound_icon);
        ivSound.setImageResource(preferences.getBoolean("Sound", true) ? R.drawable.sound_on : R.drawable.sound_mute);

        ivSound.setOnClickListener(view -> {
            Toast.makeText(LiveVideo1.this, "Sound function currently disabled ", Toast.LENGTH_SHORT).show();
        });
    }


    void getVideoList() {

        VideoViewModel bean = new VideoViewModel();
//        major_title.setText("");

        switch (classnum) {
            //                ===================Classs LKG Wb Links====================
            case 0:
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("urpxP7gW-jQ");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("1EzW-tnZ-Lw");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("kbT1Q8tFH6U");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("e-6h4s6XKeA");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("3fjloNSfXtg");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("MdFlme-zSz4");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("-V4SMR4kkqM");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("YuU3koekMak");
                        videoViewModelsList.add(bean);
                        break;

                }
                break;

            //                ===================Classs UKG Wb Links====================
            case 1:
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("Bible Rhyme");
                        bean.setUrl("BNlueG7M7cE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("LZXtTgZ0rpI");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("Bible Rhyme");
                        bean.setUrl("91mYUm_TrV8");
                        videoViewModelsList.add(bean);
                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("bv5ajWNrnt4");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("Bible Rhyme");
                        bean.setUrl("xVGORBCdh5A");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("IWmBQ5ZYub4");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("Bible Rhyme");
                        bean.setUrl("lVfMpjfswQA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("uqW70DsZpVw");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("Bible Rhyme");
                        bean.setUrl("noUUDYZI3ZU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("vW-uWMZB23o");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("Bible Rhyme");
                        bean.setUrl("4iW9MN7vMpQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("KwJJJoSGw84");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("Bible Rhyme");
                        bean.setUrl("uJ6JQEPADsA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("JiYumdP4C6k");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new VideoViewModel();
                        bean.setName("Bible Rhyme");
                        bean.setUrl("dWxMmA1ZSCw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Picture Talk");
                        bean.setUrl("s_1xtrrA2Ks");
                        videoViewModelsList.add(bean);
                        break;
                }
                break;

            //                ===================Classs 1 Wb Links====================
            case 2:
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("The Holy Trinity");
                        bean.setUrl("Cj5fvAm_hZc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Holy Trinity");
                        bean.setUrl("Ge-zRHVdBuA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Elizabeth of the Holy Trinity");
                        bean.setUrl("NT2NXIyQ2Jc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Father I adore you");
                        bean.setUrl("LZXtTgZ0rpI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Ph-ZBiIETB0");
                        bean.setUrl("LZXtTgZ0rpI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Holy Trinity");
                        bean.setUrl("Sd-IjjGy9U0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Elizabeth of the Holy Trinity");
                        bean.setUrl("lDKfoxnYwgk");
                        videoViewModelsList.add(bean);

                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("The Creation");
                        bean.setUrl("teu7BCZTgDs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Calms the Storm");
                        bean.setUrl("seGoILtqokY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("");
                        bean.setUrl("bv5ajWNrnt4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Joseph Vaz");
                        bean.setUrl("j2x1CY_WVYw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Frank Foreman");
                        bean.setUrl("njpWalYduU4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("All things bright and beautiful");
                        bean.setUrl("gnxlHI5RQBQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Creation Story");
                        bean.setUrl("gy1Vvnna8BY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Joseph Vaz");
                        bean.setUrl("dtxMpRi_4K0");
                        videoViewModelsList.add(bean);

                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("Adam and Eve");
                        bean.setUrl("urpxP7gW-jQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Boy Jesus in the Temple");
                        bean.setUrl("BvI4bBAuxl4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Stanislaus Kostka");
                        bean.setUrl("I-b2UomqDPw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Dr. Abdul Kalam");
                        bean.setUrl("zFemNSrhu9o");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Trust and obey");
                        bean.setUrl("XD3JiXt_g_g");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Adam and Eve");
                        bean.setUrl("leBCvqV3f3w");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Stanislaus Kostka");
                        bean.setUrl("I-b2UomqDPw");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("Noah and the Ark");
                        bean.setUrl("_vjjhMWJ2wE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus the Real Vine");
                        bean.setUrl("btojLhvXzUc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Francis of Assisi and the Wolf of Gubbio");
                        bean.setUrl("sUhZI9F1W-8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Mr. Marimuthu Yoganathan");
                        bean.setUrl("76pFmLNAlRQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The heavens are blue proclaiming to you");
                        bean.setUrl("n_XW_gNbq7c");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Noah and the Ark");
                        bean.setUrl("onnEaINBaGg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Francis of Assisi");
                        bean.setUrl("G1zKDs9zO0M");
                        videoViewModelsList.add(bean);

                        break;
                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("Abraham Leaves His Home");
                        bean.setUrl("8JeYA2x1OIk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Heals a Roman Officer’s Servant");
                        bean.setUrl("kyJ5w9cXjBQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Thomas Aquinas");
                        bean.setUrl("N5fFicWP4vI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Dashrath Manji");
                        bean.setUrl("1yFEOQ1yf0w");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Lord’s Prayer");
                        bean.setUrl("UwvBtcwPQS8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("I just keep trusting my Lord");
                        bean.setUrl("a0-d33AQu9Q");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Abraham");
                        bean.setUrl("YJxjAZGhrTE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Thomas Aquinas");
                        bean.setUrl("XSYIGXqdmEk&t=88s");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("Abraham Offers His Son Isaac");
                        bean.setUrl("EaK2itKXeFQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Do Not Worry");
                        bean.setUrl("PG1LjFO99dA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Philip Neri");
                        bean.setUrl("emkztqLJsj8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Dance of Subhreet Kaur");
                        bean.setUrl("O0GTgCIgyDk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("I'm rejoicing night and day");
                        bean.setUrl("-kAZ61pLA7E");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Abraham and Issac");
                        bean.setUrl("fMZZX79UppQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Philip Neri");
                        bean.setUrl("1WdJo_HzsN0");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("Jacob");
                        bean.setUrl("80y-sBnzAGk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Blesses Little Children");
                        bean.setUrl("AbYM-YaeNUM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Don Bosco");
                        bean.setUrl("Sf2qJZqwiBs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Pope St. Pius X");
                        bean.setUrl("NNs1w1ZGaJQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("There shall be showers of blessing");
                        bean.setUrl("yrLS7yk8qUc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jacob and Esau");
                        bean.setUrl("up5uES5suzU");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new VideoViewModel();
                        bean.setName("Joseph and His Brothers");
                        bean.setUrl("rXzDo70R57c");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Lost Son");
                        bean.setUrl("UgHKMbo5gMQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Kuriokose Elias Chavara");
                        bean.setUrl("YSgkUP1rV8A");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Malala Yousafzai");
                        bean.setUrl("SyV6zjTviwI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Sing life, sing love, sing Jesus");
                        bean.setUrl("lq6QCBRproM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Joseph");
                        bean.setUrl("Buss7KIb_Eo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Kuriakose");
                        bean.setUrl("uAmkzIWS7fM");
                        videoViewModelsList.add(bean);
                        break;
                }
                break;
            //                ===================Classs 2 Wb Links====================
            case 3:
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("Moses Parts the Red Sea");
                        bean.setUrl("q88TGKPDTvs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Birth of Jesus is Announced");
                        bean.setUrl("JyVXIvdTF20");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Joseph");
                        bean.setUrl("BWZ48mG95Xc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Footprints in the Sand");
                        bean.setUrl("AyAazf0UdJs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Walk with me, oh my Lord");
                        bean.setUrl("qn199-QOr7M");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Moses");
                        bean.setUrl("65KgsHvFKd4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Joseph");
                        bean.setUrl("K4NBV840xTc");
                        videoViewModelsList.add(bean);

                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("Joshua");
                        bean.setUrl("ZaQXr_VDNXw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Heals the Man with a Paralysed Hand");
                        bean.setUrl("F3B4mqMToko");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Heals a Leper");
                        bean.setUrl("iT7yj0EO3ac");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Saul Becomes Paul");
                        bean.setUrl("9eRXq-cKmr0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Hima Das – India’s Rising Athletics Star");
                        bean.setUrl("9QWZIVn5DT0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Lord, you are my everything");
                        bean.setUrl("M1-xGZPB14w");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Joshua's Battle");
                        bean.setUrl("_6afPQvZPL4");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("Samson, the Strong Man");
                        bean.setUrl("JoOBUfttA1s");
                        videoViewModelsList.add(bean);


                        bean = new VideoViewModel();
                        bean.setName("John the Baptist");
                        bean.setUrl("QFz5OP7388w");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Ignatius of Antioch");
                        bean.setUrl("-AhhGJbZytU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Mr. Thomas Raja");
                        bean.setUrl("z9xnLi3tI0c");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Just like a child ");
                        bean.setUrl("8Kq2QePceuM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Samson");
                        bean.setUrl("pD1Q2yXk3P8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Ignatius of Antioch");
                        bean.setUrl("I2ooLH6ReXo");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("Ruth");
                        bean.setUrl("ke9pdq61D-s");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Heals Simon’s Mother-in-law");
                        bean.setUrl("tOM5Q9XdHzQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Jeanne Jugan");
                        bean.setUrl("IlEJkSgJXkg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Wooden Bowl");
                        bean.setUrl("9_W_mBKXaek");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("To follow the Lord and find freedom");
                        bean.setUrl("bOnZ2reGsrs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Ruth");
                        bean.setUrl("V_zhrItaLok");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Jeanne Jugan");
                        bean.setUrl("IlEJkSgJXkg");
                        videoViewModelsList.add(bean);
                        break;

                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("Nehemiah - God's Builder");
                        bean.setUrl("Q0ot653Zj1k");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Teaches His Disciples to Pray");
                        bean.setUrl("w6zYIYULZWo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Joseph of Cupertino");
                        bean.setUrl("pm3UBypIY98");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("“Servant of God” Fr. John Peter");
                        bean.setUrl("2VUJW9at7_M");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("As the deer pants for the water");
                        bean.setUrl("FBppKZ0eJlQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Nehemiah and the Walls of Jerusalem");
                        bean.setUrl("qKc25cQedd0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Joseph of Cupertino");
                        bean.setUrl("eXxOLNRBdXw");
                        videoViewModelsList.add(bean);

                        break;
                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("Job's Trials");
                        bean.setUrl("RtDJVDM820k");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Heals the Paralytic at the Pool of Bethzatha");
                        bean.setUrl("ZPc_QTlIq2I");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Blessed Devasahayam Pillai");
                        bean.setUrl("D-c3a2NPkUk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Eric Liddell-  The Torch Lighter");
                        bean.setUrl("lCxkY2gGFRg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus, you are my salvation");
                        bean.setUrl("gwq1WTSC03g");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Job");
                        bean.setUrl("595YUE0H3zU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Devasahayam");
                        bean.setUrl("2nOb0WUDIsM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("");
                        bean.setUrl("KwJJJoSGw84");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("Tobit");
                        bean.setUrl("w-aLq_8hB1g");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Feeds a Great Crowd");
                        bean.setUrl("O17CvIAyZJ4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Elizabeth of Hungary");
                        bean.setUrl("AnC6WV8G78E");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Anupriya");
                        bean.setUrl("M0n_DIAtMfA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Lord, make me like you");
                        bean.setUrl("3KeQIr5yjrU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Tobit");
                        bean.setUrl("1ccfXIzWzPs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Elizabeth of Hungary");
                        bean.setUrl("i2fwGRWVyi0");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new VideoViewModel();
                        bean.setName("God Calls Samuel");
                        bean.setUrl("OtZKI8LnKbw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Birth of Jesus is Announced");
                        bean.setUrl("JyVXIvdTF20");
                        videoViewModelsList.add(bean);


                        bean = new VideoViewModel();
                        bean.setName("St. Margaret Mary Alacoque");
                        bean.setUrl("cg7Y9f_gELc");
                        videoViewModelsList.add(bean);


                        bean = new VideoViewModel();
                        bean.setName("Life After Football: Fr. Philip Mulryne");
                        bean.setUrl("gk8zwz8GfUc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Listen, let your heart keep seeking");
                        bean.setUrl("RDCdSxMUnJA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Samuel");
                        bean.setUrl("ra2YS1ph13A");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Margaret Mary Alacoque");
                        bean.setUrl("uRxN7G1Gk1I");
                        videoViewModelsList.add(bean);

                        break;
                }
                break;

//                ===================Classs 3 Wb Links====================
            case 4:
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("Saul the First King of Israel");
                        bean.setUrl("QX9pWgYJpGk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Saul becomes Paul");
                        bean.setUrl("9eRXq-cKmr0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Augustine");
                        bean.setUrl("uRsjaBTphVM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Reny George");
                        bean.setUrl("fNuAcetBbCM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("God will make a way");
                        bean.setUrl("RsMAXhc0QTs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("King Saul, the first King of Israel");
                        bean.setUrl("lGu9CAxL8a0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Augustine");
                        bean.setUrl("A3-SZv3UJTw");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("David and Goliath");
                        bean.setUrl("TSEkqCXNePM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus and Peter");
                        bean.setUrl("lHptbdsZnCc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Peter Damian");
                        bean.setUrl("gOnlIURzhkU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Abraham Lincoln");
                        bean.setUrl("-Y1T9Q-4rJg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Make me a servant, humble and meek");
                        bean.setUrl("VbrqIW8buxk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("King David");
                        bean.setUrl("88TbmM3zxyw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Peter Damian");
                        bean.setUrl("hgZTCgKba84");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("King Solomon");
                        bean.setUrl("TdEc_feBpWI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus talks about Paying Taxes");
                        bean.setUrl("HZrE_Bx2K94");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Louis de Montfort");
                        bean.setUrl("D9zfpDdBxMM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Kamaraj");
                        bean.setUrl("ix67yZyMRiY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The fear of the Lord");
                        bean.setUrl("tMnIVe4-QUY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("King Solomon");
                        bean.setUrl("jI6SDKgEUhk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Louis de Montfort");
                        bean.setUrl("yg6nFPPC25U");
                        videoViewModelsList.add(bean);

                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("Queen Esther");
                        bean.setUrl("Ssj3MR2XaV4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Heals a crippled Woman on the Sabbath");
                        bean.setUrl("QvUpWNfDhwE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Joan of Arc");
                        bean.setUrl("wmPsBqovrVU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Hanifa Zara");
                        bean.setUrl("rnID2mxHWaQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Oh the word of my Lord");
                        bean.setUrl("CLpSbFWB-gM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Queen Esther");
                        bean.setUrl("q94wBYdMwtE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Joan of Arc");
                        bean.setUrl("HtLowX05Hgg");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("King Hezekiah");
                        bean.setUrl("Ay9G7cs6bDw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Heals Blind Bartimaeus");
                        bean.setUrl("hoBKpnNHT0k");

                        videoViewModelsList.add(bean);
                        bean = new VideoViewModel();
                        bean.setName("St. Peregrine Laziosi");
                        bean.setUrl("T1duo2EsDS0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Paraneetha Healing Testimony");
                        bean.setUrl("jKPZ1gQXnTE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Ask and it shall be given to you!");
                        bean.setUrl("JvBfBBS00zE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("King Hezekiah");
                        bean.setUrl("KMkJmPgg4do");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Peregrine Laziosi");
                        bean.setUrl("d6pCiFgxe9c");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        Log.i(getClass().getName(), "=====================" + classnum);
                        bean = new VideoViewModel();
                        bean.setName("King Josiah");
                        bean.setUrl("6C4MEXXbBmM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Greatest Commandment");
                        bean.setUrl("db-lE_f2U5I");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Benedict");
                        bean.setUrl("RLD026SDN8s");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Mr. Traffic Ramasamy");
                        bean.setUrl("8CwqTFoIPIQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("This is my will, my one command");
                        bean.setUrl("0_YIGO6bjG4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("King Josiah");
                        bean.setUrl("maZpQH8ICHs");
                        videoViewModelsList.add(bean);

                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("King Asa");
                        bean.setUrl("bAorQgBfPUg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Do not Worry");
                        bean.setUrl("PG1LjFO99dA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Mary Mazzarello – Life and Mission");
                        bean.setUrl("IIF6lgGtaug");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Brother Bakht Singh");
                        bean.setUrl("Uzqy-P3O2yg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Come, O Lord");
                        bean.setUrl("NfhqhLg8cMw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("King Asa Seeks God");
                        bean.setUrl("YX6GoWFHIeg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Mary Mazzarello");
                        bean.setUrl("tsP0yIHzl_w");
                        videoViewModelsList.add(bean);

                        break;
                    case 8:
                        bean = new VideoViewModel();
                        bean.setName("Jehoshaphat");
                        bean.setUrl("R-mOjdmBR8A");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Parable of the Workers in the Vineyard");
                        bean.setUrl("3QiZicWl7Q4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Martin De Porres");
                        bean.setUrl("aT_2421h2nA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Rajaram Mohan Roy");
                        bean.setUrl("1Et3y4AJIzU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Make me a channel of your peace");
                        bean.setUrl("ihhvm6eLWZI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("King Jehoshaphat");
                        bean.setUrl("4C8DurL_H5A");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Martin De Porres");
                        bean.setUrl("klwYPD99lu8");
                        videoViewModelsList.add(bean);
                }
                break;

            //         =============       Class 4 Web Links ===================
            case 5:
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("Elijah and Fire from Heaven");
                        bean.setUrl("ndwrbldGR4c");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Poor Widow’s Offering");
                        bean.setUrl("GivTS-DBBWI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Lawrence");
                        bean.setUrl("jQ4cHHOQod0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Mullai Periyar Dam and Colonel John Pennycuick");
                        bean.setUrl("EkIWFu7Vo6M");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("I give my hands");
                        bean.setUrl("9BAMR-tlfm0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Prophet Elijah");
                        bean.setUrl("byxlyYn8fOI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Lawrence");
                        bean.setUrl("lIF1KsWSLNg");
                        videoViewModelsList.add(bean);

                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("Prophet Elisha");
                        bean.setUrl("PbF1R7cPCMM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Parable of the Good Samaritan");
                        bean.setUrl("aSfm2xdKau4");
                        videoViewModelsList.add(bean);


                        bean = new VideoViewModel();
                        bean.setName("Mother Teresa");
                        bean.setUrl("aFsEku6zwXM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("An inspiring story of Sneha Mohandas");
                        bean.setUrl("RsOsksdd-Q8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("I will sing of the mercies of the Lord");
                        bean.setUrl("7-q6z5fpgNk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Prophet Elisha");
                        bean.setUrl("gJxp02VzJOk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Teresa of Kolkata");
                        bean.setUrl("teQb8eubFzg");
                        videoViewModelsList.add(bean);

                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("Prophet Isaiah’s Visions");
                        bean.setUrl("Ht-nTXQbvcg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Scripture is fulfilled in Jesus");
                        bean.setUrl("qIB4C6NMpvs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus and Zacchaeus");
                        bean.setUrl("YuU3koekMak");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Oscar Romero");
                        bean.setUrl("aZ2j9W780Mo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Martin Luther King Junior");
                        bean.setUrl("Hmw8QjLjDh0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Spirit of the Lord God");
                        bean.setUrl("AfT4mcE_XJg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Prophet Isaiah");
                        bean.setUrl("O9TvOiqNqr4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName(" St. Oscar Romero");
                        bean.setUrl("n6h1G8-dtnY");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("Prophet Jeremiah");
                        bean.setUrl("IBWCU1otkd4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Calls His Disciples");
                        bean.setUrl("iypUNd5yTn0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Alphonsus Liguori");
                        bean.setUrl("XPorozChjR0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("‘Servant of God’ Thatipathri Gnanamma");
                        bean.setUrl("Tk0FJH_qZGk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Follow me, follow me");
                        bean.setUrl("XoxlvKch9tA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Prophet Jeremiah");
                        bean.setUrl("3-1RyO1YIVM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Alphonsus Maria Ligouri");
                        bean.setUrl("LmVUdYfRbz0");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("Prophet Ezekiel");
                        bean.setUrl("Lp8PqePFBIU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus the Good Shepherd");
                        bean.setUrl("LVTCbp1SY4E");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Mary Euphrasia Pelletier");
                        bean.setUrl("i51c2RAFCYU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Nicholas Winston");
                        bean.setUrl("PKkgO06bAZk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Boy who Helped Save Lives");
                        bean.setUrl("fbvt4RzOru4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Lord is my shepherd");
                        bean.setUrl("hYqt9upqzhg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Prophet Ezekiel");
                        bean.setUrl("qIZ_4pl_F0I");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Peter");
                        bean.setUrl("vh_UY_g9OUE");
                        videoViewModelsList.add(bean);
                        bean = new VideoViewModel();
                        bean.setName("St. Peter");
                        bean.setUrl("MKVibBXXIno");
                        videoViewModelsList.add(bean);
                        bean = new VideoViewModel();
                        bean.setName("St. Peter");
                        bean.setUrl("P65h7U2XTjU");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("Daniel in Lion’s Den");
                        bean.setUrl("jlPk-2N1H9o");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Daniel Song");
                        bean.setUrl("j0R0VaRYOTI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus is the Way, the Truth and the Life");
                        bean.setUrl("xmZ8qqNjfwY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus is brought before Pilate");
                        bean.setUrl("6m6iMul-RcQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Sebastian");
                        bean.setUrl("iJqSBRcB0Uk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Mahatma Gandhi");
                        bean.setUrl("oyiaTWWPhgc");
                        videoViewModelsList.add(bean);
                        bean = new VideoViewModel();
                        bean.setName("Mahatma Gandhi");
                        bean.setUrl("S1XTPVL5Pjo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("King Harishchandra");
                        bean.setUrl("ji5ZV-_qY-w");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus, you are my salvation");
                        bean.setUrl("gwq1WTSC03g");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Prophet Daniel");
                        bean.setUrl("3WmVVLPIAjw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Sebastian");
                        bean.setUrl("7Ugn9dUknxE");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("Prophet Amos");
                        bean.setUrl("Wqa8kAN4rYA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Rich man and Lazarus");
                        bean.setUrl("9Z04CC2djk0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Maria Gemma Umberta Galgani");
                        bean.setUrl("mFxn56kGiTw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Mr. Palam Kalyanasundaram");
                        bean.setUrl("Tx5yJzXsO3k");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Whatsoever you do");
                        bean.setUrl("w0H2bT6eUzo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Prophet Amos");
                        bean.setUrl("lfD9M7TZZL4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Maria Gemma Galgani");
                        bean.setUrl("KhWUrkg-47Y");
                        videoViewModelsList.add(bean);

                        break;
                    case 8:
                        bean = new VideoViewModel();
                        bean.setName("Prophet Jonah and the People of Nineveh");
                        bean.setUrl("sZNHknCuq88");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Parable of the Unforgiving Servant");
                        bean.setUrl("IbLByuz6eyU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Damien of Moloki");
                        bean.setUrl("CR0sjCJQqPw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Mr. Panjuraja");
                        bean.setUrl("gi7yz209QQ8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Compassion is Greater than Victory – Nine Gold Medals");
                        bean.setUrl("FufRO2n_fLE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("To be the body of the Lord");
                        bean.setUrl("owiJA7z1Rv0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Prophet Jonah");
                        bean.setUrl("wxdgzCT4ttU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Damien");
                        bean.setUrl("AweoZYsiCu4");
                        videoViewModelsList.add(bean);
                        break;
                }
                break;

//         =============       Class 5 Web Links ===================
            case 6:
                Log.i(getClass().getName(), "=====================" + classnum);
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("The Sacrament of Baptism");
                        bean.setUrl("PmmU3jP7h1w");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Moses Parts the Red Sea");
                        bean.setUrl("q88TGKPDTvs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Baptism of Jesus");
                        bean.setUrl("t-h-FBXV0IY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Francis Xavier");
                        bean.setUrl("2VhBIxCP0DM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Called to be servants");
                        bean.setUrl("YyRCQE3H10M");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Noah and the Ark");
                        bean.setUrl("t1p5ocaJzTM&t=1326s");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Francis Xavier");
                        bean.setUrl("koSWgfvL30c&t=238s");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("The Sacrament of Confirmation");
                        bean.setUrl("Lu3MoT_egFI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Ezekiel’s Vision of Dry Bones");
                        bean.setUrl("bQboPOM6wYA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Coming of the Holy Spirit on the Day of Pentecost ");
                        bean.setUrl("2F4210jk2Xk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Stephen");
                        bean.setUrl("vCWDzvGEwhc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Blessed Sister Rani Maria");
                        bean.setUrl("AmXcwWqfyzA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Holy Spirit gift of God");
                        bean.setUrl("1beNLugzzYk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Holy Spirit: POWER of the First Christians!");
                        bean.setUrl("TCmy6ySzUcI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Stephen");
                        bean.setUrl("aW1Pkzbqr50");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("The Sacrament of Eucharist");
                        bean.setUrl("iRtDGELg27w");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("God Gives ‘Manna’");
                        bean.setUrl("21K55IsS2jE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Last Supper");
                        bean.setUrl("KywnAfLnOxw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Padre Pio");
                        bean.setUrl("RAU9wnlhQwU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("In loving memory of Graham Staines, Philip and Timothy");
                        bean.setUrl("s52mBXKQWfU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("A Stunning Sacrifice");
                        bean.setUrl("b1gXC1HHQk4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Christians let us love one another");
                        bean.setUrl("b52P4la0kiE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Last Supper");
                        bean.setUrl("9Edfb_faOb0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Padre Pio");
                        bean.setUrl("PMgfj0S7mMs");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("The Sacrament of Reconciliation");
                        bean.setUrl("dgEhqvydsK0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The prophecy of Jonah and the Repentance of Nineveh");
                        bean.setUrl("sZNHknCuq88");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus Forgives Peter");
                        bean.setUrl("Ppb9bKX1r7k");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. John Nepomucene: Martyr and Defender of the Seal of Confession ");
                        bean.setUrl("gquzQNJ0-2M");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("We forgive you: How Sr. Rani Maria’s family pardoned her killer ");
                        bean.setUrl("OOXxyuZI-sI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Yes, I shall arise");
                        bean.setUrl("6o717ZWbmAE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("King David’s Repentance");
                        bean.setUrl("m1hmronKtR0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. John of Nepomucene");
                        bean.setUrl("gquzQNJ0-2M");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("The Sacrament of the Anointing of the Sick");
                        bean.setUrl("aGVym96GWW0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("King Hezekiah is Healed");
                        bean.setUrl("Ay9G7cs6bDw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Parable of the Good Samaritan");
                        bean.setUrl("IWmBQ5ZYub4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Camillus of Lellis");
                        bean.setUrl("R3xmaLCxpuY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName(" Dr. Jayachandran");
                        bean.setUrl("91BoMI4n3bQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Jesus the healer is here");
                        bean.setUrl("uqnHHq3Y-4M");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("King Hezekiah");
                        bean.setUrl("2pB34LFgajA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. Camillus De Lellis");
                        bean.setUrl("9LCd73ULmF0");
                        videoViewModelsList.add(bean);

                        break;
                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("The Sacrament of Priesthood");
                        bean.setUrl("mHr02TqoD-4");
                        videoViewModelsList.add(bean);
                        bean = new VideoViewModel();
                        bean.setName("The Sacrament of Priesthood");
                        bean.setUrl("u87iWVtaSWc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Consecration of the Priests");
                        bean.setUrl("KKngLz5eqQs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Last Supper");
                        bean.setUrl("997ni1xcmKw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. John Maria Vianney");
                        bean.setUrl("Bu_FfYL_XJ0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Life and Mission of Fr. Louis Leveil");
                        bean.setUrl("bjjAfRMYQ44");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Lord, make me like you");
                        bean.setUrl("3KeQIr5yjrU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Last Supper");
                        bean.setUrl("9Edfb_faOb0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("St. John Maria Vianney");
                        bean.setUrl("Qt6z8K1iAe8");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("The Sacrament of Marriage");
                        bean.setUrl("eNKVNvAK5_s");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("The Wedding of Issac and Rebecca");
                        bean.setUrl("xrwZrMzX09o");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Wedding at Cana");
                        bean.setUrl("PApIBQVovd0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Louis and Zelie Martin – Parents of St. Therese of Lisieux");
                        bean.setUrl("4SZdX-d97aw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Life long Love of Husband and Wife");
                        bean.setUrl("1Udw7otD2z4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("Come fill our homes with Your presence");
                        bean.setUrl("4-vS0Q34oH4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("A Wife for Isaac");
                        bean.setUrl("7AvQ0d6HB1I");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName(" St. Louis and St. Zelie");
                        bean.setUrl("wsxgujZhjYI");
                        videoViewModelsList.add(bean);
                        break;
                }
                break;
        }
    }


    private void setReviewAdapter() {
        if (language.equals("1")) {
            getVideoList();
        } else
            setData();
        videoAdapter = new VideoAdapter(videoViewModelsList);
        video_list.setAdapter(videoAdapter);
    }

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(LiveVideo1.this)) {
            Intent intent = new Intent(LiveVideo1.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
            intent.putExtra("from_page", "book_landing");
            startActivity(intent);
            finish();
        }

    }

    private void setData() {

        VideoViewModel bean = new VideoViewModel();
//        major_title.setText("");
        Log.i(getClass().getName(), "=================0000=======   " + position);
        switch (classnum) {
            //                ===================Classs LKG Wb Links====================
            case 0:
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("K5ha1PEnhZg");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("U1AEF86T7NY");
                        videoViewModelsList.add(bean);
                        break;

                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("44Yv9Sk1mB8");
                        videoViewModelsList.add(bean);
                        break;

                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("26139ZCkXtY");
                        videoViewModelsList.add(bean);
                        break;

                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("blyCQoy0Pow");
                        videoViewModelsList.add(bean);
                        break;

                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("j2vH6h8JR4k");
                        videoViewModelsList.add(bean);
                        break;

                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("-kilAu_EOkM");
                        videoViewModelsList.add(bean);
                        break;

                    case 8:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("Ez3e59ukiIM");
                        videoViewModelsList.add(bean);
                        break;

                    case 9:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("Ez3e59ukiIM");
                        videoViewModelsList.add(bean);
                        break;

                    case 10:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("uIqb_A_PuKg");
                        videoViewModelsList.add(bean);
                        break;

                    case 11:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("2EvJftfujuY");
                        videoViewModelsList.add(bean);
                        break;

                    case 12:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("WXD-51tiz0c");
                        videoViewModelsList.add(bean);
                        break;

                    case 13:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("y9ZVen9Q7ws");
                        videoViewModelsList.add(bean);
                        break;

                    case 14:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("s0dLmE20mK8");
                        videoViewModelsList.add(bean);
                        break;

                    case 15:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("xhD8lG9VDVc");
                        videoViewModelsList.add(bean);
                        break;

                    case 16:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("VmQU_c904Lo");
                        videoViewModelsList.add(bean);
                        break;

                    case 17:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("bbPKhYBaWRg");
                        videoViewModelsList.add(bean);
                        break;

                    case 18:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("9UFaihwP27Q");
                        videoViewModelsList.add(bean);
                        break;

                    case 19:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("8K3a9B3mu2A");
                        videoViewModelsList.add(bean);
                        break;

                    case 20:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("aDZjQVFxImI");
                        videoViewModelsList.add(bean);
                        break;

                    case 21:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("a4Hf8kCYfiI");
                        videoViewModelsList.add(bean);
                        break;

                    case 22:
                        bean = new VideoViewModel();
                        bean.setName("படம் பார்த்துக் கதைக் கூறி மகிழ்வோம்");
                        bean.setUrl("p58YXiAw2JE");
                        videoViewModelsList.add(bean);

                        break;
                }
                break;
            case 1:
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("மூவொரு இறைவனின் பெருவிழா");
                        bean.setUrl("CgFx--8daM0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தூய்மைமிகு மூவொரு கடவுள்");
                        bean.setUrl("oGoLMA0jEpI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித மூவொரு கடவுளின் எலிசபெத்");
                        bean.setUrl("NT2NXIyQ2Jc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மூவொரு இறைவா சரணம்");
                        bean.setUrl("Np6imu3y0qM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மூவொரு இறைவன்");
                        bean.setUrl("CgFx--8daM0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித மூவொரு இறைவனின் எலிசபெத்");
                        bean.setUrl("lDKfoxnYwgk");
                        videoViewModelsList.add(bean);

                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("உலகத்தின் உருவாக்கம்");
                        bean.setUrl("NCtwiO-e7IE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசு காற்றையும் கடலையும் அடக்குதல்");
                        bean.setUrl("CVer4Pyz2z8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசு கடல்மீது நடத்தல்");
                        bean.setUrl("xCEOlA1hpZ8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஜோசப் வாஸ்");
                        bean.setUrl("L4Mg6Jc4HoE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தளபதி பிராங்க் போர்மன்");
                        bean.setUrl("njpWalYduU4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயற்கையில் உறைந்திடும்");
                        bean.setUrl("OYeUMp4h9pA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("சின்ன மனுஷனுக்குள்ள");
                        bean.setUrl("9-V9FhIfT-g");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("படைப்பின் கதை");
                        bean.setUrl("czLNMD_TRKA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஜோசப் வாஸ்");
                        bean.setUrl("WPiioa1bbsg");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("ஆதாம் ஏவாள்");
                        bean.setUrl("s0dLmE20mK8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("கோவிலில் சிறுவன் இயேசு");
                        bean.setUrl("yKGvQWlQ0wU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித தனிஸ்லாஸ் கோஸ்கோ");
                        bean.setUrl("yTk7sGIWs_o");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திரு. அப்துல் கலாம்");
                        bean.setUrl("YAeoKHRnwKI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசப்பா");
                        bean.setUrl("fN8lyC9JTMU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆதாம் ஏவாளின் கதை");
                        bean.setUrl("uUYLV3LzHzA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஸ்தனிஸ்லாஸ் கோஸ்கோ");
                        bean.setUrl("I-b2UomqDPw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஸ்தனிஸ்லாஸ் கோஸ்கோ");
                        bean.setUrl("Ali2NH2xOH0");
                        videoViewModelsList.add(bean);

                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("நோவாவின் கதை");
                        bean.setUrl("Gd9n1yPK1kk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நோவா தாத்தா பாடல்");
                        bean.setUrl("tizRCX36mOI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசுவே உண்மையான திராட்சைச் செடி");
                        bean.setUrl("u1pyAhW8278");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அசிசி நகர் புனித பிரான்சிஸ்");
                        bean.setUrl("WQ52ey44J5o");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திரு. மாரிமுத்து யோகநாதன்");
                        bean.setUrl("Er0xRJ-GL4o");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இறைவன் நமது வானகத் தந்தை");
                        bean.setUrl("LhIaUDJp-PY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நோவா");
                        bean.setUrl("RB2IYEyEEE0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அசிசி நகர் புனித பிரான்சிஸ்");
                        bean.setUrl("ajHYjXcZrhU");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("ஆபிரகாமின் நம்பிக்கை");
                        bean.setUrl("VOTRKa-LRuM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நூற்றுவர் தலைவனின் ஆழமான நம்பிக்கை");
                        bean.setUrl("kyJ5w9cXjBQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அக்குயினோ நகர் புனித தாமஸ்");
                        bean.setUrl("b0yMAm619GA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மலை மனிதன் தஷ்ரத் மஞ்சி");
                        bean.setUrl("aacen31YiGw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசு கற்றுத் தந்த இறைவேண்டல்");
                        bean.setUrl("JJirgrUn1xI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("உன்னை நம்பி வாழும்போது");
                        bean.setUrl("o8lXwAGfBpY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆபிரகாம்");
                        bean.setUrl("XSn1IuoT7lM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அக்குயினோ நகர் புனித தாமஸ்");
                        bean.setUrl("XSYIGXqdmEk");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("ஆபிரகாம் ஈசாக்கைப்  பலியிடுதல்");
                        bean.setUrl("kts5uHXLM4Q");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("கவலை வேண்டாம்");
                        bean.setUrl("d5LTmB1tMHw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பிலிப்பு நேரி");
                        bean.setUrl("fJIHgqJgXO0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நடன மங்கை சுப்ரீத் கவுர் நடனம்");
                        bean.setUrl("O0GTgCIgyDk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தூதுரை மன்றாட்டு");
                        bean.setUrl("bQ0XKC7mTgc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மகிழ்வோம் மகிழ்வோம்");
                        bean.setUrl("0FWEGFygR2o");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஈசாக்கு");
                        bean.setUrl("jjdRg8e7rxo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பிலிப்பு நேரி");
                        bean.setUrl("1WdJo_HzsN0");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("யாக்கோபும், ஏசாவும்");
                        bean.setUrl("UlQAEqjcNjc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசு சிறுபிள்ளைகளுக்கு ஆசி வழங்குதல்");
                        bean.setUrl("FvgJ2Xjy0Y0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அறிவோம் தொன்போஸ்கோவை!");
                        bean.setUrl("j1pUrJgv3BE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திருத்தந்தை புனித பத்தாம் பத்திநாதர்");
                        bean.setUrl("u7F_dZYfu3o");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தாய் போல தேற்றி");
                        bean.setUrl("8jORhzktCmY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("யாக்கோபு மற்றும் ஏசா");
                        bean.setUrl("nFophsu_3B0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித தொன்போஸ்கோ");
                        bean.setUrl("FGJYtYhZawI");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new VideoViewModel();
                        bean.setName("யோசேப்பும், அவரது சகோதரர்களும்");
                        bean.setUrl("fwGIAgGZQTM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("காணாமற்போன மகன் உவமை");
                        bean.setUrl("7JaKYFDYupg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித குரியாக்கோஸ் எலியாஸ் சாவரா");
                        bean.setUrl("zC7srORtjXo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மலாலா யூசாப்சி");
                        bean.setUrl("n4s4xKAFrSo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("வரம் கேட்டு");
                        bean.setUrl("pFrk39Zn-wk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("யோசேப்பு");
                        bean.setUrl("LlQXAGdv_Uc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித குரியாக்கோஸ் எலியாஸ் சாவரா");
                        bean.setUrl("PnC56ASAXmo");
                        videoViewModelsList.add(bean);
                        break;

                }
                break;
            case 2:
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("மோசே வாழ்க்கை வரலாறு");
                        bean.setUrl("POi6QZl5AyM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மோசே வாழ்க்கை வரலாறு");
                        bean.setUrl("DcucNkSdTXQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மோசே வாழ்க்கை வரலாறு");
                        bean.setUrl("cCCwpILmsjg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மோசே வாழ்க்கை வரலாறு");
                        bean.setUrl("ui1TfG9RVOI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மோசே வாழ்க்கை வரலாறு");
                        bean.setUrl("foNDEXUqMlg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசு பிறப்பின் முன்னறிவிப்பு");
                        bean.setUrl("DBRXXHgrr6g");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித யோசேப்பு");
                        bean.setUrl("WUkeQRrccfw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("பகிச்சா சிங்");
                        bean.setUrl("D5FCISHFFP0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("விண்ணையும் மண்ணையும் படைத்தவராம்");
                        bean.setUrl("VmGAyXgXaGM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அதிசயங்கள் செய்கிறவர்");
                        bean.setUrl("GxLrIVwaZyk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசுவே எனக்கொரு");
                        bean.setUrl("nHig1v0oRHI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மோசே");
                        bean.setUrl("spZwtk1rtA0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித யோசேப்பு");
                        bean.setUrl("5pTCffTOe6s");
                        videoViewModelsList.add(bean);

                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("யோசுவா எனும் வெற்றித் தலைவனின் கதை");
                        bean.setUrl("YZOUPpM9GBc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசு சூம்பிய கை உடையவரைக் குணப்படுத்துதல்");
                        bean.setUrl("PsNH5oa4gLo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசு தொழுநோயாளரைக் குணப்படுத்துதல்");
                        bean.setUrl("fmkxAp6Ys3s");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பவுல்");
                        bean.setUrl("GKR8e_vQbSY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("யார் இந்த ஹீமா தாஸ்");
                        bean.setUrl("m0bgYJeshn0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தூய ஆவியின் வல்லமையால்");
                        bean.setUrl("VmGAyXgXaGM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நன்மைகள் செய்த இறைவனுக்கு");
                        bean.setUrl("gO1dTDVb50c");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("யோசுவா");
                        bean.setUrl("95WuobStFXI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பவுல்");
                        bean.setUrl("lY6orKnIM2g");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("சிம்சோன்");
                        bean.setUrl("tsudPwJZMCo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திருமுழுக்கு யோவானின் பிறப்பு");
                        bean.setUrl("FmQmqOLjbdM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அந்தியோக்கு நகர் புனித இஞ்ஞாசியார்");
                        bean.setUrl("gKWRV-GLGDY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திரு. T. ராஜா");
                        bean.setUrl("dZkuaWwdOG8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("பிலாத்துவின் ஆட்சியில் பாடுபட்டார்");
                        bean.setUrl("VmGAyXgXaGM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆண்டவர் தம் திருத்தலத்தில்");
                        bean.setUrl("rkr3Ahr7iws");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("சிம்சோன்");
                        bean.setUrl("XdJWdx8qyfA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அந்தியோக்கு நகர் புனித இஞ்ஞாசியார்");
                        bean.setUrl("XtBhAOfNAU0");
                        videoViewModelsList.add(bean);

                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("ரூத்து");
                        bean.setUrl("nzSQG1hck4Q");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசு பேதுருவின் மாமியாரைக் குணமாக்குதல்");
                        bean.setUrl("NKv3wDXwzW4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஜான் ஜுகான்");
                        bean.setUrl("SwadNVE5KRk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("விண்ணகம் வாழும் தந்தையிடம்");
                        bean.setUrl("VmGAyXgXaGM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தந்தையும் மகனும்");
                        bean.setUrl("pmzqyj4q8uI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("என்னை உமது கருவியாய்");
                        bean.setUrl("AZLypZjo2NY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ரூத்து");
                        bean.setUrl("Zt6Wa6yKzHg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஜான் ஜுகான்");
                        bean.setUrl("IlEJkSgJXkg");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("நெகேமியா");
                        bean.setUrl("OQPH9u4VH-w");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசு இறைவனிடம் வேண்டக் கற்றுக்கொடுத்தல்");
                        bean.setUrl("wS_o9LOd_5s");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இறைஊழியர் அருள்தந்தை ஜான் பீட்டர்");
                        bean.setUrl("2VUJW9at7_M");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தூய ஆவியாரை நம்புகிறேன்");
                        bean.setUrl("VmGAyXgXaGM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தந்தையும் தாயுமான நல்லவரே இறைவா");
                        bean.setUrl("Hx6goZvXgd4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நெகேமியா");
                        bean.setUrl("4TWJUKU_WT0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஜோசப் குப்பெர்டினோ");
                        bean.setUrl("eXxOLNRBdXw");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("யோபு");
                        bean.setUrl("zRcGuKXLIcM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசு உடல் நலமற்றவரைக் குணப்படுத்துதல்");
                        bean.setUrl("VKSQlQ7Mhgo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனிதர் தேவசகாயம்");
                        bean.setUrl("9H_Cczt4QGQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மாற்றுத்திறனாளி அனிதா");
                        bean.setUrl("6Yc5wf6otCA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திரு அவை உரைப்பதை நம்புகிறேன்");
                        bean.setUrl("VmGAyXgXaGM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("உன் புகழைப் பாடுவது");
                        bean.setUrl("DAJGs1gAGHU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("யோபு");
                        bean.setUrl("Iqe8kJMRa6E");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனிதர் தேவசகாயம்");
                        bean.setUrl("2nOb0WUDIsM");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("தோபித்து");
                        bean.setUrl("uaRQgDuscgY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அங்கேரி நாட்டுப் புனித எலிசபெத்");
                        bean.setUrl("JS9o8QNz1dY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அனுப்பிரியா");
                        bean.setUrl("WpsEdI5SZBc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திருத்தந்தை புனித பத்தாம் பத்திநாதர்");
                        bean.setUrl("u7F_dZYfu3o");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("விண்ணையும் மண்ணையும்");
                        bean.setUrl("VmGAyXgXaGM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நல்ல இதயம் ஒன்று தா");
                        bean.setUrl("-SmjtFln7K8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தோபித்து");
                        bean.setUrl("sNXNHdyI5Lk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அங்கேரி நாட்டுப் புனித எலிசபெத்");
                        bean.setUrl("x9OUsPWjaBk");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new VideoViewModel();
                        bean.setName("ஆண்டவர் சாமுவேலை அழைத்தல்");
                        bean.setUrl("ep-cVWU3iqo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசுவின் பிறப்பு");
                        bean.setUrl("K5ha1PEnhZg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித மார்கரேட் மேரி அலகாக்");
                        bean.setUrl("grQo1go7Fnc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("பிலிப் மல்ரின்");
                        bean.setUrl("sJ4NnPz-sZM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆண்டவரே பேசும்");
                        bean.setUrl("7dFwBcmXD6s");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("கடவுள் சாமுவேலை அழைத்தல்");
                        bean.setUrl("ra2YS1ph13A");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித மார்கரேட் மேரி அலகாக்");
                        bean.setUrl("grQo1go7Fnc");
                        videoViewModelsList.add(bean);
                        break;

                }
                break;

            case 3:
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("சவுலும், தாவீதும்");
                        bean.setUrl("VsMJHKC0STg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("சவுலின் மனமாற்றம்");
                        bean.setUrl("lY6orKnIM2g");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித அகுஸ்தின்");
                        bean.setUrl("REB5NsCmrbA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ரெனி ஜார்ஜ்");
                        bean.setUrl("fNuAcetBbCM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மனம் வருந்தி");
                        bean.setUrl("ruO-cuQo-4M");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இஸ்ரயேலின் முதல் அரசர் சவுல்");
                        bean.setUrl("lGu9CAxL8a0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித அகுஸ்தின்");
                        bean.setUrl("K_jwEzIlk6g");
                        videoViewModelsList.add(bean);

                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("தாவீதும் கோலியாத்தும்");
                        bean.setUrl("XnypZ290A_A");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசுவும் பேதுருவும்");
                        bean.setUrl("u035QvpUaho");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பீட்டர் தமியான்");
                        bean.setUrl("3Zw9Hebr_tY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆபிரகாம் லிங்கன்");
                        bean.setUrl("59TB9v8WeDs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஏழை மனம் அழைக்கின்றது");
                        bean.setUrl("ZEcqxwZ_lB0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தாவீது");
                        bean.setUrl("-eA7uXCO3gE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பீட்டர் தமியான்");
                        bean.setUrl("Q--V2jUeJ-0");
                        videoViewModelsList.add(bean);

                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("சாலமோனின் அரசாட்சி");
                        bean.setUrl("Py-vmE9uBtU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("சீசருக்கு வரி செலுத்துதல்");
                        bean.setUrl("0Vy4EEAhqH0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மான்போர்ட் நகர் புனித லூயிஸ்");
                        bean.setUrl("D9zfpDdBxMM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("காமராஜர்");
                        bean.setUrl("6NajBICytDI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆவியைத் தர வேண்டும் இறைவா");
                        bean.setUrl("Gb5LMsWcFlY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("சாலமோன்");
                        bean.setUrl("z-C_WjUq0kA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மான்போர்ட் நகர் புனித லூயிஸ்");
                        bean.setUrl("XdJWdx8qyfA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அந்தியோக்கு நகர் புனித இஞ்ஞாசியார்");
                        bean.setUrl("yg6nFPPC25U");
                        videoViewModelsList.add(bean);

                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("எஸ்தர்");
                        bean.setUrl("j4-rouJi588");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("உடல் ஊனமுற்ற பெண் ஓய்வுநாளில் குணமடைதல்");
                        bean.setUrl("AVq256-yB0U");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஜோன் ஆஃப் ஆர்க்");
                        bean.setUrl("m9oHpvru4So");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஹனிஃபா ஜாரா");
                        bean.setUrl("TSRNpptOcQ4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("கலங்காதே என் நெஞ்சமே");
                        bean.setUrl("rVtCJahUW-8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("எஸ்தர்");
                        bean.setUrl("pZzW-1GGy_c");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஜோன் ஆஃப் ஆர்க்");
                        bean.setUrl("HtLowX05Hgg");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("அரசன் எசேக்கியாவின் நோய் குணமாதல்");
                        bean.setUrl("zSnyhc-Xu9Y");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("பார்வையற்ற பர்த்திமேயு பார்வை பெறுதல்");
                        bean.setUrl("blyCQoy0Pow");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பிரகரின் லசியோசி");
                        bean.setUrl("T1duo2EsDS0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("பிரான்னித்தா");
                        bean.setUrl("jKPZ1gQXnTE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நம்பி வந்தேன் இயேசுவே");
                        bean.setUrl("C_892qNnr2U");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அரசன் எசேக்கியா");
                        bean.setUrl("INUUeeSHEFk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பிரகரின் லசியோசி");
                        bean.setUrl("d6pCiFgxe9c");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("அரசர் யோசியா");
                        bean.setUrl("6C4MEXXbBmM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("முதன்மையானக் கட்டளை");
                        bean.setUrl("db-lE_f2U5I");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பெனடிக்ட்");
                        bean.setUrl("L2S8Y-IpMDc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திரு. டிராபிக் ராமசாமி");
                        bean.setUrl("sw7C3LtcG9k");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அன்பு செய்யுங்கள்");
                        bean.setUrl("G5O01Muqcwk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அரசர் யோசியா");
                        bean.setUrl("rbH7By6TqFs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பெனடிக்ட்");
                        bean.setUrl("maZpQH8ICHs");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("அரசர் ஆசா");
                        bean.setUrl("bAorQgBfPUg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("கவலை வேண்டாம்");
                        bean.setUrl("PG1LjFO99dA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித மரிய மசரல்லோ");
                        bean.setUrl("uIv--8dlBrY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("சகோதரர் பகத்சிங்");
                        bean.setUrl("TtzZ7RojEtc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நற்கருணை நாதரே என்னில் துணையாக வாருமே");
                        bean.setUrl("XxDbsEpTZVk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அரசர் ஆசா");
                        bean.setUrl("YX6GoWFHIeg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித மரிய மசரல்லோ");
                        bean.setUrl("tsP0yIHzl_w");
                        videoViewModelsList.add(bean);

                        break;
                    case 8:
                        bean = new VideoViewModel();
                        bean.setName("அரசர் யோசபாத்தின் செபம்");
                        bean.setUrl("EHm1lYbNsio");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திராட்சைத் தோட்ட வேலையாள்கள் உவமை");
                        bean.setUrl("_x5sPmVTWcA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித மார்ட்டின் தெ போரஸ்");
                        bean.setUrl("-aD7gnBp-JI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ராஜாராம் மோகன்ராய்");
                        bean.setUrl("TdlOt6z6CQU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("பசுவிற்கு நீதி வழங்கிய மனுநீதி சோழன்!");
                        bean.setUrl("Hqkoxmdidr4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அமைதியின் தெய்வமே இறைவா");
                        bean.setUrl("R7X5snROUNc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அரசர் யோசபாத்து");
                        bean.setUrl("r4LHGisTECs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித மார்ட்டின் தெ போரஸ்");
                        bean.setUrl("0NY0VwwmSYs");
                        videoViewModelsList.add(bean);
                        break;

                }
                break;
            case 4:
                switch (position) {
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("இறைவாக்கினர் எலியாவின் வாழ்வில் கடவுள் செய்த அற்புதங்கள்");
                        bean.setUrl("NZ8c0kPCZaY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஏழைக் கைம்பெண்ணின் காணிக்கை");
                        bean.setUrl("DxkKd0-wbfM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித லாரன்ஸ்");
                        bean.setUrl("QEj7YKYRTfQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("பென்னி குவிக்");
                        bean.setUrl("GYP-zs-uHBc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("கொடுப்பதன் இன்பம்");
                        bean.setUrl("ADh0ZGnqbbY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("எலியா இறைவாக்கினர்");
                        bean.setUrl("byxlyYn8fO");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித லாரன்ஸ்");
                        bean.setUrl("03rHuOILCPo");
                        videoViewModelsList.add(bean);

                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("இறைவாக்கினர் எலிசா");
                        bean.setUrl("vH3AyYtdxrE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நல்ல சமாரியர் உவமை");
                        bean.setUrl("ckKZJd3ZvrM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("கொல்கொத்தா நகர் புனித தெரசா");
                        bean.setUrl("oc351-N0qo0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("சினேகா மோகன்தாஸ்");
                        bean.setUrl("6jT1Df_zqxk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இரக்கத்தின் ஆண்டவரே");
                        bean.setUrl("RLJv2x4OLvI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("எலிசா இறைவாக்கினர்");
                        bean.setUrl("dEtNdNfeNBk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("கொல்கொத்தா நகர் புனித தெரசா");
                        bean.setUrl("6hd7IdUjLok");
                        videoViewModelsList.add(bean);

                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("இறைவாக்கினர் எசாயா நூல்");
                        bean.setUrl("ttPnRVS0GX4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மறைநூல் வாக்கு இயேசுவில் நிறைவேறியது");
                        bean.setUrl("OmbGIBHT73I");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசுவும் சக்கேயுவும்");
                        bean.setUrl("z895oMTsKm0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஆஸ்கர் ரொமேரோ");
                        bean.setUrl("Ai8vvrDgGpY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மார்டின் லூதர் கிங் ஜூனியர்");
                        bean.setUrl("dZkuaWwdOG8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மாண்புயர் இவ்வருளடையாளத்தைத்");
                        bean.setUrl("ccmd4wsxofk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆண்டவரின் ஆவி என்மேலே");
                        bean.setUrl("gPb4x1WLE2Q");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("எசாயா இறைவாக்கினர்");
                        bean.setUrl("O9TvOiqNqr4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஆஸ்கர் ரொமேரோ");
                        bean.setUrl("gGPH2Kr-dVU");
                        videoViewModelsList.add(bean);

                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("இறைவாக்கினர் எரேமியா நூல்");
                        bean.setUrl("EI203yoBSe8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசு முதல் சீடரை அழைத்தல்");
                        bean.setUrl("C0PXMQWB5rk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித அல்போன்ஸ் மரிய லிகோரி");
                        bean.setUrl("6F5U4jb4A38");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("'இறை ஊழியர்' தாட்டிபத்திரி ஞானம்மா");
                        bean.setUrl("4bkT042Zv5I");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("கிறிஸ்துவின் ஆன்மாவே");
                        bean.setUrl("6C-X4Li9NvI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("உனக்காக இனி வாழ முடிவெடுத்தேன்");
                        bean.setUrl("5gKJ21WBcnc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("எரேமியா இறைவாக்கினர்");
                        bean.setUrl("LB2iQ8Hw5o0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித அல்போன்ஸ் மரிய லிகோரி");
                        bean.setUrl("6F5U4jb4A38");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("இறைவாக்கினர் எசேக்கியேல் நூல்");
                        bean.setUrl("GsW96SB42cQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசுவே நல்ல ஆயர்");
                        bean.setUrl("JxyyWFFnnyY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித மேரி யுப்ரேசியா பெல்லேடியர்");
                        bean.setUrl("i51c2RAFCYU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நிக்கோலாஸ் வின்ஸ்டன்");
                        bean.setUrl("PKkgO06bAZk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("உயிர்களைக் காக்க உதவிய சிறுவன்");
                        bean.setUrl("fbvt4RzOru4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆண்டவர் என் ஆயன்");
                        bean.setUrl("gEm9iN1xF_k");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("எசேக்கியேல் இறைவாக்கினர்");
                        bean.setUrl("Lp8PqePFBIU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பேதுரு");
                        bean.setUrl("GLRbZf5DHHQ");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("தானியேலும் சிங்கங்களும்");
                        bean.setUrl("S5xA7zBVWAs");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசுவே உண்மை");
                        bean.setUrl("5YMIFQqbwfc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித செபஸ்தியார்");
                        bean.setUrl("ZXlyKx48ELg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மகாத்மா காந்தி");
                        bean.setUrl("KqZ14BbATjI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இறைவனே என்னைக் காக்கின்றார்");
                        bean.setUrl("siw-DE-mDHc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தானியேல் இறைவாக்கினர்");
                        bean.setUrl("lWocRgAFHKM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித செபஸ்தியார்");
                        bean.setUrl("uBrq9C-wv0Y");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("இறைவாக்கினர் ஆமோஸ் நூல்");
                        bean.setUrl("rGAm1zmBtvE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("செல்வரும் இலாசரும்");
                        bean.setUrl("-kilAu_EOkM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித மரிய ஜெம்மா உம்பேத்தா கல்காணி");
                        bean.setUrl("jJfwehSWnCY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திரு. பாலம் கல்யாணசுந்தரம்");
                        bean.setUrl("EEbQzsSYZh4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("என் ஆன்மா எந்நாளுமே ஆண்டவரை");
                        bean.setUrl("ajwUAxaZqVw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆமோஸ் இறைவாக்கினர்");
                        bean.setUrl("lfD9M7TZZL4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித மரிய ஜெம்மா கல்காணி");
                        bean.setUrl("mFxn56kGiTw");
                        videoViewModelsList.add(bean);

                        break;
                    case 8:
                        bean = new VideoViewModel();
                        bean.setName("யோனாவும் பெரிய மீனும்");
                        bean.setUrl("u3VK2bHEE_U");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("மன்னிக்க மறுத்த பணியாள் உவமை");
                        bean.setUrl("_x5sPmVTWcA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித தமியான்");
                        bean.setUrl("6uDJvL49iP4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திரு. பஞ்சுராஜா");
                        bean.setUrl("gi7yz209QQ8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("வெற்றியை விட இரக்கமே பெரிது");
                        bean.setUrl("FufRO2n_fLE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இரக்கம் நிறைந்த தெய்வமே");
                        bean.setUrl("bEQPpmn6Dm8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("யோனா இறைவாக்கினர்");
                        bean.setUrl("30DJ_ZZEdfg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித தமியான்");
                        bean.setUrl("K_lGNoPNxtQ");
                        videoViewModelsList.add(bean);
                        break;

                }
                break;
            case 5:
                switch (position) {
                    case 11:
                        bean = new VideoViewModel();
                        bean.setName("அருளடையாளங்கள் என்றால் என்ன?");
                        bean.setUrl("jKTT4ZmgaCU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அருளடையாளங்கள் ஓர் அறிமுகம்");
                        bean.setUrl("tiolpKk8aEg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஏழு அருளடையாளப் பாடல்கள்");
                        bean.setUrl("Yv-4Tlzy-r0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அருளடையாளங்கள் ஏழாகும் – பாடல்");
                        bean.setUrl("8E8EfkacczM");
                        videoViewModelsList.add(bean);
                        break;
                    case 1:
                        bean = new VideoViewModel();
                        bean.setName("திருமுழுக்கு அருளடையாளம்");
                        bean.setUrl("7hvVG4Y4FUY");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திருமுழுக்கு அருளடையாளம்");
                        bean.setUrl("bkBpVsF3xik");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("செங்கடல் பிரிக்கப்படுதல்");
                        bean.setUrl("OxnOy7-QQPI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இயேசுவின் திருமுழுக்கு");
                        bean.setUrl("-LrWCURqdOM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பிரான்சிஸ் சவேரியார்");
                        bean.setUrl("M4geNNGryIo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆனந்த திருமுழுக்குக் கொண்டாட்டம்");
                        bean.setUrl("3PeXVXBSntM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பிரான்சிஸ் சவேரியார்");
                        bean.setUrl("YIQ9VjKZPJQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("செங்கடல் பிரிக்கப்படுதல்");
                        bean.setUrl("92WnrE4fidA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பிரான்சிஸ் சவேரியார்");
                        bean.setUrl("YIQ9VjKZPJQ");
                        videoViewModelsList.add(bean);

                        break;
                    case 2:
                        bean = new VideoViewModel();
                        bean.setName("உறுதிப்பூசுதல் அருளடையாளம்");
                        bean.setUrl("4S5tXLfUbhI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("உறுதிப்பூசுதல் அருளடையாளம்");
                        bean.setUrl("Lu3MoT_egFI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("எசேக்கியேல் உலர்ந்த எலும்புகளுக்கு உயிர்கொடுத்தல்");
                        bean.setUrl("2pUfb5B1ZNU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("பெந்தக்கோஸ்தே நாளில் தூய ஆவியாரின் வருகை");
                        bean.setUrl("62R2f0VDw5E");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஸ்தேவான்");
                        bean.setUrl("dqxxMlIMRRg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அருளாளர் அருள்சகோதரி ராணி மரியா");
                        bean.setUrl("zW8Z2WfLMTA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("வானம் திறந்து வெண்புறா போல");
                        bean.setUrl("47igsbGLSVU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தூய ஆவியார்");
                        bean.setUrl("Soxoqj33y5w");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஸ்தேவான்");
                        bean.setUrl("c2YW3GR5Fhs");
                        videoViewModelsList.add(bean);

                        break;
                    case 3:
                        bean = new VideoViewModel();
                        bean.setName("நற்கருணை அருளடையாளம்");
                        bean.setUrl("8Jw__UWhu2A");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நற்கருணை அருளடையாளம்");
                        bean.setUrl("UFYuLhEeohU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இரயேல் மக்களுக்குக் கடவுள் அளித்த மன்னா உணவு");
                        bean.setUrl("ui1TfG9RVOI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("இறுதி இராவுணவு");
                        bean.setUrl("UAbmrdXoUgM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பியோ");
                        bean.setUrl("4Qxe4elAN2g");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("பாதிரியார் கிரஹாம் ஸ்டூவர்ட் ஸ்டெயின்ஸ்");
                        bean.setUrl("UPLuKcQ58xE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புலியும் பசுவும்");
                        bean.setUrl("-pqHoo5SaOc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நானே வானின்று இறங்கி வந்த");
                        bean.setUrl("UXTDnPRm3n4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("எலியாவுக்கு உணவளித்தக் கடவுள்");
                        bean.setUrl("_UllXAp_xbo");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித பியோ");
                        bean.setUrl("ooxpwVLjOig");
                        videoViewModelsList.add(bean);

                        break;
                    case 4:
                        bean = new VideoViewModel();
                        bean.setName("ஒப்புரவு அருளடையாளம்");
                        bean.setUrl("FDMRtBlE32g");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஒப்புரவு அருளடையாளம்");
                        bean.setUrl("SEmaSAY_eXU");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("யோனாவின் இறைவாக்கும், நினிவே மக்களின் மனமாற்றமும்");
                        bean.setUrl("u3VK2bHEE_U");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("காணாமற்போன மகன் உவமை");
                        bean.setUrl("q4uTND0Jabc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித நெப்போமுசேன் யோவான்");
                        bean.setUrl("fXRvVxrnWTw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திரு. சமந்தர் சிங்");
                        bean.setUrl("OOXxyuZI-sI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அப்பா நான் தவறு செய்தேன்");
                        bean.setUrl("GfjvM5DV2Bc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("தாவீது அரசரின் மனமாற்றம்");
                        bean.setUrl("m1hmronKtR0");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித நெப்போமுசேன் யோவான்");
                        bean.setUrl("fXRvVxrnWTw");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new VideoViewModel();
                        bean.setName("ஒப்புரவு நோயில்பூசுதல் அருளடையாளம்");
                        bean.setUrl("czsuFYnQUls");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஒப்புரவு நோயில்பூசுதல் அருளடையாளம்");
                        bean.setUrl("P9UMbhvcLdI");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அரசன் எசேக்கியாவின் நோய் குணமாதல்");
                        bean.setUrl("qZGEiqm1DOE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("நல்ல சமாரியர் உவமை");
                        bean.setUrl("ckKZJd3ZvrM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித கமில்லஸ் தெ லெல்லிஸ்");
                        bean.setUrl("ZgBRIMNzeW8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஐந்து ரூபாய் மருத்துவர் திரு. ஜெயச்சந்திரன்");
                        bean.setUrl("91BoMI4n3bQ");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("என் கூடவே இரும் ஓ இயேசுவே");
                        bean.setUrl("6sXffM_7iAw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அரசன் எசேக்கியா");
                        bean.setUrl("2pB34LFgajA");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித கமில்லஸ் தெ லெல்லிஸ்");
                        bean.setUrl("ZgBRIMNzeW8");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new VideoViewModel();
                        bean.setName("குருத்துவ அருளடையாளம்");
                        bean.setUrl("FR5KuKhpUSE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("குருத்துவ அருளடையாளம");
                        bean.setUrl("u87iWVtaSWc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆரோனும், அவர்தம் மகன்களும் திருநிலைப்படுத்தப்படுதல்");
                        bean.setUrl("u17Xzdc4PZw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆண்டவரின் திருவிருந்து");
                        bean.setUrl("UAbmrdXoUgM");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஜான் மரிய வியான்னி");
                        bean.setUrl("j-VQZDnW0F4");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அருள்பணியாளர் லூயி லெவே");
                        bean.setUrl("yMtV9zyqcNE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அருள்பணியாளர் லூயி லெவே");
                        bean.setUrl("kxFG1fbTO90");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆண்டவரே நீரே என்னை மயக்கி விட்டீர்");
                        bean.setUrl("A-DcboDWmZE");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஆரோன், அவர்தம் மகன்கள் ஆகியோரின் திருநிலைப்பாடு");
                        bean.setUrl("MPFbK4ENIB8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித ஜான் மரிய வியான்னி");
                        bean.setUrl("Vx5ok2687KQ");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new VideoViewModel();
                        bean.setName("திருமண அருளடையாளம்");
                        bean.setUrl("JRXxHvPHGd8");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("திருமண அருளடையாளம்");
                        bean.setUrl("eNKVNvAK5_s");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஈசாக்கு ரெபேக்கா திருமணம்");
                        bean.setUrl("0gu-qpgp0zk");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("கானாவில் திருமணம்");
                        bean.setUrl("9UFaihwP27Q");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித லூயி மார்டின், புனித செலி குவரின்");
                        bean.setUrl("4SZdX-d97aw");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புத்திசாலி மனைவி");
                        bean.setUrl("vyE3DUEyROc");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("அன்பு என்பது வல்லமை");
                        bean.setUrl("9kaqG2PWuWg");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("ஈசாக்கு ரெபேக்கா திருமணம்");
                        bean.setUrl("7AvQ0d6HB1I");
                        videoViewModelsList.add(bean);

                        bean = new VideoViewModel();
                        bean.setName("புனித லூயி மார்டின், புனித செலி குவரின்");
                        bean.setUrl("wsxgujZhjYI");
                        videoViewModelsList.add(bean);

                        break;
                    case 12:
                        bean = new VideoViewModel();
                        bean.setName("அருள்கருவிகள்");
                        bean.setUrl("jYZjQbZfeZs");
                        videoViewModelsList.add(bean);
                        break;

                }
                break;
        }
    }


    @Override
    public void onBackPressed() {
        try {
            if (!isMusicServiceRunning && pref.getMusicval().equals("1")) {
                startService(new Intent(getApplicationContext(), MusicService.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}