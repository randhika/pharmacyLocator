package com.app.simo.phamacylocator.Service;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.simo.phamacylocator.Offer.Offer;
import com.app.simo.phamacylocator.R;

import java.util.List;

public class ServiceCustomAdapter extends ArrayAdapter<Offer> {

    public ServiceCustomAdapter(Activity activity, int service_description, List<Offer> doctors) {
        super(activity, service_description , doctors);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Offer dr = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_description, parent, false);
        }



        TextView serviceName = (TextView) convertView.findViewById(R.id.service_name);
        TextView serviceDescription = (TextView) convertView.findViewById(R.id.service_description);

        serviceName.setText(dr.getService().getName());
        serviceDescription.setText(dr.getService().getDescription());


        // Return the completed view to render on screen
        return convertView;
    }

}

