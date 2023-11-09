package com.ferri.userapp.ui.activity

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.ui.intro.IntroActivity
import com.ferri.userapp.utils.*
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.UpdateAvailability
import org.jsoup.Jsoup
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class SplashActivity : BaseActivity() {
    /*variable declaration*/
    private var mIVLogo: ImageView? = null
    var rootView: View? = null
    val TAG = "SplashActivity"
    private val appUpdateManager: AppUpdateManager by lazy { AppUpdateManagerFactory.create(this) }
    private var isUpdateStatusCalled=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        initLayouts()
        initializeListeners()
    }

    /* init layout */
    private fun initLayouts() {
        mIVLogo = findViewById(R.id.ivLogo)
        rootView = findViewById(R.id.rootView)
    }

    /* initialize listener */
    private fun initializeListeners() {

        try {
            if (getPreference(
                    this@SplashActivity,
                    Constants.TOKEN
                ) == "" && getPreference(this@SplashActivity, Constants.FirstTimeUser) == ""
            ) {
                openIntroSlider()
            } else {
                if (isNetworkAvailable(this@SplashActivity)) {
//                    AppUpdate(this@SplashActivity).execute()
                    checkForAppUpdate()

                } else toast(this@SplashActivity)
            }
        } catch (e: Exception) {
            Log.i(TAG, "initializeListeners: Error=${e.localizedMessage}")
        }


    }

    private fun checkForAppUpdate() {

        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            isUpdateStatusCalled=true
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                showUpdateDialog()
            }else goAhead()
        }

        if (!isUpdateStatusCalled) checkAppUpdate()


        /* // Creates instance of the manager.
            val appUpdateManager = FakeAppUpdateManager(this)
            val appUpdateInfoTask = appUpdateManager.appUpdateInfo
            appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
                val version = appUpdateInfo.availableVersionCode()
                Log.d(TAG,"IN APP Latest Version="+ version)
                toast(this,"IN APP Latest Version="+ version)
            }*/
    }

    private fun checkAppUpdate() {
        try {
            val executor: ExecutorService = Executors.newSingleThreadExecutor()
            val handler = Handler(Looper.getMainLooper())


            executor.execute(Runnable {
                //Background work here
                var playStoreVersion: String? = null
                try {
                    Log.i(TAG, "checkAppUpdate: Url= https://play.google.com/store/apps/details?id=${getPackageName()}")
                    playStoreVersion =
                        Jsoup.connect("https://play.google.com/store/apps/details?id=${getPackageName()}&hl=in")
                            .timeout(300000)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .referrer("http://www.google.com")
                            .get()
                            .select(".hAyfc .htlgb")
                            .get(7)
                            .ownText()
                } catch (e: Exception) {
                    Log.i(TAG, "doInBackground: " + e.localizedMessage).toString()
                    playStoreVersion = "0"
                }


                handler.post {
                    //UI Thread work here
                    try {

                        val currentVersion = getPackageManager()
                            .getPackageInfo(getPackageName(), 0).versionName;
                        Log.d(
                            TAG,
                            "Current version =" + currentVersion + " playstore version=" + playStoreVersion
                        );
                        if (playStoreVersion != null && !playStoreVersion.isEmpty()) {

                            Log.d(
                                TAG,
                                "Update =" + (currentVersion < playStoreVersion)
                            );

                            if (currentVersion < playStoreVersion) {

                                showUpdateDialog()
                            }
                            else goAhead()
                        }
                    } catch (e: Exception) {
                        Log.i(TAG, "onPostExecute: " + e.localizedMessage).toString()
                    }

                }
            })


        } catch (e: Exception) {
            Log.i(TAG, "checkAppUpdate: Error=${e.localizedMessage}")
        }
    }

    private fun showUpdateDialog() {
        try {
            val dialog = AlertDialog.Builder(this).setTitle("Update available")
            dialog.setMessage("New version with updated features available on the play store. Please update the application to continue.")
            dialog.setCancelable(false)
            dialog.setPositiveButton(
                "Update"
            ) { dialog, which ->
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

                val appPackageName =
                    packageName // getPackageName() from Context or Activity object
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appPackageName")
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                        )
                    )
                }
            }
            dialog.show()
        }catch (e:Exception){
            Log.i(TAG, "showUpdateDialog: Error=${e.localizedMessage}")
        }

    }


    private fun openIntroSlider() {
        startActivity(Intent(this, IntroActivity::class.java))
        finish()
    }

    private fun goAhead() {
        try {

            if (!isPreference(this@SplashActivity, Constants.IsUserRegistered)) {
                signInActivity(rootView!!)
            } else {
                explodeBaseActivity(rootView!!, this)
                /* if (!checkAndRequestPermissions(this)) {
                     val intent = Intent(this, PermissionActivity::class.java)
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK);
                     startActivity(intent)
                 } else {
                     explodeBaseActivity(rootView!!, this)
                 }*/
            }

        } catch (e: Exception) {
            Log.i(TAG, "otherCheck: Error=${e.localizedMessage}")
        }


    }


    fun signInActivity(view: View) {

        val login = SelectionActivity()
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "transition")
        val revealX = (view.x + view.width / 2).toInt()
        val revealY = (view.y + view.height / 2).toInt()
        val intent = Intent(this, SelectionActivity::class.java)
        intent.putExtra(login.EXTRA_CIRCULAR_REVEAL_X, revealX)
        intent.putExtra(login.EXTRA_CIRCULAR_REVEAL_Y, revealY)
//        ActivityCompat.startActivity(this, intent, options.toBundle())
        startActivity(intent)

    }


    fun explodeBaseActivity(
        view: View,
        activity: SplashActivity
    ) {

        Log.i(TAG, "explodeBaseActivity: called")
        
        //Change InitialActivity to DashboardActivity Activity after Suggest Route process is done

        val base = DashboardActivity()
        val options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, "transition")
        val revealX = (view.x + view.width / 2).toInt()
        val revealY = (view.y + view.height / 2).toInt()
        val intent = Intent(activity, DashboardActivity::class.java)
        intent.putExtra(base.EXTRA_CIRCULAR_REVEAL_X, revealX)
        intent.putExtra(base.EXTRA_CIRCULAR_REVEAL_Y, revealY)
//        ActivityCompat.startActivity(activity, intent, options.toBundle())
        startActivity(intent)
        finish()
    }


}