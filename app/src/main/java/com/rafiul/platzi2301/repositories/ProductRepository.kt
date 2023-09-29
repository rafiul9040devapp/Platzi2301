package com.rafiul.platzi2301.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.liveData
import com.rafiul.platzi2301.models.file.ResponseFile
import com.rafiul.platzi2301.models.product.ResponseProduct
import com.rafiul.platzi2301.paging.PagingSourceOfProduct
import com.rafiul.platzi2301.services.ProductServices
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(private var api: ProductServices) {

    val data = Pager(
        PagingConfig(
            pageSize = 10,
            initialLoadSize = 10
        )
    ) {
        PagingSourceOfProduct(api)
    }

    fun getDataOfPaginationForProductFromRepository() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100, initialLoadSize = 10),
        pagingSourceFactory = {
            PagingSourceOfProduct(api)
        }
    ).liveData


    suspend fun getAllProductsFromRepository(): Response<List<ResponseProduct>> {
        return api.getAllProductsFromServices()
    }

    suspend fun getProductByIdFromRepository(id: Int): Response<ResponseProduct> {
        return api.getProductByIdFromServices(id)
    }

    suspend fun uploadFilesFromRepository(part: MultipartBody.Part): Response<ResponseFile> {
        return api.uploadFileFromServices(part)
    }

}