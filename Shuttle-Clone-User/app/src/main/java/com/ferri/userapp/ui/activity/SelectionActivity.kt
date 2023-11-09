package com.ferri.userapp.ui.activity

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.*
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import com.facebook.*
import com.facebook.GraphRequest.GraphJSONObjectCallback
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.model.SocialLoginUserDetails
import com.ferri.userapp.ui.activity.SelectionActivity
import com.ferri.userapp.utils.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import java.net.URL
import java.util.*

class SelectionActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mBtnContinue: Button? = null
    private var btnSoftUiContinue: Button? = null
    private var mIvFacebook: ImageView? = null
    private var mIvGoogle: ImageView? = null
    private val TAG = "SelectionActivity"
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var callbackManager: CallbackManager? = null
    private var socialLoginUserDetails: SocialLoginUserDetails? = null
    private val downloadManager: DownloadManager? = null
    var internalDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    var count = 0
    var pathFolder: String? = null
    var pathFile: String? = null
    var fileName: String? = null
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
        setContentView(R.layout.activity_selection)
//        initializeGoogleLogin()
        initLayouts()
        initializeListeners()

        // explode animation on activity start.
        explodeAnim(savedInstanceState, intent)
    }

    /* init layout */
    private fun initLayouts() {
        mBtnContinue = findViewById(R.id.btnContinue)
        btnSoftUiContinue = findViewById(R.id.btnSoftUiContinue)
        mIvFacebook = findViewById(R.id.ivFacebook)
        mIvGoogle = findViewById(R.id.ivGoogle)
        rootLayout = findViewById<View>(R.id.root_layout)
    }

    /* initialize listener */
    @SuppressLint("ClickableViewAccessibility")
    private fun initializeListeners() {
        mBtnContinue!!.setOnClickListener(this)
        mBtnContinue!!.stateListAnimator = null
        btnSoftUiContinue!!.setOnClickListener(this)
        btnSoftUiContinue!!.stateListAnimator = null
        mIvFacebook!!.setOnClickListener(this)
        mIvGoogle!!.setOnClickListener(this)
    }

    private fun initializeFacebookLogin() {
        try {
            LoadingDialog.showLoadingDialog(this, "Please wait..")
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance()
                .logInWithReadPermissions(this, Arrays.asList("email,public_profile,user_gender"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        // App code
                        mylog(TAG, "onSuccess=" + loginResult.accessToken.applicationId)
                        mylog(TAG, "onSuccess=" + loginResult.accessToken.userId)
                        getFaceBookData(loginResult.accessToken)
                    }

                    override fun onCancel() {
                        // App code
                        mylog(TAG, "onCancel=Login canceled")
                        LoginManager.getInstance().logOut()
                    }

                    override fun onError(exception: FacebookException) {
                        // App code
                        mylog(TAG, "onError=" + exception.localizedMessage)
                        LoginManager.getInstance().logOut()
                    }
                })

        } catch (e: Exception) {
            mylog(TAG, "initializeFacebookLogin: Error=${e.localizedMessage}")
        }
    }

    private fun getFaceBookData(accessToken: AccessToken) {
        try {
            val graphRequest: GraphRequest = GraphRequest.newMeRequest(
                accessToken,
                GraphJSONObjectCallback { `object`, response ->
                    mylog(TAG, "onCompleted: JSONObject=$`object`")
                    LoadingDialog.cancelLoading()
                    try {
                        val fname = `object`!!.getString("first_name")
                        val lname = `object`.getString("last_name")
                        val socialId = `object`.getString("id")
                        val profile_pic =
                            URL("http://graph.facebook.com/$socialId/picture?type=large")
                        val photo = profile_pic.toString()
                        var email: String? = ""
                        if (`object`.getString("email") != null) {
                            email = `object`.getString("email")
                        }
                        socialLoginUserDetails =
                            SocialLoginUserDetails(fname, lname, socialId, photo, email, "Facebook")
                        savePreference(this@SelectionActivity, Constants.FirstTimeUser, "NO")
                        savePreference(this@SelectionActivity, Constants.REGISTER_WITH_SOCIAL, true)
                        savePreference(
                            this@SelectionActivity,
                            Constants.SOCIAL_USER_DETAILS,
                            Gson().toJson(socialLoginUserDetails)
                        )

                        /*Intent intent = new Intent(SelectionActivity.this, SignInActivity.class);
                                  Bundle bundle = new Bundle();
                                  bundle.putSerializable("socialLoginData", socialLoginUserDetails);
                                  intent.putExtras(bundle);*/

                        startActivity(SignInActivity::class.java)
                        finish()
                    } catch (e: Exception) {
                        LoadingDialog.cancelLoading()
                        mylog(TAG, "onCompleted: Error=" + e.localizedMessage)
                    }
                })
            val bundle = Bundle()
            bundle.putString("fields", "first_name, last_name, id, email")
            graphRequest.parameters = bundle
            graphRequest.executeAsync()
        } catch (e: Exception) {
            mylog(TAG, "getFaceBookData: Errror=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
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

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mBtnContinue) {
            vibratePhone(this)
            savePreference(this@SelectionActivity, Constants.FirstTimeUser, "NO")
            savePreference(this@SelectionActivity, Constants.REGISTER_WITH_SOCIAL, false)
            startActivity(SignInActivity::class.java)
            finish()
        }
       /* if (v === mIvFacebook) {
            initializeFacebookLogin()
        }
        if (v === mIvGoogle) {
            LoadingDialog.showLoadingDialog(this, "Please wait..")
            val signInIntent = mGoogleSignInClient!!.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }*/
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        } else {
            callbackManager!!.onActivityResult(requestCode, resultCode, data)
            //            getFaceBookData(AccessToken.getCurrentAccessToken());
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val acct = completedTask.getResult(ApiException::class.java)
            //            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            LoadingDialog.cancelLoading()
            if (acct != null) {
                val personName = acct.displayName
                val personGivenName = acct.givenName
                val personFamilyName = acct.familyName
                val personEmail = acct.email
                val personId = acct.id
                val personPhoto = acct.photoUrl
                mylog(TAG, "handleSignInResult: personName=$personName")
                mylog(TAG, "handleSignInResult: personGivenName=$personGivenName")
                mylog(TAG, "handleSignInResult: personFamilyName=$personFamilyName")
                mylog(TAG, "handleSignInResult: personEmail=$personEmail")
                mylog(TAG, "handleSignInResult: personId=$personId")
                mylog(TAG, "handleSignInResult: personPhoto=$personPhoto")
                socialLoginUserDetails = SocialLoginUserDetails(
                    personGivenName,
                    personFamilyName,
                    personId,
                    personPhoto.toString() + "",
                    personEmail,
                    "Google"
                )
                savePreference(this@SelectionActivity, Constants.FirstTimeUser, "NO")
                savePreference(this@SelectionActivity, Constants.REGISTER_WITH_SOCIAL, true)
                savePreference(
                    this@SelectionActivity,
                    Constants.SOCIAL_USER_DETAILS,
                    Gson().toJson(socialLoginUserDetails)
                )

                /*Intent intent = new Intent(SelectionActivity.this, SignInActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("socialLoginData", socialLoginUserDetails);
                intent.putExtras(bundle);*/startActivity(SignInActivity::class.java)
                finish()
            }

            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            LoadingDialog.cancelLoading()
            mylog(TAG, "signInResult:failed code=" + e.statusCode)
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