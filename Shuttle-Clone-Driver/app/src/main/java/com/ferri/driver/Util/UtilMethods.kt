package com.ferri.driver.Util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.*
import android.preference.PreferenceManager
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ferri.driver.Activity.MainActivity
import com.ferri.driver.Model.RefreshTokenModel
import com.ferri.driver.R
import com.ferri.driver.RetrofitRepository.ApiCallBack
import com.ferri.driver.RetrofitRepository.RetrofitClient
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.net.URL
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

val TAG = "UtilMethods"

fun convertUrlToBase64(url: String?): String {
    val newurl: URL
    val bitmap: Bitmap
    var base64 = ""
    try {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        newurl = URL(url)
        bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream())
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        base64 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return base64
}


fun vibratePhone(context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator.vibrate(200)
    }
}

fun getMarkerIconFromDrawable(drawable: Drawable): BitmapDescriptor? {
    val canvas = Canvas()
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    canvas.setBitmap(bitmap)
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}

/*Animation*/
fun RunLayoutAnimation(context: Context?, recyclerView: RecyclerView) {
    val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.anim_up_to_down)
    recyclerView.layoutAnimation = controller
    recyclerView.adapter!!.notifyDataSetChanged()
    recyclerView.scheduleLayoutAnimation()
}

fun getPreference(context: Context?, key: String?): String? {
    return PreferenceManager.getDefaultSharedPreferences(context).getString(key, "")
}

fun savePreference(context: Context?, key: String?, value: String?) {
    PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value)
        .apply()
}

fun isPreference(context: Context?, key: String?): Boolean {
    return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, false)
}

fun savePreference(context: Context?, key: String?, value: Boolean) {
    PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value)
        .apply()
}

/*show custom toast*/
fun toast(context: Context?, aContent: String? = "Please check your internet Connection") {
    val mToast = CustomToast(context)
    mToast.setCustomView(aContent)
    mToast.duration = Toast.LENGTH_SHORT
    mToast.show()
}

fun checkAndRequestPermissions(context: Context): Boolean {

    /*val camerapermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
    val writepermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)*/
    val locationpermission =
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)

    val listPermissionsNeeded = ArrayList<String>()


    /* if (writepermission != PackageManager.PERMISSION_GRANTED) {
         listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
     }*/
    if (locationpermission != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
    }
    /*if (camerapermission != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded.add(Manifest.permission.CAMERA)
    }*/

    if (!listPermissionsNeeded.isEmpty()) {

        return false
    }
    return true
}

fun checkLocationPermissions(context: Context): Boolean {

    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        return true
    }
    return false
}


fun isInternetConnection(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

/*show Log*/
fun myLog(logActivity: String?, log: String) {
    Log.i(logActivity, "printLog: LOG=$log")
}

fun currentTime(): String {
    return SimpleDateFormat("HH:mm a").format(Date())
}

/*fun makeCall(mContext: Context) {
    if (ActivityCompat.checkSelfPermission(
            mContext,
            Manifest.permission.CALL_PHONE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            mContext,
            arrayOf(
                Manifest.permission.CALL_PHONE
            ),
            1
        )

    } else {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data =
            Uri.parse("tel:" + "01149517900")

        mContext.startActivity(callIntent)
    }
}*/

fun checkToken(mContext: Context, apiCallBack: ApiCallBack) {

    RetrofitClient.getClient().refreshToken(
        getPreference(mContext, AppConstants.PHONE_NO),
        getPreference(mContext, AppConstants.csrfTOKEN),
        AppConstants.onModel
    )!!
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableSingleObserver<RefreshTokenModel?>() {
            override fun onSuccess(response: RefreshTokenModel) {
                myLog("CHECK_TOKEN", "onSuccess: response=" + Gson().toJson(response))
                if (response.status!!) {
                    savePreference(mContext, AppConstants.csrfTOKEN, response.data?.csrfToken)
                    savePreference(mContext, AppConstants.TOKEN, "Bearer " + response.data?.token)

                    apiCallBack.onResponse(true)

                } else apiCallBack.onResponse(false)
            }

            override fun onError(e: Throwable) {
                myLog("CHECK_TOKEN", "onError: checkToken Error=" + e.localizedMessage)
                apiCallBack.onResponse(false)
            }
        })

}

fun sessionExpireDialog(mContext: Context) {
    try {
        val dialogBuilder = AlertDialog.Builder(mContext)

        dialogBuilder.setMessage("You will be logged out for security reasons. Sorry for the inconvenience please login again.")
            .setCancelable(false)
            .setPositiveButton(
                "OK",
                DialogInterface.OnClickListener { dialog, id ->
                    savePreference(mContext, AppConstants.TOKEN, "")
                    savePreference(mContext, AppConstants.csrfTOKEN, "")
                    savePreference(mContext, AppConstants.PHONE_NO, "")
                    savePreference(mContext, AppConstants.BASEURL, "")
                    savePreference(mContext, AppConstants.IsDriverLogIn, false)
                    (mContext as Activity).finishAffinity()
                    dialog.dismiss()
                })

        val alert = dialogBuilder.create()
        alert.setTitle("Session Expire.!!")
        if (!alert.isShowing)
            alert.show()
    } catch (e: Exception) {
        myLog("SessionExpire", "sessionExpireDialog: Error=${e.localizedMessage}")
    }
}

fun alertDialog(mContext: Context,alert: String) {
    try {
        val dialogBuilder = AlertDialog.Builder(mContext)

        dialogBuilder.setMessage(alert)
            .setCancelable(false)
            .setPositiveButton(
                "Close",
                DialogInterface.OnClickListener { dialog, id ->
                   dialog.dismiss()
                })

        val alert = dialogBuilder.create()
        alert.setTitle("Alert!!")
        if (!alert.isShowing)
            alert.show()
    } catch (e: Exception) {
        myLog("alertDialog", "alertDialog: Error=${e.localizedMessage}")
    }
}

fun increaseVolume(context: Context) {
    try {
        val am: AudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        am.setStreamVolume(
            AudioManager.STREAM_MUSIC,
            am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
            0
        )
    } catch (e: Exception) {
        Log.i(TAG, "increaseVolume: Error=${e.localizedMessage}")
    }

}

@SuppressLint("NewApi")
fun isNetworkAvailable(context: Context): Boolean {
    (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
        return getNetworkCapabilities(activeNetwork)?.run {
            when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } ?: false
    }
}

fun getCurrentTime(): String {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val dateTime = LocalDateTime.now()
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM, hh:mm:ss a"))

    } else {
        var date = Date()
        val formatter = SimpleDateFormat("dd MMM, hh:mm:ss a")
        val answer: String = formatter.format(date)
        return answer
    }
}

var df: DecimalFormat = DecimalFormat("###.##")

fun getDistanceFromLatLonInM(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    var a = Math.pow(Math.sin(deg2rad(lat2 - lat1) / 2), 2.0) +
            Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
            Math.pow(Math.sin(deg2rad(lon2 - lon1) / 2), 2.0);
    return df.format(12742000 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))).toDouble()
}

fun deg2rad(deg: Double): Double {
    return deg * (Math.PI / 180)
}

fun isMyServiceRunning(serviceClass: Class<*>, context: Context): Boolean {
    try {
        val manager =
            context.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    } catch (e: Exception) {
        Log.i(TAG, "isMyServiceRunning: Error=${e.localizedMessage}")
        return false
    }

}

/**
 * Helper functions to simplify permission checks/requests.
 */
fun Context.hasPermission(permission: String): Boolean {

    // Background permissions didn't exit prior to Q, so it's approved by default.
    if (permission == Manifest.permission.ACCESS_BACKGROUND_LOCATION &&
        android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q
    ) {
        return true
    }

    return ActivityCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
}


fun requestPermissionWithRationale(
    activity: MainActivity,
    permission: String,
    requestCode: Int,
    snackbar: Snackbar
) {
    val provideRationale =
        ActivityCompat.shouldShowRequestPermissionRationale(activity!!, permission)

    if (provideRationale) {
        snackbar.show()
    } else {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    }
}


fun View.clickWithThrottle(debounceTime: Long = 1500L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}


fun bearingBetweenLocations(
    latLng1: LatLng,
    latLng2: LatLng
): Double {
    val PI = 3.14159
    val lat1 = latLng1.latitude * PI / 180
    val long1 = latLng1.longitude * PI / 180
    val lat2 = latLng2.latitude * PI / 180
    val long2 = latLng2.longitude * PI / 180
    val dLon = long2 - long1
    val y = Math.sin(dLon) * Math.cos(lat2)
    val x =
        Math.cos(lat1) * Math.sin(lat2) - (Math.sin(lat1)
                * Math.cos(lat2) * Math.cos(dLon))
    var brng = Math.atan2(y, x)
    brng = Math.toDegrees(brng)
    brng = (brng + 360) % 360
    return brng
}