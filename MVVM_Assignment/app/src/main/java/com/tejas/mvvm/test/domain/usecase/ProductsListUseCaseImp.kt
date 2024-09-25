package com.tejas.mvvm.test.domain.usecase

import com.tejas.mvvm.test.data.base.ResultWrapper
import com.tejas.mvvm.test.data.network.response.productlist.ProductListResponse
import com.tejas.mvvm.test.domain.model.network.request.SearchItemsModel
import com.tejas.mvvm.test.domain.repositories.ProductListingRepository
import javax.inject.Inject

class ProductsListUseCaseImp @Inject constructor(
    private val productListingRepository: ProductListingRepository
) : ProductsListUseCase {

    override suspend fun run(searchItemsModel: SearchItemsModel): ResultWrapper<ProductListResponse> {
        return productListingRepository.getProductList(searchItemsModel)
    }
}