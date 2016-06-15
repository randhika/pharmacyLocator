package com.app.simo.phamacylocator;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.app.simo.phamacylocator.Doctor.Doctor;
import com.app.simo.phamacylocator.Http.RetrofitBuilder;
import com.app.simo.phamacylocator.Phamacy.Endpoint;
import com.app.simo.phamacylocator.Phamacy.Pharmacy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Contact extends AppCompatActivity implements Callback<Pharmacy> {

    int idPosition;
    private String phoneNumber;
    private String contactFirstName;
    private String contactLastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_contact);

        Bundle bundle = this.getIntent().getExtras();
        idPosition = bundle.getInt("Identify");

        Retrofit retrofit = RetrofitBuilder.build();

        Endpoint endpoint = retrofit.create(Endpoint.class);

        Call<Pharmacy> call = endpoint.findPharmacyDetail(idPosition);

        call.enqueue(this);
    }




    @Override
    public void onResponse(Call<Pharmacy> call, Response<Pharmacy> response) {

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        Pharmacy pharmacy = response.body();
        List<Doctor> d = pharmacy.getDoctor();
        try {
            for (int i = 0; i < d.size(); i++) {
                phoneNumber = (d.get(i).getPhone());
                contactFirstName = (d.get(i).getFirstname());
                contactLastName = (d.get(i).getLastname());
                Toast.makeText(getApplicationContext(), ""+ phoneNumber, Toast.LENGTH_SHORT).show();
            }

            Intent phoneCall = new Intent(Intent.ACTION_DIAL);
            phoneCall.setData(Uri.parse("tel:" + phoneNumber));
            try {
                startActivity(phoneCall);
            }catch (ActivityNotFoundException ex) {
                Log.e("dailing", "call failed", ex);
                Toast.makeText(getApplicationContext(), "Failed to call", Toast.LENGTH_SHORT).show();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e("a", "b", e);
        }

        if (actionBar != null) {
            actionBar.setTitle(contactFirstName +"\t" +contactLastName);
            actionBar.setSubtitle(phoneNumber);
        }



    }

    @Override
    public void onFailure(Call<Pharmacy> call, Throwable t) {

    }
}
