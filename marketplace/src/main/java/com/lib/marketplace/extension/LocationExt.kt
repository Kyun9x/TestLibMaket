package com.ipos.saler.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.location.LocationManager
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.lib.marketplace.app.ApplicationIpos

object LocationExt {
    fun gpsIsEnable(): Boolean {
        val lm =
                ApplicationIpos.getInstance().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gpsEnabled = false
        var networkEnabled = false

        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
        }

        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (ex: Exception) {
        }

        return gpsEnabled || networkEnabled
    }

    @SuppressLint("MissingPermission")
    fun getMyLocation(context: Context, onSuccess: (LocationResult?) -> Unit) {
        // Create the LocationRequest object
        val locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1000) // 1 second, in milliseconds

        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                object : LocationCallback() {

                    override fun onLocationResult(result: LocationResult?) {
                        super.onLocationResult(result)
                        onSuccess(result)

                        fusedLocationProviderClient.removeLocationUpdates(this)
                    }
                },
                null
        )
    }

    fun displayLocationSettingsRequest(
            activity: FragmentActivity,
            reqCode: Int,
            onLocationIsEnable: (LocationSettingsResponse) -> Unit
    ) {
        val googleApiClient = GoogleApiClient.Builder(activity).addApi(LocationServices.API).build()
        googleApiClient.connect()

        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 10000
            fastestInterval = 1000
        }
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(activity)
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener(activity) {
            // All location settings are satisfied. The client can initialize
            // location requests here.
            // ...
            onLocationIsEnable(it)
        }

        task.addOnFailureListener(activity) { e ->
            if (e is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult(activity, reqCode)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }

            }
        }
    }

    fun openGpsSetting(context: Any, reqCode: Int) {
        when (context) {
            is AppCompatActivity -> context.startActivityForResult(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                    reqCode
            )
            is Fragment -> context.startActivityForResult(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                    reqCode
            )
        }
    }
}
