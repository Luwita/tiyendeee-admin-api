package com.ferri.userapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.model.PassesList
import com.ferri.userapp.ui.adapter.PassAdapter
import com.ferri.userapp.utils.mylog

class PassActivity : BaseActivity() {
    var rvPasses :RecyclerView?=null
    var layPassAlert :LinearLayout?=null

    private val TAG="PassActivity"
    private var seatNo=""
    private var passesList: List<PassesList>? = ArrayList()
    private var routeId = ""
    private var pickupId = ""
    private var dropId = ""
    private var busId = ""
    private var has_return = ""
    private var bookingType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass)

        findViewById<View>(R.id.ivBack).setOnClickListener { onBackPressed() }

        rvPasses=findViewById(R.id.rvPasses)
        layPassAlert=findViewById(R.id.layPassAlert)

        try {
            if (intent!=null){
                passesList = intent.getSerializableExtra("passlist") as List<PassesList>
                if (passesList!!.size>=0) {
                    hideView(layPassAlert)
                    showView(rvPasses)
                }

            }
        }catch (e:Exception){
            mylog(TAG, "onCreate: Error=${e.localizedMessage}")}

        rvPasses?.apply {
            layoutManager=LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter=PassAdapter(context,passesList)
        }

    }
}