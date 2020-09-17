package com.act.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class LongLatt extends AppCompatActivity {

    private static final String[] requestList = new String[]{ACCESS_COARSE_LOCATION,            ACCESS_FINE_LOCATION    };
    private static final int PERMISSION_REQUEST_CODE = 200;

    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.longlat);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(20 * 1000);

        mLocationCallback = getLocationCallBack();

    }

    public void onUpdateLocationClick(View v) {
        if (checkPermission()) {
            updateLocation();
        } else {
            ActivityCompat.requestPermissions(this, requestList,PERMISSION_REQUEST_CODE);
        }

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

    private LocationCallback getLocationCallBack() {
        final TextView t = findViewById(R.id.textView);
        return new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    String message = "Location Update Failed";
                    t.setText(message);
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        String message = "Current Location" +
                                " Latitude: " + location.getLatitude() +
                                " Longitude: " + location.getLongitude();
                        t.setText(message);
                        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
                        return;
                    }
                }
                mFusedLocationClient.removeLocationUpdates(mLocationCallback);
                String message = "Location Update Failed";
                t.setText(message);
            }
        };
    }

    private boolean checkPermission() {
        int resultCoarse = ContextCompat.checkSelfPermission(getApplicationContext(),
                requestList[0]);
        int resultFine = ContextCompat.checkSelfPermission(getApplicationContext(),
                requestList[1]);

        return resultCoarse == PackageManager.PERMISSION_GRANTED
                && resultFine == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            boolean coarseLocationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            boolean fineLocationAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

            if (coarseLocationAccepted && fineLocationAccepted) {
                updateLocation();
            } else {
                TextView t = findViewById(R.id.textView);
                String message = "Unable to display location data, you need to change location permissions manually.";
                t.setText(message);
            }

        }
    }

}