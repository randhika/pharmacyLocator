package com.app.simo.phamacylocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.app.simo.phamacylocator.Http.RetrofitBuilder;
import com.app.simo.phamacylocator.Phamacy.Endpoint;
import com.app.simo.phamacylocator.Phamacy.Pharmacy;
import com.app.simo.phamacylocator.gps.GPSTracker;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.google.android.gms.maps.CameraUpdateFactory.newCameraPosition;

public class PhamDetails extends AppCompatActivity implements OnMapReadyCallback, Callback<Pharmacy> {

    int the_iD;
    private GoogleMap googleMap;
    private Double latitude;
    private Double longitude;
    GPSTracker gps;

    private SupportMapFragment mapFragment;
    private GoogleMap map;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private long UPDATE_INTERVAL = 60000;  /* 60 secs */
    private long FASTEST_INTERVAL = 5000; /* 5 secs */

    /*
     * Define a request code to send to Google Play services This code is
     * returned in Activity.onActivityResult
     */
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pham_details);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        build();

        // Locate the viewpager in activity_main.xml
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        // Set the ViewPagerAdapter into ViewPager
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DrugFragment(), "Drugs");
        adapter.addFrag(new DoctorFragment(), "Doctors");
        adapter.addFrag(new ServiceFragment(), "Services");

        viewPager.setAdapter(adapter);

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.pager_header);
        mTabLayout.setupWithViewPager(viewPager);

        Bundle b = this.getIntent().getExtras();
        the_iD = b.getInt("ID");

        Intent a = new Intent (this, DrugFragment.class);
        a.putExtra("id", the_iD);
        setIntent(a);

        gps = new GPSTracker(PhamDetails.this);


    }

    public void marker() {
        //check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            LatLng ltlng = new LatLng(latitude, longitude);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(ltlng);
            markerOptions.title("Pharmacy");
            googleMap.addMarker(markerOptions);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.setBuildingsEnabled(true);
        googleMap.setIndoorEnabled(true);

        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);


    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void build() {

        Bundle b = this.getIntent().getExtras();
        the_iD = b.getInt("ID");

        Retrofit retrofit = RetrofitBuilder.build();
        Endpoint endpoint = retrofit.create(Endpoint.class);
        Call<Pharmacy> call = endpoint.findPharmacyDetail(the_iD);

        call.enqueue(this);
    }


    @Override
    public void onResponse(final Call<Pharmacy> call, final Response<Pharmacy> response) {

        final Pharmacy a = response.body();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("" + a.getName());
            actionBar.setSubtitle("" + a.getAddress());
        }

        LatLng ltlng = new LatLng(Double.parseDouble(""+a.getLat()), Double.parseDouble(""+a.getLng()));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ltlng);
        markerOptions.title("" + a.getName());
        markerOptions.snippet(""+ a.getAddress());
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_marker));
        CameraPosition cameraPosition = CameraPosition.builder().bearing(30).target(ltlng).tilt(30).zoom(18).build();
        googleMap.animateCamera(newCameraPosition(cameraPosition));
        googleMap.addMarker(markerOptions);

    }



    @Override
    public void onFailure(Call<Pharmacy> call, Throwable t) {


    }


    public void mapAppearance() {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.setBuildingsEnabled(true);
        googleMap.setIndoorEnabled(true);

        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pharmacy_details, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
