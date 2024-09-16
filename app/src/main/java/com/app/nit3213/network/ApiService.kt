package com.app.nit3213.network

import com.app.nit3213.data.LoginRequest
import com.app.nit3213.data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {
    @POST
    fun authenticate(@Url url: String, @Body authRequest: LoginRequest): Call<LoginResponse>
}
