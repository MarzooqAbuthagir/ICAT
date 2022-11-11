package com.maria.pastelhub.forgetpassword.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.maria.pastelhub.api.Login;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.forgetpassword.interfaces.ForgetResultCallbacks;
import com.maria.pastelhub.forgetpassword.model.ForgetUser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetViewModel extends ViewModel {

    private ForgetUser forgetUser;
    private ForgetResultCallbacks forgetResultCallbacks;

    public ForgetViewModel(ForgetResultCallbacks forgetResultCallbacks) {
        this.forgetResultCallbacks = forgetResultCallbacks;
        this.forgetUser = new ForgetUser();
    }

    public TextWatcher getEmailTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                forgetUser.setMail(editable.toString());
            }
        };
    }

    public void onForgetClick(View view) {

        int errorCode = forgetUser.isValidateMail();

        if (errorCode == 1) {
            forgetResultCallbacks.onError("Failed", "Enter Email address");
        } else if (errorCode == 2) {
            forgetResultCallbacks.onError("Failed", "Enter valid Email address");
        } else {
            try {
                String d="{"+"email:"+forgetUser.getMail()+"}";
                JsonObject params = new JsonObject();
                params.addProperty("email", forgetUser.getMail());
//                JSONParser parser = new JSONParser();
//                JSONObject json = (JSONObject) parser.parse(stringToParse);
                forgot(params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void forgot(JsonObject jsonObject) {
        Call<Login> call = RetrofitClient.getInstance().getMyApi().forgot(jsonObject);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<com.maria.pastelhub.api.Login> call, Response<Login> response) {
                com.maria.pastelhub.api.Login r = response.body();
                Gson g = new Gson();
                Log.i(getClass().getName(), "=================error 1  " + g.toJson(jsonObject));
//                Log.i(getClass().getName(), "=================error 1  " + g.toJson(response));
                if (r.getStatus() == 200)
                    forgetResultCallbacks.onSuccess("Verification Mail Sent", r.getMessage());
                else forgetResultCallbacks.onError("Failed", r.getMessage());
            }

            @Override
            public void onFailure(Call<com.maria.pastelhub.api.Login> call, Throwable t) {
                Log.i(getClass().getName(), "=================error   " + call.request().url());
                forgetResultCallbacks.onError("Failed", t.getMessage());

            }

        });
    }

}
