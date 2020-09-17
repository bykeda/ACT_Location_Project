package com.act.map;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class EditLocation extends AppCompatActivity {
    static EditText editLocName, editLocDesc;
    String editLocDateTime;
    static Double editLocLong, editLocLatt;
    static Integer id;
    Button update, delete, showMap;

    private LocationViewModel locViewModel;
    /*
    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 23;
    private GoogleMap mMap;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editlocation);

        update = findViewById(R.id.editmodify);
        delete = findViewById(R.id.editDelete);
        showMap = findViewById(R.id.editShowMap);

        editLocName = findViewById(R.id.editLocName);
        editLocDesc = findViewById(R.id.editLocDesc);

        editLocName.setText(LocationListAdapter.data.locmm.getLocName());
        editLocDesc.setText(LocationListAdapter.data.locmm.getLocDesc());

        editLocLatt = LocationListAdapter.data.locmm.getLocLatt();
        editLocLong = LocationListAdapter.data.locmm.getLocLong();
        editLocDateTime = LocationListAdapter.data.locmm.getLocDateTime();
        id = LocationListAdapter.data.locmm.getLocId();

        locViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationM loc = new LocationM();

                loc.setLocName(editLocName.getText().toString());
                loc.setLocDesc(editLocDesc.getText().toString());
                loc.setLocId(id);

                locViewModel.update(loc);
                Toast.makeText(getBaseContext(), "Location Details Update Successfully", Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationM loc = new LocationM();

                loc.setLocId(id);

                locViewModel.delete(loc);
                Toast.makeText(getBaseContext(), "Location Details Deleted Successfully", Toast.LENGTH_LONG).show();
            }
        });

        /*showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setContentView(R.layout.activity_maps);
                //checkPermission();

                Intent intent = new Intent(getBaseContext(), MapsActivityEdit.class);
                intent.putExtra("editLocLong", editLocLong);
                intent.putExtra("editLocLatt", editLocLatt);
                intent.putExtra("editLocName", editLocName.getText());
                intent.putExtra("editLocDesc", editLocDesc.getText());
                intent.putExtra("editLocDateTime", editLocDateTime);

                startActivity(intent);
            }
        });*/
    }

  /*  private void checkPermission() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(this);
            }
            //mapFragment.getMapAsync(this);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng = new LatLng(editLocLatt, editLocLong);
        mMap = googleMap;

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(latLng)
                .title(editLocName.getText().toString())
                .snippet(editLocName.getText().toString())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        InfoWindowData info = new InfoWindowData();
        info.setLocName("Location Name " + editLocName.getText().toString());
        info.setLocDesc("Location Desc " + editLocDesc.getText().toString());
        info.setLocLongLatt("Latt " + editLocLatt.toString() + "  Long " + editLocLong.toString());
        info.setLocDateTime("Location Time " + editLocDateTime);

        //Marker m = mMap.addMarker(markerOptions);
        Marker m = mMap.addMarker(markerOptions);
        m.setTag(info);
        m.showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));

        mMap.setInfoWindowAdapter(new InfoWindow(this));

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
*/

    public void editShowMap(View view) {

        Intent intent = new Intent(getBaseContext(), MapsActivityEdit.class);

        intent.putExtra("editLocLong", editLocLong.toString());
        intent.putExtra("editLocLatt", editLocLatt.toString());
        intent.putExtra("editLocName", editLocName.getText().toString());
        intent.putExtra("editLocDesc", editLocDesc.getText().toString());
        intent.putExtra("editLocDateTime", editLocDateTime);

        startActivity(intent);
    }
}
