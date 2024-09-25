package com.tejas.mvvm.test.presentation.productsList

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tejas.mvvm.test.R
import com.tejas.mvvm.test.data.base.ResultWrapper
import com.tejas.mvvm.test.data.network.response.productlist.SearchResults
import com.tejas.mvvm.test.domain.model.network.request.SearchItemsModel
import com.tejas.mvvm.test.domain.usecase.ProductsListUseCase
import com.tejas.mvvm.test.utils.hasInternetConnection
import com.tejas.mvvm.test.utils.validateSearchInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsFragmentViewModel @Inject constructor(
    application: Application,
    private val productsListUseCase: ProductsListUseCase
) : AndroidViewModel(application){

    private var _productListResponse = MutableLiveData<List<SearchResults>>()
    val productListResponse: LiveData<List<SearchResults>> = _productListResponse

    var errorFieldVisibility = ObservableField(View.GONE)
    var errorFieldString = ObservableField<String>()
    var progressVisibility = ObservableField(View.GONE)
    var searchItems: SearchItemsModel = SearchItemsModel("","","")

    fun getProductsList() {
        val application = getApplication<Application>()
        if(!hasInternetConnection(application)) {
            errorFieldVisibility.set(View.VISIBLE)
            errorFieldString.set(application.getString(R.string.error_no_internet))
            return
        }

        // Validate the inputs
        validateSearchInput(application, searchItems.maker, searchItems.model, searchItems.year)?.let {
            errorFieldVisibility.set(View.VISIBLE)
            errorFieldString.set(it)
            _productListResponse.value = emptyList()
            return
        }
        progressVisibility.set(View.VISIBLE)
        viewModelScope.launch {
            when (val productListResponse = productsListUseCase.run(searchItems)) {
                is ResultWrapper.NetworkError -> {
                    errorFieldVisibility.set(View.VISIBLE)
                    errorFieldString.set(application.getString(R.string.error_zero_record))
                }
                is ResultWrapper.GenericError -> {
                    errorFieldVisibility.set(View.VISIBLE)
                    errorFieldString.set(application.getString(R.string.error_generic))
                }
                is ResultWrapper.Success -> {
                    errorFieldVisibility.set(View.GONE)
                    _productListResponse.postValue(productListResponse.value.searchResults)
                }
            }
        progressVisibility.set(View.GONE)
        }
    }
}