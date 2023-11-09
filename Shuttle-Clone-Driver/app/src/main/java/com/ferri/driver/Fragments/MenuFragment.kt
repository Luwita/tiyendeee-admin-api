package com.ferri.driver.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ferri.driver.Activity.ProfileActivity
import com.ferri.driver.R
import com.ferri.driver.Util.*
import com.ferri.driver.ViewModel.MainViewModel

class MenuFragment : Fragment(), View.OnClickListener {
    companion object {
        var mTitle = "More"
    }

    private var TAG = "MenuFragment"
    private var mTvProfile: TextView? = null
    private var mTvSupport: TextView? = null
    private var mTvLogout: TextView? = null
    private var mainViewModel: MainViewModel? = null
    private var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initView(view)
        setListener()

        return view
    }


    private fun initView(view: View) {
        mTvProfile = view.findViewById(R.id.tvProfile)
        mTvSupport = view.findViewById(R.id.tvHelp)
        mTvLogout = view.findViewById(R.id.tvLogout)
    }

    private fun setListener() {
        mTvProfile!!.setOnClickListener(this)
        mTvSupport!!.setOnClickListener(this)
        mTvLogout!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.tvProfile -> {
                activity!!.startActivity(Intent(activity, ProfileActivity::class.java))
            }
            R.id.tvHelp -> {
                toast(activity, "Support")
            }
            R.id.tvLogout -> {
                logOut()
            }
        }
    }

    private fun logOut() {

        try {
            if (isInternetConnection(mContext!!)) {
                LoadingDialog.showLoadingDialog(mContext!!, "Please wait...")
                mainViewModel!!.logOut(
                    getPreference(mContext!!, AppConstants.TOKEN)!!,
                    getPreference(mContext, AppConstants.csrfTOKEN)!!
                )
                    .observe(this,
                        Observer {
                            if (it != null) {
                                LoadingDialog.cancelLoading()
                                if (it.isStatus!!) {

                                    clearData()
                                    activity!!.finish()

                                } else alertDialog(requireActivity(), it.message.toString())
                            }
                            else {
                                mainViewModel!!.tokenRefresh(
                                    getPreference(mContext, AppConstants.PHONE_NO)!!,
                                    getPreference(mContext, AppConstants.csrfTOKEN)!!,
                                    getPreference(mContext, AppConstants.onModel)!!
                                ).observe(this,
                                    Observer {
                                        if (it != null) {
                                            if (it.status!!) {
                                                LoadingDialog.cancelLoading()
                                                savePreference(mContext, AppConstants.TOKEN, "Bearer " + it.data!!.token)
                                                savePreference(mContext, AppConstants.csrfTOKEN, it.data.csrfToken)

                                                Handler().postDelayed({
                                                    logOut()
                                                },1000)

                                            }else alertDialog(requireActivity(), it.message.toString())
                                        }else sessionExpireDialog(requireActivity())
                                    })
                            }
                        })

            } else toast(mContext)

        } catch (e: Exception) {
            myLog(TAG, e.localizedMessage)
        }


    }

    private fun clearData() {
        savePreference(mContext, AppConstants.TOKEN, "")
        savePreference(mContext, AppConstants.csrfTOKEN, "")
        savePreference(mContext, AppConstants.PHONE_NO, "")
        savePreference(mContext, AppConstants.BASEURL, "")
        savePreference(mContext, AppConstants.IsDriverLogIn,false )
    }


}