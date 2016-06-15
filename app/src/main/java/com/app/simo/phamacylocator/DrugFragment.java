package com.app.simo.phamacylocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.simo.phamacylocator.Drug.DrugCustomAdapter;
import com.app.simo.phamacylocator.Http.RetrofitBuilder;
import com.app.simo.phamacylocator.Phamacy.Endpoint;
import com.app.simo.phamacylocator.Phamacy.Pharmacy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DrugFragment extends Fragment implements Callback<Pharmacy> {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_drugs, container, false);
        //build();

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

        Pharmacy pharm = response.body();

        ListView drugList = (ListView) view.findViewById(R.id.list_drugs);
        DrugCustomAdapter adapterDoctor = new DrugCustomAdapter(DrugFragment.this.getActivity(), R.layout.drugs, pharm.getDrugs());
        drugList.setAdapter(adapterDoctor);


    }

    @Override
    public void onFailure(Call<Pharmacy> call, Throwable t) {
        //Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

    }
}
