package com.ferri.userapp.ui.fragment

import android.app.AlertDialog
import com.facebook.FacebookSdk.getApplicationContext
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.ferri.userapp.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import android.text.Html
import android.text.method.LinkMovementMethod
import com.ferri.userapp.BaseActivity
import android.content.Intent
import android.content.DialogInterface
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.ferri.userapp.RetrofitRepository.RetrofitClient
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import com.ferri.userapp.model.DefaultResponse
import com.ferri.userapp.ui.fragment.MoreFragment
import com.ferri.userapp.RetrofitRepository.ApiCallBack
import com.facebook.login.LoginManager
import com.ferri.userapp.ui.activity.*
import com.ferri.userapp.utils.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import java.lang.Exception
import java.util.*
import java.util.concurrent.Executor

class MoreFragment() : Fragment(), View.OnClickListener {
    private var mTvProfileSettings: TextView? = null
    private var mTvWallet: TextView? = null
    private var mTvCards: TextView? = null
    private var mTvReferEarn: TextView? = null
    private var mTvHelp: TextView? = null
    private var mTvLogout: TextView? = null
    private var mTvSetting: TextView? = null
    private var mTvTransactionHistory: TextView? = null
    private var mTvBookingHistory: TextView? = null
    private var mTvPass: TextView? = null
    private var mTvPoweredBy: TextView? = null
    private var mTvSuggestRoutes: TextView? = null
    private var mTvExploreRoutes: TextView? = null
    private val mFlag = "1"
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var isRegisteredWithSocial = false

    /* create view */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_more, null)
        isRegisteredWithSocial = isPreference(context, Constants.REGISTER_WITH_SOCIAL)
        initLayouts(view)
        initializeListeners()
        initializeGoogleLogin()
        return view
    }

    private fun initializeGoogleLogin() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient((context)!!, gso)
    }

    /* initialize listener */
    private fun initializeListeners() {
        mTvProfileSettings!!.setOnClickListener(this)
        mTvWallet!!.setOnClickListener(this)
        mTvCards!!.setOnClickListener(this)
        mTvReferEarn!!.setOnClickListener(this)
        mTvHelp!!.setOnClickListener(this)
        mTvLogout!!.setOnClickListener(this)
        mTvSetting!!.setOnClickListener(this)
        mTvPass!!.setOnClickListener(this)
        mTvExploreRoutes!!.setOnClickListener(this)
        mTvSuggestRoutes!!.setOnClickListener(this)
        mTvTransactionHistory!!.setOnClickListener(this)
        mTvBookingHistory!!.setOnClickListener(this)
    }

    /* init layout */
    private fun initLayouts(view: View) {
        mTvProfileSettings = view.findViewById(R.id.tvProfileSettings)
        mTvWallet = view.findViewById(R.id.tvWallet)
        mTvCards = view.findViewById(R.id.tvCards)
        mTvReferEarn = view.findViewById(R.id.tvReferEarn)
        mTvHelp = view.findViewById(R.id.tvHelp)
        mTvLogout = view.findViewById(R.id.tvLogout)
        mTvSetting = view.findViewById(R.id.tvSetting)
        mTvPass = view.findViewById(R.id.tvPass)
        mTvPoweredBy = view.findViewById(R.id.tvPoweredBy)
        mTvExploreRoutes = view.findViewById(R.id.tvExploreRoutes)
        mTvSuggestRoutes = view.findViewById(R.id.tvSuggestRoutes)
        mTvTransactionHistory = view.findViewById(R.id.tvTransactionHistory)
        mTvBookingHistory = view.findViewById(R.id.tvBookingHistory)

        /*String styledText = "This is <font color='blue'>simple</font>.";
        mTvPoweredBy.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);*/
        mTvPoweredBy!!.setText(
            Html.fromHtml(getString(R.string.text_with_link))
        )
        mTvPoweredBy!!.setMovementMethod(LinkMovementMethod.getInstance())
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mTvProfileSettings) (requireActivity() as BaseActivity).startActivity(
            ProfileSettingsActivity::class.java
        ) else if (v === mTvWallet) (requireActivity() as BaseActivity).startActivity(
            WalletActivity::class.java
        ) else if (v === mTvCards) {
            val intent = Intent(activity, CardsActivity::class.java)
            intent.putExtra(Constants.intentdata.CARDFLAG, mFlag)
            startActivity(intent)
        } else if (v === mTvReferEarn) (requireActivity() as BaseActivity).startActivity(
            ReferEarnActivity::class.java
        ) else if (v === mTvHelp) (requireActivity() as BaseActivity).startActivity(
            HelpActivity::class.java
        ) else if (v === mTvSetting) (requireActivity() as BaseActivity).startActivity(
            SettingActivity::class.java
        ) else if (v === mTvPass) (requireActivity() as BaseActivity).startActivity(
            PassActivity::class.java
        ) else if (v === mTvExploreRoutes) (requireActivity() as BaseActivity).startActivity(
            ExploreActivity::class.java
        ) else if (v === mTvSuggestRoutes) (requireActivity() as BaseActivity).startActivity(
            SuggestRouteActivity::class.java
        ) else if (v === mTvTransactionHistory) (requireActivity() as BaseActivity).startActivity(
            TransactionHistoryActivity::class.java
        ) else if (v === mTvBookingHistory) (requireActivity() as BaseActivity).startActivity(
            BookingHistoryActivity::class.java
        ) else if (v === mTvLogout) {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(getString(R.string.text_confirmation))
                .setMessage(getString(R.string.msg_logout))
            builder.setPositiveButton(getString(R.string.text_yes),
                DialogInterface.OnClickListener { dialog, id -> logOut() })
            builder.setNegativeButton(getString(R.string.text_no),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id: Int) {
                        dialog.cancel()
                    }
                })
            val alert = builder.create()
            alert.show()
        }
    }

    private fun logOut() {
        try {
            if (isNetworkAvailable((context)!!)) {
                LoadingDialog.showLoadingDialog(context, "Please wait...")
                RetrofitClient.getClient().logOutUser(
                    getPreference(context, Constants.TOKEN), getPreference(
                        context, Constants.csrfTOKEN
                    )
                ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                        override fun onSuccess(defaultResponse: DefaultResponse) {
                            LoadingDialog.cancelLoading()
                            if (defaultResponse.isStatus) {
                                toast(context, defaultResponse.message)
                                savePreference(context, Constants.FirstTimeUser, "NO")
                                savePreference(context, Constants.csrfTOKEN, "")
                                savePreference(context, Constants.TOKEN, "")
                                savePreference(context, Constants.IsUserRegistered, false)
                                savePreference(context, Constants.REGISTER_WITH_SOCIAL, false)
                                savePreference(context, Constants.SOCIAL_USER_DETAILS, "")
                                startActivity(Intent(activity, SelectionActivity::class.java))
                                activity!!.finish()
                            } else toast(context, defaultResponse.message)
                        }

                        override fun onError(e: Throwable) {
                            Log.i(TAG, "onError: logOutUser Error=" + e.localizedMessage)
                            LoadingDialog.cancelLoading()
                            if ((e.localizedMessage == Constants.Unauthorized) || (e.localizedMessage == Constants.Forbidden)) checkToken(
                                getApplicationContext(),
                                object : ApiCallBack {
                                    override fun onResponse(success: Boolean) {
                                        if (success) logOut()
                                        else sessionExpireDialog(requireActivity())
                                    }
                                })
                        }
                    })
                if (isRegisteredWithSocial) {
                    try {
                        if (GoogleSignIn.getLastSignedInAccount(requireContext()) != null) {
                            signOut()
                        }
                        LoginManager.getInstance().logOut()
                    } catch (e: Exception) {
                        Log.i(TAG, "logOutUser: Errorx=" + e.localizedMessage)
                    }
                }
            } else toast(context, getString(R.string.txt_Network))
        } catch (e: Exception) {
            Log.i(TAG, "logOutUser: Errord=" + e.localizedMessage)
        }
    }

    private fun signOut() {
        mGoogleSignInClient!!.signOut()
            .addOnCompleteListener((this as Executor), object : OnCompleteListener<Void?> {
                override fun onComplete(task: Task<Void?>) {
                    // ...
                }
            })
    }

    companion object {
        /*variable declaration*/
        val mTitle = "More"
        private val TAG = "MoreFragment"
    }
}