package com.ferri.userapp.ui.activity

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.ViewModel.HomeFragmentViewModel
import com.ferri.userapp.model.RoutesDataItem
import com.ferri.userapp.ui.adapter.BusRoutesAdapter
import com.ferri.userapp.ui.adapter.RouteStopsAdapter
import com.ferri.userapp.utils.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class ExploreActivity : BaseActivity() {
    private var TAG = "ExploreActivity"
    private var homeFragmentViewModel: HomeFragmentViewModel? = null
    private var routesRecyclerView: RecyclerView? = null
    private var busRoutesAdapter: BusRoutesAdapter? = null
    private var notRoutesFoundLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)

        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }
        findViewById<View>(R.id.ivHome).setOnClickListener { goHome(applicationContext) }
        routesRecyclerView = findViewById(R.id.rvBusRoutes)
        notRoutesFoundLayout = findViewById(R.id.llRoutesNotFound)

        routesRecyclerView!!.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))

    }

    override fun onStart() {
        super.onStart()
        homeFragmentViewModel!!.exploreRoutes(this, getPreference(this, Constants.TOKEN))

        LoadingDialog.showLoadingDialog(this, "Loading...")
        homeFragmentViewModel!!.exploreRoutes.observe(this, Observer {
            LoadingDialog.cancelLoading()
try{
            if (it != null) {
                if (it.status!! && it.data!!.size != 0) {
                    routesRecyclerView!!.apply {
                        layoutManager = LinearLayoutManager(this@ExploreActivity, RecyclerView.VERTICAL, false)
                        adapter = BusRoutesAdapter(this@ExploreActivity, it.data)
                        setHasFixedSize(true)
                    }
                    RunLayoutAnimation(routesRecyclerView)
                    notRoutesFoundLayout!!.visibility = View.GONE
                } else notRoutesFoundLayout!!.visibility = View.VISIBLE
            } else toast(this, getString(R.string.txt_Something_wrong))
}catch (e:Exception){toast(this, getString(R.string.txt_Something_wrong))}
        })
    }

     fun viewRouteStops(stopsData: RoutesDataItem) {
        try {

            val view: View = layoutInflater.inflate(R.layout.route_stops_layout, null)
            val dialog = BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme)

            view.findViewById<TextView>(R.id.tvTitle).setText(stopsData.routeTitle + " stops")
            view.findViewById<ImageView>(R.id.imgCancel).setOnClickListener { dialog.dismiss() }
            val rvBusRouteStops=view.findViewById<RecyclerView>(R.id.rvBusRouteStops)


            rvBusRouteStops.apply {
                layoutManager=LinearLayoutManager(this@ExploreActivity, RecyclerView.VERTICAL, false)
                adapter=RouteStopsAdapter(this@ExploreActivity, stopsData!!.stops)
                setHasFixedSize(true)
            }
            RunLayoutAnimation(rvBusRouteStops)
            dialog.setOnShowListener(object : DialogInterface.OnShowListener {
                override fun onShow(dialog: DialogInterface) {
                    val d = dialog as BottomSheetDialog
                    val bottomSheet =
                            d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
                    val behavior = BottomSheetBehavior.from<View?>(bottomSheet!!)
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED

                    behavior.setBottomSheetCallback(object :
                            BottomSheetBehavior.BottomSheetCallback() {
                        override fun onStateChanged(bottomSheet: View, newState: Int) {
                            if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                            }
                        }

                        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                    })
                }
            })

            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
        } catch (e: Exception) {
            Log.i(TAG, "viewRouteStops: Error=" + e.localizedMessage)
        }
    }

}