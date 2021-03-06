package gymkapp.main

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import gymkapp.main.databinding.BottomNavBinding
import gymkapp.main.viewmodel.LoginViewModel
import gymkapp.main.viewmodel.LoginViewModel.AuthenticationState.AUTHENTICATED

class MainActivity : AppCompatActivity() {

  private val loginViewModel: LoginViewModel by viewModels()
  private lateinit var bind: BottomNavBinding

  @SuppressLint("SourceLockedOrientationActivity")
  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    with(PreferenceManager.getDefaultSharedPreferences(this)) {
      if (getBoolean(R.string.NightModeSysKey.toString(), false)) {
        setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
      } else setDefaultNightMode(
        if (getBoolean(R.string.NightModeKey.toString(), false)) {
          MODE_NIGHT_YES
        } else {
          MODE_NIGHT_NO
        }
      )
    }

    bind = BottomNavBinding.inflate(layoutInflater)
    requestedOrientation =
      ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //Desactivamos el modo "landscape"
    setContentView(bind.root)

    Log.d(javaClass.simpleName, "Me han creado")
    val navController = findNavController(R.id.fragments_content)
    bind.bottomNavigationView.setupWithNavController(navController)
    loginViewModel.authenticationState.observe(
      this,
      Observer {
        bind.bottomNavigationView.visibility = if (it == AUTHENTICATED) View.VISIBLE else View.GONE
      }
    )
  }
}
