package com.ferri.driver.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AccelerateInterpolator
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.ferri.driver.R
import com.ferri.driver.Util.*
import com.ferri.driver.ViewModel.MainViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import java.util.*

class SignInActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mBtnContinue: Button? = null
    private var mEdMobileNumber: EditText? = null
    private var mIvFacebook: ImageView? = null
    private var mIvGoogle: ImageView? = null
    private val TAG = "SignInActivity"
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var callbackManager: CallbackManager? = null

    private var mainViewModel: MainViewModel? = null

    var rootLayout: View? = null
    val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
    val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
    private var revealX = 0
    private var revealY = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_sign_in)
        initLayouts()
        initializeListeners()
        initializeGoogleLogin()
        fCMToken
        // explode animation on activity start.
        explodeAnim(savedInstanceState, intent)
    }

    // Get new FCM registration token
    private val fCMToken: Unit
        // Log and toast
        private get() {
            FirebaseMessaging.getInstance().token
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new FCM registration token
                    val token = task.result
                    savePreference(this@SignInActivity, AppConstants.DEVICE_TOKEN, token)

                    // Log and toast
                    Log.d(TAG, "TOKEN=$token")
                })
        }

    /* init layout */
    private fun initLayouts() {
        mEdMobileNumber = findViewById(R.id.edMobileNumber)
        mBtnContinue = findViewById(R.id.btnContinue)
        mIvFacebook = findViewById(R.id.ivFacebook)
        mIvGoogle = findViewById(R.id.ivGoogle)
        rootLayout = findViewById<View>(R.id.root_layout)
    }

    /* initialize listener */
    @SuppressLint("ClickableViewAccessibility")
    private fun initializeListeners() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mBtnContinue!!.setOnClickListener(this)
        mIvFacebook!!.setOnClickListener(this)
        mIvGoogle!!.setOnClickListener(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBtnContinue!!.stateListAnimator = null
        }

    }

    /* validation */
    private fun validate(): Boolean {
        var flag = true
        if (mEdMobileNumber!!.text.toString().length != 10) {
            flag = false
            showToast(getString(R.string.msg_mobile_number))
        }
        return flag
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mBtnContinue) {
            if (validate()) {

                if (isInternetConnection(this)) {
                    LoadingDialog.showLoadingDialog(this, "Please wait...");
                    mainViewModel!!.driverLogin(mEdMobileNumber!!.text.toString())
                        .observe(this, androidx.lifecycle.Observer {
                            LoadingDialog.cancelLoading()
                            if (it != null) {
                                if (it.status!!) {
                                    savePreference(this, AppConstants.TOKEN, "Bearer " + it.token)
                                    savePreference(this, AppConstants.csrfTOKEN, it.csrfToken)
                                    savePreference(this, AppConstants.BASEURL, it.baseurl)

                                    val intent = Intent(this, VerificationActivity::class.java)
                                    intent.putExtra("otp", it.otp)
                                    intent.putExtra("phone", mEdMobileNumber!!.text.toString())
                                    startActivity(intent)
                                } else alertDialog(this, it.title.toString())
                            } else sessionExpireDialog(this)
                        })
                }else toast(this)
            }
        } else if (v === mIvFacebook) {
            initializeFacebookLogin()
        } else if (v === mIvGoogle) {
            val signInIntent = mGoogleSignInClient!!.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    private fun initializeFacebookLogin() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance()
            .logInWithReadPermissions(this, Arrays.asList("email,public_profile,user_gender"))
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    // App code
                    myLog(TAG, "onSuccess=" + loginResult.accessToken.applicationId)
                    myLog(TAG, "onSuccess=" + loginResult.accessToken.userId)
                    getFaceBookData(loginResult.accessToken)
                }

                override fun onCancel() {
                    // App code
                    myLog(TAG, "onCancel=Login canceled")
                }

                override fun onError(exception: FacebookException) {
                    // App code
                    myLog(TAG, "onError=" + exception.localizedMessage)
                }
            })
    }

    private fun getFaceBookData(accessToken: AccessToken) {
        try {
            /*val graphRequest: GraphRequest = GraphRequest().newMeRequest(
                accessToken,
                GraphJSONObjectCallback { `object`, response ->
                    myLog(
                        TAG,
                        "onCompleted: JSONObject=$`object`"
                    )
                })*/
        } catch (e: Exception) {
            myLog(TAG, "getFaceBookData: Errror=" + e.localizedMessage)
        }
    }

    private fun initializeGoogleLogin() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        /*      // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);*/
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val acct = completedTask.getResult(ApiException::class.java)
            //            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                val personName = acct.displayName
                val personGivenName = acct.givenName
                val personFamilyName = acct.familyName
                val personEmail = acct.email
                val personId = acct.id
                val personPhoto = acct.photoUrl
                myLog(TAG, "handleSignInResult: personName=$personName")
                myLog(TAG, "handleSignInResult: personGivenName=$personGivenName")
                myLog(TAG, "handleSignInResult: personFamilyName=$personFamilyName")
                myLog(TAG, "handleSignInResult: personEmail=$personEmail")
                myLog(TAG, "handleSignInResult: personId=$personId")
                myLog(TAG, "handleSignInResult: personPhoto=$personPhoto")
            }

            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            myLog(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }

    companion object {
        private const val RC_SIGN_IN = 150
    }

    // explode animation on activity start.
    private fun explodeAnim(savedInstanceState: Bundle?, intent: Intent) {
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)
        ) {
            rootLayout?.setVisibility(View.INVISIBLE)
            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0)
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0)
            val viewTreeObserver: ViewTreeObserver = rootLayout!!.getViewTreeObserver()
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object :
                    ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        revealActivity(revealX, revealY)
                        rootLayout!!.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                    }
                })
            }
        } else {
            rootLayout!!.setVisibility(View.VISIBLE)
        }
    }

    // explode animation.
    protected fun revealActivity(x: Int, y: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val finalRadius = (Math.max(
                rootLayout!!.getWidth(),
                rootLayout!!.getHeight()
            ) * 1.1f)

            // create the animator for this view (the start radius is zero)
            val circularReveal =
                ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0f, finalRadius)
            circularReveal.duration = 800
            circularReveal.interpolator = AccelerateInterpolator()

            // make the view visible and start the animation
            rootLayout!!.setVisibility(View.VISIBLE)
            circularReveal.start()
        } else {
            finish()
        }
    }
}