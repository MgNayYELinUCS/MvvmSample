package com.example.mvvmsample.ui.auth

import androidx.lifecycle.LiveData
import com.example.mvvmsample.data.network.model.AuthResponse

interface onAuthListener {
    fun onStarted()
    fun onSuccess(user: AuthResponse.User)
    fun onFailure(message:String)
}