package com.rafiul.platzi2301.views.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafiul.platzi2301.models.category.ResponseCategory
import com.rafiul.platzi2301.repositories.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) :
    ViewModel() {
    private val _responseCategory = MutableLiveData<Response<List<ResponseCategory>>>()
    val categoryResponse: LiveData<Response<List<ResponseCategory>>> = _responseCategory

    fun getAllCategoriesFromVM() {
        viewModelScope.launch {
            _responseCategory.postValue(repository.getAllCategoriesFromRepository())
        }
    }

    private val _responseCategoryByID = MutableLiveData<Response<ResponseCategory>>()
    val categoryByIdResponse: LiveData<Response<ResponseCategory>> = _responseCategoryByID

    fun getCategoryByIdFromVM(id: Int) {
        viewModelScope.launch {
            _responseCategoryByID.postValue(repository.getCategoryByIdFromRepository(id))
        }
    }
}