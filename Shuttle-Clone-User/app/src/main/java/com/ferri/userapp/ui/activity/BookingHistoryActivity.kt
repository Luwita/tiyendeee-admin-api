package com.ferri.userapp.ui.activity

import com.ferri.userapp.BaseActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.R
import com.ferri.userapp.RetrofitRepository.ApiCallBack
import com.ferri.userapp.RetrofitRepository.RetrofitClient
import com.ferri.userapp.model.BookingTransactionHistoryResponse
import com.ferri.userapp.ui.adapter.BookingTransactionHistoryAdapter
import com.ferri.userapp.utils.*
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class BookingHistoryActivity : BaseActivity(), View.OnClickListener {
    private val TAG = "BookingHistoryActivity"
    private var rvBookingTransHistory: RecyclerView? = null
    private var mLayNoDataAvailable: LinearLayout? = null
    private var mIvBack: ImageView? = null
    private var offSet = 0
    private var limit = 10
    private var loadingBookingItems = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_trans_history)
        initLayouts()
        initializeListeners()
        getBookingHistory()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)

        val llmComplete = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvBookingTransHistory!!.layoutManager = llmComplete

       /* rvBookingTransHistory!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val currentItems = llmComplete!!.getChildCount()
                val totalItems = llmComplete.getItemCount()
                val scrollOutItems = llmComplete.findFirstVisibleItemPosition()

                if (dy > 0 && loadingBookingItems && (currentItems + scrollOutItems == totalItems)) {
                    loadingBookingItems = false
                    offSet = totalItems + 1
                    getBookingHistory()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    loadingBookingItems = true
                }
            }
        })*/

    }

    private fun getBookingHistory() {
        try {
            if (isNetworkAvailable(this)) {
                LoadingDialog.showLoadingDialog(this, "Loading...")
                RetrofitClient.getClient().getBookingHistory(getPreference(this, Constants.TOKEN),offSet,limit)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object :
                        DisposableSingleObserver<BookingTransactionHistoryResponse?>() {
                        override fun onSuccess(response: BookingTransactionHistoryResponse) {
                            mylog(TAG, "BookingTransactionHistory: Response=${Gson().toJson(response)}")
                            if (response.status!!&&response.data!!.size!=0) {
                                rvBookingTransHistory!!.adapter = BookingTransactionHistoryAdapter(
                                    this@BookingHistoryActivity,
                                    response.data!!,
                                    response.data[0].bookingDetails!!,
                                    "BOOKING_DTL"
                                )
                                RunLayoutAnimation(rvBookingTransHistory)
                                 mLayNoDataAvailable!!.isVisible=false
                            }else mLayNoDataAvailable!!.isVisible=true

                            LoadingDialog.cancelLoading()
                        }

                        override fun onError(e: Throwable) {
                            mylog(TAG, "onError: TransactionHistory=" + e.localizedMessage)
                            LoadingDialog.cancelLoading()
                            mLayNoDataAvailable!!.isVisible=true
                            if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                    Constants.Forbidden
                                )
                            )
                                checkToken(
                                    applicationContext,
                                    ApiCallBack { success ->
                                        if (success) getBookingHistory()
                                        else sessionExpireDialog(this@BookingHistoryActivity)
                                    })
                        }
                    })
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: java.lang.Exception) {
            mylog(TAG, "BookingTransactionHistory: Error=" + e.localizedMessage)
        }

    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        rvBookingTransHistory = findViewById(R.id.rvBookingTransHistory)
        mLayNoDataAvailable = findViewById(R.id.layNoDataAvailable)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressed()
    }
}