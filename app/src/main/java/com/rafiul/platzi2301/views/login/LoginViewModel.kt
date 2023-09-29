package com.rafiul.platzi2301.views.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafiul.platzi2301.models.login.RequestLogin
import com.rafiul.platzi2301.models.login.ResponseLogin
import com.rafiul.platzi2301.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _response = MutableLiveData<Response<ResponseLogin>>() //read and write data
    val loginResponse: LiveData<Response<ResponseLogin>> = _response //read only data

    fun getLoginFromVM(request: RequestLogin) {
        viewModelScope.launch {
            val data = repository.getLoginFromRepository(request)
            _response.postValue(data)
        }
    }
}