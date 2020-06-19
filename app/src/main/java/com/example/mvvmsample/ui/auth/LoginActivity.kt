package com.example.mvvmsample.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsample.R
import com.example.mvvmsample.data.db.AppDatabase
import com.example.mvvmsample.data.network.MyApi
import com.example.mvvmsample.data.network.model.AuthResponse
import com.example.mvvmsample.data.repositories.UserRepository
import com.example.mvvmsample.databinding.ActivityLoginBinding
import com.example.mvvmsample.ui.home.HomeActivity
import com.example.mvvmsample.util.hide
import com.example.mvvmsample.util.show
import com.example.mvvmsample.util.snackBar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),onAuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = MyApi()
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)
        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user!=null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
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