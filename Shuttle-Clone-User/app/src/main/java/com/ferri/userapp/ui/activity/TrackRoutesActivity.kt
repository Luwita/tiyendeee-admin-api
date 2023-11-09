package com.ferri.userapp.ui.activity

import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.ViewModel.HomeFragmentViewModel
import com.ferri.userapp.model.TrackData
import com.ferri.userapp.utils.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*


class TrackRoutesActivity : BaseActivity(), OnMapReadyCallback {

    private var TAG = "TrackRoutesActivity"
    private var homeFragmentViewModel: HomeFragmentViewModel? = null
    private var tvRouteStopTitle: TextView? = null
    private var rvBusRoutesStops: RecyclerView? = null
    private var routeId = ""
    private var pickupId = ""
    private var dropId = ""
    private var pnrNo = ""
    private var mapView: MapView? = null
    val markers: MutableList<Marker> = ArrayList()
    private var map: GoogleMap? = null
    private var currentMarker: Marker? = null
    private val DEFAULT_ZOOM = 17f

    private var myHandler: Handler? = null
    private var myRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_routes)

        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)

        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }
        findViewById<View>(R.id.ivHome).setOnClickListener { goHome(applicationContext) }

//        tvRouteStopTitle = findViewById(R.id.tvRouteStopTitle)
        rvBusRoutesStops = findViewById(R.id.rvBusRoutesStops)

        try {
            if (intent != null) {
                pnrNo = intent.getStringExtra("pnrNo").toString()
                routeId = intent.getStringExtra("routeId").toString()
                pickupId = intent.getStringExtra("pickupId").toString()
                dropId = intent.getStringExtra("dropId").toString()
                Log.i(TAG, "onCreate: pnrNo=$pnrNo")
            }
        } catch (e: Exception) {
            mylog(TAG, "onCreate: error=${e.localizedMessage}")
        }


        mapView = findViewById<View>(R.id.map) as MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)



        homeFragmentViewModel!!.trackRoutes.observe(this, Observer {
            try {
                if (it.status!!)
                    updateBusTrackStatus(it.data!!)
            }catch (e:Exception){toast(this, getString(R.string.txt_Something_wrong))}

        })
    }

    private fun updateBusTrackStatus(data: TrackData) {
        try {

            val latlng=LatLng(data.busLat!!.toDouble(), data!!.busLng!!.toDouble())


            val location=Location("")
            location.latitude=data.busLat!!.toDouble()
                location.longitude=data.busLng!!.toDouble()
                location.bearing=data.angle!!.toFloat()

            animateMarker(location,currentMarker)

            map!!.animateCamera(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.Builder().target(latlng)
                        .bearing(location.bearing)
                        .zoom(17f)
                        .tilt(45F).build()
                )
            )

//            createMarker(data.busLat!!.toDouble(), data!!.busLng!!.toDouble(), data.busModelNo.toString(), "stops.get(i)!!.name", R.drawable.bus_stop_pin)
        }catch (e:Exception){
            Log.i(TAG, "updateBusTrackStatus: Errr=${e.localizedMessage}")}
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

        val latlng=LatLng(0.0, 0.0)

        currentMarker=  map!!.addMarker(
            MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_bus))
                .position(
                    latlng
                ).anchor(0.50f, 0.50f)
        )

        homeFragmentViewModel!!.routeStops.observe(this, Observer {
            LoadingDialog.cancelLoading()

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

                    map!!.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            currentMarker!!.getPosition(), DEFAULT_ZOOM
                        )
                    )

                    polylineOptions
                        .width(5f)
                        .color(Color.RED)
                    map!!.addPolyline(polylineOptions)

                  /*  rvBusRoutesStops!!.apply {
                        layoutManager = LinearLayoutManager(this@TrackRoutesActivity, RecyclerView.VERTICAL, false)
                        adapter = ViewStopsAdapter(this@TrackRoutesActivity, stops)
                        setHasFixedSize(true)
                    }
                    RunLayoutAnimation(rvBusRoutesStops)*/

                }
            } else toast(this, getString(R.string.txt_Something_wrong))
        })

        startTracker()
    }

    private fun startTracker() {
        try {
            myHandler = Handler()
            val delay = 3000L // 3 sec

            myRunnable = object : Runnable {
                override fun run() {
                    homeFragmentViewModel!!.trackRoutes(this@TrackRoutesActivity, getPreference(this@TrackRoutesActivity,Constants.TOKEN),pnrNo)
                    myHandler!!.postDelayed(this, delay)
                }
            }

            myHandler!!.postDelayed(myRunnable!!,delay)


        }catch (e:Exception){
            Log.i(TAG, "startTracker: Error=${e.localizedMessage}")}
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
        try {
            myHandler?.removeCallbacks(myRunnable!!)
        }catch (e:Exception){
            Log.i(TAG, "onDestroy: Error=${e.localizedMessage}")}
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }



}