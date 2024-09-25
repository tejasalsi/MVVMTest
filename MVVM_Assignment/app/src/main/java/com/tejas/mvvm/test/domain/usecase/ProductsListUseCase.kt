package com.tejas.mvvm.test.domain.usecase

import com.tejas.mvvm.test.data.base.ResultWrapper
import com.tejas.mvvm.test.data.network.response.productlist.ProductListResponse
import com.tejas.mvvm.test.domain.base.BaseUseCaseWithParams
import com.tejas.mvvm.test.domain.model.network.request.SearchItemsModel

interface ProductsListUseCase :
    BaseUseCaseWithParams<SearchItemsModel, ResultWrapper<ProductListResponse?>> {
    override suspend fun run(searchItemsModel: SearchItemsModel): ResultWrapper<ProductListResponse>
}