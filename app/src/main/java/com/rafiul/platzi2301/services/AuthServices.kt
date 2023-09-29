package com.rafiul.platzi2301.services

import com.rafiul.platzi2301.models.login.RequestLogin
import com.rafiul.platzi2301.models.login.ResponseLogin
import com.rafiul.platzi2301.models.register.RequestRegister
import com.rafiul.platzi2301.models.register.ResponseRegister
import com.rafiul.platzi2301.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServices {
    @POST(Constants.LOGIN_END_POINT)
    suspend fun getLoginFromAuth(@Body request: RequestLogin): Response<ResponseLogin>

    @POST(Constants.REGISTER_END_POINT)
    suspend fun getRegisterFromAuth(@Body request: RequestRegister): Response<ResponseRegister>
}