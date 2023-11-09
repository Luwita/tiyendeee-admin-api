package com.ferri.userapp.ui.activity

import com.ferri.userapp.BaseActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.R
import com.ferri.userapp.RetrofitRepository.ApiCallBack
import com.ferri.userapp.RetrofitRepository.RetrofitClient
import com.ferri.userapp.model.WalletHistoryResponseModel
import com.ferri.userapp.ui.adapter.WalletHistoryAdapter
import com.ferri.userapp.utils.*
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TransactionHistoryActivity : BaseActivity(), View.OnClickListener {
    private val TAG = "TransactionHistoryActivity"
    private var mRvWalletHistory: RecyclerView? = null
    private var mIvBack: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_history)
        initLayouts()
        initializeListeners()
        getWalletHistory()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mRvWalletHistory!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    private fun getWalletHistory() {
        try {
            if (isNetworkAvailable(this)) {
                LoadingDialog.showLoadingDialog(this, "Loading...")
                RetrofitClient.getClient().getWalletHistory(getPreference(this, Constants.TOKEN))
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object :
                        DisposableSingleObserver<WalletHistoryResponseModel?>() {
                        override fun onSuccess(response: WalletHistoryResponseModel) {
                            mylog(TAG, "TransactionHistory: Response=${Gson().toJson(response)}")
                            if (response.status!!) {
                                mRvWalletHistory!!.adapter = WalletHistoryAdapter(
                                    this@TransactionHistoryActivity,
                                    response.data!!
                                )
                                RunLayoutAnimation(mRvWalletHistory)
                            }
                            LoadingDialog.cancelLoading()
                        }

                        override fun onError(e: Throwable) {
                            mylog(TAG, "onError: TransactionHistory=" + e.localizedMessage)
                            LoadingDialog.cancelLoading()
                            if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                    Constants.Forbidden
                                )
                            )
                                checkToken(
                                    applicationContext,
                                    ApiCallBack { success ->
                                        if (success) getWalletHistory()
                                        else sessionExpireDialog(this@TransactionHistoryActivity)
                                    })
                        }
                    })
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: java.lang.Exception) {
            mylog(TAG, "WalletHistory: Error=" + e.localizedMessage)
        }

    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mRvWalletHistory = findViewById(R.id.rvWalletHistory)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressed()
    }
}