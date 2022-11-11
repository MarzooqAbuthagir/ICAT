package com.maria.pastelhub.activity.lineconnector;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.activity.BaseActivityExtends;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.application.ICatApplication;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineConnector extends AppCompatActivity implements View.OnClickListener {

    float m1 = 0, m2 = 0, m3 = 0, m4 = 0, e1 = 0, e2 = 0, e3 = 0, e4 = 0, r1 = 0, r2 = 0, r3 = 0, r4 = 0, p1 = 0, p2 = 0, p3 = 0, p4 = 0,
            d1 = 0, d2 = 0, d3 = 0, d4 = 0, f1 = 0, f2 = 0, f3 = 0, f4 = 0, h1 = 0, h2 = 0, h3 = 0, h4 = 0;
    int i = 0, j = 0, k = 0;
    int cr1 = 0, cr2 = 0, cr3 = 0, cr4 = 0, cr5 = 0, qr1 = 0, qr2 = 0, qr3 = 0, qr4 = 0, qr5 = 0;

    DrawView drawView;

    NetworkStatusFinder networkStatusFinder;

    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.major_title)
    TextView major_title;

    @BindView(R.id.rl1)
    RelativeLayout rl1;

    @BindView(R.id.q1)
    RadioButton q1;

    @BindView(R.id.a1)
    RadioButton a1;

    @BindView(R.id.q2)
    RadioButton q2;

    @BindView(R.id.a2)
    RadioButton a2;

    @BindView(R.id.q3)
    RadioButton q3;

    @BindView(R.id.a3)
    RadioButton a3;

    @BindView(R.id.q4)
    RadioButton q4;

    @BindView(R.id.a4)
    RadioButton a4;

    @BindView(R.id.q5)
    RadioButton q5;

    @BindView(R.id.a5)
    RadioButton a5;

    String volval;
    Pref pref;

    ImageView sound_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_connector);

        networkStatus();

        ButterKnife.bind(this);

        clickEvent();

        major_title.setText(R.string.line_connector_tamil);

        sound_icon = findViewById(R.id.sound_icon_header);

        pref = new Pref(LineConnector.this);

        musiccontrol();

    }

    public void musiccontrol() {

        volval = pref.getMusicval();

        sound_icon = findViewById(R.id.sound_icon);



        sound_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (volval.equals("1")) {
                    volval = "0";
                    sound_icon.setImageResource(R.drawable.sound_mute);
                    pref.setMusicVal("0");
                    if (!ICatApplication.isMusicServiceRunning) {
                        stopService(new Intent(LineConnector.this, MusicService.class));
                    }
                } else if (volval.equals("0")) {
                    volval = "1";
                    sound_icon.setImageResource(R.drawable.sound_on);
                    pref.setMusicVal("1");
                    startService(new Intent(LineConnector.this, MusicService.class));
                } else {
                    volval = "1";
                    sound_icon.setImageResource(R.drawable.sound_on);
                    pref.setMusicVal("1");
                    if (ICatApplication.isMusicServiceRunning) {
                        startService(new Intent(LineConnector.this, MusicService.class));
                    }
                }
            }
        });

        if (volval != null) {
            if (volval.equals("1")) {
                sound_icon.setImageResource(R.drawable.sound_on);
                if (ICatApplication.isMusicServiceRunning) {
                    startService(new Intent(this, MusicService.class));
                }
            } else {
                sound_icon.setImageResource(R.drawable.sound_mute);
                if (ICatApplication.isMusicServiceRunning) {
                    stopService(new Intent(this, MusicService.class));
                }
            }
        }

    }

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(LineConnector.this)) {
            Intent intent = new Intent(LineConnector.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
            startActivity(intent);
            finish();
        }

    }

    private void clickEvent() {
        q1.setOnClickListener(this);
        a1.setOnClickListener(this);
        q2.setOnClickListener(this);
        a2.setOnClickListener(this);
        q3.setOnClickListener(this);
        a3.setOnClickListener(this);
        q4.setOnClickListener(this);
        a4.setOnClickListener(this);
        q5.setOnClickListener(this);
        a5.setOnClickListener(this);
        back_major_header.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        backClicked();
    }

    private void backClicked() {
        startActivity(new Intent(LineConnector.this, BaseActivityExtends.class));
        finish();
        overridePendingTransition(R.anim.slide_in_left1, R.anim.slide_in_left);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.q1) {
            if (i == 0) {
                i += 1;
                qr1 += 1;
            } else {
                if (qr1 == 1) {
                    q1.setChecked(true);
                } else {
                    q1.setChecked(false);
                }
                new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
            }
        } else if (view.getId() == R.id.a1) {
            if (i == 1) {
                if (cr1 == 0) {
                    k += 1;
                    cr1 += 1;
                    i = 0;
                    drawView = new DrawView(this,q1,a1);
                    drawView.setBackgroundColor(Color.WHITE);
                    rl1.addView(drawView);
                    if (k == 5) {
                        new AlertClass(LineConnector.this, "Two Start", LineConnector.this, "மிக அருமை!", "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    }
                } else {
                    if (cr1 == 1) {
                        a1.setChecked(true);
                    } else {
                        a1.setChecked(false);
                    }
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                }
            } else {
                if (cr1 == 1) {
                    a1.setChecked(true);
                } else {
                    a1.setChecked(false);
                }
                if (i == 0) {
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "Select the question first", 3);
                } else {
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                }
            }
        } else if (view.getId() == R.id.q2) {
            if (i == 0) {
                i += 2;
                qr2 += 1;
            } else {
                if (qr2 == 1) {
                    q2.setChecked(true);
                } else {
                    q2.setChecked(false);
                }
                new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
            }
        } else if (view.getId() == R.id.a2) {
            if (i == 2) {
                if (cr2 == 0) {
                    k += 1;
                    cr2 += 1;
                    i = 0;
                    drawView = new DrawView(this,q2,a2);
                    drawView.setBackgroundColor(Color.WHITE);
                    rl1.addView(drawView);
                    if (k == 5) {
                        new AlertClass(LineConnector.this, "Two Start", LineConnector.this, "மிக அருமை!", "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    }
                } else {
                    if (cr2 == 1) {
                        a2.setChecked(true);
                    } else {
                        a2.setChecked(false);
                    }
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                }
            } else {
                if (cr2 == 1) {
                    a2.setChecked(true);
                } else {
                    a2.setChecked(false);
                }
                if (i == 0) {
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "முதலில் கேள்வியைத் தேர்ந்தெடுக்கவும்", 3);
                } else {
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                }
            }
        } else if (view.getId() == R.id.q3) {
            if (i == 0) {
                i += 3;
                qr3 += 1;
            } else {
                if (qr3 == 1) {
                    q3.setChecked(true);
                } else {
                    q3.setChecked(false);
                }
                new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
            }
        } else if (view.getId() == R.id.a3) {
            if (i == 3) {
                if (cr3 == 0) {
                    k += 1;
                    cr3 += 1;
                    i = 0;
                    drawView = new DrawView(this,q3,a3);
                    drawView.setBackgroundColor(Color.WHITE);
                    rl1.addView(drawView);
                    if (k == 5) {
                        new AlertClass(LineConnector.this, "Two Start", LineConnector.this, "மிக அருமை!", "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    }
                } else {
                    if (cr3 == 1) {
                        a3.setChecked(true);
                    } else {
                        a3.setChecked(false);
                    }
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                }
            } else {
                if (cr3 == 1) {
                    a3.setChecked(true);
                } else {
                    a3.setChecked(false);
                }
                if (i == 0) {
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "Select the question first", 3);
                } else {
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                }
            }
        } else if (view.getId() == R.id.q4) {
            if (i == 0) {
                i += 4;
                qr4 += 1;
            } else {
                if (qr4 == 1) {
                    q4.setChecked(true);
                } else {
                    q4.setChecked(false);
                }
                new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
            }
        } else if (view.getId() == R.id.a4) {
            if (i == 4) {
                if (cr4 == 0) {
                    k += 1;
                    cr4 += 1;
                    i = 0;
                    drawView = new DrawView(this,q4,a4);
                    drawView.setBackgroundColor(Color.WHITE);
                    rl1.addView(drawView);
                    if (k == 5) {
                        new AlertClass(LineConnector.this, "Two Start", LineConnector.this, "மிக அருமை!", "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    }
                } else {
                    if (cr4 == 1) {
                        a4.setChecked(true);
                    } else {
                        a4.setChecked(false);
                    }
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                }
            } else {
                if (cr4 == 1) {
                    a4.setChecked(true);
                } else {
                    a4.setChecked(false);
                }
                if (i == 0) {
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "Select the question first", 3);
                } else {
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                }
            }
        } else if (view.getId() == R.id.q5) {
            if (i == 0) {
                i += 5;
                qr5 += 1;
            } else {
                if (qr5 == 1) {
                    q5.setChecked(true);
                } else {
                    q5.setChecked(false);
                }
                new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
            }
        } else if (view.getId() == R.id.a5) {
            if (i == 5) {
                if (cr5 == 0) {
                    k += 1;
                    cr5 += 1;
                    i = 0;
                    drawView = new DrawView(this,q5,a5);
                    drawView.setBackgroundColor(Color.WHITE);
                    rl1.addView(drawView);
                    if (k == 5) {
                        new AlertClass(LineConnector.this, "Two Start", LineConnector.this, "மிக அருமை!", "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    }
                } else {
                    if (cr5 == 1) {
                        a5.setChecked(true);
                    } else {
                        a5.setChecked(false);
                    }
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                }
            } else {
                if (cr5 == 1) {
                    a5.setChecked(true);
                } else {
                    a5.setChecked(false);
                }
                if (i == 0) {
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "Select the question first", 3);
                } else {
                    new AlertClass(LineConnector.this, "", LineConnector.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                }
            }
        } else if (view.getId() == R.id.back_major_header) {
            backClicked();
        }
    }

    public class DrawView extends View {

        Paint paint = new Paint();
        View startView;
        View endView;

        public DrawView(Context context, View startView, View endView) {
            super(context);
            paint.setColor(getResources().getColor(R.color.colorAccent));
            paint.setStrokeWidth(10f);
            paint.setAntiAlias(true);
            this.startView = startView;
            this.endView = endView;
        }

        @SuppressLint("NewApi")
        public void onDraw(Canvas canvas) {

            if (k == 1) {
                m1 = startView.getX()+(startView.getWidth() - Math.round(startView.getWidth()/7.3));
                m2 = startView.getY()+(startView.getHeight() - Math.round(startView.getHeight()/1.75));
                m3 = endView.getX()+Math.round(startView.getWidth()/7.3);
                m4 = endView.getY()+Math.round(startView.getHeight()/1.75);
                canvas.drawLine(m1, m2, m3, m4, paint);
            }

            if (k == 2) {
                e1 = startView.getX()+(startView.getWidth() - Math.round(startView.getWidth()/7.3));
                e2 = startView.getY()+(startView.getHeight() - Math.round(startView.getHeight()/1.75));
                e3 = endView.getX()+Math.round(startView.getWidth()/7.3);
                e4 = endView.getY()+Math.round(startView.getHeight()/1.75);
                canvas.drawLine(m1, m2, m3, m4, paint);
                canvas.drawLine(e1, e2, e3, e4, paint);
            }

            if (k == 3) {
                r1 = startView.getX()+(startView.getWidth() - Math.round(startView.getWidth()/7.3));
                r2 = startView.getY()+(startView.getHeight() - Math.round(startView.getHeight()/1.75));
                r3 = endView.getX()+Math.round(startView.getWidth()/7.3);
                r4 = endView.getY()+Math.round(startView.getHeight()/1.75);
                canvas.drawLine(m1, m2, m3, m4, paint);
                canvas.drawLine(e1, e2, e3, e4, paint);
                canvas.drawLine(r1, r2, r3, r4, paint);
            }

            if (k == 4) {
                p1 = startView.getX()+(startView.getWidth() - Math.round(startView.getWidth()/7.3));
                p2 = startView.getY()+(startView.getHeight() - Math.round(startView.getHeight()/1.75));
                p3 = endView.getX()+Math.round(startView.getWidth()/7.3);
                p4 = endView.getY()+Math.round(startView.getHeight()/1.75);
                canvas.drawLine(m1, m2, m3, m4, paint);
                canvas.drawLine(p1, p2, p3, p4, paint);
                canvas.drawLine(e1, e2, e3, e4, paint);
                canvas.drawLine(r1, r2, r3, r4, paint);
            }

            if (k == 5) {
                d1 = startView.getX()+(startView.getWidth() - Math.round(startView.getWidth()/7.3));
                d2 = startView.getY()+(startView.getHeight() - Math.round(startView.getHeight()/1.75));
                d3 = endView.getX()+Math.round(startView.getWidth()/7.3);
                d4 = endView.getY()+Math.round(startView.getHeight()/1.75);
                canvas.drawLine(m1, m2, m3, m4, paint);
                canvas.drawLine(d1, d2, d3, d4, paint);
                canvas.drawLine(p1, p2, p3, p4, paint);
                canvas.drawLine(e1, e2, e3, e4, paint);
                canvas.drawLine(r1, r2, r3, r4, paint);
            }

            if (k == 6) {
                f1 = startView.getX()+(startView.getWidth() - Math.round(startView.getWidth()/7.3));
                f2 = startView.getY()+(startView.getHeight() - Math.round(startView.getHeight()/1.75));
                f3 = endView.getX()+Math.round(startView.getWidth()/7.3);
                f4 = endView.getY()+Math.round(startView.getHeight()/1.75);
                canvas.drawLine(m1, m2, m3, m4, paint);
                canvas.drawLine(d1, d2, d3, d4, paint);
                canvas.drawLine(p1, p2, p3, p4, paint);
                canvas.drawLine(e1, e2, e3, e4, paint);
                canvas.drawLine(r1, r2, r3, r4, paint);
                canvas.drawLine(f1, f2, f3, f4, paint);
            }

            if (k == 7) {
                h1 = startView.getX()+(startView.getWidth() - Math.round(startView.getWidth()/7.3));
                h2 = startView.getY()+(startView.getHeight() - Math.round(startView.getHeight()/1.75));
                h3 = endView.getX()+Math.round(startView.getWidth()/7.3);
                h4 = endView.getY()+Math.round(startView.getHeight()/1.75);
                canvas.drawLine(m1, m2, m3, m4, paint);
                canvas.drawLine(d1, d2, d3, d4, paint);
                canvas.drawLine(p1, p2, p3, p4, paint);
                canvas.drawLine(e1, e2, e3, e4, paint);
                canvas.drawLine(r1, r2, r3, r4, paint);
                canvas.drawLine(f1, f2, f3, f4, paint);
                canvas.drawLine(h1, h2, h3, h4, paint);
            }
        }

    }

}