package com.ferri.driver.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferri.driver.Adapters.NotificationListAdapter
import com.ferri.driver.Util.*
import com.ferri.driver.ViewModel.MainViewModel
import com.ferri.driver.databinding.ActivityNotificationBinding

class NotificationActivity : BaseActivity() {
    var ivBack: ImageView? = null
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding:ActivityNotificationBinding
    private val TAG="NotificationActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)


        doOperationOnLayouts()
        loadData()

    }

    private fun loadData() {

        if (isInternetConnection(this)) {
            LoadingDialog.showLoadingDialog(this, "Please wait...")
            mainViewModel.getNotificationData(getPreference(this, AppConstants.TOKEN).toString())
                .observe(this,
                    Observer {
                        LoadingDialog.cancelLoading()
                        try {
                            if (it!=null) {
                                if (it.status!! && it.data!!.size != 0) {
                                    binding.rvNotifications.visibility = View.VISIBLE
                                    binding.layNoNotificationAvailable.visibility = View.GONE
                                    binding.rvNotifications.apply {
                                        setHasFixedSize(true)
                                        layoutManager =
                                            LinearLayoutManager(this@NotificationActivity)
                                        adapter = NotificationListAdapter(
                                            applicationContext,
                                            this@NotificationActivity,
                                            it.data!!
                                        )
                                    }
                                    RunLayoutAnimation(this, binding.rvNotifications!!)
                                } else {
                                    binding.rvNotifications.visibility = View.GONE
                                    binding.layNoNotificationAvailable.visibility = View.VISIBLE
//                                    alertDialog(this,it.message.toString())
                                }
                            }else sessionExpireDialog(this)
                        } catch (e: Exception) {
                            binding.rvNotifications.visibility = View.GONE
                            binding.layNoNotificationAvailable.visibility = View.VISIBLE
                            Log.i(TAG, "loadData: Error=${e.localizedMessage}")
                        }


                    })
        }else toast(this)
    }


    /* add functionality to layout */
    private fun doOperationOnLayouts() {
        binding.ivBack!!.setOnClickListener { finish() }
    }

    fun updateNotificationStatus(id: String?) {
        if (isInternetConnection(this))
            mainViewModel.updateNotificationStatus(getPreference(this,AppConstants.TOKEN).toString(),id.toString(),"1")
    }
}