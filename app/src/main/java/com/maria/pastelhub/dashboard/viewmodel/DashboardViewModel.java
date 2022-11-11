package com.maria.pastelhub.dashboard.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.maria.pastelhub.R;
import com.maria.pastelhub.api.Login;
import com.maria.pastelhub.api.Results;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.dashboard.interfaces.DashBoardResultCallbacks;
import com.maria.pastelhub.dashboard.model.DashboardUser;
import com.maria.pastelhub.forgetpassword.interfaces.ForgetResultCallbacks;
import com.maria.pastelhub.forgetpassword.model.ForgetUser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends ViewModel {

    public String id = "";
    public String name = "";
    public String classes = "";
    public String rating = "0                             ";
    public int image = 0;
    public int bookLanguage = 0;
    public String teacher = "";
    public float price;
    private DashBoardResultCallbacks forgetResultCallbacks;

    public DashboardViewModel(DashBoardResultCallbacks forgetResultCallbacks) {
        this.forgetResultCallbacks = forgetResultCallbacks;
    }

    public MutableLiveData<ArrayList<DashboardViewModel>> arrayListMutableLiveData = new MutableLiveData<>();

    public ArrayList<DashboardViewModel> dashboardViewModels;

    public DashboardViewModel() {
    }

    public DashboardViewModel(DashboardUser dashboardUser) {
        this.id = dashboardUser.id;
        this.name = dashboardUser.name;
        this.classes = dashboardUser.classes;
        this.rating = dashboardUser.rating;
        this.image = dashboardUser.image;
        this.bookLanguage = dashboardUser.bookLanguage;
        this.teacher = dashboardUser.teacher;
        this.price = dashboardUser.price;

    }

    public int getImage() {
        return image;
    }

    public String getRating() {
        return rating + "/ 5";
    }

    public Float getRatingFloat() {
        return Float.parseFloat(rating);
    }

    @BindingAdapter(value = "imageUrl")
    public static void loadImage(ImageView imageView, int imageUrl) {
        Picasso.get().load(imageUrl).fit().into(imageView);
//        Glide.with(imageView).load(imageUrl).placeholder(R.drawable.default_book);
    }

    public MutableLiveData<ArrayList<DashboardViewModel>> getArrayListMutableLiveData() {
        dashboardViewModels = new ArrayList<>();
        DashboardUser dashboardUser = new DashboardUser("1", "Kids Catechism", "LKG", "3.5", R.drawable.catechism_lkg, 1).setTeacher("Rev. Fr. George Chinnappan SDB.").setPrice(65); //Rev. Fr. George Chinnappan SDB, General Secretary, TNBC Commission for Catechetics, TNBCLC, Tindivanam
        DashboardViewModel dashboardViewModel = new DashboardViewModel(dashboardUser);
        DashboardUser dashboardUser1 = new DashboardUser("2", "Kids Catechism", "UKG", "5.0", R.drawable.catechism_ukg, 1).setTeacher("Rev. Fr. George Chinnappan SDB.").setPrice(65);
        DashboardViewModel dashboardViewModel1 = new DashboardViewModel(dashboardUser1);
        DashboardUser dashboardUser2 = new DashboardUser("3", "God who Creates, Saves and Guides", "Class 1", "5.0", R.drawable.catechism_class_1, 1).setTeacher("Rev. Fr. George Chinnappan SDB.").setPrice(85);
        DashboardViewModel dashboardViewModelC1 = new DashboardViewModel(dashboardUser2);
        DashboardUser dashboardUser3 = new DashboardUser("4", "God Who Protects and Provides", "Class 2", "5.0", R.drawable.catechism_class_2, 1).setTeacher("Rev. Fr. George Chinnappan SDB.").setPrice(85);
        DashboardViewModel dashboardViewModelC2 = new DashboardViewModel(dashboardUser3);
        DashboardUser dashboardUser4 = new DashboardUser("5", "God Who Strengthens through His Presence", "Class 3", "5.0", R.drawable.catechism_class_3, 1).setTeacher("Rev. Fr. George Chinnappan SDB.").setPrice(85);
        DashboardViewModel dashboardViewModelC3 = new DashboardViewModel(dashboardUser4);
        DashboardUser dashboardUser5 = new DashboardUser("6", "God Who Calls, Anoints and Sends", "Class 4", "5.0", R.drawable.catechism_class_4, 1).setTeacher("Rev. Fr. George Chinnappan SDB.").setPrice(85);
        DashboardViewModel dashboardViewModelC4 = new DashboardViewModel(dashboardUser5);
        DashboardUser dashboardUser6 = new DashboardUser("7", "Sacraments: God's Gift for a New Life", "Class 5", "5.0", R.drawable.catechism_class_5, 1).setTeacher("Rev. Fr. George Chinnappan SDB.").setPrice(85);
        DashboardViewModel dashboardViewModelC5 = new DashboardViewModel(dashboardUser6);
        dashboardViewModels.add(dashboardViewModel);
        dashboardViewModels.add(dashboardViewModel1);
        dashboardViewModels.add(dashboardViewModelC1);
        dashboardViewModels.add(dashboardViewModelC2);
        dashboardViewModels.add(dashboardViewModelC3);
        dashboardViewModels.add(dashboardViewModelC4);
        dashboardViewModels.add(dashboardViewModelC5);
        arrayListMutableLiveData.setValue(dashboardViewModels);

        return arrayListMutableLiveData;
    }

    public MutableLiveData<ArrayList<DashboardViewModel>> getArrayListMutableLiveData1() {
        dashboardViewModels = new ArrayList<>();
        DashboardUser dashboardUser = new DashboardUser("1", "மழலையர் மறைக்கல்வி", "LKG & UKG", "3.5", R.drawable.mazhalaiyarmaraikkalvi, 0).setTeacher("அருள்பணி. ஜார்ஜ் சின்னப்பன் ச.ச,").setPrice(65);
        DashboardViewModel dashboardViewModel = new DashboardViewModel(dashboardUser);
        DashboardUser dashboardUser1 = new DashboardUser("2", "படைத்து, மீட்டு வழிநடத்தும் கடவுள்", "Class 1", "5.0", R.drawable.maraikkalvi1, 0).setTeacher("அருள்பணி. ஜார்ஜ் சின்னப்பன் ச.ச,").setPrice(85);
        DashboardViewModel dashboardViewModel1 = new DashboardViewModel(dashboardUser1);
        DashboardUser dashboardUser3 = new DashboardUser("3", "பாதுகாத்து பராமரிக்கும் கடவுள்", "Class 2", "2.7", R.drawable.maraikkalvi2, 0).setTeacher("அருள்பணி. ஜார்ஜ் சின்னப்பன் ச.ச,").setPrice(85);
        DashboardViewModel dashboardViewModel3 = new DashboardViewModel(dashboardUser3);
        DashboardUser dashboardUser4 = new DashboardUser("4", "உடனிருந்து உறுதியூட்டும் கடவுள்", "Class 3", "3.3", R.drawable.maraikkalvi3, 0).setTeacher("அருள்பணி. ஜார்ஜ் சின்னப்பன் ச.ச,").setPrice(85);
        DashboardViewModel dashboardViewModel4 = new DashboardViewModel(dashboardUser4);
        DashboardUser dashboardUser5 = new DashboardUser("5", "அழைத்து ஆசிர்வதித்து அனுப்பும் கடவுள்", "Class 4", "4.7", R.drawable.maraikkalvi4, 0).setTeacher("அருள்பணி. ஜார்ஜ் சின்னப்பன் ச.ச,").setPrice(85);
        DashboardViewModel dashboardViewModel5 = new DashboardViewModel(dashboardUser5);
        DashboardUser dashboardUser6 = new DashboardUser("6", "புதுவாழ்வைக் கொடையாக வழங்கும் கடவுள்", "Class 5", "4.2", R.drawable.maraikkalvi5, 0).setTeacher("அருள்பணி. ஜார்ஜ் சின்னப்பன் ச.ச,").setPrice(85);
        DashboardViewModel dashboardViewModel6 = new DashboardViewModel(dashboardUser6);
        dashboardViewModels.add(dashboardViewModel);
        dashboardViewModels.add(dashboardViewModel1);
        dashboardViewModels.add(dashboardViewModel3);
        dashboardViewModels.add(dashboardViewModel4);
        dashboardViewModels.add(dashboardViewModel5);
        dashboardViewModels.add(dashboardViewModel6);
        arrayListMutableLiveData.setValue(dashboardViewModels);

        return arrayListMutableLiveData;
    }


}
