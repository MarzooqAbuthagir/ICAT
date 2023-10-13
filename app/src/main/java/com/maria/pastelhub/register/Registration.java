package com.maria.pastelhub.register;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.maria.pastelhub.R;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.databinding.ActivityRegistrationBinding;
import com.maria.pastelhub.login.Login;
import com.maria.pastelhub.register.interfaces.RegisterResultCallbacks;
import com.maria.pastelhub.register.viewmodel.RegistrationViewModel;
import com.maria.pastelhub.register.viewmodel.RegistrationViewModelFactory;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Registration extends AppCompatActivity implements RegisterResultCallbacks, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    static LocationManager locationManager;
    public static String latitude = "0.0";
    public static String longitude = "0.0";
    //    private static final int REQUEST_LOCATION = 1;
    public static Context context;
    public static String state = "", country = "";

    private static final int REQUEST_CODE = 101;

    GoogleApiClient gac;
    LocationRequest locationRequest;
    static final int REQUEST_CODE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        fetchLastLocation();
//        ActivityCompat.requestPermissions(this,
//                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        ActivityRegistrationBinding activityRegistrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        activityRegistrationBinding.setViewModel(ViewModelProviders.of(
                        this,
                        new RegistrationViewModelFactory(this))
                .get(RegistrationViewModel.class));
//        try {
//            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
//            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                OnGPS();
//            } else {
//                getLocation(this);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private void fetchLastLocation() {
        if (isGpsOn()) {

            isGooglePlayServicesAvailable();
            locationRequest = new LocationRequest();
            /* 10 secs */
            long UPDATE_INTERVAL = 2 * 1000;
            locationRequest.setInterval(UPDATE_INTERVAL);
            /* 2 sec */
            long FASTEST_INTERVAL = 2000;
            locationRequest.setFastestInterval(FASTEST_INTERVAL);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            gac = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            gac.connect();

        } else {
            OnGPS();
        }
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(this, status, 2404).show();
            }
            return false;
        }
        return true;
    }

    public void loginClicked(View view) {
        toLogin();
    }

    @Override
    public void onBackPressed() {
        toLogin();
    }

    public void toLogin() {
        startActivity(new Intent(Registration.this, Login.class));
        finish();
        overridePendingTransition(R.anim.slide_in_left1, R.anim.slide_in_left);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onSuccess(String title, String message) {
        new AlertClass(Registration.this, "Two", Registration.this, title, message, 5);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onError(String title, String message) {
        new AlertClass(Registration.this, "Two", Registration.this, title, message, 1);
    }

    @Override
    public void onLocReq() {
        fetchLastLocation();
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, REQUEST_CODE);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

//    public static void getLocation(Context context) {
//        if (ActivityCompat.checkSelfPermission(
//                context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
//        } else {
//            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if (locationGPS != null) {
//                double lat = locationGPS.getLatitude();
//                double longi = locationGPS.getLongitude();
//                latitude = String.valueOf(lat);
//                longitude = String.valueOf(longi);
//                getAddress(context, lat, longi);
////                showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
//            } else {
//                Toast.makeText(context, "Unable to find location.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    public static void getAddress(Context context, double LATITUDE, double LONGITUDE) {
        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);

            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                state = addresses.get(0).getAdminArea();
                country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                Log.d("Tag", "getAddress:  address" + address);
                Log.d("=========", "getAddress:  city" + city);
                Log.d("TAG", "getAddress:  state" + state);
                Log.d("TAG", "getAddress:  state" + country);
                Log.d("TAG", "getAddress:  postalCode" + postalCode);
                Log.d("TAG", "getAddress:  knownName" + knownName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (isGpsOn()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Registration.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_LOCATION);
                return;
            }
            LocationServices.FusedLocationApi.getLastLocation(gac);
            LocationServices.FusedLocationApi.requestLocationUpdates(gac, locationRequest, this);
        }
    }

    private boolean isGpsOn() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return !(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) || !manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            latitude = String.valueOf(location.getLatitude());
            longitude = String.valueOf(location.getLongitude());

            getAddress(context, location.getLatitude(), location.getLongitude());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                System.out.println("Permission Granted");
                fetchLastLocation();
            }
        } else if (requestCode == REQUEST_CODE_LOCATION) {
            fetchLastLocation();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE || requestCode == REQUEST_CODE_LOCATION) {
            fetchLastLocation();
        }
    }
}