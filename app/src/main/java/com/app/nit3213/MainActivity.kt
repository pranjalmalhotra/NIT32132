package com.app.nit3213

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.nit3213.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val authViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val location = "sydney"
        val username = "Pranjal"
        val password = "s4667717"

        // Observe authentication result
        authViewModel.authResult.observe(this) { authResponse ->
            if (authResponse != null) {
                Log.wtf("checking","${authResponse.keypass}")
            }
        }

        authViewModel.isLoading.observe(this) { isLoading ->
            Log.wtf("checking","${isLoading}")

        }

        // Observe error messages
        authViewModel.errorMessage.observe(this) { error ->
            Log.wtf("checking","${error}")

            if (error != null) {
                // Show error message, e.g., using a Toast
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }

        // Trigger login when ready
        authViewModel.login(location, username, password)

    }
}