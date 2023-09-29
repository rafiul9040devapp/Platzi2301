package com.rafiul.platzi2301.repositories

import com.rafiul.platzi2301.models.login.RequestLogin
import com.rafiul.platzi2301.models.login.ResponseLogin
import com.rafiul.platzi2301.models.register.RequestRegister
import com.rafiul.platzi2301.models.register.ResponseRegister
import com.rafiul.platzi2301.services.AuthServices
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private var api: AuthServices) {

    suspend fun getLoginFromRepository(request: RequestLogin): Response<ResponseLogin> {
        return api.getLoginFromAuth(request)
    }

    suspend fun getRegisterFromRepository(request: RequestRegister): Response<ResponseRegister> {
        return api.getRegisterFromAuth(request)
    }
}