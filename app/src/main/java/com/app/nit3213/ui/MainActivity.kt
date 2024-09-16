package com.app.nit3213.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.nit3213.R
import com.app.nit3213.databinding.ActivityMainBinding
import com.app.nit3213.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding:ActivityMainBinding
    private val location = "sydney"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tvLogin.setOnClickListener {
            validateFields()
        }
    }

    private fun validateFields() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (username.isEmpty()) {
            binding.etUsername.error = "Username cannot be empty"
            return
        }

        if (password.isEmpty()) {
            binding.etUsername.error = "Password cannot be empty"
            return
        }
        loginUser(username,password)
    }

    private fun loginUser(username: String, password: String) {
        Log.wtf("testing","$username $password $location")
        mainViewModel.login(location, username, password)
        setUpObserver()
    }

    private fun setUpObserver() {
        mainViewModel.authResult.observe(this) { authResponse ->
            if (authResponse != null) {
                startActivity(Intent(this,DashboardActivity::class.java).putExtra("keypass",authResponse.keypass))
                finish()
            }
        }

        mainViewModel.isLoading.observe(this) { isLoading -> }

        mainViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_LONG).show()
            }
        }
    }
}