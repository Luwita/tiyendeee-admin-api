package rigo.regotaxi.rigodrivers.fb

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.ferri.driver.Model.TrackingStatusResponse

object LiveUpdate {

    val updateLocation : MutableLiveData<Location> by lazy {
        MutableLiveData<Location>()
    }

    val updateTrackStatus : MutableLiveData<TrackingStatusResponse> by lazy {
        MutableLiveData<TrackingStatusResponse>()
    }
}