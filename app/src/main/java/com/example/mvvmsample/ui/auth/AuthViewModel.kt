package com.example.mvvmsample.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsample.data.repositories.UserRepository
import com.example.mvvmsample.util.ApiException
import com.example.mvvmsample.util.Coroutine

class AuthViewModel(
    private val repository: UserRepository
) :ViewModel() {
    var email:String? = null
    var password:String? = null

    fun getLoggedInUser() = repository.getUser()

    var authListener:onAuthListener? = null
    fun onLoginButtonClick(view:View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
      /*  val loginResponse = UserRepositoty().userLOgin(email!!,password!!)
        authListener?.onSuccess(loginResponse)*/
        Coroutine.main {
            try {
                val authResponse = repository.userLogin(email!!,password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            }catch (e:ApiException){
                    authListener?.onFailure(e.message.toString())
            }


        }
    }
}