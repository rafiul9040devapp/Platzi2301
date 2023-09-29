package com.rafiul.platzi2301.services

import com.rafiul.platzi2301.models.file.ResponseFile
import com.rafiul.platzi2301.models.product.ResponseProduct
import com.rafiul.platzi2301.utils.Constants
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query


interface ProductServices {

    @GET(Constants.PRODUCT_END_POINT)
    suspend fun getAllProductsFromServices(): Response<List<ResponseProduct>>

    @GET(Constants.PRODUCT_END_POINT + "/{id}")
    suspend fun getProductByIdFromServices(@Path("id") id: Int): Response<ResponseProduct>


    //paging end point
    @GET(Constants.PRODUCT_END_POINT + "/")
    suspend fun getAllProductsFromServicesForPagination(
        @Query("offset") offset: Int, @Query("limit") limit: Int
    ): List<ResponseProduct>

    @Multipart
    @POST(Constants.FILE_END_POINT)
    suspend fun uploadFileFromServices(@Part file: MultipartBody.Part): Response<ResponseFile>

}