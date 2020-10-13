package com.lib.marketplace.activity.order_online;


import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.lib.marketplace.R;
import com.lib.marketplace.activity.BaseActivity;
import com.lib.marketplace.app.ApplicationIpos;
import com.lib.marketplace.app.Constants;
import com.lib.marketplace.bussiness.LocationBussiness;
import com.lib.marketplace.model.orderonline.DmLocation;
import com.lib.marketplace.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class MapActivity extends BaseActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {


    protected TextView mSearch;
    protected ImageView mMyLocation;
    protected TextView btn_xacNhan;
    protected ImageView mBack;

    private GoogleMap googleMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private int AUTOCOMPLETE_REQUEST_CODE = 1;
    private static int REQUEST_CODE_RECOVER_PLAY_SERVICES = 200;
    private List<Place.Field> fields;
    boolean check = false;
    private DmLocation mDmLocation;
    private LocationBussiness locationBussiness;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mSearch = findViewById(R.id.edit);
        mMyLocation = findViewById(R.id.myLocation);
        btn_xacNhan = findViewById(R.id.btn_xacNhan);
        mBack = findViewById(R.id.back);
        locationBussiness = ApplicationIpos.getInstance().getLocationBussiness();
        MapFragment mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mMapFragment);
        fragmentTransaction.commit();
        mMapFragment.getMapAsync(this);
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Call your Alert message
            showGPSDisabledAlertToUser();
        }
        mSearch.setOnClickListener(view -> initMap());
        if (checkGooglePlayServices()) {
            buildGoogleApiClient();

            //prepare connection request
            createLocationRequest();
        }
        fields = Arrays.asList(Place.Field.LAT_LNG, Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS);
        mDmLocation = (DmLocation) getIntent().getSerializableExtra(Constants.KEY_DATA);
        initClick();

    }

    private void initClick() {
        mBack.setOnClickListener(view -> onBackPressed());
        btn_xacNhan.setOnClickListener(view -> {
            Intent i = new Intent();
            i.putExtra(Constants.KEY_DATA, mDmLocation);
            setResult(RESULT_OK, i);
            finish();
        });
    }

    private void initMap() {
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY, fields)
                .setTypeFilter(TypeFilter.ADDRESS)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {

        String strAdd = "";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE,
                    LONGITUDE, 1);

            if (addresses != null) {

                Address returnedAddress = addresses.get(0);

                StringBuilder strReturnedAddress = new StringBuilder();

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {

                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(
                            " - ");
                }
                String postalCode = addresses.get(0).getPostalCode();
                String address = addresses.get(0).getAddressLine(0);
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String knownName = addresses.get(0).getFeatureName();
                String city = addresses.get(0).getFeatureName() + " - " + addresses.get(0).getLocality();
                Log.e(TAG, address);
                strAdd = address;
                Log.w("My Current loction address",
                        "" + strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                mSearch.setText(place.getAddress());
                mDmLocation = new DmLocation(place.getLatLng(), place.getAddress());
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 15));
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions()
                        .position(place.getLatLng()));
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }

    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng point) {
                googleMap.clear();
                double lat = point.latitude;
                double log = point.longitude;
                final LatLng mDummyLatLng = new LatLng(lat, log);
                googleMap.addMarker(new MarkerOptions()
                        .position(mDummyLatLng));
                mSearch.setText(getCompleteAddressString(lat, log));

                mDmLocation = new DmLocation(point, getCompleteAddressString(lat, log));
            }
        });
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mMyLocation.setOnClickListener(view -> setAnimationMap(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
        }
    }

    private void setAnimationMap(Double lat, Double log) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, log), 15));

    }

    private boolean checkGooglePlayServices() {

        int checkGooglePlayServices = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (checkGooglePlayServices != ConnectionResult.SUCCESS) {
            /*
             * google play services is missing or update is required
             *  return code could be
             * SUCCESS,
             * SERVICE_MISSING, SERVICE_VERSION_UPDATE_REQUIRED,
             * SERVICE_DISABLED, SERVICE_INVALID.
             */
            GooglePlayServicesUtil.getErrorDialog(checkGooglePlayServices,
                    this, REQUEST_CODE_RECOVER_PLAY_SERVICES).show();

            return false;
        }

        return true;

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    private void initMap(double lat, double log) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, log), 15));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null && googleMap != null) {
            double lat = mLastLocation.getLatitude();
            double log = mLastLocation.getLongitude();
            mSearch.setText(getCompleteAddressString(lat, log));
            final LatLng mDummyLatLng = new LatLng(lat, log);
            googleMap.addMarker(new MarkerOptions()
                    .position(mDummyLatLng));
            initMap(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            mDmLocation = new DmLocation(mDummyLatLng, getCompleteAddressString(lat, log));
            mMyLocation.setOnClickListener(view -> initMap(mLastLocation.getLatitude(), mLastLocation.getLongitude()));

        }

        startLocationUpdates();
    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.turnon_gps))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.btn_ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    Intent callGPSSettingIntent = new Intent(
                                            android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivityForResult(callGPSSettingIntent, 1);
                                } catch (Exception e) {
                                    dialog.dismiss();
                                }
                            }
                        });
        alertDialogBuilder.setNegativeButton(getString(R.string.btn_cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
    }


}
