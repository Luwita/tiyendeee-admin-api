package com.ferri.userapp.ui.activity

import android.annotation.TargetApi
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.RetrofitRepository.ApiCallBack
import com.ferri.userapp.RetrofitRepository.RetrofitClient
import com.ferri.userapp.model.SocialLoginUserDetails
import com.ferri.userapp.model.UserProfileUpdateResponse
import com.ferri.userapp.ui.events.UpdateLocationEvent
import com.ferri.userapp.ui.fragment.SearchLocationDialogFragment
import com.ferri.userapp.utils.*
import com.ferri.userapp.utils.Constants.UPDATE_HOME
import com.ferri.userapp.utils.Constants.UPDATE_OFFICE
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.*


class ProfileSettingsActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mIvBack: ImageView? = null
    private var mIvNotification: ImageView? = null
    private var mIvAddProfile: ImageView? = null
    private var mIvProfileImage: CircleImageView? = null
    private var mReferralCard: CardView? = null
    private var mBtnSave: Button? = null
    private var mSpGender: Spinner? = null
    private val TAG = "ProfileSettingsActivity"
    private var phoneNo = ""
    private var officeAddress = ""
    private var officeLat = ""
    private var officeLng = ""
    private var homeAddress = ""
    private var homeLat = ""
    private var homeLng = ""
    private var homeLeaveTime = ""
    private var officeLeaveTime = ""
    private var mEdFirstName: EditText? = null
    private var mEdLastName: EditText? = null
    private var mTvHomeAddress: TextView? = null
    private var mTvOfficeAddress: TextView? = null
    private var mTvHomeTime: TextView? = null
    private var mTvOfficeTime: TextView? = null
    private var mEdEmail: EditText? = null
    private var mEdContact: EditText? = null
    private var mEdReferralCode: EditText? = null
    private var body: MultipartBody.Part? = null
    private var imageUri: Uri? = null
    private var socialLoginUserDetails: SocialLoginUserDetails? = null
    private var isRegisteredWithSocial = false
    var eventBus = EventBus.getDefault()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        isRegisteredWithSocial = isPreference(this, Constants.REGISTER_WITH_SOCIAL)
        if (isRegisteredWithSocial) {
            val socialData = getPreference(this, Constants.SOCIAL_USER_DETAILS)
            mylog(TAG, "onCreate: socialData=$socialData")
            socialLoginUserDetails = Gson().fromJson(socialData, SocialLoginUserDetails::class.java)
        }

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
            eventBus = EventBus.getDefault()
        }

        initLayouts()
        initializeListeners()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mBtnSave!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
        mIvAddProfile!!.setOnClickListener(this)
        mTvHomeAddress!!.setOnClickListener(this)
        mTvHomeTime!!.setOnClickListener(this)
        mTvOfficeAddress!!.setOnClickListener(this)
        mTvOfficeTime!!.setOnClickListener(this)
        mBtnSave!!.stateListAnimator = null
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mBtnSave = findViewById(R.id.btnSave)
        mEdFirstName = findViewById(R.id.edFirstName)
        mEdLastName = findViewById(R.id.edLastName)
        mEdEmail = findViewById(R.id.edEmail)
        mEdContact = findViewById(R.id.edContact)
        mEdReferralCode = findViewById(R.id.edReferralCode)
        mReferralCard = findViewById(R.id.card3)
        mIvAddProfile = findViewById(R.id.ivAddProfile)
        mSpGender = findViewById(R.id.spGenger)
        mTvHomeAddress = findViewById(R.id.tvHomeAddress)
        mTvHomeTime = findViewById(R.id.tvHomeTime)
        mTvOfficeAddress = findViewById(R.id.tvOfficeAddress)
        mTvOfficeTime = findViewById(R.id.tvOfficeTime)
        mIvProfileImage = findViewById(R.id.ivProfileImage)
        mIvNotification = findViewById(R.id.ivNotification)
        SetNotificationImage(mIvNotification)
        val intent = intent
        if (intent != null) {
            phoneNo = intent.getLongExtra("phone", 0).toString()
            if (phoneNo != "0") {
                mEdContact!!.setEnabled(false)
                mEdContact!!.setText(phoneNo)
            }
        }
        if (!isPreference(
                this@ProfileSettingsActivity,
                Constants.IsUserUpdatingFirstTime
            )
        ) getProfileDetails() else {
            mylog(TAG, "initLayouts: IsUserUpdatingFirstTime ELSEE")
            mReferralCard!!.setVisibility(View.VISIBLE)
            getDynamicLinkData()
            if (isRegisteredWithSocial) {
                mEdEmail!!.setText(socialLoginUserDetails!!.email)
                mEdFirstName!!.setText(socialLoginUserDetails!!.fname)
                mEdLastName!!.setText(socialLoginUserDetails!!.lname)
                mEdContact!!.setEnabled(false)
                mylog(TAG, "initLayouts: isRegisteredWithSocial TRUVE")
                try {
                    if (socialLoginUserDetails!!.photo != null && socialLoginUserDetails.toString() == "") {
                        Glide.with(applicationContext).load(socialLoginUserDetails!!.photo)
                            .placeholder(R.drawable.ic_profile).into(mIvProfileImage!!)
                        val url = URL(socialLoginUserDetails!!.photo)
                        val image =
                            BitmapFactory.decodeStream(url.openConnection().getInputStream())
                        convertBitmapToFile(image)
                    }
                } catch (e: Exception) {
                    mylog(TAG, e.localizedMessage)
                }
            } else mylog(TAG, "initLayouts: IsUserUpdatingFirstTime TRUE")
        }
    }

    private fun getDynamicLinkData() {
        try {

            FirebaseDynamicLinks.getInstance()
                .getDynamicLink(intent)
                .addOnSuccessListener(this) { pendingDynamicLinkData ->
                    // Get deep link from result (may be null if no link is found)
                    var deepLink: Uri? = null
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.link
                    }


                    Log.d(TAG, "dynamicLink: deepLink " + deepLink)

                    val userId = deepLink.toString().indexOf('=')
                        .let { if (it == -1) null else deepLink.toString().substring(it + 1) }
                    if (userId != null) {
                        mEdReferralCode!!.setText(userId)
                        mEdReferralCode!!.isEnabled = false
                    }
                    Log.d(TAG, "dynamicLink :user id " + userId)
                }
                .addOnFailureListener(this) { e -> Log.w(TAG, "getDynamicLink:onFailure", e) }
        } catch (e: java.lang.Exception) {
            Log.i(TAG, "getDynamicLinkData: error=${e.localizedMessage}")
        }
    }

    private fun getProfileDetails() {
        try {
            if (isNetworkAvailable(this)) {
                LoadingDialog.showLoadingDialog(this, "Loading...")
                RetrofitClient.getClient().getProfileDetails(getPreference(this, Constants.TOKEN))
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<UserProfileUpdateResponse?>() {
                        override fun onSuccess(data: UserProfileUpdateResponse) {
                            LoadingDialog.cancelLoading()
                            if (data.isStatus) {
                                try {
                                    mylog(TAG, "dataResponse=" + apiResponse(data))
                                    mEdEmail!!.setText(data.data.email)
                                    mEdFirstName!!.setText(data.data.firstname)
                                    mEdLastName!!.setText(data.data.lastname)
                                    mEdContact!!.setText(data.data.phone)
                                    mEdContact!!.isEnabled = false


                                    mTvOfficeAddress!!.setText(data.data.officeAddress)
                                    mTvHomeAddress!!.setText(data.data.homeAddress)
                                    mTvHomeTime!!.setText(data.data.homeTiming)
                                    mTvOfficeTime!!.setText(data.data.officeTiming)


                                    savePreference(
                                        this@ProfileSettingsActivity,
                                        Constants.USER_NAME,
                                        data.data.firstname + " " + data.data.lastname
                                    )
                                    savePreference(
                                        this@ProfileSettingsActivity,
                                        Constants.EMAIL,
                                        data.data.email + ""
                                    )
                                    if (data.data.profilePic != null) {
                                        mylog(TAG, "onSuccess: url1=" + data.data.profilePic)
                                        val url = data.baseurl + data.data.profilePic
                                        mylog(TAG, "onSuccess: url2=$url")
                                        Glide.with(applicationContext).load(url)
                                            .placeholder(R.drawable.ic_profile)
                                            .into(mIvProfileImage!!)
                                    }
                                    if (data.data.gender == "Male") {
                                        mSpGender!!.setSelection(0);
                                    } else mSpGender!!.setSelection(1)
                                    savePreference(
                                        this@ProfileSettingsActivity,
                                        Constants.GANDER,
                                        data.data.gender + ""
                                    )

                                    officeAddress = data.data.officeAddress
                                    homeAddress = data.data.officeAddress
                                    officeLat = data.data.officeLat.toString()
                                    officeLng = data.data.officeLng.toString()
                                    homeLat = data.data.homeLat.toString()
                                    homeLng = data.data.homeLng.toString()
                                    homeLeaveTime = data.data.homeTiming
                                    officeLeaveTime = data.data.officeTiming


                                } catch (e: Exception) {
                                    mylog(
                                        TAG,
                                        "onSuccess:getProfileDetails Errrr=" + e.localizedMessage
                                    )
                                }
                            } else toast(this@ProfileSettingsActivity, data.message)
                        }

                        override fun onError(e: Throwable) {
                            mylog(TAG, "onError: updateInformation=" + e.localizedMessage)
                            LoadingDialog.cancelLoading()
                            if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                    Constants.Forbidden
                                )
                            )
                                checkToken(
                                    applicationContext,
                                    ApiCallBack { success ->
                                        if (success) getProfileDetails()
                                        else sessionExpireDialog(this@ProfileSettingsActivity)
                                    })
                        }
                    })
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "getProfileDetails: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressed() else if (v === mIvNotification) startActivity(
            NotificationActivity::class.java
        ) else if (v === mIvAddProfile) {
            openDialogBox()
        } else if (v === mBtnSave) {
            if (validate()) {
                updateInformation()
            }
        } else if (v === mTvHomeAddress) {
            val searchFragment = SearchLocationDialogFragment(this!!, Constants.UPDATE_HOME)
            if (!searchFragment!!.isVisible) {
                searchFragment!!.show(supportFragmentManager!!, "SearchAddressFragment")
            }
        } else if (v === mTvHomeTime) {
            val mcurrentTime = Calendar.getInstance()
            var hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            var minute = mcurrentTime.get(Calendar.MINUTE)

            TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minutes: Int) {

                    /*if (mcurrentTime.get(Calendar.AM_PM) === Calendar.AM) am_pm = "AM" else if (mcurrentTime.get(Calendar.AM_PM) === Calendar.PM) am_pm = "PM"

                    val strHrsToShow = if (hourOfDay === 0) "12" else hourOfDay.toString() + ""

                    mTvHomeTime!!.setText(strHrsToShow + ":" + minute + " " + am_pm)*/

                    hour = hourOfDay
                    minute = minutes
                    var timeSet = ""
                    if (hour > 12) {
                        hour -= 12
                        timeSet = "PM"
                    } else if (hour === 0) {
                        hour += 12
                        timeSet = "AM"
                    } else if (hour === 12) {
                        timeSet = "PM"
                    } else {
                        timeSet = "AM"
                    }

                    var min: String? = ""
                    if (minute < 10) min = "0$minute" else min = java.lang.String.valueOf(minute)

                    val mTime = StringBuilder().append(hour).append(':')
                        .append(min).append(" ").append(timeSet).toString()



                    mTvHomeTime!!.setText(mTime)
                    homeLeaveTime = mTime

                }
            }, hour, minute, false).show()

        } else if (v === mTvOfficeAddress) {
            val searchFragment = SearchLocationDialogFragment(this!!, Constants.UPDATE_OFFICE)
            if (!searchFragment!!.isVisible) {
                searchFragment!!.show(supportFragmentManager!!, "SearchAddressFragment")
            }
        } else if (v === mTvOfficeTime) {
            val mcurrentTime = Calendar.getInstance()
            var hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            var minute = mcurrentTime.get(Calendar.MINUTE)
            TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minutes: Int) {

                    hour = hourOfDay
                    minute = minutes
                    var timeSet = ""
                    if (hour > 12) {
                        hour -= 12
                        timeSet = "PM"
                    } else if (hour === 0) {
                        hour += 12
                        timeSet = "AM"
                    } else if (hour === 12) {
                        timeSet = "PM"
                    } else {
                        timeSet = "AM"
                    }

                    var min: String? = ""
                    if (minute < 10) min = "0$minute" else min = java.lang.String.valueOf(minute)

                    val mTime = StringBuilder().append(hour).append(':')
                        .append(min).append(" ").append(timeSet).toString()



                    mTvOfficeTime!!.setText(mTime)
                    officeLeaveTime = mTime
                }
            }, hour, minute, false).show()
        }
    }

    private fun openDialogBox() {
        try {
            val alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setTitle("Set Profile")
            alertDialog.setMessage("Choose any option.")
            alertDialog.setButton(
                AlertDialog.BUTTON_POSITIVE, "Open Gallery"
            ) { dialog, which ->
                val pickPhoto = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(pickPhoto, OPEN_GALLERY)
            }
            alertDialog.setButton(
                AlertDialog.BUTTON_NEGATIVE, "Take Picture"
            ) { dialog, which ->
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                val photo = File(getExternalFilesDir(null), "Pic.jpg")
                imageUri =
                    FileProvider.getUriForFile(applicationContext, "$packageName.provider", photo)
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(takePicture, TAKE_PHOTO)
            }
            alertDialog.show()
            val btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            val layoutParams = btnPositive.layoutParams as LinearLayout.LayoutParams
            layoutParams.weight = 10f
            btnPositive.layoutParams = layoutParams
            btnNegative.layoutParams = layoutParams
        } catch (e: Exception) {
            mylog(TAG, "openDialogBox: Error=" + e.localizedMessage)
        }
    }

    fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap {
        var width = image.width
        var height = image.height
        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode) {
            TAKE_PHOTO -> if (resultCode == RESULT_OK) {
                try {
                    var selectedImage = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                    selectedImage = getResizedBitmap(
                        selectedImage,
                        400
                    ) // 400 is for example, replace with desired size
                    Glide.with(applicationContext).asBitmap().placeholder(R.drawable.ic_profile)
                        .load(selectedImage).into(mIvProfileImage!!)
                    convertBitmapToFile(selectedImage)
                } catch (e: Exception) {
                    mylog(TAG, "onActivityResult: TAKE_PICTURE Errror=" + e.localizedMessage)
                }
            }
            OPEN_GALLERY -> if (resultCode == RESULT_OK) {
                try {
                    val imageUri = imageReturnedIntent!!.data
                    val imageStream = contentResolver.openInputStream(imageUri!!)
                    var selectedImage = BitmapFactory.decodeStream(imageStream)
                    selectedImage = getResizedBitmap(
                        selectedImage,
                        400
                    ) // 400 is for example, replace with desired size
                    Glide.with(applicationContext).asBitmap().placeholder(R.drawable.ic_profile)
                        .load(selectedImage).into(mIvProfileImage!!)
                    convertBitmapToFile(selectedImage)
                } catch (e: Exception) {
                    mylog(TAG, "onActivityResult: OPEN_GALLERY Errror=" + e.localizedMessage)
                }
            }
        }
    }

    private fun convertBitmapToFile(selectedImage: Bitmap) {
        try {
            //create a file to write bitmap data
            val file = File(cacheDir, "ProfilePic")
            file.createNewFile()

            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            selectedImage.compress(Bitmap.CompressFormat.PNG, 70 /*ignored for PNG*/, bos)
            val bitmapdata = bos.toByteArray()

            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()

/*
// pass it like this
            val requestFile: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)

// MultipartBody.Part is used to send also the actual file name
            val body: MultipartBody.Part = MultipartBody.Part.createFormData("image", file.name, requestFile)

// add another part within the multipart request
            val fullName: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "Your Name")
*/


            if (file != null) {
                val requestFile: RequestBody =
                    RequestBody.create("image/png".toMediaTypeOrNull(), file)
                mylog(TAG, "convertBitmapToFile: file.getName()=" + file.name)
                // MultipartBody.Part is used to send also the actual file name
                body =
                    MultipartBody.Part.createFormData("ProfilePic", file.name + ".png", requestFile)
            } else mylog(TAG, "convertBitmapToFile: file is null")
        } catch (e: Exception) {
            mylog(TAG, "convertBitmapToFile: Error=" + e.localizedMessage)
        }
    }

    private fun updateInformation() {
        try {
            if (isNetworkAvailable(this)) {
                val FName: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    mEdFirstName!!.text.toString()
                )
                val LName: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    mEdLastName!!.text.toString()
                )
                val gender: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    mSpGender!!.selectedItem.toString()
                )
                val email: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    mEdEmail!!.text.toString()
                )
                val referedby: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    mEdReferralCode!!.text.toString()
                )

                val device_token: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    getPreference(this, Constants.deviceToken)
                )
                val office_add: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), officeAddress)
                val office_lat: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), officeLat)
                val office_lng: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), officeLng)
                val home_add: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), homeAddress)
                val home_lat: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), homeLat)
                val home_lng: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), homeLng)
                val home_Leave_Time: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), homeLeaveTime)
                val office_Leave_Time: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), officeLeaveTime)

                var socialid: RequestBody? = null
                var mode: RequestBody? = null
                if (isRegisteredWithSocial) {
                    socialid = RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(),
                        socialLoginUserDetails!!.socialId
                    )
                    mode = RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(),
                        socialLoginUserDetails!!.mode
                    )
                } else {
                    socialid = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "")
                    mode = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "")
                }
                updateHomeAndOfficeAddress()
                LoadingDialog.showLoadingDialog(this, "Please wait...")
                RetrofitClient.getClient().updateUser(
                    getPreference(this, Constants.TOKEN),
                    body,
                    FName,
                    LName,
                    gender,
                    email,
                    referedby,
                    socialid,
                    mode,
                    device_token,
                    home_add,
                    home_lat,
                    home_lng,
                    office_add,
                    office_lat,
                    office_lng,
                    home_Leave_Time,
                    office_Leave_Time
                )
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<UserProfileUpdateResponse?>() {
                        override fun onSuccess(data: UserProfileUpdateResponse) {
                            LoadingDialog.cancelLoading()
                            mylog(TAG, "dataResponse=" + apiResponse(data))
                            if (data.isStatus) {
                                updateHomeAndOfficeAddress()
                                toast(this@ProfileSettingsActivity, data.message)
                                if (isPreference(
                                        this@ProfileSettingsActivity,
                                        Constants.IsUserUpdatingFirstTime
                                    )
                                ) {
                                    savePreference(
                                        this@ProfileSettingsActivity,
                                        Constants.IsUserRegistered,
                                        true
                                    )
                                    savePreference(
                                        this@ProfileSettingsActivity,
                                        Constants.IsUserUpdatingFirstTime,
                                        false
                                    )
                                    startActivity(DashboardActivity::class.java)
                                    finishAffinity()
                                } else getProfileDetails()
                            } else toast(this@ProfileSettingsActivity, data.message)
                        }

                        override fun onError(e: Throwable) {
                            mylog(TAG, "onError: updateInformation=" + e.localizedMessage)
                            LoadingDialog.cancelLoading()
                            if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                    Constants.Forbidden
                                )
                            )
                                checkToken(
                                    applicationContext,
                                    ApiCallBack { success -> if (success) updateInformation() })
                        }
                    })


            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "updateInformation: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }

    private fun updateHomeAndOfficeAddress() {
        try {
            RetrofitClient.getClient().addOfficeRideAddress(
                getPreference(this, Constants.TOKEN),
                homeLat,
                homeLng,
                homeAddress,
                homeLeaveTime,
                officeLat,
                officeLng,
                officeAddress,
                officeLeaveTime
            )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserProfileUpdateResponse?>() {
                    override fun onSuccess(data: UserProfileUpdateResponse) {
                        mylog(TAG, "dataResponse=" + apiResponse(data))
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: updateHomeAndOfficeAddress=" + e.localizedMessage)
                    }
                })
        } catch (e: java.lang.Exception) {
            mylog(TAG, "updateHomeAndOfficeAddress: Error=${e.localizedMessage}")
        }
    }

    /* validations */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdFirstName!!.text)) {
            flag = false
            showToast(getString(R.string.msg_first_name))
        } else if (TextUtils.isEmpty(mEdLastName!!.text)) {
            flag = false
            showToast(getString(R.string.msg_last_name))
        } else if (TextUtils.isEmpty(mEdEmail!!.text)) {
            flag = false
            showToast(getString(R.string.msg_email_required))
        } else if (TextUtils.isEmpty(mEdContact!!.text)) {
            flag = false
            showToast(getString(R.string.msg_mobile_number))
        } else if (TextUtils.isEmpty(mTvHomeAddress!!.text)) {
            flag = false
            showToast(getString(R.string.msg_home_add))
        } else if (TextUtils.isEmpty(mTvOfficeAddress!!.text)) {
            flag = false
            showToast(getString(R.string.msg_office_add))
        } else if (TextUtils.isEmpty(mTvHomeTime!!.text)) {
            flag = false
            showToast(getString(R.string.msg_time))
        } else if (TextUtils.isEmpty(mTvOfficeTime!!.text)) {
            flag = false
            showToast(getString(R.string.msg_time))
        }
        return flag
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(pusher: UpdateLocationEvent) {
        try {
            mylog(TAG, "UpdateLocationEvent: " + pusher.locationData.title)
            mylog(TAG, "UpdateLocationEvent: " + pusher.updateFor)
            if (pusher.updateFor == UPDATE_OFFICE) {
                officeAddress = pusher.locationData.title
                officeLat = pusher.locationData.locationLatitude.toString()
                officeLng = pusher.locationData.locationLongitude.toString()
                mTvOfficeAddress!!.setText(officeAddress)
            } else if (pusher.updateFor == UPDATE_HOME) {
                homeAddress = pusher.locationData.title
                homeLat = pusher.locationData.locationLatitude.toString()
                homeLng = pusher.locationData.locationLongitude.toString()
                mTvHomeAddress!!.setText(homeAddress)
            }

        } catch (e: Exception) {
            mylog(TAG, "setLocationData: ")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    companion object {
        private const val TAKE_PHOTO = 102
        private const val OPEN_GALLERY = 103
    }
}