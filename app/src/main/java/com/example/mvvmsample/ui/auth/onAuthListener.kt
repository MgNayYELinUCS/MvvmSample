package com.example.mvvmsample.ui.auth

interface onAuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message:String)
}