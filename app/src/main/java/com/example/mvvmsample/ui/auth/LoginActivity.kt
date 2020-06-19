package com.example.mvvmsample.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsample.R
import com.example.mvvmsample.data.network.model.AuthResponse
import com.example.mvvmsample.databinding.ActivityLoginBinding
import com.example.mvvmsample.util.hide
import com.example.mvvmsample.util.show
import com.example.mvvmsample.util.snackBar
import com.example.mvvmsample.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.log

class LoginActivity : AppCompatActivity(),onAuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //probelalkhan@gmail.com
        //123456
        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user:AuthResponse.User) {
        progress_bar.hide()
        //toast("${user.name} is Logged in")
        root_layout.snackBar("${user.name} is Logged in")

        /* loginResponse.observe(this, Observer {
             progress_bar.hide()
             toast(it)
             Log.i("@@data",it)
         })*/
    }


    override fun onFailure(message: String) {
        progress_bar.hide()
        ed_Name.error = "Enter User Name"
        root_layout.snackBar(message)
    }
}