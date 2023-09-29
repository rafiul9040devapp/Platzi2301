package com.rafiul.platzi2301.services

import com.rafiul.platzi2301.models.profile.ResponseProfile
import com.rafiul.platzi2301.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface UserServices {

    @GET(Constants.PROFILE_END_POINT)
    suspend fun getProfileFromUser() : Response<ResponseProfile>
}