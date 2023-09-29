package com.rafiul.platzi2301.views.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafiul.platzi2301.models.register.RequestRegister
import com.rafiul.platzi2301.models.register.ResponseRegister
import com.rafiul.platzi2301.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _responseRegister = MutableLiveData<Response<ResponseRegister>>()
    val registerResponse: LiveData<Response<ResponseRegister>> = _responseRegister

    fun getRegisterFromVM(request: RequestRegister) {
        viewModelScope.launch {
            val data = repository.getRegisterFromRepository(request)
            _responseRegister.postValue(data)
        }
    }
}