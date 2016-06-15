package com.app.simo.phamacylocator.Drug;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.simo.phamacylocator.R;

import java.util.List;

public class DrugCustomAdapter extends ArrayAdapter<Drug> {
    public DrugCustomAdapter(Activity drugFragment, int resource, List<Drug> drugs) {
        super(drugFragment, resource, drugs);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drugs, parent, false);
        }

        Drug drug = getItem(position);

        TextView drugName = (TextView) convertView.findViewById(R.id.drug);

        drugName.setText(""+drug.getName());

        return convertView;
    }
}

