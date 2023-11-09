package com.ferri.userapp.ui.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.RetrofitRepository.RetrofitClient
import com.ferri.userapp.model.Data
import com.ferri.userapp.model.ReferCodeModel
import com.ferri.userapp.utils.*
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.shortLinkAsync
import com.google.firebase.ktx.Firebase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ReferEarnActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mTvLink: TextView? = null
    private var mTvCode: TextView? = null
    private var tvReferPolicy: TextView? = null
    private var mTvRfrAmount: TextView? = null
    private var mIvBack: ImageView? = null
    private var mIvFaceBook: ImageView? = null
    private var mIvWhatsapp: ImageView? = null
    private var mIvGoogle: ImageView? = null
    private var mIvTwitter: ImageView? = null
    private var mIvNotification: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refer_earn)
        initLayouts()
        initializeListeners()
        getReferDetails()
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mTvLink = findViewById(R.id.tvLink)
        mTvRfrAmount = findViewById(R.id.tvRfrAmount)
        mTvCode = findViewById(R.id.tvRfrCode)
        mIvFaceBook = findViewById(R.id.ivFaceBook)
        mIvWhatsapp = findViewById(R.id.ivWhatsapp)
        mIvGoogle = findViewById(R.id.ivGoogle)
        mIvTwitter = findViewById(R.id.ivTwitter)
        mIvNotification = findViewById(R.id.ivNotification)
        tvReferPolicy = findViewById(R.id.tvReferPolicy)
    }

    private fun getReferDetails() {
        try {
            LoadingDialog.showLoadingDialog(this, "Please wait...")
            if (isNetworkAvailable(this)) {
                RetrofitClient.getClient().getReferDetails(getPreference(this, Constants.TOKEN))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<ReferCodeModel?>() {
                        override fun onSuccess(defaultResponse: ReferCodeModel) {
                            mylog(TAG, "onSuccess: MSG=" + defaultResponse.message)
                            mylog(TAG, "onSuccess: Status=" + defaultResponse.status)
                            LoadingDialog.cancelLoading()
                            if (defaultResponse.status!!) {
                                setDetails(defaultResponse.data)
                            }
                        }

                        override fun onError(e: Throwable) {
                            mylog(TAG, "onError: getReferDetails Error=" + e.localizedMessage)
                            LoadingDialog.cancelLoading()
                            if (e.localizedMessage == Constants.Unauthorized || e.localizedMessage == Constants.Forbidden) checkToken(
                                applicationContext
                            ) { success ->
                                if (success) getReferDetails()
                                else sessionExpireDialog(this@ReferEarnActivity)
                            }
                        }
                    })
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "getReferDetails: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }

    private fun setDetails(response: Data?) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvReferPolicy!!.text = Html.fromHtml(
                    response!!.referralPolicy,
                    Html.FROM_HTML_MODE_COMPACT
                )
            } else {
                tvReferPolicy!!.text = Html.fromHtml(response!!.referralPolicy)
            }
            mTvRfrAmount!!.text = response.amount.toString() + ""
            mTvCode!!.text = response.refercode
            savePreference(this, Constants.REFERRAL_CODE, response.refercode)
        } catch (e: Exception) {
            mylog(TAG, "setDetails: Error" + e.localizedMessage)
        }
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mTvCode!!.setOnClickListener(this)
        mIvFaceBook!!.setOnClickListener(this)
        mIvWhatsapp!!.setOnClickListener(this)
        mIvGoogle!!.setOnClickListener(this)
        mIvTwitter!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
        mTvLink!!.text = getString(R.string.text_link)
        SetNotificationImage(mIvNotification)
    }

    /* onClick listener */
    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressed() else if (v === mTvCode) {
            setClipboard(this, mTvCode!!.text.toString())
            showToast(getString(R.string.txt_copy))
        } else if (v === mIvFaceBook) createDynamicLink("Default") else if (v === mIvWhatsapp) createDynamicLink(
            "Whatsapp"
        ) else if (v === mIvGoogle) createDynamicLink("Default") else if (v === mIvTwitter) createDynamicLink(
            "Default"
        ) else if (v === mIvNotification) {
            startActivity(NotificationActivity::class.java)
        }
    }


    private fun createDynamicLink(shareOn: String) {

        vibratePhone(this)

        val sharelink = "https://ferriuser.page.link/?" +
                "link=http://shuttle.theferri.com?referral=" + getPreference(
            this,
            Constants.REFERRAL_CODE
        ) +
                "&apn=" + packageName +
                "&st=" + "User Share & Earn" +
                "&sd=" + "User joins, your earn 20, Rider joins, your earn 20" +
                "&si=" + "http://shuttle.theferri.com/uploads/ferri-shuttle.png"


        Firebase.dynamicLinks.shortLinkAsync {
            longLink = Uri.parse(sharelink)
        }.addOnSuccessListener { result ->
            // Short link created
            val shortLink = result.shortLink
            val flowchartLink = result.previewLink
            Log.d(TAG, "shortLink: " + shortLink)

            Log.i(TAG, "createDynamicLink: shortLink=$shortLink")
            Log.i(TAG, "createDynamicLink: flowchartLink=$flowchartLink")
            shareInviteLink(shortLink.toString(), shareOn)

        }.addOnFailureListener {
            Log.d(
                TAG,
                "createDynamicLink: something went wrong in shortLinkTask create error=${it.localizedMessage}"
            )
            shareInviteLink("", shareOn)
        }

        /* FirebaseDynamicLinks.getInstance().createDynamicLink()
                 .setLink(Uri.parse(sharelink))
                 .setDomainUriPrefix("ferriuser.page.link")
                 .buildShortDynamicLink()
                 .addOnCompleteListener(this) { task ->
                     if (task.isSuccessful) {
                         // Short link created
                         val shortLink = task.result.shortLink
                         val flowchartLink = task.result.previewLink
                         Log.i(TAG, "createDynamicLink: shortLink=$shortLink")
                         shareInviteLink(shortLink.toString(), shareOn)
                     } else {
                         Log.i(TAG, "createDynamicLink: error=${task.exception?.localizedMessage}")
                     }
                 }*/
    }


    fun shareInviteLink(linkUrl: String?, shareOn: String) {
        var shareMessage = ""
        try {
            shareMessage =
                "\n\nHello there, India’s first Govt fare ride is here with 100% lowest rate commute.\nI’m loving this app,\nTake a ride with Ferri today. \n\n" +
                        "(CLICK HERE & INSTALL Ferri NOW !)   \n\n" + getInvitationMessage(linkUrl)

            Log.i(TAG, "shareInviteLink: shareMessage=$shareMessage")
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            if (shareOn.equals("Whatsapp")) shareIntent.setPackage("com.whatsapp")
            val bm = BitmapFactory.decodeResource(resources, R.drawable.share_screen)
            val path = MediaStore.Images.Media.insertImage(contentResolver, bm, "", null)
            val screenshotUri = Uri.parse(path)

            shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri)
            shareIntent.type = "image/*"

            startActivity(Intent.createChooser(shareIntent, "Share with"))

        } catch (e: Exception) {
            Log.i(TAG, "shareInviteLink: Error=${e.localizedMessage}")
            shareWithNoImage(shareMessage, shareOn)
        }
    }

    private fun shareWithNoImage(shareMessage: String, shareOn: String) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            if (shareOn.equals("Whatsapp")) shareIntent.setPackage("com.whatsapp")
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "Share with"))
        } catch (e: Exception) {
            Log.i(TAG, "shareWithNoImage: Error=${e.localizedMessage}")
        }
    }

    private fun getInvitationMessage(linkUrl: String?): Any? {

        if (linkUrl != null) {
            val playStoreLink =
                linkUrl + "\n"
            val tst = "Get benefits with this referral code: "
            if (!getPreference(this, Constants.REFERRAL_CODE).equals("")) {
                return playStoreLink + tst + getPreference(this, Constants.REFERRAL_CODE)
            }
            return playStoreLink
        } else {
            val playStoreLink =
                "https://play.google.com/store/apps/details?id=" + packageName + "\n"
            val tst = "Get benefits with this referral code: "
            if (!getPreference(this, Constants.REFERRAL_CODE).equals("")) {
                return playStoreLink + tst + getPreference(this, Constants.REFERRAL_CODE)
            }
            return playStoreLink
        }
    }

    companion object {
        private const val TAG = "ReferEarnActivity"
    }
}