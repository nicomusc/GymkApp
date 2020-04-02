package gymkapp.main

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

/**
 * Clase para gestionar el estado de sesión del usuario
 */
class LoginViewModel: ViewModel() {

  enum class AuthenticationState {
    UNAUTHENTICATED,
    AUTHENTICATED,
    INVALID_AUTHENTICATION
  }

  //Propiedad observable que representa el estado actual de la sesion
  val authenticationState = MutableLiveData(AuthenticationState.AUTHENTICATED)
  //valor en memoria del loginToken
  private var loginToken = ""

  /**
   * Comprueba que el usuario activo tenga un login token, si no lo tiene se le asigna uno
   * Si el usuario no tiene un loginToken valido (existe en el modelo o en almacenamiento local) entonces
   * se cambia el estado a "UNAUTHENTICATED"
   * Al entrar en esta funcion el valor por defecto del authenticationState deberia ser "AUTHENTICATED"
   */
  fun check(pref: SharedPreferences){

    if(loginToken.isEmpty() && try {
        pref.getString(R.string.TokenKey.toString(),"")!!.also { loginToken=it }.isEmpty()
      } catch(e: Exception){ true }){
      authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }
  }

  fun refuseAuthentication(){
    authenticationState.value = AuthenticationState.UNAUTHENTICATED
  }

  /**
   * Al entrar el valor de authenticationState debería ser "UNAUTHENTICATED"
   */
  fun login(user:String, password:String){

    //loginToken = remoteApi().login(user,password) ?: ""
    if(loginToken.isEmpty()) authenticationState.value = AuthenticationState.INVALID_AUTHENTICATION
  }
}