package com.rafiul.platzi2301.services

import com.rafiul.platzi2301.models.category.ResponseCategory
import com.rafiul.platzi2301.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface CategoryServices {

    @GET(Constants.CATEGORY_END_POINT)
    suspend fun getAllCategoriesFromServices(): retrofit2.Response<List<ResponseCategory>>

    @GET(Constants.CATEGORY_END_POINT + "/{id}")
    suspend fun getCategoryByIDFromServices(@Path("id") id: Int): Response<ResponseCategory>
}