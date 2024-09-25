package com.tejas.mvvm.test.data.repositories

import com.tejas.mvvm.test.data.base.ResultWrapper
import com.tejas.mvvm.test.data.network.response.productlist.ProductListResponse
import com.tejas.mvvm.test.domain.model.network.request.SearchItemsModel
import com.tejas.mvvm.test.domain.repositories.ProductListingRepository

class FakeProductListingRepository : ProductListingRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getProductList(
        searchItemsModel: SearchItemsModel
    ): ResultWrapper<ProductListResponse> {
        return if(shouldReturnNetworkError) {
            ResultWrapper.GenericError(503, null)
        } else {
            ResultWrapper.Success(ProductListResponse(emptyList()))
        }
    }

}