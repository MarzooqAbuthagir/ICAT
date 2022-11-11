package com.maria.pastelhub.videos.livevideos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.maria.pastelhub.application.ICatApplication;
import com.maria.pastelhub.book_landing.BookLanding;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;
import com.maria.pastelhub.videos.AudioVideoAdapter;
import com.maria.pastelhub.videos.AudioVideoBean;
import com.maria.pastelhub.videos.livevideos.adpater.VideoAdapter;
import com.maria.pastelhub.videos.livevideos.viewmodel.VideoViewModel;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.maria.pastelhub.application.ICatApplication.isMusicServiceRunning;

public class LiveVideo extends AppCompatActivity {

    NetworkStatusFinder networkStatusFinder;
    TextView major_title;
    private AudioVideoAdapter videoAdapter;
    private final ArrayList<AudioVideoBean> videoViewModelsList = new ArrayList<>();
    RecyclerView recyclerView;
    Pref pref;
    int classnum = 0, position, selectedPositon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_live_video);

            networkStatus();

//        ButterKnife.bind(this);

            findViewById(R.id.back_major_header).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

            major_title = findViewById(R.id.major_title);

            recyclerView = findViewById(R.id.video_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


            pref = new Pref(LiveVideo.this);

            classnum = getIntent().getIntExtra("ID", 0);
            selectedPositon = getIntent().getIntExtra("pos", 0);
            String language = getIntent().getStringExtra("language");

            major_title.setText(getIntent().getStringExtra("Title"));
            if (language.equals("0") && classnum == 0) {
                if (getIntent().getStringExtra("choice").equals("tamilOne"))
                    position = 11;
                else if (getIntent().getStringExtra("choice").equals("tamilTwo"))
                    position = 12;
            } else
                position = Integer.parseInt(major_title.getText().toString().split(" ")[2]);

            SharedPreferences preferences = getSharedPreferences("SharedPrefManager", Context.MODE_PRIVATE);

            ImageView ivSound = findViewById(R.id.sound_icon);
            ivSound.setImageResource(preferences.getBoolean("Sound", true) ? R.drawable.sound_on : R.drawable.sound_mute);

            ivSound.setOnClickListener(view -> {
                Toast.makeText(LiveVideo.this, "Sound function currently disabled ", Toast.LENGTH_SHORT).show();
            });

            setReviewAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
//        SingleTon.setListener(this, findViewById(R.id.sound_icon));

        stopService(new Intent(LiveVideo.this, MusicService.class));

//        setReviewAdapter();
    }


    private void setReviewAdapter() {
        try {
            videoViewModelsList.clear();
            int contentView = 0;
            if (getString(R.string.videos_tamil).equals(getIntent().getStringExtra("Views"))) {
                contentView = 1;
                getVideoList();
            } else if (getString(R.string.songs_tamil).equals(getIntent().getStringExtra("Views"))) {
                contentView = 0;
                getAudioList();
            }

            Log.d("DEBUG", " :: Views :: " + getIntent().getStringExtra("Views") + " :: " + getString(R.string.videos_tamil) + " :: " + getString(R.string.songs_tamil));

            videoAdapter = new AudioVideoAdapter(LiveVideo.this, videoViewModelsList, contentView, getIntent().getStringExtra("language"));
            recyclerView.setAdapter(videoAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(LiveVideo.this)) {
            Intent intent = new Intent(LiveVideo.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
            intent.putExtra("from_page", "book_landing");
            startActivity(intent);
            finish();
        }

    }

    String songPath = "https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/audio";
    String numberSongPath = "https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/Numbers/";

    void getAudioList() {
        AudioVideoBean bean;
        Log.i(getClass().getName(), "==================   " + classnum + "   " + position);
        switch (classnum) {
            case 0:
                switch (position) {
                    case 11:
                        if (selectedPositon == 1) {
                            bean = new AudioVideoBean();
                            bean.setTitle("அன்பின் வடிவம் இயேசப்பா");
                            bean.setUrl(songPath + "/UYIREZHUTHU_PAADAL_MIX_MAS_TRK.mp3");
                            bean.setLyric("அன்பின் வடிவம் இயேசப்பா\n" +
                                    "ஆசி தருபவர் இயேசப்பா\n" +
                                    "இரக்கம் கொள்பவர் இயேசப்பா\n" +
                                    "ஈர்ப்புடன் அணைப்பவர் இயேசப்பா\n" +
                                    "\n" +
                                    "உயிர்த்து எழுந்தவர் இயேசப்பா\n" +
                                    "ஊக்கம் தருபவர் இயேசப்பா\n" +
                                    "எங்கும் இருப்பவர் இயேசப்பா\n" +
                                    "ஏழையர் தெய்வம் இயேசப்பா\n" +
                                    "\n" +
                                    "ஐயம் தீர்ப்பவர் இயேசப்பா\n" +
                                    "ஒன்றே தெய்வம் இயேசப்பா\n" +
                                    "ஓதும் வேதமும் இயேசப்பா\n" +
                                    "ஔடதம் அவரே இயேசப்பா\n");
                            videoViewModelsList.add(bean);

                            bean = new AudioVideoBean();
                            bean.setTitle("அகிலம் மீட்கப் பிறந்தவர் என் இயேசு");
                            bean.setUrl(songPath + "/01_Akilam_Mix_Mas_trk.mp3");
                            bean.setLyric("அகிலம் மீட்கப் பிறந்தவர் என் இயேசு\n" +
                                    "என்னை அன்பாகத்தான் அணைத்துக் கொள்வார் இயேசு\n" +
                                    "ஆடிப்பாடி மகிழ்ந்திருப்பேன் நானும்\n" +
                                    "அவர் கையைப் பிடித்து நடந்திடுவேன் நாளும் - 2\n" +
                                    "என் அன்பு இயேசு என் செல்ல இயேசு\n" +
                                    "என் அன்பு இயேசு செல்ல இயேசு\n" +
                                    "குட்டி இயேசுவே – 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 2) {

                            bean = new AudioVideoBean();
                            bean.setTitle("ஆசி தரும் ஆசி தரும் அன்பு இயேசுவே");
                            bean.setUrl(songPath + "/02_AASI_THARUM_MIX_MAS_TRK.mp3");
                            bean.setLyric("ஆசி தரும் ஆசி தரும் அன்பு இயேசுவே\n" +
                                    "ஆசையோடு ஓடி வந்தேன் அள்ளிக் கொள்ளுமே\n" +
                                    "ஆசி தரும் ஆசி தரும் செல்ல இயேசுவே\n" +
                                    "ஆவலோடு தேடி வந்தேன் ஆசி தாருமே - 2\n" +
                                    "ஆசி தாருமே ஆசி தாருமே\n" +
                                    "அள்ளி என்னை அணைத்துக் கொண்டு\n" +
                                    "ஆசி தாருமே - 2\n");

                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 3) {

                            bean = new AudioVideoBean();
                            bean.setTitle("இயேசப்பா உயிர் நீங்கப்பா – என்");
                            bean.setUrl(songPath + "/03_YESAPPA_MIX_MAS_TRK.mp3");
                            bean.setLyric("இயேசப்பா உயிர் நீங்கப்பா - என்\n" +
                                    "இதயமே உங்க வீடப்பா\n" +
                                    "இயேசப்பா நீங்க வாங்கப்பா - இறை\n" +
                                    "இன்பத்த அள்ளித் தாங்கப்பா - 2\n" +
                                    "இயேசப்பா...… வாங்கப்பா...…\n" +
                                    "தாங்கப்பா...… உயிர் நீங்கப்பா – 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 4) {
                            bean = new AudioVideoBean();
                            bean.setTitle("ஈர்த்து இயேசு அழைக்கிறார்");
                            bean.setUrl(songPath + "/04_EERTHU_MIX_MAS_TRK.mp3");
                            bean.setLyric("ஈர்த்து இயேசு அழைக்கிறார்\n" +
                                    "ஈக்கள் போல பறந்து வா - 2\n" +
                                    "ஈகை செய்ய அழைக்கிறார்\n" +
                                    "பூக்கள் போல மலர்ந்து வா - 2\n" +
                                    "\n" +
                                    "பறந்து வா மலர்ந்து வா\n" +
                                    "ஈர்க்கும் இயேசு அருகில் நீயும் பறந்து வா – 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 5) {
                            bean = new AudioVideoBean();
                            bean.setTitle("உயிரான இயேசுவே – என்");
                            bean.setUrl(songPath + "/05_UYIRANA_YESUVE_MIX_MAS_TRK.mp3");
                            bean.setLyric("உயிரான இயேசுவே - என்\n" +
                                    "உடன் வாழும் இயேசுவே - 2\n" +
                                    "உயிர்த்த என் இயேசுவே - என்\n" +
                                    "உள்ளம் வந்து தங்குமே - 2\n" +
                                    "\n" +
                                    "தங்குமே என் உள்ளமே\n" +
                                    "வாருமே வாழ்வைத் தாருமே – 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 6) {
                            bean = new AudioVideoBean();
                            bean.setTitle("ஊற்றுத் தண்ணீர் இயேசப்பா");
                            bean.setUrl(songPath + "/06_OOTRU_THANNI_MIX_MAS_TRK.mp3");
                            bean.setLyric("ஊற்றுத் தண்ணீர் இயேசப்பா\n" +
                                    "ஊக்கம் தரும் இயேசப்பா - 2\n" +
                                    "ஊனமுற்றோர் நலம் பெறவே\n" +
                                    "ஊட்டம் அளிக்கும் இயேசப்பா - 2\n" +
                                    "இயேசப்பா நலம் தாங்கப்பா\n" +
                                    "இயேசப்பா குணம் நீங்கப்பா – 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 7) {
                            bean = new AudioVideoBean();
                            bean.setTitle("எல்லாம் அறிந்த இயேசய்யா");
                            bean.setUrl(songPath + "/07_ELLAM_ARINTHA_MIX_MAS_TRK.mp3");
                            bean.setLyric("எல்லாம் அறிந்த இயேசய்யா\n" +
                                    "எருசலேம் கோவில் வந்தாரே\n" +
                                    "எத்தனை கேள்விகள் கேட்டாலும்\n" +
                                    "எடுப்புடன் பதிலைத் தந்தாரே - 2\n" +
                                    "\n" +
                                    "எங்கும் வாழும் இயேசய்யா\n" +
                                    "என்னில் வருவார் பாரய்யா\n" +
                                    "எட்டுத் திக்கிலும் இயேசய்யா\n" +
                                    "எனக்குள் இருக்கார் பாரய்யா - 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 8) {
                            bean = new AudioVideoBean();
                            bean.setTitle("ஏக்கத்தோடு பறந்து போகும் பட்டாம்பூச்சியே");
                            bean.setUrl(songPath + "/08_YEKATHODU_MIX_MAS_TRK.mp3");
                            bean.setLyric("ஏக்கத்தோடு பறந்து போகும் பட்டாம்பூச்சியே\n" +
                                    "ஏங்கி ஏங்கி பறந்து நீங்க எங்கே போறீங்க?\n" +
                                    "ஏசு தெய்வம் நல்ல தெய்வம் கவலைப்படாதே\n" +
                                    "ஏழை நமக்கு உதவி செய்ய வந்திடுவாரே\n" +
                                    "\n" +
                                    "கவலைப்படாதே நீ கவலைப்படாதே\n" +
                                    "அன்பு தெய்வம் இயேசு நமக்கு உதவிடுவாரே\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 9) {
                            bean = new AudioVideoBean();
                            bean.setTitle("ஐயா ஐயா இயேசய்யா");
                            bean.setUrl(songPath + "/09_AIYA_AIYA_MIX_MAS_TRK.mp3");
                            bean.setLyric("ஐயா ஐயா இயேசய்யா\n" +
                                    "ஐந்து அப்பம் இருக்கய்யா - 2\n" +
                                    "ஐயாயிரம் பேர் சாப்பிடவே\n" +
                                    "ஐயம் நீக்கி உதவய்யா - 2\n" +
                                    "ஐயம் நீக்குமே எனது ஐயம் நீக்குமே\n" +
                                    "உதவி செய்யவே எனக்கு ஆற்றல் தாருமே – 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 10) {
                            bean = new AudioVideoBean();
                            bean.setTitle("ஒரு நாள் இயேசு செபித்திடவே");
                            bean.setUrl(songPath + "/10_ORU_NAL_YESU_MIX_MAS_TRK.mp3");
                            bean.setLyric("ஒரு நாள் இயேசு செபித்திடவே\n" +
                                    "ஒலிவ மலைக்கு வந்தாரே - 2\n" +
                                    "ஒளியாம் தந்தை ஆற்றல்பெற\n" +
                                    "ஒருமனதோடு செபித்தாரே - 2\n" +
                                    "\n" +
                                    "செபம் செபம் செபம்\n" +
                                    "எனக்கு எல்லாம் ஜெயம்\n" +
                                    "ஜெயம் ஜெயம் ஜெயம்\n" +
                                    "அது செபத்தினாலே வரும் – 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 11) {
                            bean = new AudioVideoBean();
                            bean.setTitle("ஓடி ஓடி உழைக்கனும்");
                            bean.setUrl(songPath + "/11_ODI_ODI_MIX_MAS_TRK.mp3");
                            bean.setLyric("ஓடி ஓடி உழைக்கனும்\n" +
                                    "ஓயாமல் உழைக்கனும் - 2\n" +
                                    "ஓய்வுநாளாய் இருந்தாலும்\n" +
                                    "ஓய்வில்லாமல் உதவனும் - 2\n" +
                                    "\n" +
                                    "நன்மை செய்ய பழகனும்... ஆ... ஆ...…\n" +
                                    "நன்மை செய்ய பழகனும் - அதை\n" +
                                    "நாளும் செய்து மகிழனும் - 2\n" +
                                    "நல்ல தெய்வம் இயேசுபோல\n" +
                                    "நன்மை மட்டும் செய்யனும் - 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 12) {
                            bean = new AudioVideoBean();
                            bean.setTitle("ஔடதம் என்றால் மருந்து");
                            bean.setUrl(songPath + "/12_OUDATHAM_MIX_MAS_TRK.mp3");
                            bean.setLyric("ஔடதம் என்றால் மருந்து\n" +
                                    "இயேசுவே அருமருந்து - 2\n" +
                                    "நோய்கள் போகும் பறந்து\n" +
                                    "அவர் பார்வை எனக்கு விருந்து - 2\n" +
                                    "\n" +
                                    "பறந்து வா விரைந்து வா\n" +
                                    "நடந்து வா நலம்பெறவே வா – 2\n");
                            videoViewModelsList.add(bean);
                        }
                        break;


                    case 12:
                        if (selectedPositon == 1) {
                            bean = new AudioVideoBean();
                            bean.setTitle("ஒன்று என்று சொல்லும் போது");
                            bean.setUrl(numberSongPath + "audio/ONDRU_ENDRU_MAS_TRK.mp3");
                            bean.setLyric("ஒன்று என்று சொல்லும் போது\n" +
                                    "ஒரே கடவுளைப் புகழ்வோம்\n" +
                                    "\n" +
                                    "இரண்டு என்று சொல்லும் போது\n" +
                                    "ஆதாம், ஏவாள் நினைப்போம்\n" +
                                    "\n" +
                                    "மூன்று என்று சொல்லும் போது\n" +
                                    "மூன்று ஞானியர் அறிவோம்\n" +
                                    "\n" +
                                    "நான்கு என்று சொல்லும் போது\n" +
                                    "நற்செய்தி நூல்கள் படிப்போம்\n" +
                                    "\n" +
                                    "ஐந்து என்று சொல்லும் போது\n" +
                                    "ஐந்து நூல்கள் தெரிவோம்\n" +
                                    "\n" +
                                    "ஆறு என்று சொல்லும் போது\n" +
                                    "கடவுளின் பண்புகள் உணர்வோம்\n" +
                                    "\n" +
                                    "ஏழு என்று சொல்லும் போது\n" +
                                    "ஏசுவின் வார்த்தைகள் கற்போம்\n" +
                                    "\n" +
                                    "எட்டு என்று சொல்லும் போது\n" +
                                    "பேறுபெற்றோராய் வாழ்வோம்\n" +
                                    "\n" +
                                    "ஒன்பது என்று சொல்லும் போது\n" +
                                    "ஆவியின் கனிகள் பெறுவோம்\n" +
                                    "\n" +
                                    "பத்து என்று சொல்லும் போது\n" +
                                    "பத்துக் கட்டளைகள் கடைபிடிப்போம்\n");
                            videoViewModelsList.add(bean);

                            bean = new AudioVideoBean();
                            bean.setTitle("ஒன்று ஒன்று ஒன்று கடவுள் என்றும் ஒன்று");
                            bean.setUrl(numberSongPath + "audio/01_ONDRU_MIX_MAS_TRK.mp3");
                            bean.setLyric("ஒன்று ஒன்று ஒன்று கடவுள் என்றும் ஒன்று\n" +
                                    "நன்று நன்று நன்று அவர் படைப்புகளும் நன்று - 2\n" +
                                    "படைத்தக் கடவுள் பரிசுத்தரைப்\n" +
                                    "புகழ்ந்து பாடுவோம் - 2\n" +
                                    "படைப்பனைத்தும் ஒன்று சேர்ந்து\n" +
                                    "மகிழ்ந்து போற்றுவோம் - 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 2) {
                            bean = new AudioVideoBean();
                            bean.setTitle("ஆதாம் ஏவாள் இருவரும் தான்");
                            bean.setUrl(numberSongPath + "audio/02_AATHAM_AEVAL_MIX_MAS_TRK.mp3");
                            bean.setLyric("ஆதாம் ஏவாள் இருவரும் தான்\n" +
                                    "கடவுள் படைத்த முதல் மனிதர் - 2\n" +
                                    "ஏதேன் தோட்டம் நடுவினிலே\n" +
                                    "மகிழ்வாய் வாழ்ந்து வந்தனரே - 2\n" +
                                    "\n" +
                                    "ஆதாம் என்பவர் அப்பாவாம்\n" +
                                    "ஏவாள் என்பவர் அம்மாவாம் - 2\n" +
                                    "காயின் ஆபேல் பிள்ளைகளாம்\n" +
                                    "நாம் அவர்களின் சந்ததியாம் - 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 3) {

                            bean = new AudioVideoBean();
                            bean.setTitle("மூன்று ஞானிகள் ஒட்டகம் மேலே");
                            bean.setUrl(numberSongPath + "audio/03_MOONDRU_GNANIGAL_MIX_MAS_TRK.mp3");
                            bean.setLyric("மூன்று ஞானிகள் ஒட்டகம் மேலே\n" +
                                    "குழந்தை இயேசுவைப் பார்க்க வந்தாரே - 2\n" +
                                    "பொன்னும் தூபமும் வெள்ளைப்போளமும்\n" +
                                    "கொடுத்து இயேசுவை வணங்கிச் சென்றாரே - 2\n" +
                                    "\n" +
                                    "பார்க்க வந்தாரே பரிசு தந்தாரே\n" +
                                    "குழந்தை இயேசுவை வணங்கிச் சென்றாரே – 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 4) {

                            bean = new AudioVideoBean();
                            bean.setTitle("இயேசுப்பற்றி அறியனுமா? (விவிலியம் படி - 2)");
                            bean.setUrl(numberSongPath + "audio/04_YESU_PATRI_MIX_MAS_TRK.mp3");
                            bean.setLyric("இயேசுப்பற்றி அறியனுமா? (விவிலியம் படி - 2)\n" +
                                    "அவரைப்போல வாழனுமா? (நற்செய்தி படி - 2) - 2\n" +
                                    "நாலு பேரு எழுதியது அது நல்லதொரு நற்செய்தி நூல்கள்\n" +
                                    "மத்தேயு மாற்கு லூக்கா யோவான் அவர்களின் திருப்பெயர்கள்\n" +
                                    "இது அவர்களின் திருப்பெயர்கள்\n" +
                                    "\n" +
                                    "விவிலியம் படி அதைத் தினமும் படி\n" +
                                    "இயேசுபோல் வாடிநந்திடவே\n" +
                                    "நற்செய்தி தினமும் படி - நல்\n" +
                                    "நற்செய்தி தினமும் படி - 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 5) {
                            bean = new AudioVideoBean();
                            bean.setTitle("கடவுள் தந்த புத்தகங்கள் ஐந்து");
                            bean.setUrl(numberSongPath + "audio/05_KADAVUL_THANTHA_MIX_MAS_TRK.mp3");
                            bean.setLyric("கடவுள் தந்த புத்தகங்கள் ஐந்து\n" +
                                    "உணர்ந்து நீயும் படித்திடுவாய் நன்கு - 2\n" +
                                    "\n" +
                                    "ஆதியாகமம் அது முதலாம் நூலாம்\n" +
                                    "விடுதலைப் பயணம் அது இரண்டாம் நூலாம்\n" +
                                    "லேவியராகமம் அது மூன்றாம் நூலாம்\n" +
                                    "எண்ணாகமம் அது நான்காம் நூலாம்\n" +
                                    "உபாகமம் அது ஐந்தாம் நூலாம் – 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 6) {
                            bean = new AudioVideoBean();
                            bean.setTitle("கடவுளின் பண்புகள் ஆறு – அதை");
                            bean.setUrl(numberSongPath + "audio/06_KADAVULIN_PANBUGAL_MIX_MAS_TRK.mp3");
                            bean.setLyric("கடவுளின் பண்புகள் ஆறு - அதை\n" +
                                    "அன்புடன் விளக்கி நீயும் கூறு - 2\n" +
                                    "\n" +
                                    "தாமாக இருக்கிறார் நம் கடவுள்\n" +
                                    "ஆதியும் அந்தமும் நம் கடவுள் - 2\n" +
                                    "உருவம் அற்றவர் நம் கடவுள்\n" +
                                    "நன்மைகளின் ஊற்றும் நம் கடவுள் - 2\n" +
                                    "எங்கும் இருப்பவர் நம் கடவுள்\n" +
                                    "அனைத்திற்கும் காரணம் நம் கடவுள் – 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 7) {
                            bean = new AudioVideoBean();
                            bean.setTitle(" 'நானே' வாக்கியம் ஏழய்யா – அதைக்");
                            bean.setUrl(numberSongPath + "audio/07_NANE_VAKIYAM_MIX_MAS_TRK.mp3");
                            bean.setLyric("'நானே' வாக்கியம் ஏழய்யா - அதைக்\n" +
                                    "கற்று நீயும் கூறய்யா - 4\n" +
                                    "உயிர் தரும் உணவு இயேசய்யா\n" +
                                    "உலகின் ஒளியே இயேசய்யா - 2\n" +
                                    "விண்ணக வாசல் இயேசய்யா\n" +
                                    "உயிரும் உயிர்ப்பும் இயேசய்யா - 2\n" +
                                    "நல்ல ஆயன் இயேசய்யா\n" +
                                    "வழியும் உண்மையும் இயேசய்யா\n" +
                                    "திராட்சைச் செடியும் இயேசய்யா\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 8) {
                            bean = new AudioVideoBean();
                            bean.setTitle("பேறுபெற்றோர் நாம் பேறுபெற்றோர்");
                            bean.setUrl(numberSongPath + "audio/08_PERU_PETROR_MIX_MAS_TRK.mp3");
                            bean.setLyric("பேறுபெற்றோர் நாம் பேறுபெற்றோர்\n" +
                                    "இயேசுவின் குழந்தைகள் நாம் பேறுபெற்றோர்\n" +
                                    "\n" +
                                    "ஏழையர் உள்ளத்தோரே பேறுபெற்றோர்\n" +
                                    "துயரத்தில் இருப்போரே பேறுபெற்றோர்\n" +
                                    "கனிவு கொண்டவரே பேறுபெற்றோர்\n" +
                                    "நீதிவழி வாழ்பவரே பேறுபெற்றோர்\n" +
                                    "அவர்கள் இயேசுவின் குழுந்தைகளாய் பேறுபெற்றோர்\n" +
                                    "\n" +
                                    "இரக்கம் உடையோரே பேறுபெற்றோர்\n" +
                                    "தூய உள்ளத்தோரே பேறுபெற்றோர்\n" +
                                    "அமைதி தருபவரே பேறுபெற்றோர்\n" +
                                    "நீதிக்காகத் துன்புறுவோர் பேறுபெற்றோர்\n" +
                                    "அவர்கள் இயேசுவின் குழுந்தைகளாய் பேறுபெற்றோர்\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 9) {
                            bean = new AudioVideoBean();
                            bean.setTitle("தூய ஆவியின் கனிகள் ஒன்பதாம்");
                            bean.setUrl(numberSongPath + "audio/09_THOOYA_AAVIYIN_MIX_MAS_TRK.mp3");
                            bean.setLyric("தூய ஆவியின் கனிகள் ஒன்பதாம்\n" +
                                    "அதன்படி வாழ்வது நல்லதாம் - 2\n" +
                                    "\n" +
                                    "அன்பே ஆவியின் முதல் கனியாம்\n" +
                                    "நன்னயம் ஆவியின் இரண்டாம் கனியாம்\n" +
                                    "தன்னடக்கம் ஆவியின் மூன்றாம் கனியாம்\n" +
                                    "மகிழ்ச்சி ஆவியின் நான்காம் கனியாம்\n" +
                                    "பரிவு ஆவியின் ஐந்தாம் கனியாம் - 2\n" +
                                    "\n" +
                                    "கனிவு ஆவியின் ஆறாம் கனியாம்\n" +
                                    "அமைதி ஆவியின் ஏழாம் கனியாம்\n" +
                                    "பொறுமை ஆவியின் எட்டாம் கனியாம்\n" +
                                    "நம்பிக்கை ஆவியின் ஒன்பதாம் கனியாம்\n" +
                                    "இவைகள் ஆவியின் நல்ல கனிகளாம் – 2\n");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 10) {
                            bean = new AudioVideoBean();
                            bean.setTitle("கடவுளின் கட்டளைகள் பத்து");
                            bean.setUrl(numberSongPath + "audio/10_KADAVULIN_KATTALAIGAL_MIX_MAS_TRK.mp3");
                            bean.setLyric("கடவுளின் கட்டளைகள் பத்து\n" +
                                    "அத்தனையும் கடவுள் தந்த சொத்து - 2\n" +
                                    "\n" +
                                    "நமது கடவுள் ஒருவரே முதல் கட்டளை\n" +
                                    "அவர்பெயர் வீணாக சொல்லாதே இரண்டாம் கட்டளை - 2\n" +
                                    "\n" +
                                    "தூய நாட்களை அனுசரி மூன்றாம் கட்டளை\n" +
                                    "பெற்றோரை மதித்திடு நான்காம் கட்டளை\n" +
                                    "கொலைகள் செய்வது பாவம் இது ஐந்தாம் கட்டளை - 2\n" +
                                    "\n" +
                                    "மோக பாவம் குற்றம் இது ஆறாம் கட்டளை\n" +
                                    "களவு செய்வது பாவம் இது ஏழாம் கட்டளை - 2\n" +
                                    "பொய்கள் சொல்வது பாவம் இது எட்டாம் கட்டளை\n" +
                                    "பிறர் தாரம் விரும்பாதே ஒன்பதாம் கட்டளை\n" +
                                    "பிறர் உடைமையை விரும்பாதே பத்தாம் கட்டளை - 2\n");
                            videoViewModelsList.add(bean);
                        }
                        break;
                }
                break;
            case 1:
                switch (position) {
                    case 1:
                        bean = new AudioVideoBean();
                        bean.setTitle("இயேசப்பா எந்தன் இயேசப்பா!");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/01.EsappaEnthanEsappa.mp3");
                        bean.setLyric("இயேசப்பா எந்தன் இயேசப்பா - 2\n" +
                                "ஒரு கதை ஒன்னு சொல்லுங்கப்பா இயேசப்பா\n" +
                                "மூவொரு கடவுள் கதைய சொல்லுங்கப்பா இயேசப்பா – 2\n" +
                                "\n" +
                                "தந்தை கடவுள் படைத்தவராம்\n" +
                                "இயேசு கடவுள் மீட்பவராம்\n" +
                                "தூய ஆவியார் காப்பவராம்\n" +
                                "மூவொரு கடவுள் வாழ்பவராம்\n" +
                                "\n" +
                                "மூவரின் வாழ்வும் கதையல்ல\n" +
                                "மூவரும் நமது வாழ்வாமே\n" +
                                "அன்பும் ஒற்றுமையும் பகிர்வும் தான்\n" +
                                "மூவொரு கடவுளின் இயல்பாமே\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new AudioVideoBean();
                        bean.setTitle("ஒன்னு ரெண்டு மூனு நாலு ஐந்து");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/02.OnnuRenduMoonu.mp3");
                        bean.setLyric("ஒன்னு ரெண்டு மூனு நாலு ஐந்து\n" +
                                "ஆறு நாளில் கடவுள் உலகைப் படைத்து\n" +
                                "உனக்கும் எனக்கும் கொடுத்தாரே\n" +
                                "ஆ ஹ ஆ ஹ ஹா – 4\n" +
                                "\n" +
                                "ஒன்றாம் நாளில் - ஒளியும் இருளும் படைத்தார்\n" +
                                "இரண்டாம் நாளில் - வானம் பூமி படைத்தார்\n" +
                                "மூன்றாம் நாளில் - நீரும் நிலமும் படைத்தார்\n" +
                                "நான்காம் நாளில் - சூரியக் குடும்பம் படைத்தார்\n" +
                                "\n" +
                                "ஐந்தாம் நாளில் - பாம்பு பறவை படைத்தார்\n" +
                                "ஆறாம் நாளில் - ஆணும் பெண்ணும் படைத்தார்\n" +
                                "ஏழாம் நாளில் ஆசிர் பொழிந்தார்\n" +
                                "படைப்பின் முதல்வனை வணங்கிடுவோம்\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new AudioVideoBean();
                        bean.setTitle("செல்லப்பிள்ளை நான் செல்லப்பிள்ளை");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/03.ChellaPillai.mp3");
                        bean.setLyric("செல்லப்பிள்ளை நான் செல்லப்பிள்ளை\n" +
                                "இயேசுவுக்கும் அன்னைக்கும் செல்லப்பிள்ளை\n" +
                                "செல்லப்பிள்ளை நான் செல்லப்பிள்ளை\n" +
                                "இயேசுவுக்குக் கீழ்ப்படியும் செல்லப்பிள்ளை\n" +
                                "\n" +
                                "கீழ்ப்படிந்ததாலே மரியா தாயுமானார்\n" +
                                "கீழ்ப்படிந்ததாலே சூசை தந்தையானார் - 2\n" +
                                "கீழ்ப்படிந்ததாலே இயேசு மீட்பரானார்\n" +
                                "கீழ்ப்படிவேன் - 3\n" +
                                "கீழ்ப்படிவேன் கடவுளுக்குக் கீழ்ப்படிவேன்\n" +
                                "கீழ்ப்படிவேன் பெற்றோருக்குக் கீழ்ப்படிவேன்\n" +
                                "\n" +
                                "அப்பா அம்மா பேச்ச தட்டாம கேட்பேன்\n" +
                                "பெரியவர்கள் பேச்ச நிச்சயமா கேட்பேன் - 2\n" +
                                "மனசாட்சி குரல அமைதியில கேட்பேன்\n" +
                                "கீழ்ப்படிவேன் - 3\n" +
                                "கீழ்ப்படிவேன் கடவுளுக்குக் கீழ்ப்படிவேன்\n" +
                                "கீழ்ப்படிவேன் பெற்றோருக்குக் கீழ்ப்படிவேன்\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new AudioVideoBean();
                        bean.setTitle("அன்பு தெய்வம் இயேசுவே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/04.AnbuDeivamYeasuve.mp3");
                        bean.setLyric("அன்பு தெய்வம் இயேசுவே - என்\n" +
                                "அழகு தெய்வம் இயேசுவே\n" +
                                "இயற்கை என்னும் அதிசயத்தைப்\n" +
                                "பாதுகாக்கும் தெய்வமே\n" +
                                "\n" +
                                "நல்ல நல்ல போதனைகள்\n" +
                                "இயற்கை மூலம் தந்தீரே\n" +
                                "இயற்கை போல என்னையுமே\n" +
                                "மகிழ்வாய் வாழச் சொன்னீரே – 2\n" +
                                "\n" +
                                "நல்ல மரமாய் வாழ்வேனே\n" +
                                "நல்ல கனிகள் கொடுப்பேனே\n" +
                                "நல்ல நிலமாய் மாறி நான்\n" +
                                "இந்த உலகைக் காப்பேனே - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new AudioVideoBean();
                        bean.setTitle("நம்பிடுவேனே நான் நம்பிடுவேனே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/05.Nambiduveane.mp3");
                        bean.setLyric("நம்பிடுவேனே நான் நம்பிடுவேனே\n" +
                                "நல்லவர் இயேசுவை நம்பிடுவேனே\n" +
                                "வணங்கிடுவேன் நான் வணங்கிடுவேன்\n" +
                                "வல்லவர் இயேசுவை வணங்கிடுவேன் – 2\n" +
                                "\n" +
                                "நூற்றுவர் தலைவர் நம்பியதால்\n" +
                                "பணியாளர் உடனே குணமடைந்தார்\n" +
                                "நானும் இயேசுவை நம்பிவிட்டால்\n" +
                                "நாளும் அதிசயம் கண்டிடுவேன்\n" +
                                "\n" +
                                "நம்பினோர் அனைவரும் நலமடைந்தார்\n" +
                                "நம்பினோர் அனைவரும் பலமடைந்தார்\n" +
                                "இயேசுவை என்றும் நம்பிடுவேன்\n" +
                                "நாளும் நலமாய் வாழ்ந்திடுவேன்\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new AudioVideoBean();
                        bean.setTitle("எங்கக் கூட இயேசு உண்டு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/06.EngaKooda.mp3");
                        bean.setLyric("எங்கக் கூட இயேசு உண்டு\n" +
                                "நாங்க எதற்கு பயப்படனும்\n" +
                                "எங்கக் கூட இயேசு உண்டு\n" +
                                "நாங்க எதற்கு துக்கப்படனும்\n" +
                                "பூமி குலுங்கட்டும் ஆகாயம் வீழட்டும்\n" +
                                "நாங்க எதற்கு பயந்து ஓடனும் - 2\n" +
                                "லால லல்லா லா – 2\n" +
                                "\n" +
                                "எங்கக் கூட இயேசு உண்டு\n" +
                                "பறவைகள் போல பறந்திடுவோம்\n" +
                                "எங்கக் கூட இயேசு உண்டு\n" +
                                "மலர்களைப் போல மலர்ந்திடுவோம்\n" +
                                "கவலைகள் மாறட்டும் கலக்கங்கள் தீரட்டும்\n" +
                                "இயேசுவோடு மகிழ்ந்திருப்போம் - 2\n" +
                                "லால லல்லா லா - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new AudioVideoBean();
                        bean.setTitle("அம்மாவப் போல எனக்கு தாலாட்டுப் பாட");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/07.AmmavaPola.mp3");
                        bean.setLyric("அம்மாவப் போல எனக்கு தாலாட்டுப் பாட\n" +
                                "அப்பாவப் போல என்ன தோளில் சுமந்து போக - 2\n" +
                                "இயேசு இயேசு இயேசு இருக்கிறார் - 2\n" +
                                "இயேசு இருக்கிறார் நான் பயப்பட மாட்டேன் – 2\n" +
                                "\n" +
                                "அண்ணன் தம்பி போல என்ன அன்பு செ ய்கிறார்\n" +
                                "ஆச முத்தம் தந்து எனக்கு ஆசி கொடுக்கிறார் - 2\n" +
                                "இயேசு…...\n" +
                                "\n" +
                                "அக்கா தங்க போல என்ன அன்பு செய்கிறார்\n" +
                                "பாசத்தோடு பழகி எனக்கு ஆசி கொடுக்கிறார் - 2\n" +
                                "இயேசு…...\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new AudioVideoBean();
                        bean.setTitle("ஒரு நாள் இயேசு மலைமேலே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/08.OruNaalYesuMalaiMele.mp3");
                        bean.setLyric("ஒரு நாள் இயேசு மலைமேலே\n" +
                                "மக்கள் கூட்டம் நடுவினிலே - 2\n" +
                                "தொலைந்தே போன மகனின் கதையை\n" +
                                "கருத்தாய்ச் சொல்லி மகிழ்ந்தாரே – 2\n" +
                                "\n" +
                                "தீமை செய்தான் இளைய மகன்\n" +
                                "நன்மை செய்தார் அவன் தந்தை - 2\n" +
                                "தவற்றை உணர்ந்து திரும்பி வந்தான்\n" +
                                "மகிழ்வுடன் தந்தை ஏற்றாரே – 2\n" +
                                "\n" +
                                "நன்மைகள் செய்யும் இயேசுவைப் போல்\n" +
                                "மன்னித்து ஏற்ற தந்தையைப் போல் - 2\n" +
                                "தீமைக்குத் தீமை செய்யாமல்\n" +
                                "நன்மையால் தீமையை வெல்வோமே – 2\n");
                        videoViewModelsList.add(bean);
                        break;
                }
                break;
            case 2:
                switch (position) {
                    case 1:
                        bean = new AudioVideoBean();
                        bean.setTitle("சின்ன பாப்பா மனசுக்குள்ள");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class2audio/11.ChinnaPaapaaManasukulla.mp3");
                        bean.setLyric("சின்ன பாப்பா மனசுக்குள்ள\n" +
                                "பெரிய ஆசை ஒன்னிருக்கு\n" +
                                "என்ன தெரியுமா? சொல்லுங்க பார்ப்போம்\n" +
                                "இயேசு உங்க கைபிடிச்சு\n" +
                                "நடக்கும் ஆசை எனக்கிருக்கு - 2\n" +
                                "அப்படியா! சொல்லவே இல்லை\n" +
                                "ஆசை ரொம்ப இருக்குது\n" +
                                "மனசுக்குள்ள இருக்குது – 2\n" +
                                "\n" +
                                "ஏழைகள் கூட நடந்தீங்க\n" +
                                "என் மனச நீங்க தொட்டிங்க\n" +
                                "பாவிகள் கூட நடந்தீங்க\n" +
                                "பரிவாய் வாழச் சொன்னிங்க – 2\n" +
                                "\n" +
                                "ஏழைகள் கூட நடப்பேனே\n" +
                                "எளிய உதவிகள் செய்வேனே\n" +
                                "உங்க கூட நான் தினம் நடந்து\n" +
                                "உமதருளை பெற்றிடுவேனே - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new AudioVideoBean();
                        bean.setTitle("நல்ல கடவுள் இயேசப்பா");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class2audio/12.NallaKadavulEasappa.mp3");
                        bean.setLyric("நல்ல கடவுள் இயேசப்பா\n" +
                                "நன்மைகளின் ஊற்றப்பா\n" +
                                "தடைகள் பல வந்தாலும்\n" +
                                "தாண்டி செல்வேன் இயேசப்பா – 2\n" +
                                "\n" +
                                "ஓய்வு நாள் தான் முக்கியம்\n" +
                                "குணமளிக்கக் கூடாது\n" +
                                "என்று சொன்ன மனிதரை\n" +
                                "கண்டு இயேசு வருந்தினார் – 2\n" +
                                "\n" +
                                "கனிவு கொண்ட இயேசுவோ\n" +
                                "சூம்பிய கை மனிதனை\n" +
                                "கையை நீட்டு என்றாரே\n" +
                                "குணமளித்துச் சென்றாரே – 2\n" +
                                "\n" +
                                "தடைகள் பல வந்தாலும்\n" +
                                "மனிதர் தீமை செய்தாலும்\n" +
                                "நன்மை மட்டும் செய்வேனே\n" +
                                "இயேசு போல வாழ்வேனே - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new AudioVideoBean();
                        bean.setTitle("குட்டி பாப்பா குட்டி பாப்பா ஓடி வாங்க");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class2audio/13.KuttiPaappaa.mp3");
                        bean.setLyric("குட்டி பாப்பா குட்டி பாப்பா ஓடி வாங்க\n" +
                                "இயேசப்பா அழைக்கிறாரு பாடி வாங்க\n" +
                                "சுட்டி பாப்பா சுட்டி பாப்பா ஓடி வாங்க\n" +
                                "இயேசப்பா விரும்புறாரு ஆடி வாங்க – 2\n" +
                                "\n" +
                                "அறிவில் வளர ஓடி வாங்க\n" +
                                "ஞானத்தில் வளர பாடி வாங்க\n" +
                                "பெற்றோருக்கு உதவ ஓடி வாங்க\n" +
                                "இயேசுவின் பிள்ளையாடீநு ஆடி வாங்க\n" +
                                "\n" +
                                "இயேசுவின் குழந்த யாரு யாரு?\n" +
                                "அவர்சொல் கேட்கும் குழந்த தானே\n" +
                                "நல்லா செபித்தால் என்ன பிடிக்கும்\n" +
                                "நல்லது செய்தால் உன்ன பிடிக்கும்\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new AudioVideoBean();
                        bean.setTitle("வந்தாரய்யா வந்தாரு இயேசு சாமி வந்தாரு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class2audio/14.VanthaaraiyaVanthaaru.mp3");
                        bean.setLyric("வந்தாரய்யா வந்தாரு இயேசு சாமி வந்தாரு\n" +
                                "நோயும் பேயும் ஓட்டிட தொழுகை கூடம் வந்தாரு\n" +
                                "நல்லவராம் இயேசுசாமி நன்மை செய்ய வந்தாரு\n" +
                                "நலமெல்லாம் நமை சூழ நாளும் வரங்கள் தந்தாரு – 2\n" +
                                "\n" +
                                "மக்கள் கூட்டம் சூழ்ந்திட பேதுருவும் வந்தாரு\n" +
                                "மாமியாரின் காய்ச்சல குணப்படுத்தக் கேட்டாரு - 2\n" +
                                "பேதுருவின் இல்லத்திற்கு இயேசு சாமி போனாரு - 2\n" +
                                "கைய தூக்கி நிறுத்தவே காய்ச்சல் பறந்து போனதே\n" +
                                "\n" +
                                "வயது முதிர்ந்த பெரியோரை வாடிநநாளில் மதிக்கனும்\n" +
                                "வாழும் வரை உதவனும் நற்குணத்தில் வளரனும் - 2\n" +
                                "பெற்றோரை காக்கனும் பெருமை பேச வாழனும் - 2\n" +
                                "ரூத்து நகோமி போலவே ஆசி பெற்று வாழனும்\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new AudioVideoBean();
                        bean.setTitle(" செபிக்கக் கற்றுத் தாரும் இயேசுவே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class2audio/15.JebikkaKatrutharum.mp3");
                        bean.setLyric("செபிக்கக் கற்றுத் தாரும் இயேசுவே (2) - எனக்கு\n" +
                                "செபிக்கக் கற்றுத் தாரும் இயேசுவே – 2\n" +
                                "\n" +
                                "கையைக் கூப்பி நான் செபிக்கனுமே\n" +
                                "முட்டிப் போட்டு நானும் செபிக்கனுமே - 2\n" +
                                "கண்ணை மூடி செபிக்கனுமே - 2\n" +
                                "கற்றுத் தாங்க இயேசுவே - 2\n" +
                                "செபிக்கனும் - 2 செபிக்கனுமே – 4\n" +
                                "\n" +
                                "காலையில் நானும் செபிக்கனுமே\n" +
                                "உம் புகழை தினமும் பாடனுமே - 2\n" +
                                "மாலையில் நான் செபிக்கனுமே - 2\n" +
                                "நன்றி சொல்லி மகிழனுமே - 2\n" +
                                "செபிக்கனும் - 2 செபிக்கனுமே - 4\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new AudioVideoBean();
                        bean.setTitle("கவலை எதுவும் இல்லை எனக்கு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class2audio/16.KavalaiEthuvumIllai.mp3");
                        bean.setLyric("கவலை எதுவும் இல்லை எனக்கு\n" +
                                "இயேசு என்னோட இருக்கையிலே\n" +
                                "துன்பம் எதுவும் இல்லை எனக்கு\n" +
                                "இயேசு என்னோட நடக்கையிலே\n" +
                                "\n" +
                                "இன்பத்திலும் என்னோடு இயேசு\n" +
                                "துன்பத்திலும் என்மேல பாசம் - 2\n" +
                                "இயேசப்பா நீங்க இருக்கையிலே\n" +
                                "பயமில்லையே எனக்குக் குறையில்லையே – 2\n" +
                                "\n" +
                                "மகிடிநந்திடுவேன் நான் என்றும்\n" +
                                "அவர் தினம் என்னோடு இருக்க - 2\n" +
                                "சந்தோசமே எனக்கு சந்தோசமே\n" +
                                "நம்பிக்கையிலே நாளும் வளர்ந்திடுவேன் - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new AudioVideoBean();
                        bean.setTitle("நல்ல பையன் நானுங்க");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class2audio/17.NallaPayanNaanunga.mp3");
                        bean.setLyric("நல்ல பையன் நானுங்க\n" +
                                "நல்ல பொண்ணு நானுங்க\n" +
                                "நல்லவராம் இயேசுவ\n" +
                                "நம்பி வந்தோம் பாருங்க – 2\n" +
                                "\n" +
                                "உணவு கொடுக்க சொன்னாரு - மக்கள்\n" +
                                "பசியைப் போக்கச் சொன்னாரு\n" +
                                "சிறுவன் கொடுத்த அப்பத்த - அவர்\n" +
                                "பலுகிப் பெருகச் செய்தாரு – 2\n" +
                                "\n" +
                                "விவிலியத்தில் சொன்ன அந்த\n" +
                                "நல்ல சிறுமி/சிறுவன் நானுங்க\n" +
                                "கொடுப்பதில் தான் மகிடிநச்சி இருக்கு\n" +
                                "இயேசு சொன்னார் கேளுங்க - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new AudioVideoBean();
                        bean.setTitle("சின்ன பாப்பா தூக்கமா?");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class2audio/18.ChinnaPaapaaThookkama.mp3");
                        bean.setLyric("சின்ன பாப்பா தூக்கமா?\n" +
                                "கண்ண முழிச்சி பாரம்மா\n" +
                                "அன்பு இயேசு வெளியிலே\n" +
                                "காத்திருக்கார் தெரியுமா? - 2\n" +
                                "சின்ன பாப்பா தூக்கமா?\n" +
                                "\n" +
                                "இயேசு உன்னை அழைக்கிறார்\n" +
                                "காது உனக்கு கேட்குதா?\n" +
                                "இயேசு உன்னைப் பார்க்கிறார்\n" +
                                "கண்ணு உனக்குத் தெரியுதா? - 2\n" +
                                "என் கண்ணே உனக்குத் தெரியுதா?\n" +
                                "\n" +
                                "காது நல்லா கேக்குது\n" +
                                "இயேசு பேச்ச கேட்பேனே\n" +
                                "கண்ணு நல்லா தெரியுது\n" +
                                "இயேசு முகத்த பார்ப்பேனே - 2\n" +
                                "நான் இயேசு முகத்த பார்ப்பேனே\n");
                        videoViewModelsList.add(bean);
                        break;
                }
                break;
            case 3:
                switch (position) {
                    case 1:
                        bean = new AudioVideoBean();
                        bean.setTitle("எங்களோட இயேசு நல்லவர்");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class3audio/21.EngalodaEasu.mp3");
                        bean.setLyric("எங்களோட இயேசு நல்லவர்\n" +
                                "எங்களோட இயேசு வல்லவர் - 2\n" +
                                "மனம் வருந்தும் குழந்தைகளை\n" +
                                "வாரி எடுத்து அணைப்பவர் – 2\n" +
                                "\n" +
                                "தவறு செய்தால் வருந்துவார்\n" +
                                "அன்பாய் நம்மை திருத்துவார் - 2\n" +
                                "செய்த தவற்றை உணர்ந்தால் - நம்\n" +
                                "வாடிநவை மாற்றி காட்டுவார் – 2\n" +
                                "\n" +
                                "இயேசுவின் செல்லப் பிள்ளையாய்\n" +
                                "தவற்றை திருத்தி வாழுவேன் - 2\n" +
                                "இயேசு வியக்கும் குழந்தையாய் - என்\n" +
                                "வாழ்வை மாற்றி வளருவேன் – 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new AudioVideoBean();
                        bean.setTitle("கடவுள் பயம் உனக்கிருந்தால் வா வா வா");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class3audio/22.KadavulBayam.mp3");
                        bean.setLyric("கடவுள் பயம் உனக்கிருந்தால் வா வா வா\n" +
                                "கீழ்ப்படிதல் உனக்கிருந்தால் வா வா வா\n" +
                                "எளிய மனம் உனக்கிருந்தால் வா வா வா\n" +
                                "அன்பு இயேசு அழைக்கிறார் வா வா வா\n" +
                                "\n" +
                                "தாவீது போல எளிமை கொண்டால்\n" +
                                "இயேசு உன்னை உயர்த்திடுவார்\n" +
                                "பேதுரு போல பணிவிருந்தால்\n" +
                                "இயேசு உன்னை உயர்த்துவார்\n" +
                                "\n" +
                                "அன்பு பண்பு பணிவு கொண்ட\n" +
                                "பேதுரு போல வாழ்ந்திடுவேன்\n" +
                                "எளிமையோடு நான் வாழ்ந்தால்\n" +
                                "இயேசு என்னை உயர்த்துவார்\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new AudioVideoBean();
                        bean.setTitle("சின்னஞ்சிறு பிள்ளைகளே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class3audio/23.ChinnanChiruPillaigale.mp3");
                        bean.setLyric("சின்னஞ்சிறு பிள்ளைகளே\n" +
                                "என்ன வேணும் சொல்லுங்களே\n" +
                                "இயேசு உன்னை அழைக்கிறார்\n" +
                                "ஓடி வந்து நில்லுங்களே\n" +
                                "\n" +
                                "பணம் உனக்கு வேணுமா? வேணாம் - 4\n" +
                                "குணம் உனக்கு வேணுமா? ஆமாம் - 4\n" +
                                "வானம் உனக்கு வேணுமா? வேணாம் - 4\n" +
                                "ஞானம் உனக்கு வேணுமா? ஆமாம் – 4\n" +
                                "\n" +
                                "பொருள் உனக்கு வேணுமா? வேணாம் - 4\n" +
                                "அருள் உனக்கு வேணுமா? ஆமாம் - 4\n" +
                                "புகழ் ஆசை வேணுமா? வேணாம் - 4\n" +
                                "இறையாசிர் வேணுமா? ஆமாம் - 4\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new AudioVideoBean();
                        bean.setTitle("அன்பு இருக்கா உனக்கு அன்பு இருக்கா?");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class3audio/24.AnbuIrukkaa.mp3");
                        bean.setLyric("அன்பு இருக்கா உனக்கு அன்பு இருக்கா?\n" +
                                "நல்ல தெய்வம் இயேசு மேல அன்பு இருக்கா?\n" +
                                "ஆசை இருக்கா உனக்கு ஆசை இருக்கா?\n" +
                                "இயேசு போல வாழ உனக்கு ஆசை இருக்கா?\n" +
                                "\n" +
                                "சத்தமாக உண்மை சொன்னார் இயேசு\n" +
                                "சுத்தமான மனசு கொண்டார் இயேசு\n" +
                                "துணிந்து நின்று தடைகள் வென்றார் இயேசு\n" +
                                "நன்மை செய்து மகிழ்ந்திருந்தார் இயேசு\n" +
                                "\n" +
                                "உண்மை பேச ஆசையிருக்கு இயேசுவே\n" +
                                "மனசுக்குள்ள பயமிருக்கு இயேசுவே\n" +
                                "நன்மை செய்ய மனசிருக்கு இயேசுவே\n" +
                                "துணிவு கொஞ்சம் தந்திடுங்க இயேசுவே\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new AudioVideoBean();
                        bean.setTitle("இயேசு அன்பு வேணுமா?");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class3audio/25.YeasuAnbuVeanuma.mp3");
                        bean.setLyric("இயேசு அன்பு வேணுமா?\n" +
                                "செபம் செய்யப் பழகு\n" +
                                "இயேசு ஆசி வேணுமா?\n" +
                                "செபம் செய்யப் பழகு\n" +
                                "அம்மா அப்பா மகிழவே\n" +
                                "செபம் செய்யப் பழகு\n" +
                                "சும்மா இருக்கும் நேரத்திலும்\n" +
                                "செபம் செய்யப் பழகு\n" +
                                "செபம் செய்யப் பழகு நீ செபம் செய்யப் பழகு – 4\n" +
                                "\n" +
                                "புதுமை காண வேணுமா?\n" +
                                "செபம் செய்யப் பழகு\n" +
                                "வளமை உனக்கு வேணுமா?\n" +
                                "செபம் செய்யப் பழகு\n" +
                                "நோய்கள் தீர வேணுமா?\n" +
                                "செபம் செய்யப் பழகு\n" +
                                "பேய்கள் ஓட வேணுமா?\n" +
                                "செபம் செய்யப் பழகு\n" +
                                "செபம் செய்யப் பழகு நீ செபம் செய்யப் பழகு - 4\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new AudioVideoBean();
                        bean.setTitle("படி படி படி படி கட்டளைகள் நீ படி");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class3audio/26.PadiPadi.mp3");
                        bean.setLyric("படி படி படி படி\n" +
                                "கட்டளைகள் நீ படி\n" +
                                "பிடி பிடி பிடி பிடி\n" +
                                "அத்தனையும் கடைப்பிடி\n" +
                                "துடி துடி துடி துடி\n" +
                                "அன்பு செய்ய நீ துடி\n" +
                                "முடி முடி முடி முடி\n" +
                                "கடமைகள் நீ முடி\n" +
                                "\n" +
                                "கடவுள் அன்பே முதல் படி\n" +
                                "நாளும் அதனை கடைப்பிடி - 2\n" +
                                "மனிதர் அன்பே உன் வழி - 2\n" +
                                "இயேசுவின் பிள்ளையென நீ அறி - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new AudioVideoBean();
                        bean.setTitle("சின்ன சின்ன பட்டாம்பூச்சி");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class3audio/27.ChinnaChinnaPattampoochi.mp3");
                        bean.setLyric("சின்ன சின்ன பட்டாம்பூச்சி\n" +
                                "சேதி உனக்குத் தெரியுமா?\n" +
                                "அன்பு இயேசு அழைக்கிறார்\n" +
                                "வேகமாகப் பறந்து வா – 2\n" +
                                "\n" +
                                "மலரைப் போல மணம் வீசும்\n" +
                                "இயேசு உன்னை அழைக்கிறார்\n" +
                                "அவரைத் தேடி ஓடிவந்தால்\n" +
                                "ஆசி உனக்கு அருளுவார்\n" +
                                "\n" +
                                "அங்கும் இங்கும் ஓடமாட்டேன்\n" +
                                "எந்தன் அழகு இயேசுவே\n" +
                                "உம்மை நாடி ஓடி வந்தால்\n" +
                                "எந்தன் வாடிநவு ஒளிருமே\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new AudioVideoBean();
                        bean.setTitle("உன்னை அன்பு செய்யும் கடவுள் யார் தெரியுமா?");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class3audio/28.UnnaiAnbuSeiyumKadavul.mp3");
                        bean.setLyric("உன்னை அன்பு செய்யும் கடவுள்\n" +
                                "யார் தெரியுமா? - 2\n" +
                                "என்னை அன்பு செய்யும் கடவுள்\n" +
                                "இயேசு தெய்வமே – 2\n" +
                                "\n" +
                                "உனக்கு நீதி வழங்கும் தெய்வம்\n" +
                                "யார் தெரியுமா? - 2\n" +
                                "எனக்கு நீதி வழங்கும் கடவுள்\n" +
                                "இயேசு தெய்வமே – 2\n" +
                                "\n" +
                                "உனக்கு அமைதி அளிக்கும் கடவுள்\n" +
                                "யார் தெரியுமா? - 2\n" +
                                "எனக்கு அமைதி அளிக்கும் கடவுள்\n" +
                                "இயேசு தெய்வமே – 2\n" +
                                "\n" +
                                "உனக்கு உயிர் கொடுத்த கடவுள்\n" +
                                "யார் தெரியுமா? - 2\n" +
                                "எனக்கு உயிர் கொடுத்த கடவுள்\n" +
                                "இயேசு தெய்வமே - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                }
                break;
            case 4:
                switch (position) {
                    case 1:
                        bean = new AudioVideoBean();
                        bean.setTitle("கொடைகளின் ஆண்டவர் இயேசு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class4audio/41.KodaigalinAandavarYesu.mp3");
                        bean.setLyric("கொடைகளின் ஆண்டவர் இயேசு\n" +
                                "கொடுப்பதில் வல்லவர் இயேசு - 2\n" +
                                "இருப்பதைக் கொடுத்தால் இன்பம்\n" +
                                "இயேசுவே நமது செல்வம் – 2\n" +
                                "\n" +
                                "இருப்பதைக் கைம்பெண் கொடுத்தார்\n" +
                                "இறையருள் பெற்றே சென்றார் - 2\n" +
                                "உள்ளதை உவப்புடன் கொடுத்தால்\n" +
                                "இறைமகன் இயேசு மகிடிநவார்\n" +
                                "\n" +
                                "இருப்பதை மகிழ்வுடன் கொடுப்பேன்\n" +
                                "இறையருள் பெற்றே மகிழ்வேன் - 2\n" +
                                "இன்முகத்துடனே கொடுப்பேன்\n" +
                                "இயேசுவின் பிள்ளையாய் வாழ்வேன்\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new AudioVideoBean();
                        bean.setTitle("இனிமையான இயேசுவே இரக்கம் கொண்ட தெய்வமே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class4audio/42.InimaiyanaEasuve.mp3");
                        bean.setLyric("இனிமையான இயேசுவே\n" +
                                "இரக்கம் கொண்ட தெய்வமே\n" +
                                "எனது சின்ன இதயத்தில்\n" +
                                "இறங்கி வந்து தங்குமே – 2\n" +
                                "\n" +
                                "பலிகள் தேவை இல்லையே\n" +
                                "இரக்கம் மட்டும் போதுமே\n" +
                                "இறைத் தந்தை போலவே\n" +
                                "வாழச் சொன்ன தெய்வமே – 2\n" +
                                "\n" +
                                "எனது வாடிநவில் உம்மைப் போல்\n" +
                                "எளியவர்க்கு உதவுவேன்\n" +
                                "இதயத்திலே கனிவு கொண்டு\n" +
                                "இரக்கத்தோடு வாழுவேன் - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new AudioVideoBean();
                        bean.setTitle("தெய்வம் தந்த இந்த வாழ்க்கையை");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class4audio/43.DeivamThankthaIntha.mp3");
                        bean.setLyric("தெய்வம் தந்த இந்த வாழ்க்கையை\n" +
                                "எறிந்தெறிந் தெறிந்தெறிந் துடைக்காதே\n" +
                                "தெய்வம் தந்த திறமைகள் எல்லாம்\n" +
                                "குழிதோண்டி குழிதோண்டிப் புதைக்காதே\n" +
                                "தெய்வம் தந்த உடல்நலமும்\n" +
                                "தெய்வம் தந்த நேரங்களும்\n" +
                                "இறையாட்சிப் பணிக்காகச் செலுத்திடுவோம் - 2\n" +
                                "தாம் தின தின தின்னா தின்னா தாம் தின தின தின்னா – 2\n" +
                                "\n" +
                                "இயேசுவைப் போல ஏழையர்க்கு\n" +
                                "அன்போடும் பண்போடும் உதவிடுவேன்\n" +
                                "இயேசுவைப் போல வறியவர்க்குக்\n" +
                                "கனிவோடும் துணிவோடும் உழைத்திடுவேன்\n" +
                                "ஒடுக்கப்பட்டோர் எழுந்திடவும்\n" +
                                "தாடிநத்தப்பட்டோர் உயர்ந்திடவும்\n" +
                                "சமத்துவ உலகத்தை உருவாக்குவேன் - 2\n" +
                                "தாம் தின தின தின்னா தின்னா தாம் தின தின தின்னா - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new AudioVideoBean();
                        bean.setTitle("நன்றி நன்றி நன்றி இயேசுவே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class4audio/44.NandriNandriNandri.mp3");
                        bean.setLyric("நன்றி நன்றி நன்றி இயேசுவே\n" +
                                "நன்றி நன்றி நன்றி மீட்பரே\n" +
                                "\n" +
                                "பெயரைச் சொல்லி அழைச்சீங்க - நன்றி இயேசுவே\n" +
                                "உம் பேரன்பால் நிறைச்சீங்க - நன்றி இயேசுவே\n" +
                                "உள்ளங்கையில் பொறிச்சீங்க - நன்றி இயேசுவே\n" +
                                "உம் பிள்ளை நான்னு சொன்னீங்க - நன்றி இயேசுவே\n" +
                                "\n" +
                                "என்னை அன்பு செய்றீங்க - நன்றி இயேசுவே\n" +
                                "என்றும் என்னுள் இருக்கீங்க - நன்றி இயேசுவே\n" +
                                "உமது பணியை கொடுத்தீங்க - நன்றி இயேசுவே\n" +
                                "உமக்காய் என்றும் வாழ்ந்திடுவேன் - நன்றி இயேசுவே\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new AudioVideoBean();
                        bean.setTitle("எங்க இயேசு நல்ல ஆயன் தானே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class4audio/45.EngaEasuNallaAayan.mp3");
                        bean.setLyric("எங்க இயேசு நல்ல ஆயன் தானே\n" +
                                "நம்ம இயேசு அன்பு தெய்வம் தானே\n" +
                                "அவர் பின்னே நாமும் ஓடிச் சென்றால்\n" +
                                "வாரி எடுத்து அணைத்திடுவார் தானே – 2\n" +
                                "\n" +
                                "தூங்கும் போதும் காத்திடுவார் இயேசு\n" +
                                "விழிக்கும் போதும் பக்கம் நிற்பார் இயேசு - 2\n" +
                                "படிக்கும் போது உதவி செய்வார் இயேசு\n" +
                                "முடிக்கும் போது உடனிருப்பார் இயேசு – 2\n" +
                                "\n" +
                                "செல்லமான ஆட்டுக்குட்டி நானே\n" +
                                "அவரை விட்டுப் பிரிய மாட்டேன் தானே - 2\n" +
                                "அங்கும் இங்கும் ஓட மாட்டேன் நானே\n" +
                                "அவரைப் பற்றிப் பிடித்துக் கொள்வேன் நானே - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new AudioVideoBean();
                        bean.setTitle("பயப்பட மாட்டேன் நான் பயப்பட மாட்டேன்");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class4audio/46.BayappadaMaatean.mp3");
                        bean.setLyric("பயப்பட மாட்டேன் நான் பயப்பட மாட்டேன்\n" +
                                "என் இயேசு அரணாய் இருக்க பயப்பட மாட்டேன்\n" +
                                "கலங்கிட மாட்டேன் நான் கலங்கிட மாட்டேன்\n" +
                                "என் கடவுள் துணையாய் நடக்கக் கலங்கிட மாட்டேன்\n" +
                                "\n" +
                                "சிங்கக் குகையில் விழுந்தாலும் பயப்படமாட்டேன்\n" +
                                "நெருப்புக் குழியில் விழுந்தாலும் பயப்படமாட்டேன்\n" +
                                "உண்மையோடு வாழும் போது இயேசு வருவார்\n" +
                                "பாசத்தோடு அள்ளி அணைத்து என்னைக் காப்பார்\n" +
                                "\n" +
                                "உண்மை நன்மை நேர்மை கொண்டு நானும் வாழுவேன்\n" +
                                "அன்பு இயேசு பாதுகாப்பைப் பெற்றுக் கொள்ளுவேன்\n" +
                                "தூய உள்ளம் கொண்டு வாழும் நல்லப் பிள்ளையாய்\n" +
                                "இயேசு வழியில் நான் நடந்து வெற்றிக் காணுவேன்\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new AudioVideoBean();
                        bean.setTitle("ரொம்ப பிடிக்கும் ஏழைகளை இயேசுவுக்கு ரொம்ப பிடிக்கும்");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class4audio/47.RombaPidikkum.mp3");
                        bean.setLyric("ரொம்பப் பிடிக்கும் ரொம்பப் பிடிக்கும்\n" +
                                "ஏழைகளை இயேசுவுக்கு ரொம்பப் பிடிக்கும் - 2\n" +
                                "எனக்குப் பிடிக்கும் எனக்குப் பிடிக்கும்\n" +
                                "ஏழை கடவுள் இயேசுவை ரொம்பப் பிடிக்கும்\n" +
                                "\n" +
                                "கைவிட மாட்டார் இயேசு கைவிட மாட்டார்\n" +
                                "ஏழைகளை ஒருநாளும் கைவிட மாட்டார்\n" +
                                "கொடுத்திடுவார் இயேசு கொடுத்திடுவார்\n" +
                                "விண்ணரசை ஏழையர்க்குக் கொடுத்திடுவார் – 2\n" +
                                "\n" +
                                "சின்னச் சின்ன உதவிகளை செய்திடுவேனே\n" +
                                "அன்பு இயேசு பிள்ளை என்று பெயரெடுப்பேனே\n" +
                                "நாளும் ஒரு உதவி செய்திடுவேன்\n" +
                                "விண்ணகத்தின் பரிசினைப் பெற்றிடுவேன் - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new AudioVideoBean();
                        bean.setTitle("இரக்கத்தோடு வாழச் சொன்னார் இயேசு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class4audio/48.IrakathooduVazhaChonnaar.mp3");
                        bean.setLyric("இரக்கத்தோடு வாழச் சொன்னார் இயேசு\n" +
                                "வாழ்ந்திடுவேன் அவர் வார்த்தையைக் கேட்டு\n" +
                                "இணக்கத்தோடு நடக்கச் சொன்னார் இயேசு\n" +
                                "நடந்திடுவேன் அவரைத் தினம் பார்த்து\n" +
                                "\n" +
                                "அரக்க மனம் கொண்ட மனிதர் நடுவில்\n" +
                                "நம்மை இரக்கத்தோடு வாழச் சொன்னார் இயேசு\n" +
                                "உறக்கமில்லா நல்ல இதயம் கொண்டு\n" +
                                "நம்மை உறவுகொண்டு வாழச் சொன்னார் இயேசு\n" +
                                "\n" +
                                "இரக்கம் என்னும் பண்பில் நானும் வளர்வேன்\n" +
                                "இறை இரக்கம் பெற்று நாளும் மகிழ்வேன்\n" +
                                "பரிவு உள்ளம் கொண்டு நாளும் வாழ்ந்து\n" +
                                "இயேசு போல இரக்கத்தோடு வாழ்வேன்\n");
                        videoViewModelsList.add(bean);
                        break;
                }
                break;
            case 5:
                switch (position) {
                    case 1:
                        bean = new AudioVideoBean();
                        bean.setTitle("புது வாழ்வு தரும் திருமுழுக்கு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class5audio/51.PuthuVazhvuTharum.mp3");
                        bean.setLyric("புதுவாழ்வு தரும் திருமுழுக்கு\n" +
                                "புண்ணியம் அருளும் ஒளி விளக்கு - 2\n" +
                                "புதுமைகள் செய்திடும் திருமுழுக்கு\n" +
                                "புனிதராய் மாற்றிடும் அருள்விளக்கு\n" +
                                "\n" +
                                "மூவொரு கடவுளின் குடும்பத்திலே\n" +
                                "குழந்தையாய் இணைத்திடும் திருமுழுக்கு - 2\n" +
                                "மீட்பைக் கொடையாய் வழங்கியே - நம்மை\n" +
                                "அணைத்திடும் அற்புத அகல்விளக்கு\n" +
                                "\n" +
                                "பாவங்கள் அழித்திடும் திருமுழுக்கு\n" +
                                "பாரங்கள் அகற்றிடும் திருமுழுக்கு - 2\n" +
                                "பழையதை அழித்திடும் திருமுழுக்கு - நம்மை\n" +
                                "புதிதாய் மாற்றிடும் திருமுழுக்கு\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new AudioVideoBean();
                        bean.setTitle("ஊற்றிடுமே உம் வல்லமையை");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class5audio/52.OotridumeUmVallamaiyai.mp3");
                        bean.setLyric("ஊற்றிடுமே உம் வல்லமையை\n" +
                                "இந்த நாளில் எங்கள் மேலே\n" +
                                "ஊற்றிடுமே உம் அக்கினியை\n" +
                                "எங்கள் மீது வல்லமையோடே\n" +
                                "\n" +
                                "வல்லமை வல்லமை தாருமே\n" +
                                "தேசத்தை உமக்காக கலக்கிட\n" +
                                "அபிஷேகம் அபிஷேகம் ஊற்றுமே\n" +
                                "அனல் கொண்டு உமக்காக எழும்பிடவே\n" +
                                "\n" +
                                "பெந்தகோஸ்தே நாளில் செய்ததுபோல\n" +
                                "அக்கினியின் நாவுகள் பொழிந்திடுமே\n" +
                                "அப்போஸ்தலர் நாட்களில் செய்ததுபோல\n" +
                                "இன்றும் செய்ய வேண்டுமே\n" +
                                "\n" +
                                "பாரங்கள் பாவங்கள் நீக்கிடுமே\n" +
                                "வாடிநக்கையின் பாதையை காட்டிடுமே\n" +
                                "கனிகளும் கொடைகளும் வரங்களுமே\n" +
                                "தந்தெம்மை நடத்திடுமே - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:

                        bean = new AudioVideoBean();
                        bean.setTitle("விண்ணில் வாழும் இயேசு நம் மண்ணுக்கு வந்தாரே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class5audio/53.VinnilVazhumYeasu.mp3");
                        bean.setLyric("விண்ணில வாழும் இயேசு\n" +
                                "நம் மண்ணுக்கு வந்தாரே\n" +
                                "பாவங்கள் எல்லாம் நீக்க\n" +
                                "தன்னுயிர் தந்தாரே – 2\n" +
                                "\n" +
                                "பசியைப் போக்கவே\n" +
                                "உடலைத் தந்தாரே\n" +
                                "தாகம் தீர்க்கவே\n" +
                                "இரத்தம் தத்தாரே – 2\n" +
                                "\n" +
                                "இயேசுவின் உடலை உண்டு\n" +
                                "நிலைவாழ்வு பெறுவோம்\n" +
                                "இயேசுவின் இரத்தம் பருகி\n" +
                                "பாவ மன்னிப்பு அடைவோம் – 2\n" +
                                "\n" +
                                "தினமும் திருப்பலி செல்வோம்\n" +
                                "இயேசுவின் அன்பை சுவைப்போம்\n" +
                                "உயிர்தரும் உணவாம் இயேசுவில்\n" +
                                "என்றும் மகிழ்ந்திடுவோம் - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new AudioVideoBean();
                        bean.setTitle("மன்னிப்பின் தெய்வமே இயேசய்யா");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class5audio/54.MannippinDeivame.mp3");
                        bean.setLyric("மன்னிப்பின் தெய்வமே இயேசய்யா\n" +
                                "மன்னிக்க வேண்டும் இயேசய்யா - 2\n" +
                                "தந்தை போல அணைப்பவரே இயேசய்யா\n" +
                                "என்னையும் அணைத்துக் கொள்ளுமே இயேசய்யா\n" +
                                "\n" +
                                "அன்பு செய்ய மறந்து விட்டேன் இயேசய்யா\n" +
                                "என் பாவம் பொறுத்தருளும் இயேசய்யா\n" +
                                "உதவி செய்ய மறந்து விட்டேன் இயேசய்யா\n" +
                                "என் பாவம் பொறுத்தருளும் இயேசய்யா\n" +
                                "பொய் சொல்லப் பழகி விட்டேன் இயேசய்யா\n" +
                                "என் பாவம் பொறுத்தருளும் இயேசய்யா\n" +
                                "தீய வார்த்தைப் பேசி விட்டேன் இயேசய்யா\n" +
                                "என் பாவம் பொறுத்தருளும் இயேசய்யா\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new AudioVideoBean();
                        bean.setTitle("இயேசு வரார் இயேசு வரார் ஓடிவாங்க");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class5audio/55.YeasuVaraar.mp3");
                        bean.setLyric("இயேசு வரார் இயேசு வரார் ஓடிவாங்க\n" +
                                "கஷ்டமெல்லாம் பறந்து போகும் பாடிவாங்க\n" +
                                "உடல் உள்ளம் சரியில்லையா உடனே வாங்க\n" +
                                "அன்பு இயேசு குணம் தருவார் நம்பி வாங்க\n" +
                                "\n" +
                                "குருடர்க்கு பார்வை தந்தார் தெரியும் தானே - 2\n" +
                                "நம் பார்வை சரி செய்வார் இயேசு தானே\n" +
                                "முடவரையும் நடக்கச் செய்தார் தெரியும் தானே - 2\n" +
                                "நல்வழி நம்மை நடக்க வைப்பார் இயேசு தானே\n" +
                                "\n" +
                                "நோயுற்றோர் நலம் பெற்றார் தெரியும் தானே - 2\n" +
                                "நமக்கும்கூட நலம் தருவார் இயேசு தானே\n" +
                                "இறந்தோருக்கு உயிர்கொடுத்தார் தெரியும் தானே - 2\n" +
                                "உயிர் தந்துக் காத்திடுவார் இயேசு தானே\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new AudioVideoBean();
                        bean.setTitle("இயேசு சாமி எங்க நல்ல சாமி");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class5audio/56.YeasuSaamiEnga.mp3");
                        bean.setLyric("இயேசு சாமி எங்க நல்ல சாமி\n" +
                                "நற்செய்தி பணியை செஞ்ச சாமி\n" +
                                "தன்னையே பலியா தந்த சாமி\n" +
                                "தரணிக்கு வாடிநவு வழங்கும் சாமி\n" +
                                "\n" +
                                "குருத்துவம் என்றால் தாடிநச்சி என்று\n" +
                                "பாதம் கழுவிச் சொன்ன சாமி - 2\n" +
                                "பணிவிடை செய்து மகிடிநந்திருக்க\n" +
                                "குருத்துவ அழைத்தல் தந்த சாமி\n" +
                                "\n" +
                                "உமது பணியைத் தொடர்ந்து செய்ய\n" +
                                "எனக்கும் ஆசை இருக்கு சாமி - 2\n" +
                                "அழைத்தல் என்னும் கொடையைத் தந்து\n" +
                                "ஆசி வழங்கி நடத்தும் சாமி\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new AudioVideoBean();
                        bean.setTitle("கல்யாணமாம் கல்யாணம் கானாவூரு கல்யாணம்");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/audio/class5audio/57.KalyanamamKalyanam.mp3");
                        bean.setLyric("கல்யாணமாம் கல்யாணம் கானாவூரு கல்யாணம்\n" +
                                "இயேசு சாமி கலந்துகிட்ட அற்புதமான கல்யாணம் - 2\n" +
                                "திராட்சை இரசம் இல்லன்னு கலங்கி நின்ன கல்யாணம் - 2\n" +
                                "இயேசு தண்ணீரை இரசமாக்கி நடத்தி வச்ச கல்யாணம்\n" +
                                "\n" +
                                "ஒருவர் ஒருவர் மதிக்க வேணும் இதுவே நல்ல கல்யாணம்\n" +
                                "தியாகம் செஞ்சு பழக வேணும் இதுவே அழகு கல்யாணம்\n" +
                                "ஆணும் பெண்ணும் சேர்த்து வச்சு கடவுள் செஞ்ச கல்யாணம் - 2\n" +
                                "ஆயுள் வரைக்கும் அன்பு வேணும் அதுவே உண்மை கல்யாணம் – 2\n" +
                                "\n" +
                                "இன்பத்திலும் துன்பத்திலும் ஒன்னா இருப்பது கல்யாணம்\n" +
                                "உடல் நலத்திலும் நோயிலும் தாங்கி நிற்பது கல்யாணம்\n" +
                                "உயர்வு தாழ்வு நேரங்களில் உடனிருப்பது கல்யாணம் - 2\n" +
                                "கடைசி வரைக்கும் உண்மையாக வாடிநவது தானே கல்யாணம் - 2\n");
                        videoViewModelsList.add(bean);
                        break;
                }
                break;
        }
    }

    void getVideoList() {
        AudioVideoBean bean;

        switch (classnum) {
            case 0:
                switch (position) {
                    case 11:
                        if (selectedPositon == 1) {
                            bean = new AudioVideoBean();
                            bean.setTitle("அன்பின் வடிவம் இயேசப்பா");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/Anbin_Vadivam_1.m4v");
                            videoViewModelsList.add(bean);

                            bean = new AudioVideoBean();
                            bean.setTitle("அகிலம் மீட்கப் பிறந்தவர் என் இயேசு");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/01_Akilam_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 2) {

                            bean = new AudioVideoBean();
                            bean.setTitle("ஆசி தரும் ஆசி தரும் அன்பு இயேசுவே");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/02_Aasi_Tharum_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 3) {

                            bean = new AudioVideoBean();
                            bean.setTitle("இயேசப்பா உயிர் நீங்கப்பா – என்");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/03_Yesappa_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 4) {

                            bean = new AudioVideoBean();
                            bean.setTitle("ஈர்த்து இயேசு அழைக்கிறார்");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/04_Eerthu_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 5) {

                            bean = new AudioVideoBean();
                            bean.setTitle("உயிரான இயேசுவே – என்");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/05_Uyirana_Yesuve_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 6) {

                            bean = new AudioVideoBean();
                            bean.setTitle("ஊற்றுத் தண்ணீர் இயேசப்பா");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/06_Ootru_Thanni_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 7) {

                            bean = new AudioVideoBean();
                            bean.setTitle("எல்லாம் அறிந்த இயேசய்யா");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/07_Ellam_Arintha_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 8) {

                            bean = new AudioVideoBean();
                            bean.setTitle("ஏக்கத்தோடு பறந்து போகும் பட்டாம்பூச்சியே");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/08_Yekathodu_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 9) {

                            bean = new AudioVideoBean();
                            bean.setTitle("ஐயா ஐயா இயேசய்யா");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/09_Aiya_Aiya_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 10) {

                            bean = new AudioVideoBean();
                            bean.setTitle("ஒரு நாள் இயேசு செபித்திடவே");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/10_Oru_Nal_Yesu_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 11) {

                            bean = new AudioVideoBean();
                            bean.setTitle("ஓடி ஓடி உழைக்கனும்");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/11_Odi_Odi_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 12) {
                            bean = new AudioVideoBean();
                            bean.setTitle("ஔடதம் என்றால் மருந்து");
                            bean.setUrl("https://icatapp-tnbclc.in/icat/mazhalaiyarmaraikalvi_songs/letters/video/12_Oudatham_1.m4v");
                            videoViewModelsList.add(bean);
                        }
                        break;
                    case 12:
                        if (selectedPositon == 1) {
                            bean = new AudioVideoBean();
                            bean.setTitle("ஒன்று என்று சொல்லும் போது");
                            bean.setUrl(numberSongPath + "video/Engal_Pattu_1.m4v");
                            videoViewModelsList.add(bean);

                            bean = new AudioVideoBean();
                            bean.setTitle("ஒன்று ஒன்று ஒன்று கடவுள் என்றும் ஒன்று");
                            bean.setUrl(numberSongPath + "video/01_Ondru_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 2) {

                            bean = new AudioVideoBean();
                            bean.setTitle("ஆதாம் ஏவாள் இருவரும் தான்");
                            bean.setUrl(numberSongPath + "video/02_Aatham_Aeval_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 3) {

                            bean = new AudioVideoBean();
                            bean.setTitle("மூன்று ஞானிகள் ஒட்டகம் மேலே");
                            bean.setUrl(numberSongPath + "video/03_Moondru_Gnanigal_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 4) {

                            bean = new AudioVideoBean();
                            bean.setTitle("இயேசுப்பற்றி அறியனுமா? (விவிலியம் படி - 2)");
                            bean.setUrl(numberSongPath + "video/04_Yesu_Patri_1.m4v");
                            videoViewModelsList.add(bean);

                        } else if (selectedPositon == 5) {

                            bean = new AudioVideoBean();
                            bean.setTitle("கடவுள் தந்த புத்தகங்கள் ஐந்து");
                            bean.setUrl(numberSongPath + "05_Kadavul_Thantha_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 6) {
                            bean = new AudioVideoBean();
                            bean.setTitle("கடவுளின் பண்புகள் ஆறு – அதை");
                            bean.setUrl(numberSongPath + "06_Kadavulin_Panbugal_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 7) {
                            bean = new AudioVideoBean();
                            bean.setTitle("'நானே' வாக்கியம் ஏழய்யா – அதைக்");
                            bean.setUrl(numberSongPath + "07_Nane_Vakiyam_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 8) {
                            bean = new AudioVideoBean();
                            bean.setTitle("பேறுபெற்றோர் நாம் பேறுபெற்றோர்");
                            bean.setUrl(numberSongPath + "08_Peru_Petror_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 9) {
                            bean = new AudioVideoBean();
                            bean.setTitle("தூய ஆவியின் கனிகள் ஒன்பதாம்");
                            bean.setUrl(numberSongPath + "09_Thooya_Aaviyin_1.m4v");
                            videoViewModelsList.add(bean);
                        } else if (selectedPositon == 10) {
                            bean = new AudioVideoBean();
                            bean.setTitle("கடவுளின் கட்டளைகள் பத்து");
                            bean.setUrl(numberSongPath + "10_Kadavulin_Kattalaigal_1.m4v");
                            videoViewModelsList.add(bean);
                        }
                        break;
                }
                break;
            case 1:
                switch (position) {
                    case 1:
                        bean = new AudioVideoBean();
                        bean.setTitle("இயேசப்பா எந்தன் இயேசப்பா");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/01YesappaEnthan-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new AudioVideoBean();
                        bean.setTitle("ஒன்னு ரெண்டு மூனு நாலு ஐந்து");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/02OnnuRenduMoonu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new AudioVideoBean();
                        bean.setTitle("செல்லப்பிள்ளை நான் செல்லப்பிள்ளை");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/03ChinnaPillai-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new AudioVideoBean();
                        bean.setTitle("அன்பு தெய்வம் இயேசுவே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/04AnbuTheivam-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new AudioVideoBean();
                        bean.setTitle("நம்பிடுவேனே நான் நம்பிடுவேனே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/05Nambiduvene-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new AudioVideoBean();
                        bean.setTitle("எங்கக் கூட இயேசு உண்டு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/06EngaKoodaYesu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new AudioVideoBean();
                        bean.setTitle("அம்மாவப் போல எனக்கு தாலாட்டுப் பாட ");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/07AmmaavaPola-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new AudioVideoBean();
                        bean.setTitle("ஒரு நாள் இயேசு மலைமேலே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/08OruNaalYesu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;

                }
                break;
            case 2:
                switch (position) {
                    case 1:
                        bean = new AudioVideoBean();
                        bean.setTitle("சின்ன பாப்பா மனசுக்குள்ள");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class2/01ChinnaPappaNanasu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new AudioVideoBean();
                        bean.setTitle("நல்ல கடவுள் இயேசப்பா");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class2/02NallaKadavul-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new AudioVideoBean();
                        bean.setTitle("குட்டி பாப்பா குட்டி பாப்பா ஓடி வாங்க");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class2/03KuttyPappaa-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new AudioVideoBean();
                        bean.setTitle("வந்தாரய்யா வந்தாரு இயேசு சாமி வந்தாரு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class2/04VantharaiyaVantharu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new AudioVideoBean();
                        bean.setTitle("செபிக்கக் கற்றுத் தாரும் இயேசுவே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class2/05JabikkaKaththu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new AudioVideoBean();
                        bean.setTitle("கவலை எதுவும் இல்லை எனக்கு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class2/06KavalaiEthuvum-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new AudioVideoBean();
                        bean.setTitle("நல்ல பையன் நானுங்க");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class2/07NallaPaiyan-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new AudioVideoBean();
                        bean.setTitle("சின்ன பாப்பா தூக்கமா?");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class2/08CinnaPappaThookkama-1.m4v");
                        videoViewModelsList.add(bean);
                        break;

                }
                break;
            case 3:
                switch (position) {
                    case 1:
                        bean = new AudioVideoBean();
                        bean.setTitle("எங்களோட இயேசு நல்லவர்");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class3/01EngalodaYesu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new AudioVideoBean();
                        bean.setTitle("கடவுள் பயம் உனக்கிருந்தால் வா வா வா");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class3/02KadavulPayam-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new AudioVideoBean();
                        bean.setTitle("சின்னஞ்சிறு பிள்ளைகளே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class3/03ChinnanChiru-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new AudioVideoBean();
                        bean.setTitle("அன்பு இருக்கா உனக்கு அன்பு இருக்கா?");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class3/04AnbuIrukkaa-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new AudioVideoBean();
                        bean.setTitle("இயேசு அன்பு வேணுமா?");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class3/05YesuAnbu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new AudioVideoBean();
                        bean.setTitle("படி படி படி படி கட்டளைகள் நீ படி");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class3/06PadiPadiPadi-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new AudioVideoBean();
                        bean.setTitle("சின்ன சின்ன பட்டாம்பூச்சி");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class3/07ChinnaChinna-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new AudioVideoBean();
                        bean.setTitle("உன்னை அன்பு செய்யும் கடவுள் யார் தெரியுமா?");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class3/08UnnaiAnbu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;

                }
                break;
            case 4:
                switch (position) {
                    case 1:
                        bean = new AudioVideoBean();
                        bean.setTitle("கொடைகளின் ஆண்டவர் இயேசு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class4/01KodaikalinAandavar-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new AudioVideoBean();
                        bean.setTitle("இனிமையான இயேசுவே இரக்கம் கொண்ட தெய்வமே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class4/02InimaiyaanaYesuve-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new AudioVideoBean();
                        bean.setTitle("தெய்வம் தந்த இந்த வாழ்க்கையை");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class4/03TheivamThantha-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new AudioVideoBean();
                        bean.setTitle("நன்றி நன்றி நன்றி இயேசுவே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class4/04NanriNanri-1.m4v\n");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new AudioVideoBean();
                        bean.setTitle("எங்க இயேசு நல்ல ஆயன் தானே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class4/05EngaYesu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new AudioVideoBean();
                        bean.setTitle("பயப்பட மாட்டேன் நான் பயப்பட மாட்டேன்");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class4/06PayapadaMaattan-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new AudioVideoBean();
                        bean.setTitle("ரொம்ப பிடிக்கும் ஏழைகளை இயேசுவுக்கு ரொம்ப பிடிக்கும்");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class4/07RombaPidikkum-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 8:
                        bean = new AudioVideoBean();
                        bean.setTitle("இரக்கத்தோடு வாழச் சொன்னார் இயேசு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class4/08IrakkathoduVaazha-1.m4v");
                        videoViewModelsList.add(bean);
                        break;

                }
                break;
            case 5:
                switch (position) {
                    case 1:
                        bean = new AudioVideoBean();
                        bean.setTitle("புது வாழ்வு தரும் திருமுழுக்கு");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class5/01PuthuVaazhvu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 2:
                        bean = new AudioVideoBean();
                        bean.setTitle("ஊற்றிடுமே உம் வல்லமையை");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class5/02OotridumeUnVallamaiyai-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 3:
                        bean = new AudioVideoBean();
                        bean.setTitle("விண்ணில் வாழும் இயேசு நம் மண்ணுக்கு வந்தாரே");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class5/03VinnulaVaazhum-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 4:
                        bean = new AudioVideoBean();
                        bean.setTitle("மன்னிப்பின் தெய்வமே இயேசய்யா");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class5/04MannikkumTheivame-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 5:
                        bean = new AudioVideoBean();
                        bean.setTitle("இயேசு வரார் இயேசு வரார் ஓடிவாங்க");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class5/05YesuVaraarYesu-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 6:
                        bean = new AudioVideoBean();
                        bean.setTitle(" இயேசு சாமி எங்க நல்ல சாமி");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class5/06YesuSaami-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                    case 7:
                        bean = new AudioVideoBean();
                        bean.setTitle("கல்யாணமாம் கல்யாணம் கானாவூரு கல்யாணம்");
                        bean.setUrl("https://icatapp-tnbclc.in/icat/video/class5/07KalyaanamaaKalyaanam-1.m4v");
                        videoViewModelsList.add(bean);
                        break;
                }
                break;

        }
    }

    @Override
    protected void onPause() {
        if (getString(R.string.songs_tamil).equals(getIntent().getStringExtra("Views"))) {
            try {
                if (videoAdapter.player != null)
                    if (videoAdapter.player.isPlaying()) {
                        videoAdapter.selectedPos = -1;
                        videoAdapter.player.release();
                        videoAdapter.notifyDataSetChanged();
                        videoAdapter.player = null;
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        try {
            if (!isMusicServiceRunning && pref.getMusicval().equals("1")) {
                startService(new Intent(getApplicationContext(), MusicService.class));
            }
            if (getString(R.string.songs_tamil).equals(getIntent().getStringExtra("Views"))) {
                try {
                    if (videoAdapter.player != null)
                        if (videoAdapter.player.isPlaying()) {
                            videoAdapter.player.release();
                            videoAdapter.player = null;
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}