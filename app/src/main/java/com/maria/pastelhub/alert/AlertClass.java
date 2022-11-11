package com.maria.pastelhub.alert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.maria.pastelhub.R;
import com.maria.pastelhub.activity.BaseActivityExtends;
import com.maria.pastelhub.login.Login;

public class AlertClass extends AppCompatActivity {

    String title;
    String from;
    String sub_title;
    int type_alert;
    Context context;
    Activity activity;
    Animation from_small, from_nothing;
    TextView title_alert;
    Button button_text;
    CardView card_icon;
    ImageView alert_icon;
    TextView subtitle_alert;
    ImageView tv_starts;
    CardView card_content;

    // Alert type 1 for return failure
    // Alert type 2 for return success
    // Alert type 3 for return failure for activity
    // Alert type 4 for return success for activity
    // Alert type 5 for return information

    public AlertClass(Activity activity, String from, Context context, String title, String sub_title, int type_alert) {
        this.title = title;
        this.sub_title = sub_title;
        this.type_alert = type_alert;
        this.context = context;
        this.activity = activity;
        this.from = from;
        showAlert(this.title, this.sub_title, this.type_alert, this.context);
    }

    void showAlert(String title, String sub_title, int type_alert, Context context) {

        from_small = AnimationUtils.loadAnimation(context, R.anim.from_small);
        from_nothing = AnimationUtils.loadAnimation(context, R.anim.from_nothing);

        // Alert Dialogue Builder with context
        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        // Getting alert_dialogue.xml from layout and set to file
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialogue, null);

        // Setting up view in builder
        alert.setView(view);

        // Set on click cancelable false
        alert.setCancelable(false);

        // Creating dialogue and fixing builder in it
        final AlertDialog dialog = alert.create();

        // Initializing dialogue variable
        title_alert = view.findViewById(R.id.title_alert);
        button_text = view.findViewById(R.id.button_text);
        tv_starts = view.findViewById(R.id.tv_starts);
        card_icon = view.findViewById(R.id.card_icon);
        alert_icon = view.findViewById(R.id.alert_icon);
        subtitle_alert = view.findViewById(R.id.subtitle_alert);
        card_content = view.findViewById(R.id.card_content);


        // Animation part
        card_content.setAlpha(1);
        card_icon.setAlpha(1);
        card_content.startAnimation(from_small);
        card_icon.startAnimation(from_nothing);

        // Button text
        if (from.equalsIgnoreCase("Login")||from.equalsIgnoreCase("Two")) {
            button_text.setText("Ok");
        } else {
            button_text.setText("சரி");
        }

        if (from.equals("Two Start")||from.equalsIgnoreCase("Two")) {
            tv_starts.setVisibility(View.VISIBLE);
        } else {
            tv_starts.setVisibility(View.GONE);
        }

        Log.i("------","------"+type_alert);
        // Setting title
        title_alert.setText(title);

        // Setting message text
        subtitle_alert.setText(sub_title);

        // Getting type and validate the alert
        if (type_alert == 1 || type_alert == 3) {
            button_text.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            card_icon.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary));
            alert_icon.setImageResource(R.drawable.close_blue);
            title_alert.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
            if (type_alert == 3) {
                final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.wrong);
                mediaPlayer.start();
            }
        } else if (type_alert == 2 || type_alert == 4) {
            button_text.setBackgroundColor(context.getResources().getColor(R.color.success_green));
            card_icon.setCardBackgroundColor(ContextCompat.getColor(context,R.color.success_green));
            alert_icon.setImageResource(R.drawable.tick);
            title_alert.setTextColor(ContextCompat.getColor(context,R.color.success_green));
            if (type_alert == 4) {
                final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.correct);
                mediaPlayer.start();
            }
        } else if (type_alert == 5 || type_alert == 6) {
            button_text.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            card_icon.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary));
            alert_icon.setImageResource(R.drawable.mail_alert);
            title_alert.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
        }

        // Setting button click on the alert class
        view.findViewById(R.id.button_text).setOnClickListener(view1 -> {
            dialog.dismiss();
            if (type_alert == 4) {
                //  context.startActivity(new Intent(context, BaseActivityExtends.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                ((Activity)context).finish();
                ((Activity)context).overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            } else if (type_alert == 5) {
                context.startActivity(new Intent(context, Login.class));
                ((Activity)context). finish();
            }
        });

        // Checking window null and managing background of the alert dialogue
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        // Displaying dialogue
        dialog.show();
    }

}
