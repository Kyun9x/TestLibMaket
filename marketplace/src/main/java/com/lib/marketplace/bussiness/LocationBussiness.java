package com.lib.marketplace.bussiness;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.lib.marketplace.app.ApplicationIpos;
import com.lib.marketplace.app.Constants;
import com.lib.marketplace.util.Log;
import com.lib.marketplace.util.SharedPref;

public class LocationBussiness implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final String administrative_area_level_1 = "administrative_area_level_1";
    private static final String TAG = "LocationBussiness";
    private static final long TIME_INTERVAL = 300000;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;

    private Context mContext;
    private Location mLocationCurrent;

    private SharedPref config;

    public LocationBussiness(Context mContext) {

        this.mContext = mContext;
        config = new SharedPref(this.mContext);

    }

    public void init() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        // Connect the client.
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG + ".onLocationChanged()",
                "location " + location.getLatitude() + "/ "
                        + location.getLongitude() + "/ " + location.getSpeed());
        mLocationCurrent = location;
        if (location != null) {
            config.putString(Constants.KEY_LAT, ""
                    + location.getLatitude());
            config.putString(Constants.KEY_LONG, ""
                    + location.getLongitude());

        }

        Intent intentBroad = new Intent();
        intentBroad.setAction(Constants.ACTION_LOCATION_CHANGE);
        mContext.sendBroadcast(intentBroad);
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
        mLocationRequest.setInterval(TIME_INTERVAL); // Update location every second

        try {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
            Log.i(TAG + ".onConnected()", "succ");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG + ".onConnectionFailed()", "fail");
    }

    public static Location getMyLocation() {
        LocationBussiness mLocationBussiness = ApplicationIpos.instance.getLocationBussiness();
        Location myLocation;
        myLocation = mLocationBussiness.getLocationCurrent();
        return myLocation;
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "onConnectionSuspended " + cause);
    }

    public Location getLocationCurrent() {
        if (mLocationCurrent != null) {

            Log.d(TAG + ".getLocationCurrent", " " + mLocationCurrent.toString());
        }
        return mLocationCurrent;
    }
}
