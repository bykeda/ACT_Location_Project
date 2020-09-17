package com.act.map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;


public class MapsActivity3 extends AppCompatActivity implements OnMapReadyCallback {
    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 23;
    private GoogleMap mMap;
    Button btn;

    LocationViewModel mLocViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        checkPermission();

        final LocationListAdapter adapter = new LocationListAdapter(this);
        mLocViewModel = new ViewModelProvider(this).get(LocationViewModel.class);
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
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

    /*   @Override
       public void onMapReady(GoogleMap googleMap) {
           mMap = googleMap;
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
           mMap.setMyLocationEnabled(true);
           mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
               @Override
               public void onMyLocationChange(Location location) {
                   LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
                   MarkerOptions markerOptions = new MarkerOptions();
                   markerOptions.position(latlng);

                   markerOptions.title("My Marker");
                   mMap.clear();
                   CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 15);
                   mMap.animateCamera(cameraUpdate);
                   mMap.addMarker(markerOptions);
               }
           });
       }*/
    @Override
    public void onMapReady(GoogleMap googleMap) {

        final LatLng[] allLongLatt = {null};
        final String[] newSnippet = new String[1];
        mMap = googleMap;

        MarkerOptions markerOptions = new MarkerOptions();
        /*
        int height = 100;
        int width = 100;

        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.ic_baseline_location_on_24);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        */
        mLocViewModel.getAllLocation().observe(this, new Observer<List<LocationM>>() {
            @Override
            public void onChanged(@Nullable final List<LocationM> locations) {
                for (LocationM temp : locations) {
                    allLongLatt[0] = new LatLng(temp.getLocLatt(), temp.getLocLong());
                    newSnippet[0] = String.format(Locale.getDefault(), "Desc: %s\n,Lat: %2$.5f, Long: %3$.5f", temp.getLocDesc(), allLongLatt[0].latitude, allLongLatt[0].longitude);
                    /*
                   mMap.addMarker(
                            new MarkerOptions()
                                    .position(allLongLatt[0])
                                    .title(temp.getLocName())
                                    .snippet(newSnippet[0])
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                    );
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(allLongLatt[0], 12));
                    */

                    markerOptions.position(allLongLatt[0])
                            .title(temp.getLocName())
                            .snippet(newSnippet[0])
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                    InfoWindowData info = new InfoWindowData();
                    info.setLocName("Location Name " + temp.getLocName());
                    info.setLocDesc("Location Desc " + temp.getLocDesc());
                    info.setLocLongLatt("Latt " + temp.getLocLatt().toString() + "  Long " + temp.getLocLong().toString());
                    info.setLocDateTime("Location Time " + temp.getLocDateTime());

                    //InfoWindow customInfoWindow = new InfoWindow(MapsActivity3.this);
                    //mMap.setInfoWindowAdapter(customInfoWindow);

                    Marker m = mMap.addMarker(markerOptions);
                    m.setTag(info);
                    m.showInfoWindow();

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(allLongLatt[0], 20));
                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(allLongLatt[0]));

                }
            }
        });

        mMap.setInfoWindowAdapter(new InfoWindow(this));

       /* mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                String snippet = String.format(Locale.getDefault(), "Lat: %1$.5f, Long: %2$.5f", latLng.latitude, latLng.longitude);
                mMap.addMarker(new MarkerOptions().position(latLng).title("Dropped Pin").snippet(snippet));
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}