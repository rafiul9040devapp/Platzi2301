package com.rafiul.platzi2301.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rafiul.platzi2301.models.product.ResponseProduct
import com.rafiul.platzi2301.services.ProductServices
import kotlinx.coroutines.delay
import javax.inject.Inject

class PagingSourceOfProduct @Inject constructor(private val services: ProductServices) :
    PagingSource<Int, ResponseProduct>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseProduct> {
        val page = params.key ?: 0
        Log.d("TAG", "page: $page")

        Log.d("TAG", "offset: ${page * params.loadSize}")

        val response = services.getAllProductsFromServicesForPagination(
            page * params.loadSize,
            params.loadSize
        )

        return try {

            if (page != 0) delay(1000)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            Log.d("TAG", "Exception: ${e.message}")

            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResponseProduct>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}