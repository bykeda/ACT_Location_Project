package com.act.map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Home extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    EditText nameLoc, descLoc;
    Button save, view;
    Double longtude = 0.0;
    Double latitude = 0.0;

    /*Location Longtiude and Lattitude*/

    private static final String[] requestList = new String[]{ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION};
    private static final int PERMISSION_REQUEST_CODE = 200;

    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private FusedLocationProviderClient mFusedLocationClient;

    /*Database*/
    private LocationViewModel locViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        nameLoc = findViewById(R.id.nameloc);
        descLoc = findViewById(R.id.descloc);
        save = findViewById(R.id.saveloc);
        view = findViewById(R.id.view);

        /*Location Long and Latt*/
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(20 * 1000);
        mLocationCallback = getLocationCallBack();

        if (checkPermission()) {
            updateLocation();
        }/* if (checkPermission()) {
                    updateLocation();
                } else {
                    ActivityCompat.requestPermissions(null,requestList,
                            PERMISSION_REQUEST_CODE);
                }*/

        /*Database*/

        locViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("E dd/MMM/yyyy HH:mm:ss", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());

                LocationM loc = new LocationM();

                loc.setLocName(nameLoc.getText().toString());
                loc.setLocDesc(descLoc.getText().toString());
                loc.setLocDateTime(currentDateandTime);

                if (checkPermission()) {
                    updateLocation();
                }

                loc.setLocLong(longtude);
                loc.setLocLatt(latitude);

                if (nameLoc.getText().toString().equals("") || descLoc.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Location Name or Location Description Empty", Toast.LENGTH_LONG).show();
                } else {
                    locViewModel.insert(loc);
                    Toast.makeText(getBaseContext(), "Location Details Inserted Successfully", Toast.LENGTH_LONG).show();
                    nameLoc.setText("");
                    descLoc.setText("");
                }

            }
        });
    }

    private void updateLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.getMainLooper());
    }

    private boolean checkPermission() {
        int resultCoarse = ContextCompat.checkSelfPermission(getApplicationContext(),
                requestList[0]);
        int resultFine = ContextCompat.checkSelfPermission(getApplicationContext(),
                requestList[1]);

        return resultCoarse == PackageManager.PERMISSION_GRANTED
                && resultFine == PackageManager.PERMISSION_GRANTED;
    }

    private LocationCallback getLocationCallBack() {
        //final TextView t = findViewById(R.id.textView);
        return new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    String message = "Location Update Failed";
                    longtude = 0.0;
                    latitude = 0.0;
                    //t.setText(message);
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        //String message = "Current Location" + " Latitude: " + location.getLatitude() +" Longitude: " + location.getLongitude();
                        longtude = location.getLongitude();
                        latitude = location.getLatitude();
                        //t.setText(message);
                        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
                        return;
                    }
                }
                mFusedLocationClient.removeLocationUpdates(mLocationCallback);
                String message = "Location Update Failed";
                longtude = 0.0;
                latitude = 0.0;
                //t.setText(message);
            }
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            boolean coarseLocationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            boolean fineLocationAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

            if (coarseLocationAccepted && fineLocationAccepted) {
                updateLocation();
            } else {
                longtude = 0.0;
                latitude = 0.0;
                //TextView t = findViewById(R.id.textView);
                //String message = "Unable to display location data, you need to change location permissions manually.";
                //t.setText(message);
            }

        }
    }

    public void viewLocations(View view) {
        Intent intent = new Intent(view.getContext(), ViewLocations.class);
        view.getContext().startActivity(intent);
    }

    public void showMap(View view) {
        Intent intent = new Intent(view.getContext(), MapsActivity3.class);
        view.getContext().startActivity(intent);
    }

    public void viewLattLong(View view) {
        Intent intent = new Intent(view.getContext(), LongLatt.class);
        view.getContext().startActivity(intent);
    }


}

