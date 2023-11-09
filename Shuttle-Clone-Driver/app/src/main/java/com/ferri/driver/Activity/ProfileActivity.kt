package com.ferri.driver.Activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ferri.driver.R
import com.ferri.driver.Util.*
import com.ferri.driver.ViewModel.MainViewModel
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : BaseActivity() {

    private val TAG = "ProfileActivity"
    var ivBack: ImageView? = null
    var ivNotification: ImageView? = null
    private var mTvFirstName: TextView? = null
    private  var mTvLastName:TextView? = null
    private  var mTvEmail:TextView? = null
    private  var mTvContact:TextView? = null
    private var mIvProfileImage: CircleImageView? = null
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initLayouts()
        doOperationOnLayouts()
    }

    /* init layout */
    private fun initLayouts() {
        ivBack = findViewById(R.id.ivBack)
        ivNotification = findViewById(R.id.ivNotification)
        mTvFirstName = findViewById(R.id.tvFirstName)
        mTvLastName = findViewById(R.id.tvLastName)
        mTvEmail = findViewById(R.id.tvEmail)
        mTvContact = findViewById(R.id.tvContact)
        mIvProfileImage = findViewById(R.id.ivProfileImage)
    }

    /* add functionality to layout */
    private fun doOperationOnLayouts() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        ivBack!!.setOnClickListener { finish() }
        ivNotification!!.setOnClickListener { startActivity(NotificationActivity::class.java) }

        getProfileDetails()

    }

    private fun getProfileDetails() {
        try {
            if (isInternetConnection(this)) {
                LoadingDialog.showLoadingDialog(this, "Please wait...")
                mainViewModel!!.getDriverDetails(getPreference(this, AppConstants.TOKEN)!!)
                    .observe(this,
                        Observer {
                            if (it != null) {
                                LoadingDialog.cancelLoading()
                                if (it.status!!) {
                                    try {
                                        mTvFirstName!!.setText(it.data!!.firstname)
                                        mTvLastName!!.setText(it.data!!.lastname)
                                        mTvEmail!!.setText(it.data!!.email)
                                        mTvContact!!.setText(it.data!!.phone)

                                        if (it.data.picture != null) {
                                            val url: String = getPreference(
                                                this,
                                                AppConstants.BASEURL
                                            ) + it.data.picture
                                            myLog(TAG, "onSuccess:getProfileDetails url=" + url)
                                            Glide.with(applicationContext).load(url)
                                                .placeholder(R.drawable.ic_profile).into(
                                                    mIvProfileImage!!
                                                )
                                        }

                                    } catch (e: java.lang.Exception) {
                                        myLog(
                                            TAG,
                                            "onSuccess:getProfileDetails Errrr=" + e.localizedMessage
                                        )
                                    }
                                } else alertDialog(this, it.message.toString())
                            } else {
                                mainViewModel!!.tokenRefresh(
                                    getPreference(this, AppConstants.PHONE_NO)!!,
                                    getPreference(this, AppConstants.csrfTOKEN)!!,
                                    getPreference(this, AppConstants.onModel)!!
                                ).observe(this,
                                    Observer {
                                        if (it != null) {
                                            if (it.status!!) {
                                                LoadingDialog.cancelLoading()
                                                savePreference(
                                                    this,
                                                    AppConstants.TOKEN,
                                                    "Bearer " + it.data!!.token
                                                )
                                                savePreference(
                                                    this,
                                                    AppConstants.csrfTOKEN,
                                                    it.data.csrfToken
                                                )
                                                getProfileDetails()
                                            }else alertDialog(this, it.message.toString())
                                        }else sessionExpireDialog(this)
                                    })
                            }
                        })

            } else toast(this)

        }catch (e: Exception){
            myLog(TAG, e.localizedMessage)
        }
    }
}