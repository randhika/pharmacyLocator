package com.app.simo.phamacylocator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.simo.phamacylocator.Http.RetrofitBuilder;
import com.app.simo.phamacylocator.Phamacy.Endpoint;
import com.app.simo.phamacylocator.Phamacy.Pharmacy;
import com.app.simo.phamacylocator.Phamacy.PharmacyCustomAdapter;
import com.app.simo.phamacylocator.gps.GPSTracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements LocationListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, Callback<List<Pharmacy>>{
    private GoogleMap mMap;
    private Double l;
    private Double lg;
    private GoogleMap googleMap;
    private ListView list;
    private Menu optionsMenu;
    private ProgressBar p;
    private ProgressDialog dialog;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage(getString(R.string.refreshing));

        builder();

    }

    public void builder() {
        Retrofit retrofit = RetrofitBuilder.build();

        Endpoint stationsEndpoint = retrofit.create(Endpoint.class);

        Call<List<Pharmacy>> call = stationsEndpoint.findPharmacy(3.3039399, 32.3130871, 10);

        call.enqueue(this);
    }

    // Adding marker on the GoogleMaps
    public void addMarker(LatLng latlng) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.title(latlng.latitude + "," + latlng.longitude);
        mMap.addMarker(markerOptions);
    }

    @Override
    public void onResponse(Call<List<Pharmacy>> call, Response<List<Pharmacy>> response) {

        final ArrayList<Pharmacy> a = (ArrayList<Pharmacy>)response.body();
        list = (ListView) findViewById(R.id.pharmacyList);

        final PharmacyCustomAdapter adapter = new PharmacyCustomAdapter(this,R.layout.pharnacy_list_item, a);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent pharmDetailsIntent = new Intent(MainActivity.this, PhamDetails.class);
                Pharmacy p = adapter.getItem(position);

                int identifier = p.getId();
                int identity = p.getId();

                Bundle b = new Bundle();
                b.putInt("ID", identifier);
                pharmDetailsIntent.putExtras(b);

                Bundle bundle = new Bundle();
                bundle.putInt("id", identity);

                ServiceFragment drugFrag = new ServiceFragment();
                drugFrag.setArguments(bundle);
                startActivity(pharmDetailsIntent);
            }
        });

        dialog.dismiss();

    }

    @Override
    public void onFailure(Call<List<Pharmacy>> call, Throwable t) {
        Toast.makeText(this, "ERROR REACHING SERVER ", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //setMenuBackground();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            //Toast.makeText(getApplicationContext(), "Refreshing", Toast.LENGTH_SHORT).show();
            //setRefreshActionButtonState(true);
            dialog.show();
            builder();

        }
        else if(id == R.id.action_help) {
            Intent helpIntent = new Intent(MainActivity.this, Help.class);
            startActivity(helpIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
