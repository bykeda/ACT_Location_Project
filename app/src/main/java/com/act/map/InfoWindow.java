package com.act.map;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

public class InfoWindow implements GoogleMap.InfoWindowAdapter {

  private Context context;

    public InfoWindow(Context ctx) {
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.popup,null);

        TextView locname = view.findViewById(R.id.loc_name);
        TextView locdesc = view.findViewById(R.id.loc_desc);
        TextView loclonglatt = view.findViewById(R.id.loc_longlatt);
        TextView locdatetime = view.findViewById(R.id.loc_datetime);

        InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();

        locname.setText(infoWindowData.getLocName());
        locdesc.setText(infoWindowData.getLocDesc());
        loclonglatt.setText(infoWindowData.getLocLongLatt());
        locdatetime.setText(infoWindowData.getLocDateTime());

        return view;
    }
}
