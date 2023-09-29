package com.rafiul.platzi2301.repositories

import com.rafiul.platzi2301.models.profile.ResponseProfile
import com.rafiul.platzi2301.services.UserServices
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private var api: UserServices) {

    suspend fun getProfileFomRepo() : Response<ResponseProfile>{
        return api.getProfileFromUser()
    }
}