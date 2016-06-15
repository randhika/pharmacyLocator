package com.app.simo.phamacylocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.simo.phamacylocator.Doctor.Doctor;
import com.app.simo.phamacylocator.Doctor.DoctorCustomAdapter;
import com.app.simo.phamacylocator.Http.RetrofitBuilder;
import com.app.simo.phamacylocator.Phamacy.Endpoint;
import com.app.simo.phamacylocator.Phamacy.Pharmacy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class DoctorFragment extends Fragment implements Callback<Pharmacy> {

        View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_doctors, container, false);
        PhamDetails activity = (PhamDetails) getActivity();
        Intent b= activity.getIntent();
        int Uid = b.getIntExtra("id", getId());

        Retrofit retrofit = RetrofitBuilder.build();

        Endpoint endpoint = retrofit.create(Endpoint.class);

        Call<Pharmacy> call = endpoint.findPharmacyDetail(Uid);

        call.enqueue(this);

        return view;
        }

    @Override
    public void onResponse(Call<Pharmacy> call, Response<Pharmacy> response) {

        Pharmacy pharmacy = response.body();
        ListView doctorList = (ListView) view.findViewById(R.id.list_doctors);
        final DoctorCustomAdapter adapterDoctor = new DoctorCustomAdapter(DoctorFragment.this.getActivity(),
                R.layout.doctors_description, pharmacy.getDoctor());
        doctorList.setAdapter(adapterDoctor);

        doctorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent contactIntent = new Intent(DoctorFragment.this.getActivity(), Contact.class);
                Doctor p = adapterDoctor.getItem(position);
                int identifierForPosition = p.getId();
                Bundle bundle = new Bundle();
                bundle.putInt("Identify", identifierForPosition);
                contactIntent.putExtras(bundle);
                startActivity(contactIntent);
            }
        });

    }

    @Override
    public void onFailure(Call<Pharmacy> call, Throwable t) {

    }
}
