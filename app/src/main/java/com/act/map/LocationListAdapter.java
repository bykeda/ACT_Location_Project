package com.act.map;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> {

    public static class data {
        public static LocationM locmm;
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout locItemDetail;
        // private TextView locItems;

        private Integer locId;
        private TextView locName, locDesc;
        private TextView locLongLattE, locDateTimeE;
        private Double locLong;
        private Double locLatt;


        private String locDateTime;

        public LocationViewHolder(View itemView) {
            super(itemView);
           /* locItems = itemView.findViewById(R.id.location_items);
            locItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LocationM locationM = new LocationM();
                    locationM.setLocName(locItems.getText().toString());

                    data.locmm = locationM;

                    Intent intent = new Intent(view.getContext(), EditLocation.class);
                    view.getContext().startActivity(intent);
                }
            });
*/

            locName = itemView.findViewById(R.id.itemname);
            locDesc = itemView.findViewById(R.id.itemdesc);
            locLongLattE = itemView.findViewById(R.id.itemlonglatt);
            locDateTimeE = itemView.findViewById(R.id.itemdatetime);


            locItemDetail = itemView.findViewById(R.id.itemdetail);
            locItemDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LocationM locationM = new LocationM();
                    locationM.setLocName(locName.getText().toString());
                    locationM.setLocDesc(locDesc.getText().toString());
                    locationM.setLocLong(Double.parseDouble(locLongLattE.getText().toString().split("\\s+")[0]));
                    locationM.setLocLatt(Double.parseDouble(locLongLattE.getText().toString().split("\\s+")[1]));
                    locationM.setLocDateTime(locDateTimeE.getText().toString());

                    locationM.setLocId(locId);

                    data.locmm = locationM;

                    Intent intent = new Intent(view.getContext(), EditLocation.class);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<LocationM> mlocations;

    LocationListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.viewlocations_item_new, parent, false);
        return new LocationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationListAdapter.LocationViewHolder holder, int position) {
        if (mlocations != null) {
            LocationM current = mlocations.get(position);
            //holder.locItems.setText("Location Name: " + current.getLocName() + "\nLocation Desc: " + current.getLocDesc() + "\nLocation Time: " + current.getLocDateTime() + "\nLocation Long: " + current.getLocLong() + "\nLocation Latt: " + current.getLocLatt());
            /*holder.locItems.setText(current.getLocName()
                    + ".\n" + current.getLocDesc()
                    + ".\n" + current.getLocDateTime()
                    + ".\n" + current.getLocLong()
                    + ".\n" + current.getLocLatt());*/
            holder.locId = current.getLocId();
            holder.locName.setText(current.getLocName());
            holder.locDesc.setText(current.getLocDesc());
            holder.locLongLattE.setText(current.getLocLong().toString() + " " + current.getLocLatt().toString());
            holder.locDateTimeE.setText(current.getLocDateTime());
            //holder.locLatt = current.getLocLatt();
            //holder.locDateTime = current.getLocDateTime();

        } else {
            holder.locName.setText("No Location Name");
            holder.locDesc.setText("No Location Detail");
        }
    }

    void setLocation(List<LocationM> locationMS) {
        mlocations = locationMS;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mlocations != null) {
            return mlocations.size();
        } else {
            return 0;
        }
    }
}
