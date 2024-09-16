package com.app.nit3213.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.nit3213.data.DashboardResponse
import com.app.nit3213.data.LoginResponse
import com.app.nit3213.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: MainRepository
) : ViewModel() {

    // LiveData to observe authentication result
    private val _authResult = MutableLiveData<LoginResponse?>()
    val authResult: LiveData<LoginResponse?> get() = _authResult

    // LiveData to observe dashboard result
    private val _dashboardResult = MutableLiveData<DashboardResponse?>()
    val dashboardResult: LiveData<DashboardResponse?> get() = _dashboardResult

    // LiveData to observe loading status
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // LiveData to observe errors
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun login(location: String, username: String, password: String) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val call = authRepository.authenticateUser(location, username, password)
                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            _authResult.value = response.body()
                        } else {
                            _errorMessage.value = "Error: ${response.code()}"
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        _isLoading.value = false
                        _errorMessage.value = "Failure: ${t.message}"
                    }
                })
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = "Exception: ${e.message}"
            }
        }
    }

    fun fetchDashboard(keypass: String) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = authRepository.getDashboard(keypass)
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dashboardResult.value = response.body()
                } else {
                    _errorMessage.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = "Exception: ${e.message}"
            }
        }
    }
}

