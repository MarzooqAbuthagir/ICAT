package com.maria.pastelhub.dashboard.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.maria.pastelhub.api.Login;
import com.maria.pastelhub.api.Results;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.book_landing.BookLanding;
import com.maria.pastelhub.dashboard.Dashboard;
import com.maria.pastelhub.dashboard.viewmodel.DashboardViewModel;
import com.maria.pastelhub.databinding.BookBinding;
import com.maria.pastelhub.R;
import com.maria.pastelhub.emptystatus.EmptyView;
import com.maria.pastelhub.prefference.Pref;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Tag;

import static android.view.View.GONE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<DashboardViewModel> arrayList;
    private Context context;
    private BookBinding bookBinding;
    Pref pref;

    public MyAdapter(ArrayList<DashboardViewModel> arrayList, Context context) {

        this.arrayList = arrayList;
        this.context = context;
        pref = new Pref(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_content, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        bookBinding.setViewModel(arrayList.get(position));
        DashboardViewModel model = arrayList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.bookLanguage == 1 && model.classes.contains("LKG") || model.classes.contains("UKG")) {
                    Pref pref = new Pref(context);
                    if (pref.getPreference("country").equalsIgnoreCase("India")
                            && (pref.getPreference("state").equalsIgnoreCase("Tamil Nadu") ||
                            pref.getPreference("state").equalsIgnoreCase("Puducherry") ||
                            pref.getPreference("state").equalsIgnoreCase("Pondicherry"))
                            && pref.getPreference("is_subscription").equals("0")) {
                        alertRegisterFillPopup(context);
                    } else if (pref.getPreference("country").equalsIgnoreCase("India")
                            && (!pref.getPreference("state").equalsIgnoreCase("Tamil Nadu") ||
                            !pref.getPreference("state").equalsIgnoreCase("Puducherry") ||
                            !pref.getPreference("state").equalsIgnoreCase("Pondicherry"))
                            && pref.getPreference("is_subscription").equals("0")) {
                        int amount = 100 * 100;
                        startPayment(amount, "INR");
                    } else if (!pref.getPreference("country").equalsIgnoreCase("India")) {
//                            && !pref.getPreference("is_subscription").equals("1")) {
//                        int amount = 5 * 100;
//                        startPayment(amount, "USD");
                        Intent intent = new Intent(context, BookLanding.class);
                        context.startActivity(intent.putExtra("ID", position)
                                .putExtra("language", "" + model.bookLanguage)
                                .putExtra("BookName", model.name)
                                .putExtra("Rating", model.rating)
                                .putExtra("class", model.classes)
                                .putExtra("Image", model.image).putExtra("Teacher", model.teacher).putExtra("Price", model.price));
                    } else {
                        Intent intent = new Intent(context, BookLanding.class);
                        context.startActivity(intent.putExtra("ID", position)
                                .putExtra("language", "" + model.bookLanguage)
                                .putExtra("BookName", model.name)
                                .putExtra("Rating", model.rating)
                                .putExtra("class", model.classes)
                                .putExtra("Image", model.image).putExtra("Teacher", model.teacher).putExtra("Price", model.price));
                    }
                } else if (model.bookLanguage == 0) {
                    Pref pref = new Pref(context);
                    if (pref.getPreference("country").equalsIgnoreCase("India")
                            && (pref.getPreference("state").equalsIgnoreCase("Tamil Nadu") ||
                            pref.getPreference("state").equalsIgnoreCase("Puducherry") ||
                            pref.getPreference("state").equalsIgnoreCase("Pondicherry"))
                            && pref.getPreference("is_subscription").equals("0")) {
                        alertRegisterFillPopup(context);
                    } else if (pref.getPreference("country").equalsIgnoreCase("India")
                            && (!pref.getPreference("state").equalsIgnoreCase("Tamil Nadu") ||
                            !pref.getPreference("state").equalsIgnoreCase("Puducherry") ||
                            !pref.getPreference("state").equalsIgnoreCase("Pondicherry"))
                            && pref.getPreference("is_subscription").equals("0")) {
                        int amount = 100 * 100;
                        startPayment(amount, "INR");
                    } else if (!pref.getPreference("country").equalsIgnoreCase("India")){
//                            && !pref.getPreference("is_subscription").equals("1")) {
//                        int amount = 5 * 100;
//                        startPayment(amount, "USD");
                        Intent intent = new Intent(context, BookLanding.class);
                        context.startActivity(intent.putExtra("ID", position)
                                .putExtra("language", "" + model.bookLanguage)
                                .putExtra("BookName", model.name)
                                .putExtra("Rating", model.rating)
                                .putExtra("class", model.classes)
                                .putExtra("Image", model.image).putExtra("Teacher", model.teacher).putExtra("Price", model.price));
                    } else {
                        Intent intent = new Intent(context, BookLanding.class);
                        context.startActivity(intent.putExtra("ID", position)
                                .putExtra("language", "" + model.bookLanguage)
                                .putExtra("BookName", model.name)
                                .putExtra("Rating", model.rating)
                                .putExtra("class", model.classes)
                                .putExtra("Image", model.image).putExtra("Teacher", model.teacher).putExtra("Price", model.price));
                    }
                } else {
                    context.startActivity(new Intent(context, EmptyView.class));
                    ((Activity) context).finish();
                }
            }
        });

    }

    void alertRegisterFillPopup(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.alert_subsicription, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();

        Button button_text = dialogView.findViewById(R.id.button_text);
        ImageView btnCancel = dialogView.findViewById(R.id.ivCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        button_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hided by Abu, for temporary subscription solution
//                startPayment();
                pref.setPreference("is_subscription", "1");
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    public void startPayment(int amount, String currency) {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
//        final Activity activity = context;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", currency);
            options.put("amount", amount);

            JSONObject preFill = new JSONObject();
            preFill.put("email", pref.getPreference("email"));
            preFill.put("contact", pref.getPreference("contact"));

            options.put("prefill", preFill);

            co.open((Activity) context, options);
        } catch (Exception e) {
            Toast.makeText(context, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

//    @Override
//    public void onPaymentSuccess(String razorpayPaymentID) {
//        try {
////            hided by Abu, for temporary subscription solution
////            pay();
//            pref.setPreference("is_subscription", "1");
//        } catch (Exception e) {
//            Log.e(getClass().getName(), "Exception in onPaymentSuccess", e);
//        }
//    }
//
//    @Override
//    public void onPaymentError(int code, String response) {
//        try {
//            Toast.makeText(context, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Log.e(getClass().getName(), "Exception in onPaymentError", e);
//        }
//    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        public TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);
            bookBinding = DataBindingUtil.bind(itemView);
            itemView.setTag(bookBinding);
        }
    }

    private void pay() {
        Call<Login> call = RetrofitClient.getInstance().getMyApi().pay();
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<com.maria.pastelhub.api.Login> call, Response<Login> response) {
                com.maria.pastelhub.api.Login r = response.body();
                Gson g = new Gson();
//                Log.i(getClass().getName(), "=================error 1  " + g.toJson(response));
                if (r.getStatus() == 200) {
                    Toast.makeText(context, "Payment Successful: ", Toast.LENGTH_SHORT).show();
                    pref.setPreference("is_subscription", "1");
                } else {

                }
            }

            @Override
            public void onFailure(Call<com.maria.pastelhub.api.Login> call, Throwable t) {
                Log.i(getClass().getName(), "=================error   " + call.request().url());

            }

        });
    }

    private void getDetails() {
        Call<Login> call = RetrofitClient.getInstance().getMyApi().getDetails(Integer.parseInt(pref.getPreference("id")));
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
//                List<Results> myheroList = response.body();
//                String[] oneHeroes = new String[myheroList.size()];
                pref.setPreference("is_subscription", "1");
                Log.i(getClass().getName(), "=================" + pref.getLesson());

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.i(getClass().getName(), "=================error   " + t.getMessage());
            }

        });
    }

}