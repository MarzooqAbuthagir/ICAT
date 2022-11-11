package com.maria.pastelhub.activity.maze;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.activity.colordrawing.MainActivity;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.api.SaveScore;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.log_files.LogFile;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MazeActivity extends AppCompatActivity {
    static String language = "";
    public static int startImage = R.drawable.jesus_img, endImage = R.drawable.jesus_img;
    public static int COLS = 16, ROWS = 16;
    static Activity activity;
    int classn;
    public static int position;
    NetworkStatusFinder networkStatusFinder;


    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.major_title)
    TextView major_title;

    @BindView(R.id.title)
    TextView mazeTitle;


    public static int widthref;
    static String selectedClass = "", title = "", actName = "";
    static Pref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maze);
            pref = new Pref(this);
            activity = MazeActivity.this;
            networkStatus();

            ButterKnife.bind(this);
            language = getIntent().getStringExtra("language");
            if (getIntent().getStringExtra("language").equals("1"))
                major_title.setText(R.string.show_the_way);
            else major_title.setText(R.string.maze_tamil);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            position = getIntent().getIntExtra("Position", 1);
            classn = getIntent().getIntExtra("class", 1);
            String language = getIntent().getStringExtra("language");
            title = getIntent().getStringExtra("title");
            selectedClass = getIntent().getStringExtra("selectedClass");
            actName = getIntent().getStringExtra("actName");
            System.out.println("value--------------" + language);
            System.out.println("value--------------" + selectedClass);
            System.out.println("value--------------" + title);
            System.out.println("value--------------" + actName);
            if (language.equals("1")) {
                if (selectedClass.equalsIgnoreCase("Class 1") ||
                        selectedClass.equalsIgnoreCase("Class 2") ||
                        selectedClass.equalsIgnoreCase("Class 3") ||
                        selectedClass.equalsIgnoreCase("Class 4") ||
                        selectedClass.equalsIgnoreCase("Class 5")) {
                    mazeTitle.setVisibility(View.GONE);
                } else {
                    mazeTitle.setVisibility(View.VISIBLE);
                    switch (selectedClass) {
                        case "LKG":
                            if (position == 1)
                                mazeTitle.setText("Reach Abba Father who created you in His image!");
                            if (position == 2)
                                mazeTitle.setText("Help disciples to reach Jesus, the Son of God!");
                            if (position == 3)
                                mazeTitle.setText("Reach Holy Spirit our Guide!");
                            if (position == 4)
                                mazeTitle.setText("Help Infant Jesus to reach his foster father Joseph!");
                            if (position == 5)
                                mazeTitle.setText("Reach Mary, the Mother of Jesus!");
                            if (position == 6)
                                mazeTitle.setText("Help Mary Magdalene to reach the Risen Christ!");
                            if (position == 7)
                                mazeTitle.setText("Reach Jesus our Saviour!");
                            if (position == 8)
                                mazeTitle.setText("Help these kids to reach Jesus!");
                            break;

                        case "UKG":
                            if (position == 1)
                                mazeTitle.setText("Help Angel Gabriel to meet Mother Mary!");
                            if (position == 2)
                                mazeTitle.setText("Help disciples to reach Jesus, the servant leader!");
                            if (position == 3)
                                mazeTitle.setText("Help Good Samaritan to reach the Wounded Man!");
                            if (position == 4)
                                mazeTitle.setText("Help the Whale to swallow Jonah!");
                            if (position == 5)
                                mazeTitle.setText("Help the widow to go to the temple!");
                            if (position == 6)
                                mazeTitle.setText("Reach Holy Spirit to be filled with His fire!");
                            if (position == 7)
                                mazeTitle.setText("Help Samuel to reach Priest Eli!");
                            if (position == 8)
                                mazeTitle.setText("Help Mother Mary to visit Elizabeth and Zachariah!");
                            break;
                    }
                }
                if (classn > 1) {
                    classn = classn - 1;
                    startImage = getResources().getIdentifier("maze_" + classn + "_" +
                                    position + "_2", "drawable",
                            getPackageName());
                    endImage = getResources().getIdentifier("maze_" + (classn) + "_" +
                                    (position) + "_1", "drawable",
                            getPackageName());
                } else if (classn == 1) {
                    startImage = getResources().getIdentifier("maze_" +
                                    position + "_1", "drawable",
                            getPackageName());
                    endImage = getResources().getIdentifier("maze_" +
                                    (position) + "_2", "drawable",
                            getPackageName());
                } else if (classn == 0) {
                    startImage = getResources().getIdentifier("mazel_" +
                                    position + "_1", "drawable",
                            getPackageName());
                    endImage = getResources().getIdentifier("mazel_" +
                                    (position) + "_2", "drawable",
                            getPackageName());
                } else {
                    startImage = getResources().getIdentifier("maze_" + "0_" +
                                    position + "_1", "drawable",
                            getPackageName());
                    endImage = getResources().getIdentifier("maze_" + "0_" +
                                    (position) + "_2", "drawable",
                            getPackageName());
                }

            } else {
                mazeTitle.setVisibility(View.VISIBLE);
                startImage = getResources().getIdentifier("maze_" + classn + "_" +
                                position + "_2", "drawable",
                        getPackageName());
                endImage = getResources().getIdentifier("maze_" + (classn) + "_" +
                                (position) + "_1", "drawable",
                        getPackageName());

                switch (selectedClass) {
                    case "LKG & UKG":
                        if (position == 1)
                            mazeTitle.setText("கபிரியேல் வானதூதர் மரியாவை சந்திக்க உதவி செய்வாயா!");
                        if (position ==2)
                            mazeTitle.setText("இந்த சிறுபிள்ளைகள் இயேசுவைச் சந்தித்து ஆசி பெற உதவி செய்வாயா!");
                        if (position ==3)
                            mazeTitle.setText("மார்த்தா, மரியா இயேசுவைச் சந்திக்க உதவி செய்வாயா!");
                        if (position ==4)
                            mazeTitle.setText("பன்னிரு சீடர்கள் இயேசுவைச் சந்திக்க உதவி செய்வாயா!");
                        if (position ==5)
                            mazeTitle.setText("மகதலா மரியா உயிர்த்த இயேசுவைச் சந்திக்க உதவி செய்வாயா!");
                        if (position ==6)
                            mazeTitle.setText("பார்வையற்ற பர்த்திமேயு இயேசுவைச் சந்திக்க உதவி செய்வாயா!");
                        if (position ==7)
                            mazeTitle.setText("மரியாவும், யோசேப்பும் சிறுவன் இயேசுவைக் கண்டுபிடிக்க உதவி செய்வாயா!");
                        if (position ==8)
                            mazeTitle.setText("ஏழை இலாசர் விண்ணகம் செல்ல உதவி செய்வாயா!");
                        if (position ==9)
                            mazeTitle.setText("இயேசு ஐயாயிரம் பேருக்கு உணவளித்த நிகழ்வைக் காண விரைந்து வா!");
                        if (position ==10)
                            mazeTitle.setText("இயேசுவோடு இணைந்து ஒலிவ மலைக்கு செபிக்கச் செல்வாயா!");
                        if (position ==11)
                            mazeTitle.setText("இயேசு உடல் நலமற்றவரை குணப்படுத்தும் நிகழ்வை காண விரைந்து வா!");
                        if (position ==12)
                            mazeTitle.setText("பார்வையற்றவர் இயேசுவைச் சந்திக்க உதவி செய்வாயா!");
                        if (position ==13)
                            mazeTitle.setText("உன்னைப் படைத்த ஒரே கடவுளை காண விரைந்து வருவாயா!");
                        if (position ==14)
                            mazeTitle.setText("ஆதாம், ஏவாள் தந்தைக் கடவுளை வந்து சேர உதவி செய்வாயா!");
                        if (position ==15)
                            mazeTitle.setText("மூன்று ஞானிகள் குழந்தை இயேசுவை சந்திக்க உதவி செய்வாயா!");
                        if (position ==16)
                            mazeTitle.setText("நல்ல நிலத்தைச் சென்றடைய இந்தக் குழந்தைகளுக்கு உதவி செய்வாயா!");
                        if (position ==17)
                            mazeTitle.setText("பத்து தாலந்து உடையவர் தலைவரைச் சந்திக்க உதவி செய்வாயா!");
                        if (position ==18)
                            mazeTitle.setText("கானாவில் நடக்கும் திருமணத்திற்கு செல்வாயா!");
                        if (position ==19)
                            mazeTitle.setText("இந்த ஆட்டுக்குட்டி இயேசுவை வந்தடைய உதவி செய்வாயா!");
                        if (position ==20)
                            mazeTitle.setText("ஏழைகளுக்கு உதவி செய்ய விரைந்து செல்வாயா!");
                        if (position ==21)
                            mazeTitle.setText("ஆவியின் கனிகளைப் பெற்றுக்கொள்ள விரைந்து வா!");
                        if (position ==22)
                            mazeTitle.setText("பத்துக் கட்டளைகளைப் பெற மோசேயிடம் செல்வாயா!");
                        break;

                    case "Class 1":
                        if (position == 1)
                            mazeTitle.setText("இயேசுவிடம் சென்றடைய இந்த குழந்தைகளுக்கு உதவி செய்வாயா!");
                        if (position == 2)
                            mazeTitle.setText("ஏவாள், ஆதாமை சென்றடைய உதவி செய்வாயா!");
                        if (position == 3)
                            mazeTitle.setText("ஆதாம், ஏவாள், ஏதேன் தோட்டத்தைச் சுற்றி வர உதவி செய்வாயா!");
                        if (position == 4)
                            mazeTitle.setText("பெருமழைக்கு முன் இந்த எலி பெட்டகத்தின் வாயிலைக் கண்டுபிடித்து தன்னைக் காப்பாற்றிக் கொள்ள உதவி செய்வாயா!");
                        if (position == 5)
                            mazeTitle.setText("கடவுள் வாக்களித்த கானான் நாட்டைச் சென்றடைய ஆபிரகாமுக்கு உதவி செய்வாயா!");
                        if (position == 6)
                            mazeTitle.setText("ஆபிரகாமின் ஊழியர், ஈசாக்கிற்கு துணைவியான ரெபேக்காவை கண்டுபிடிக்க உதவி செய்வாயா!");
                        if (position == 7)
                            mazeTitle.setText("யாக்கோபு தன் அண்ணன் ஏசாவைச் சந்தித்து ஆசி பெற உதவி செய்வாயா!");
                        if (position == 8)
                            mazeTitle.setText("யோசேப்பு கைதி நிலையிலிருந்து எகிப்தின் ஆளுநராக நீ உதவி செய்வாயா!");
                        break;

                    case "Class 2":
                        if (position == 1)
                            mazeTitle.setText("மோசேயும், இஸ்ரயேல் மக்களும் செங்கடைலைக் கடக்க எளிய வழியை அவர்களுக்குக் காட்டுவாயா!");
                        if (position == 2)
                            mazeTitle.setText("வழியைக் கண்டுபிடித்து எரிகோ மதிலின் மேலிருக்கும் கொடியை வந்து தொடுவாயா!");
                        if (position == 3)
                            mazeTitle.setText("சிம்சோன் பெலிஸ்தியரைத் தோற்கடிக்க உதவி செய்வாயா!");
                        if (position == 4)
                            mazeTitle.setText("ரூத்து அவரது மாமியார் நகோமியை சென்றடைய உதவி செய்வாயா!");
                        if (position == 5)
                            mazeTitle.setText("எருசலேம் மதில்களைக் கட்டியெழுப்ப நெகேமியா எருசலேம் செல்ல உதவி செய்வாயா!");
                        if (position == 6)
                            mazeTitle.setText("எரியும் புண்களின் வலியையும் வேதனையையும் தாங்கிக் கொள்ள யோபுக்கு உதவி செய்வாயா!");
                        if (position == 7)
                            mazeTitle.setText("தோபியா, தான் கொண்டு வந்த மீனின் பித்தப்பையைத் தனது தந்தை தோபித்தின் கண்களில் தேய்த்து பார்வை பெற உதவி செய்வாயா!");
                        if (position == 8)
                            mazeTitle.setText("சிறுவன் சாமுவேல், குரு ஏலியிடம் செல்ல வழிகாட்டுவாயா!");
                        break;

                    case "Class 3":
                        if (position == 1)
                            mazeTitle.setText("இஸ்ரயேலின் முதல் அரசரான சவுலை சென்று சேர்வாயா!");
                        if (position == 2)
                            mazeTitle.setText("ஆடு மேய்க்கும் தாவீது இஸ்ரயேலின் அரசராக உதவி செய்வாயா!");
                        if (position == 3)
                            mazeTitle.setText("சாலமோன் அரசர் எருசலேம் ஆலயத்தைக் கட்ட உதவி செய்வாயா!");
                        if (position == 4)
                            mazeTitle.setText("அரசி எஸ்தர் தனது சித்தப்பா மொர்தக்காயைச் சந்திக்க உதவி செய்வாயா!");
                        if (position == 5)
                            mazeTitle.setText("இந்த குடும்பம் செபம் செய்து கொண்டே இயேசுவை வந்துசேர நீ உதவி செய்வாயா!");
                        if (position == 6)
                            mazeTitle.setText("தலைமை குரு இல்க்கியா ஆண்டவரின் ஆலயத்தில் உள்ள சட்ட நூலைக் கண்டுபிடித்து, அரசர் யோசியாவிடம் எடுத்துச் செல்ல உதவி செய்வாயா!");
                        if (position == 7)
                            mazeTitle.setText("இந்தச் சிறுவனும், சிறுமியும் இயேசுவைச் சென்றடைய உதவி செய்வாயா!");
                        if (position == 8)
                            mazeTitle.setText("இயேசுவைச் சென்றடைய முயற்சி செய்யும் இந்தக் குழந்தைகளுக்கு நீ உதவி செய்வாயா!");
                        break;

                    case "Class 4":
                        if (position == 1)
                            mazeTitle.setText("ஆண்டவரின் கட்டளைப்படி காகம் எலியாவுக்கு உணவு கொடுக்க வழிகாட்டுவாயா!");
                        if (position == 2)
                            mazeTitle.setText("சிரியா மன்னனின் படைத்தலைவன் நாமான் தனது தொழுநோயிலிருந்து குணம்பெற இறைவாக்கினர் எலிசாவிடம் அழைத்துச் செல்வாயா!");
                        if (position == 3)
                            mazeTitle.setText("எசாயா இறைவாக்கினர் ஆண்டவரின் வார்த்தைகளை இஸ்ரயேல் மக்களுக்கு எடுத்துச் சொல்ல அவரை  யூதா நாட்டிற்கு அழைத்துச் செல்வாயா!");
                        if (position == 4)
                            mazeTitle.setText("சரியான வழியைக் கண்டுபிடித்து இயேசுவை சென்று சேர்வாயா?");
                        if (position == 5)
                            mazeTitle.setText("காணாமற்போன இந்த ஆடு நல்லாயனாம் இயேசுவை அடைய நீ உதவி செய்வாயா!");
                        if (position == 6)
                            mazeTitle.setText("சிங்கங்களின் வாய்களைக் கட்டி தானியேலைக் காப்பாற்ற கடவுளின் தூதரை அழைத்து வருவாயா! ");
                        if (position == 7)
                            mazeTitle.setText("தந்தை ஆபிரகாமை சென்றடைய ஏழை இலாசருக்கு உதவி செய்வாயா!");
                        if (position == 8)
                            mazeTitle.setText("யோனா, ஆண்டவரின் கட்டளைப்படி நினிவே நகருக்குச் செல்ல உதவி செய்வாயா!");
                        break;

                    case "Class 5":
                        if (position == 1)
                            mazeTitle.setText("இயேசு திருமுழுக்குப் பெற யோவானிடம் அழைத்துச் செல்வாயா!");
                        if (position == 2)
                            mazeTitle.setText("திருத்தூதர்கள், இயேசு கிறிஸ்து வாக்களித்தத் தூய ஆவியாரை பெற்றுக்கொள்ள உதவி செய்வாயா!");
                        if (position == 3)
                            mazeTitle.setText("இயேசுவும் அவரது சீடர்களும் பாஸ்கா விழாவினைக் கொண்டாட எருசலேம் செல்ல உதவி செய்வாயா!");
                        if (position == 4)
                            mazeTitle.setText("இந்தச் சிறுவன் ஒப்புரவு அருளடையாளத்தைப் பெற குருவிடம் அழைத்துச் செல்வாயா!");
                        if (position == 5)
                            mazeTitle.setText("உடல் நலமற்ற இந்தச் சிறுவன் உடல் நலம் பெற இயேசுவிடம் அழைத்துச் செல்வாயா!");
                        if (position == 6)
                            mazeTitle.setText("குருவானவர் இந்த ஏழைக் குடும்பத்தைச் சந்தித்து அவர்களுக்குத் தேவையான உதவிகள் செய்ய வழிகாட்டுவாயா!");
                        if (position == 7)
                            mazeTitle.setText("இந்த மணமகனும், மணமகளும் திருமண அருளடையாளத்தைப் பெற அருள்பணியாளரிடம் அழைத்துச் செல்வாயா!");
                        break;
                }
            }


            ImageView viewtop = ((ImageView) findViewById(R.id.up));
            viewtop.setImageResource(startImage);
//            LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) viewtop.getLayoutParams();
//            params1.height = (displayMetrics.heightPixels - widthref - (14 * COLS)) / 2;
//            viewtop.setLayoutParams(params1);
            ImageView viewbottom = ((ImageView) findViewById(R.id.down));
            viewbottom.setImageResource(endImage);
//            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) viewbottom.getLayoutParams();
//            params2.height = (displayMetrics.heightPixels - widthref - (14 * COLS)) / 2;
//            viewbottom.setLayoutParams(params2);


            back_major_header.setOnClickListener(view -> backClicked());


        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
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

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(MazeActivity.this)) {
            Intent intent = new Intent(MazeActivity.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
            startActivity(intent);
            finish();
        }

    }

    public static class GameView extends View {

        public enum Direction {
            UP, DOWN, LEFT, RIGHT
        }

        public GameView.Cell[][] cells;
        public GameView.Cell player, exit;
        public float WALL_THICKNESS = 14;
        public float cellSize, hMargin, vMargin;
        public Paint wallPaint;
        public Random random;

        public GameView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);


            wallPaint = new Paint();
            wallPaint.setColor(getResources().getColor(R.color.maze_box_line));
            wallPaint.setStrokeWidth(WALL_THICKNESS);
            wallPaint.setAntiAlias(true);
            wallPaint.clearShadowLayer();
            //wallPaint.setShadowLayer(12, 0, 0, getResources().getColor(R.color.grey));
            random = new Random();
            // new LogFile("image", "" + startImage + " - " + endImage);
            createMaze();

        }


        @Override
        protected void onDraw(Canvas canvas) {
            try {
                canvas.drawColor(Color.WHITE);

                int width = getWidth();
                int height = getHeight(); //widthref - (int) (WALL_THICKNESS * COLS);

                if (width / height < COLS / ROWS) {
                    cellSize = width / (COLS + 1);
                } else {
                    cellSize = height / (ROWS + 1);
                }

                hMargin = (width - COLS * cellSize) / 2;
                vMargin = (height - ROWS * cellSize) / 2;

                canvas.translate(hMargin, vMargin);
                for (int x = 0; x < COLS; x++) {
                    for (int y = 0; y < ROWS; y++) {
                        if (cells[x][y].topWall) {
                            canvas.drawLine(
                                    x * cellSize,
                                    y * cellSize,
                                    (x + 1) * cellSize,
                                    y * cellSize,
                                    wallPaint
                            );
                        }

                        if (cells[x][y].leftWall) {
                            canvas.drawLine(
                                    x * cellSize,
                                    y * cellSize,
                                    x * cellSize,
                                    (y + 1) * cellSize,
                                    wallPaint
                            );
                        }

                        if (cells[x][y].bottomWall) {
                            canvas.drawLine(
                                    x * cellSize,
                                    (y + 1) * cellSize,
                                    (x + 1) * cellSize,
                                    (y + 1) * cellSize,
                                    wallPaint
                            );
                        }

                        if (cells[x][y].rightWall) {
                            canvas.drawLine(
                                    (x + 1) * cellSize,
                                    y * cellSize,
                                    (x + 1) * cellSize,
                                    (y + 1) * cellSize,
                                    wallPaint
                            );
                        }
                    }
                }

                float margin = cellSize / 10;

                new LogFile("image", "" + startImage + " - " + endImage);
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), endImage), 60, 60, false);
                Rect rectangle = new Rect(
                        Math.round((player.col * cellSize + margin)),
                        Math.round((player.row * cellSize + margin)),
                        Math.round(((player.col + 1) * cellSize - margin)),
                        Math.round(((player.row + 1) * cellSize - margin)));
                canvas.drawBitmap(bitmap, null, rectangle, wallPaint);


                Bitmap bitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), startImage), 60, 60, false);
                Rect rectangle1 = new Rect(
                        Math.round(exit.col * cellSize + margin),
                        Math.round(exit.row * cellSize + margin),
                        Math.round((exit.col + 1) * cellSize - margin),
                        Math.round((exit.row + 1) * cellSize - margin));
                canvas.drawBitmap(bitmap1, null, rectangle1, null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onTouchEvent(MotionEvent event) {

            try {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    return true;

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    float x = event.getX();
                    float y = event.getY();

                    float playerCenterX = hMargin + (player.col + 0.1f) * cellSize;
                    float playerCenterY = vMargin + (player.row + 0.1f) * cellSize;

                    float dx = x - playerCenterX;
                    float dy = y - playerCenterY;

                    float absDx = Math.abs(dx);
                    float absDy = Math.abs(dy);

                    if (absDx > cellSize || absDy > cellSize) {
                        if (absDx > absDy) {
                            if (dx > 0) {
                                movePlayer(Direction.RIGHT);
                            } else {
                                movePlayer(Direction.LEFT);
                            }
                        } else {
                            if (dy > 0) {
                                movePlayer(Direction.DOWN);
                            } else {
                                movePlayer(Direction.UP);
                            }
                        }
                    }
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.onTouchEvent(event);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        private void movePlayer(GameView.Direction direction) {
            switch (direction) {
                case UP:
                    if (!player.topWall)
                        player = cells[player.col][player.row - 1];
                    break;
                case DOWN:
                    if (!player.bottomWall)
                        player = cells[player.col][player.row + 1];
                    break;
                case LEFT:
                    if (!player.leftWall)
                        player = cells[player.col - 1][player.row];
                    break;
                case RIGHT:
                    if (!player.rightWall)
                        player = cells[player.col + 1][player.row];
                    break;
            }

            checkExit();
            invalidate();
        }

        boolean isDialogVisible = false;

        @RequiresApi(api = Build.VERSION_CODES.M)
        private void checkExit() {
            if (player == exit) {
                if (!isDialogVisible) {
                    isDialogVisible = true;
                    toBack();
                }
//                createMaze();
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        public void toBack() {
            Pref pref = new Pref(getContext());
            callAlert(pref.getLesson(), getContext());
//            if (language.equals("1"))
//                new AlertClass(activity, "Two Start", getContext(), "Fantastic!",
//                        "You have successfully completed the sixth level.", 4);
//            else
//                new AlertClass(activity, "Two Start", getContext(), "மிக அருமை!",
//                        "நீங்கள் ஆறாம் நிலையை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
//            new AlertClass(activity, "Two Start", getContext(), "மிக அருமை!", "நீங்கள் இரண்டாம் நிலையை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
        }

        void callAlert(String position, Context context) {
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
                        saveScore(context);
                    } else {
                        if (language.equals("1"))
                            new AlertClass((Activity) context, "Two", context, "Fantastic!",
                                    "You have successfully completed this activity.", 4);
                        else
                            new AlertClass((Activity) context, "Two Start", context, "மிக அருமை!",
                                    "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    }
                    break;

            }
        }

        private void saveScore(Context context) {
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
                            new AlertClass((Activity) context, "Two", context, "Fantastic!",
                                    "You have successfully completed this activity.", 4);
                        else
                            new AlertClass((Activity) context, "Two Start", context, "மிக அருமை!",
                                    "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    } else
                        new AlertClass((Activity) context, "Login", context, "Failed", r.getMessage(), 1);
                }

                @Override
                public void onFailure(Call<SaveScore> call, Throwable t) {
                    Log.i(getClass().getName(), "=================error   " + t.getMessage());
                    new AlertClass((Activity) context, "Login", context, "Failed", t.getMessage(), 1);
                }
            });
        }

        private void createMaze() {

            try {
                Stack<Cell> stack = new Stack<>();
                Cell current, next;

                cells = new Cell[COLS][ROWS];

                for (int x = 0; x < COLS; x++) {
                    for (int y = 0; y < ROWS; y++) {
                        cells[x][y] = new Cell(x, y);
                    }
                }

                player = cells[COLS - 1][ROWS - 1];//moving bottom to top
                exit = cells[0][0]; // top position of image

                current = cells[0][0];
                current.visited = true;

                do {
                    next = getNeighbour(current);
                    if (next != null) {
                        removeWall(current, next);
                        stack.push(current);
                        current = next;
                        current.visited = true;
                    } else {
                        current = stack.pop();
                    }
                } while (!stack.empty());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        private void removeWall(GameView.Cell current, GameView.Cell next) {

            if (current.col == next.col && current.row == next.row + 1) {
                current.topWall = false;
                next.bottomWall = false;
            }

            if (current.col == next.col && current.row == next.row - 1) {
                current.bottomWall = false;
                next.topWall = false;
            }

            if (current.col == next.col + 1 && current.row == next.row) {
                current.leftWall = false;
                next.rightWall = false;
            }

            if (current.col == next.col - 1 && current.row == next.row) {
                current.rightWall = false;
                next.leftWall = false;
            }

        }

        private GameView.Cell getNeighbour(GameView.Cell cell) {
            ArrayList<GameView.Cell> neighbour = new ArrayList<>();

            // left neighbour
            if (cell.col > 0) {
                if (!cells[cell.col - 1][cell.row].visited) {
                    neighbour.add(cells[cell.col - 1][cell.row]);
                }
            }

            // right neighbour
            if (cell.col < COLS - 1) {
                if (!cells[cell.col + 1][cell.row].visited) {
                    neighbour.add(cells[cell.col + 1][cell.row]);
                }
            }

            // top neighbour
            if (cell.row > 0) {
                if (!cells[cell.col][cell.row - 1].visited) {
                    neighbour.add(cells[cell.col][cell.row - 1]);
                }
            }

            // bottom neighbour
            if (cell.row < ROWS - 1) {
                if (!cells[cell.col][cell.row + 1].visited) {
                    neighbour.add(cells[cell.col][cell.row + 1]);
                }
            }

            if (neighbour.size() > 0) {
                int index = random.nextInt(neighbour.size());
                return neighbour.get(index);
            }
            return null;
        }

        private class Cell {
            boolean topWall = true, leftWall = true, rightWall = true, bottomWall = true, visited = false;

            int col, row;

            public Cell(int col, int row) {
                this.col = col;
                this.row = row;
            }
        }
    }

}