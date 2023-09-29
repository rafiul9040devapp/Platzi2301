package com.rafiul.platzi2301.repositories

import com.rafiul.platzi2301.models.category.ResponseCategory
import com.rafiul.platzi2301.services.CategoryServices
import retrofit2.Response
import javax.inject.Inject


class CategoryRepository @Inject constructor(private val api: CategoryServices) {

    suspend fun getAllCategoriesFromRepository(): Response<List<ResponseCategory>> {
        return api.getAllCategoriesFromServices()
    }

    suspend fun getCategoryByIdFromRepository(id: Int): Response<ResponseCategory> {
        return api.getCategoryByIDFromServices(id)
    }
}