package com.act.map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewLocations extends AppCompatActivity {

    static LocationViewModel mLocViewModel;
    Button filter, reset;
    @SuppressLint("StaticFieldLeak")
    static TextView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewlocations_new);

        filter = findViewById(R.id.filter);
        reset = findViewById(R.id.reset);
        search = findViewById(R.id.search);

        /*reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setText("");
            }
        });*/


        RecyclerView recyclerView = findViewById(R.id.locationrecyclerview);
        final LocationListAdapter adapter = new LocationListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mLocViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        mLocViewModel.getAllLocation().observe(this, locations -> {
            adapter.setLocation(locations);
        });
        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is in the foreground.
    /*    reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setText("");
                allLocation();
            }
        });*/
        //allLocation(reset);

       /* @SuppressLint("ResourceType") TextView locItemDetail = findViewById(R.layout.viewlocations_item).findViewById(R.id.location_items);
        locItemDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Clicked", Toast.LENGTH_LONG).show();
            }
        });*/
    }


    public void editLocation(View view) {
        Intent intent = new Intent(view.getContext(), EditLocation.class);
        view.getContext().startActivity(intent);
    }

    public void searchLocation(View view) {

        switch (view.getId()) {
            case R.id.reset:
                search.setText("");
        }

        RecyclerView recyclerView = findViewById(R.id.locationrecyclerview);
        final LocationListAdapter adapter = new LocationListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mLocViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        mLocViewModel.search(search.getText().toString()).observe(this, locationMS -> {
            adapter.setLocation(locationMS);
        });
    }
}
