package com.app.simo.phamacylocator.Phamacy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.simo.phamacylocator.R;

import java.util.List;

public class PharmacyCustomAdapter extends ArrayAdapter<Pharmacy> {
    public PharmacyCustomAdapter(Context context, int resource, List<Pharmacy> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pharnacy_list_item, parent, false);
        }

        // Get the data item for this position
        Pharmacy ph = getItem(position);

        TextView pharmacyName = (TextView) convertView.findViewById(R.id.pharmacyName);
        TextView pharmacyLocation = (TextView) convertView.findViewById(R.id.pharmacyLocation);

        pharmacyName.setText(ph.getName());
        pharmacyLocation.setText(ph.getAddress());



        // Return the completed view to render on screen
        return convertView;
    }
}
