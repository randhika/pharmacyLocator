package com.app.simo.phamacylocator.Phamacy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Endpoint {

    @GET("/pharmacies")
    Call<List<Pharmacy>> findPharmacy(@Query("lat") double lat, @Query("long") double lng, @Query("limit") int limit);

    @GET("/pharmacies/{id}")
    Call<Pharmacy> findPharmacyDetail(@Path("id") int id);
}
