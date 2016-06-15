package com.app.simo.phamacylocator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.simo.phamacylocator.Http.RetrofitBuilder;
import com.app.simo.phamacylocator.Phamacy.Endpoint;
import com.app.simo.phamacylocator.Phamacy.Pharmacy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ServicesActivity extends AppCompatActivity implements Callback<Pharmacy>{
    int the_identifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = this.getIntent().getExtras();
        the_identifier = b.getInt("Id_service");

        Retrofit retrofit = RetrofitBuilder.build();

        Endpoint endpoint = retrofit.create(Endpoint.class);

        Call<Pharmacy> call = endpoint.findPharmacyDetail(the_identifier);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Pharmacy> call, Response<Pharmacy> response) {

        Pharmacy c = response.body();



    }

    @Override
    public void onFailure(Call<Pharmacy> call, Throwable t) {

    }
}
