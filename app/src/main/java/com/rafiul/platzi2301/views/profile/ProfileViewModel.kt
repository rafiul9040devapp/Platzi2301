package com.rafiul.platzi2301.views.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafiul.platzi2301.models.profile.ResponseProfile
import com.rafiul.platzi2301.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private var repository: UserRepository) : ViewModel() {

    private val _responseProfile = MutableLiveData<Response<ResponseProfile>>()
    var profileResponse: LiveData<Response<ResponseProfile>> = _responseProfile

    fun getProfileFromVM() {
        viewModelScope.launch {
            _responseProfile.postValue(repository.getProfileFomRepo())
        }
    }

}