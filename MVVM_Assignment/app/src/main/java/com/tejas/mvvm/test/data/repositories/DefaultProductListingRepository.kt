package com.tejas.mvvm.test.data.repositories

import com.tejas.mvvm.test.data.base.ResultWrapper
import com.tejas.mvvm.test.data.base.SafeApiCaller
import com.tejas.mvvm.test.data.network.NetworkService
import com.tejas.mvvm.test.data.network.response.productlist.ProductListResponse
import com.tejas.mvvm.test.domain.model.network.request.SearchItemsModel
import com.tejas.mvvm.test.domain.repositories.ProductListingRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultProductListingRepository @Inject constructor(private val service: NetworkService,
                                                          private val apiCaller: SafeApiCaller
// , Here we can inject DBService
): ProductListingRepository {

    override suspend fun getProductList (searchItemsModel: SearchItemsModel): ResultWrapper<ProductListResponse> =
        apiCaller.safeApiCall(Dispatchers.IO) {
            service.getProductList(searchItemsModel.maker, searchItemsModel.model, searchItemsModel.year)
        }
}