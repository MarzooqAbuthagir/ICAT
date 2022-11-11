package com.maria.pastelhub.alert;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
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

import com.maria.pastelhub.R;

public class AlertClassExit extends AppCompatActivity {

    String title;
    String sub_title;
    int type_alert;
    Context context;
    Animation from_small, from_nothing;
    TextView title_alert;
    Button okButton, cancelButton;
    CardView card_icon;
    ImageView alert_icon;
    TextView subtitle_alert;
    CardView card_content;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public AlertClassExit(Context context, String title, String sub_title, int type_alert) {
        this.title = title;
        this.sub_title = sub_title;
        this.type_alert = type_alert;
        this.context = context;
        showAlert(this.title, this.sub_title, this.type_alert, this.context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void showAlert(String title, String sub_title, int type_alert, Context context) {

        from_small = AnimationUtils.loadAnimation(context, R.anim.from_small);
        from_nothing = AnimationUtils.loadAnimation(context, R.anim.from_nothing);

        // Alert Dialogue Builder with context
        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        // Getting alert_dialogue.xml from layout and set to file
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialogue_exit, null);

        // Setting up view in builder
        alert.setView(view);

        // Set on click cancelable false
        alert.setCancelable(false);

        // Creating dialogue and fixing builder in it
        final AlertDialog dialog = alert.create();

        // Initializing dialogue variable
        title_alert = view.findViewById(R.id.title_alert_exit);
        okButton = view.findViewById(R.id.okButton);
        cancelButton = view.findViewById(R.id.cancelButton);
        card_icon = view.findViewById(R.id.card_icon_exit);
        alert_icon = view.findViewById(R.id.alert_icon_exit);
        subtitle_alert = view.findViewById(R.id.subtitle_alert_exit);
        card_content = view.findViewById(R.id.card_content_exit);

        // Animation part
        card_content.setAlpha(1);
        card_icon.setAlpha(1);
        card_content.startAnimation(from_small);
        card_icon.startAnimation(from_nothing);

        // Setting title
        title_alert.setText(title);

        // Setting message text
        subtitle_alert.setText(sub_title);

        // Setting button click on the alert class
        view.findViewById(R.id.okButton).setOnClickListener(view1 -> {
            dialog.dismiss();
            System.exit(0);
        });

        view.findViewById(R.id.cancelButton).setOnClickListener(view1 -> {
            dialog.dismiss();
        });

        // Checking window null and managing background of the alert dialogue
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        // Displaying dialogue
        dialog.show();
    }

}
