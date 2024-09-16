package com.app.nit3213.repository

import com.app.nit3213.data.DashboardResponse
import com.app.nit3213.data.LoginRequest
import com.app.nit3213.data.LoginResponse
import com.app.nit3213.network.ApiService
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun authenticateUser(location: String, username: String, password: String): Call<LoginResponse> {
        val url = when (location.lowercase()) {
            "footscray" -> "/footscray/auth"
            "sydney" -> "/sydney/auth"
            "ort" -> "/ort/auth"
            else -> throw IllegalArgumentException("Invalid location")
        }

        val authRequest = LoginRequest(username = username, password = password)
        return apiService.authenticate(url, authRequest)
    }

    suspend fun getDashboard(keypass:String):Response<DashboardResponse> {
        return apiService.getDashboard(keypass)
    }
}
