package com.ferri.driver.Services

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.annotation.Nullable
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.ferri.driver.Activity.BaseActivity
import com.ferri.driver.Activity.MainActivity
import com.ferri.driver.Model.DefaultResponse
import com.ferri.driver.Model.TrackingStatusResponse
import com.ferri.driver.RetrofitRepository.RetrofitClient
import com.ferri.driver.Util.*
import com.google.android.gms.location.*
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import rigo.regotaxi.rigodrivers.fb.LiveUpdate
import java.text.DecimalFormat


class LocationUpdateService : Service() {

    private val TAG = "LocationUpdateService"

    //region data
    private val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 2000
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null
    private val locationSettingsRequest: LocationSettingsRequest? = null
    var isLocationSet = false
    var previousLat = 0.0
    var previousLng = 0.0
    var locationDistance = 0.0
    var CHANNEL_ID = "Ferri Driver"

    override fun onCreate() {
        super.onCreate()
        initData()
    }


    //Location Callback
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val currentLocation: Location = locationResult.lastLocation!!
            myLog(
                TAG,
                "Latitude=${currentLocation.getLatitude()},Longitude=${currentLocation.getLongitude()}"
            )
            //Share/Publish Location
            onLocationUpdated(currentLocation)
        }
    }

    private fun onLocationUpdated(location: Location) {
        try {

            LiveUpdate.updateLocation.postValue(location)
            savePreference(this, AppConstants.DRIVER_LATITUDE, location.latitude.toString())
            savePreference(this, AppConstants.DRIVER_LONGITUDE, location.longitude.toString())
            savePreference(this, AppConstants.DRIVER_ANGLE, location.bearing.toString())
            Log.i(TAG, "onLocationUpdated: angle=${getPreference(this, AppConstants.DRIVER_ANGLE)}")


            if (!isLocationSet) {
                previousLat = location.latitude
                previousLng = location.longitude
                updateDriverLiveLocation(location)
                isLocationSet = true
            }

            locationDistance = getDistanceFromLatLonInM(
                previousLat,
                previousLng,
                location.latitude,
                location.longitude
            )

            myLog(TAG, "DIFFERENCE=${locationDistance}")
            val speed = DecimalFormat().format(location.speed.toLong()).toFloat()
            myLog(TAG, "SPEED=$speed")

            if (locationDistance >= AppConstants.MIN_DIST_FOR_LOCATION_UPDATE) {
                updateDriverLiveLocation(location)
                previousLat = location.latitude
                previousLng = location.longitude
            }


        } catch (e: Exception) {
            myLog(TAG, "onLocationUpdated: Error=${e.localizedMessage}")
        }

    }

    private fun updateDriverLiveLocation(location: Location) {
        try {
            if (isNetworkAvailable(this)) {
                Log.i(TAG, "updateDriverLiveLocation: angle=${location.bearing}")
                Log.i(TAG, "updateDriverLiveLocation: IS_BOOKING_ASSIGNED=${isPreference(this,AppConstants.IS_BOOKING_ASSIGNED)}")
                Log.i(TAG, "updateDriverLiveLocation: IS_TRIP_STARTED=${isPreference(this,AppConstants.IS_TRIP_STARTED)}")
                if (isPreference(this,AppConstants.IS_BOOKING_ASSIGNED) && isPreference(this,AppConstants.IS_TRIP_STARTED))

//                    "lng":"77.03324",
//                "lat":"28.61935",
                    
                    RetrofitClient.getClient()
                        .updateTrackingStatus(
                            getPreference(this, AppConstants.TOKEN)!!,
                            getPreference(this,AppConstants.ASSIGNED_ID).toString(),
                            "RIDING",
                            "${location.latitude}",
                            "${location.longitude}",
                            "${location.bearing}"
                        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<TrackingStatusResponse?>() {
                            override fun onSuccess(response: TrackingStatusResponse) {
                                myLog(
                                    TAG,
                                    "locationChanged: Response=${Gson().toJson(response)}"
                                )
                                LiveUpdate.updateTrackStatus.postValue(response)
                            }

                            override fun onError(e: Throwable) {
                                myLog(TAG, "onError: locationChanged=" + e.localizedMessage)
                            }
                        })
            }
        } catch (e: Exception) {
            myLog(TAG, "updateDriverLiveLocation: Error=${e.localizedMessage}")
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        prepareForegroundNotification()
        startLocationUpdates()
        return START_STICKY
    }

    private fun startLocationUpdates() {

        Log.i(TAG, "startLocationUpdates: called")

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        if (locationRequest == null || mFusedLocationClient == null) {
            Log.i(TAG, "startLocationUpdates: locationrequest is null")
            initData()
            startLocationUpdates()
        } else {
            if (isPreference(this,AppConstants.IS_BOOKING_ASSIGNED))
                mFusedLocationClient!!.requestLocationUpdates(
                    locationRequest!!,
                    locationCallback, Looper.getMainLooper()!!
                )
        }
    }

    private fun prepareForegroundNotification() {

        myLog(TAG, "prepareForegroundNotification: called")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Ferri Driver Service Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            2121, notificationIntent, 0
        )

        try {
            val notification =
                NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Location Service")
                    .setContentText("Getting background location.")
                    .setSmallIcon(com.ferri.driver.R.drawable.ic_notification_logo)
                    .setContentIntent(pendingIntent)
                    .setSound(null)
                    .build()
            startForeground(AppConstants.FRG_SERVICE_NF_ID, notification)

        } catch (e: Exception) {
            myLog(TAG, "onCreate: ${e.localizedMessage}")
        }
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun initData() {
        myLog(TAG, "initData: Called")
        prepareForegroundNotification()
        locationRequest = LocationRequest.create()
        locationRequest!!.interval = UPDATE_INTERVAL_IN_MILLISECONDS
        locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mFusedLocationClient!=null&&locationCallback!=null)
          mFusedLocationClient!!.removeLocationUpdates(locationCallback)

        myLog(TAG, "onDestroy: Called")
    }
}