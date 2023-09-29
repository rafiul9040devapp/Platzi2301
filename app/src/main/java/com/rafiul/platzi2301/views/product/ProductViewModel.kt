package com.rafiul.platzi2301.views.product

import android.icu.text.MessagePattern.Part
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rafiul.platzi2301.models.file.ResponseFile
import com.rafiul.platzi2301.models.product.ResponseProduct
import com.rafiul.platzi2301.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private var repository: ProductRepository) :
    ViewModel() {

    private val _responseProduct = MutableLiveData<Response<List<ResponseProduct>>>()

    val productResponse: LiveData<Response<List<ResponseProduct>>> = _responseProduct

    fun getAllProductsFromVM() {
        viewModelScope.launch {
            _responseProduct.postValue(repository.getAllProductsFromRepository())
        }
    }

    private val _responseProductByID = MutableLiveData<Response<ResponseProduct>>()
    val productByIdResponse: LiveData<Response<ResponseProduct>> = _responseProductByID

    fun getProductByIdFromVM(id: Int) {
        viewModelScope.launch {
            _responseProductByID.postValue(repository.getProductByIdFromRepository(id))
        }
    }


    val data = repository.getDataOfPaginationForProductFromRepository().cachedIn(viewModelScope)

    private val _productID = MutableLiveData<Int>()
    val productID: LiveData<Int> = _productID
    //after setting the value we are going to observe the value from LIVE DATA

    fun setClickedProductID(id: Int) {
        _productID.postValue(id)
    }


    private val _responseUploadFile = MutableLiveData<Response<ResponseFile>>()
    val uploadFileResponse: LiveData<Response<ResponseFile>> = _responseUploadFile

    fun uploadFileFromVM(part: MultipartBody.Part) {
        viewModelScope.launch {
            _responseUploadFile.postValue(repository.uploadFilesFromRepository(part))
        }
    }

}