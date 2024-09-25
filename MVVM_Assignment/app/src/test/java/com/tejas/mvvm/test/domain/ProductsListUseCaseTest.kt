package com.tejas.mvvm.test.domain

import com.tejas.mvvm.test.data.base.ResultWrapper
import com.tejas.mvvm.test.data.network.response.productlist.ProductListResponse
import com.tejas.mvvm.test.data.repositories.FakeProductListingRepository
import com.tejas.mvvm.test.domain.model.network.request.SearchItemsModel
import com.tejas.mvvm.test.domain.repositories.ProductListingRepository
import com.tejas.mvvm.test.domain.usecase.ProductsListUseCase
import com.tejas.mvvm.test.domain.usecase.ProductsListUseCaseImp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductsListUseCaseTest{

    private lateinit var productsListUseCase: ProductsListUseCase
    private lateinit var fakeProductListingRepository: ProductListingRepository

    @Before
    fun setup(){
        fakeProductListingRepository = FakeProductListingRepository()
        productsListUseCase = ProductsListUseCaseImp(fakeProductListingRepository)
    }

    @Test
    fun `no internet return generic error`(){
        (fakeProductListingRepository as FakeProductListingRepository).setShouldReturnNetworkError(true)
        runBlockingTest {
            val productListResponse = productsListUseCase.run(SearchItemsModel("","",""))
            assertEquals(ResultWrapper.GenericError(503, null), productListResponse)
        }
    }

    @Test
    fun `when lambda returns successfully then it should emit the empty list as success`(){
        runBlockingTest {
            val productListResponse = productsListUseCase.run(SearchItemsModel("","",""))
            assertEquals(ResultWrapper.Success(ProductListResponse(emptyList())), productListResponse)
        }
    }

}