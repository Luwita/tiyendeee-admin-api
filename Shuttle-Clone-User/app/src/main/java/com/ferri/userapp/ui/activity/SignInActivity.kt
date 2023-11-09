package com.ferri.userapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.RetrofitRepository.RetrofitClient
import com.ferri.userapp.model.UserRegisterResponseModel
import com.ferri.userapp.ui.activity.VerificationActivity
import com.ferri.userapp.utils.*
import com.ferri.userapp.utils.Constants.ByPassLogin
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SignInActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mBtnContinue: Button? = null
    private var mEdMobileNumber: EditText? = null
    private var mIvFacebook: ImageView? = null
    private var mIvGoogle: ImageView? = null
    private val TAG = "SignInActivity"
    private var apiClient: GoogleApiClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_sign_in)
        initLayouts()
        initializeListeners()
        fCMToken
       /* apiClient = GoogleApiClient.Builder(this)
                .addApi(Auth.CREDENTIALS_API)
                .build()*/


        savePreference(this,Constants.DEVICE_ID, getDeviceId(applicationContext))
    }

    override fun onStart() {
        super.onStart()
//        requestHint()
    }

    // Construct a request for phone numbers and show the picker
    private fun requestHint() {
        try {
            val hintRequest = HintRequest.Builder()
                    .setPhoneNumberIdentifierSupported(true)
                    .build()
            val intent = Auth.CredentialsApi.getHintPickerIntent(
                apiClient!!, hintRequest)
            startIntentSenderForResult(intent.intentSender,
                    RESOLVE_HINT, null, 0, 0, 0)
        } catch (e: Exception) {
            mylog(TAG, e.localizedMessage)
        }
    }

    // Obtain the phone number from the result
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK) {
                val credential: Credential = data!!.getParcelableExtra(Credential.EXTRA_KEY)!!
                //                 credential.getId();  <-- will need to process phone number string
                 val phone = credential.getId().removePrefix("+91")
                mEdMobileNumber!!.setText(phone)
//                mEdMobileNumber!!.setText(credential.id)
            }
        }
    }

    // Get new FCM registration token
    private val fCMToken: Unit
        // Log and toast
        private get() {
            FirebaseMessaging.getInstance().token
                    .addOnCompleteListener(OnCompleteListener { task ->
                        try{
                        if (!task.isSuccessful) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                            return@OnCompleteListener
                        }

                        // Get new FCM registration token
                        val token = task.result
                        savePreference(this@SignInActivity, Constants.deviceToken, token)

                        // Log and toast
                        Log.d(TAG, "TOKEN=$token")
                        }catch (e:Exception){
                            Log.i(TAG, "Error=: ${e.localizedMessage}")}
                    })
        }

    /* init layout */
    private fun initLayouts() {
        mEdMobileNumber = findViewById(R.id.edMobileNumber)
        mBtnContinue = findViewById(R.id.btnContinue)
        mIvFacebook = findViewById(R.id.ivFacebook)
        mIvGoogle = findViewById(R.id.ivGoogle)

        if (ByPassLogin){
            mEdMobileNumber!!.setInputType(InputType.TYPE_CLASS_TEXT)
        }
    }

    /* initialize listener */
    @SuppressLint("ClickableViewAccessibility")
    private fun initializeListeners() {
        mBtnContinue!!.setOnClickListener(this)
        mIvFacebook!!.setOnClickListener(this)
        mIvGoogle!!.setOnClickListener(this)
        mBtnContinue!!.stateListAnimator = null


        /* mEdMobileNumber.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //  if (validate()) {

//                    startActivity(VerificationActivity.class);
                    //  }
                    return true;
                }
                return false;
            }

        });*/
    }

    /* validation */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdMobileNumber!!.text)) {
            flag = false
            showToast(getString(R.string.msg_mobile_number))
        }
        return flag
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mBtnContinue) {
            vibratePhone(this)
            if (validate() && isNetworkAvailable(baseContext)) {
                if (!ByPassLogin) {
                    LoadingDialog.showLoadingDialog(this, "Please wait...")
                    RetrofitClient.getClient().registerUser(
                        mEdMobileNumber!!.text.toString().trim { it <= ' ' },
                        getPreference(this, Constants.DEVICE_ID)
                    ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object :
                            DisposableSingleObserver<UserRegisterResponseModel?>() {
                            override fun onSuccess(userRegisterResponseModel: UserRegisterResponseModel) {
                                LoadingDialog.cancelLoading()
                                if (userRegisterResponseModel.isStatus) {
                                    Log.i(TAG, "onSuccess: OTP=" + userRegisterResponseModel.otp)
//                                    toast(this@SignInActivity, userRegisterResponseModel.title)
                                    savePreference(
                                        this@SignInActivity,
                                        Constants.TOKEN,
                                        "Bearer " + userRegisterResponseModel.token
                                    )
                                    savePreference(
                                        this@SignInActivity,
                                        Constants.csrfTOKEN,
                                        userRegisterResponseModel.csrfToken
                                    )
                                    val intent = Intent(
                                        this@SignInActivity,
                                        VerificationActivity::class.java
                                    )
                                    intent.putExtra("otp", userRegisterResponseModel.otp)
                                    val bundle = Bundle()
                                    bundle.putSerializable(
                                        "userDetail",
                                        userRegisterResponseModel.userDetail
                                    )
                                    intent.putExtras(bundle)
                                    startActivity(intent)
                                } else toast(this@SignInActivity, userRegisterResponseModel.title)
                            }

                            override fun onError(e: Throwable) {
                                Log.i(TAG, "onError: Registration Error=" + e.localizedMessage)
                                LoadingDialog.cancelLoading()
                            }
                        })
                }else{
                    savePreference(
                        this@SignInActivity,
                        Constants.TOKEN,
                        "Bearer " + mEdMobileNumber!!.text.toString()
                    )
                    savePreference(
                        this@SignInActivity,
                        Constants.csrfTOKEN,mEdMobileNumber!!.text.toString()
                    )
                    val intent = Intent(this@SignInActivity, VerificationActivity::class.java)
                    startActivity(intent)
                }
            } else toast(this@SignInActivity, getString(R.string.txt_Network))
        }
    }

    companion object {
        private const val RESOLVE_HINT = 1040
    }
}