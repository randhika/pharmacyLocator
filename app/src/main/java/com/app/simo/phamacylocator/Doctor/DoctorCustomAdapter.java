package com.app.simo.phamacylocator.Doctor;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.simo.phamacylocator.R;

import java.util.List;

public class DoctorCustomAdapter extends ArrayAdapter<Doctor> {
    public DoctorCustomAdapter(Context context, int resource, List<Doctor> objects) {
        super(context, resource, objects);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.doctors_description, parent, false);
        }

        Doctor doctor = getItem(position);

        TextView doctorName = (TextView) convertView.findViewById(R.id.doctor_name);
        TextView doctorContacts = (TextView) convertView.findViewById(R.id.doctor_contact);

        doctorName.setText(""+doctor.getFirstname() + "\t" +doctor.getLastname());
        doctorContacts.setText(""+doctor.getPhone() + "\n" +doctor.getEmail());

        return convertView;
    }
}
