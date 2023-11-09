package com.ferri.userapp.ui.activity

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.google.android.material.snackbar.Snackbar

class PermissionActivity : BaseActivity() {
    private var REQUEST_ID_MULTIPLE_PERMISSIONS = 1
    private var TAG = "PermissionActivity"
    private var root_layout: LinearLayout? =null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_permision)

        root_layout=findViewById<LinearLayout>(R.id.root_layout)
        findViewById<Button>(R.id.btnPermsnAllw).setOnClickListener {
            if (checkAndRequestPermissions()) goThrough()
        }

    }



    private fun checkAndRequestPermissions(): Boolean {

        val locationpermission =
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        val storagePermission =
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

        val listPermissionsNeeded = ArrayList<String>()


        if (locationpermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }



        if (!listPermissionsNeeded.isEmpty()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    listPermissionsNeeded.toTypedArray(),
                    REQUEST_ID_MULTIPLE_PERMISSIONS
                )
            } else {
                try {
                    ActivityCompat.requestPermissions(
                        this as Activity,
                        listPermissionsNeeded.toTypedArray(),
                        REQUEST_ID_MULTIPLE_PERMISSIONS
                    )
                } catch (e: Exception) {
                    Log.i(TAG, "checkAndRequestPermissions: Error=" + e.localizedMessage)
                }

            }
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {


            REQUEST_ID_MULTIPLE_PERMISSIONS -> {

                val perms: MutableMap<String, Int> = HashMap()
                // Initialize the map with both permissions
//                perms[Manifest.permission.CAMERA] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.ACCESS_FINE_LOCATION] = PackageManager.PERMISSION_GRANTED
                // Fill with actual results from user
                // Fill with actual results from user
                if (grantResults.size > 0) {
                    for (i in 0 until permissions.size) perms[permissions[i]] = grantResults[i]
                    // Check for both permissions
                    if (/*perms[Manifest.permission.CAMERA] == PackageManager.PERMISSION_GRANTED
                        &&perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED
                        && */ perms[Manifest.permission.ACCESS_FINE_LOCATION] == PackageManager.PERMISSION_GRANTED
                    ) {
                        goThrough()

                    } else {

                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (/*ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.CAMERA
                            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ) ||*/ ActivityCompat.shouldShowRequestPermissionRationale(this,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            )
                        ) {
                            showDialogOK("Location Services Permission are required for this app to run",
                                DialogInterface.OnClickListener { dialog, which ->
                                    when (which) {
                                        DialogInterface.BUTTON_POSITIVE -> checkAndRequestPermissions()
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                        }
                                    }
                                })
                        } else {
                            Snackbar.make(
                                root_layout!!,
                                R.string.fine_permission_denied_explanation,
                                Snackbar.LENGTH_LONG
                            )
                                .setAction(R.string.settings) {
                                    // Build intent that displays the App settings screen.
                                    val intent = Intent()
                                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                    val uri = Uri.fromParts(
                                        "package",
                                        packageName,
                                        null
                                    )
                                    intent.data = uri
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                    startActivity(intent)
                                }
                                .show()
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }

            }

        }
    }

    private fun goThrough() {
        startActivity(Intent(this,DashboardActivity::class.java))
        finishAffinity()
    }

    private fun showDialogOK(
        message: String,
        okListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", okListener)
            .create()
            .show()
    }
}