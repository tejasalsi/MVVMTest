package com.tejas.mvvm.test.domain.repositories

import com.tejas.mvvm.test.data.base.ResultWrapper
import com.tejas.mvvm.test.data.network.response.productlist.ProductListResponse
import com.tejas.mvvm.test.domain.model.network.request.SearchItemsModel

interface ProductListingRepository {
    suspend fun getProductList (searchItemsModel: SearchItemsModel): ResultWrapper<ProductListResponse>
}