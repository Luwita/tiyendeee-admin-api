package com.ferri.userapp.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.ViewModel.HomeFragmentViewModel
import com.ferri.userapp.ui.adapter.ViewStopsAdapter
import com.ferri.userapp.utils.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*


class BusRoutesActivity : BaseActivity(), OnMapReadyCallback {

    private var TAG = "BusRoutesActivity"
    private var homeFragmentViewModel: HomeFragmentViewModel? = null
    private var tvRouteStopTitle: TextView? = null
    private var rvBusRoutesStops: RecyclerView? = null
    private var routeId = ""
    private var pickupId = ""
    private var dropId = ""
    private var mapView: MapView? = null
    val markers: MutableList<Marker> = ArrayList()
    private var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_routes)

        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)

        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }
        findViewById<View>(R.id.ivHome).setOnClickListener { goHome(applicationContext) }

//        tvRouteStopTitle = findViewById(R.id.tvRouteStopTitle)
        rvBusRoutesStops = findViewById(R.id.rvBusRoutesStops)

        try {
            if (intent != null) {
                routeId = intent.getStringExtra("routeId").toString()
                pickupId = intent.getStringExtra("pickupId").toString()
                dropId = intent.getStringExtra("dropId").toString()
            }
        } catch (e: Exception) {
            mylog(TAG, "onCreate: error=${e.localizedMessage}")
        }


        mapView = findViewById<View>(R.id.map) as MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)

    }

    override fun onMapReady(gmap: GoogleMap) {
        map = gmap
        map!!.uiSettings.isMapToolbarEnabled = false
        map!!.uiSettings.isZoomControlsEnabled = false
        map!!.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.map_style))
        if (routeId != "" && pickupId != "" && dropId != "") {
            LoadingDialog.showLoadingDialog(this, "Loading...")
            homeFragmentViewModel!!.routeStops(this, getPreference(this, Constants.TOKEN), routeId, pickupId, dropId)
        }else toast(this, getString(R.string.txt_Something_wrong))

        homeFragmentViewModel!!.routeStops.observe(this, Observer {
            LoadingDialog.cancelLoading()

            try{
            if (it != null) {
                if (it.status!! && it.data!!.size != 0) {

                    val stops = it.data

                    val polylineOptions = PolylineOptions()

                    for (i in 0 until stops.size) {
                        markers.add(createMarker(stops.get(i)!!.lat!!, stops.get(i)!!.lng!!, stops.get(i)!!.name, stops.get(i)!!.name, R.drawable.bus_stop_pin)!!)
                        polylineOptions.add(LatLng(stops.get(i)!!.lat!!, stops.get(i)!!.lng!!))
                    }


                    val builder = LatLngBounds.Builder()
                    for (marker in markers) {
                        builder.include(marker.position)
                    }
                    val bounds = builder.build()
                    val cu = CameraUpdateFactory.newLatLngBounds(bounds, 100)
                    map!!.moveCamera(cu)
                    map!!.animateCamera(cu, 2000, null);

                    polylineOptions
                        .width(5f)
                        .color(Color.RED)
                    map!!.addPolyline(polylineOptions)

                    rvBusRoutesStops!!.apply {
                        layoutManager = LinearLayoutManager(this@BusRoutesActivity, RecyclerView.VERTICAL, false)
                        adapter = ViewStopsAdapter(this@BusRoutesActivity, stops)
                        setHasFixedSize(true)
                    }
                    RunLayoutAnimation(rvBusRoutesStops)

                }
            } else toast(this, getString(R.string.txt_Something_wrong))
            }catch (e:Exception){toast(this, getString(R.string.txt_Something_wrong))}
        })
    }


    protected fun createMarker(latitude: Double, longitude: Double, title: String?, snippet: String?, iconResID: Int): Marker? {
        return map!!.addMarker(MarkerOptions()
                .position(LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .icon(getMarkerIconFromDrawable(getDrawable(iconResID)!!)))
    }


    override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }


    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    override fun onPause() {
        mapView!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView!!.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

}