package gymkapp.main

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.*

class MapsFragmentModel : ViewModel(){


  enum class LocationSettingsStatus {
    ENABLED,
    DISABLED,
    UNKNOWN
  }

  private val classTag = javaClass.simpleName

  val locationRequest: LocationRequest by lazy {
    LocationRequest().apply {
      interval = 10_000
      fastestInterval = 1_000
      priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
  }

  val locationCallback by lazy {
    object: LocationCallback(){
      override fun onLocationResult(locRes: LocationResult?) {

        Log.d(
          classTag,
          "Recibiendo actualizacion Lat Long: ${currentLoc?.latitude},${currentLoc?.longitude} -> ${locRes?.lastLocation?.latitude}, ${locRes?.lastLocation?.longitude}"
        )
        currentLoc = locRes?.lastLocation
      }
    }
  }

  var currentLoc : Location? = null
}