package com.app.nit3213.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.nit3213.R
import com.app.nit3213.adapter.DashboardAdapter
import com.app.nit3213.databinding.ActivityDashboardBinding
import com.app.nit3213.viewmodel.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val keypass=intent.getStringExtra("keypass")
        mainViewModel.fetchDashboard(keypass?:"")
        setObserver()

    }

    private fun setObserver() {
        mainViewModel.dashboardResult.observe(this) { dashboard ->
            if (dashboard != null) {
                val adapter=DashboardAdapter(dashboard.entities,this) {

                }
                binding.tvEntities.text="Entities (${dashboard.entityTotal})"
                binding.rvEntites.adapter=adapter
            }
        }

        mainViewModel.isLoading.observe(this) { isLoading -> }

        mainViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Toast.makeText(this, "No Data Found!", Toast.LENGTH_LONG).show()
            }
        }
    }
}