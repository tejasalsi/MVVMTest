package com.tejas.mvvm.test.data.network

import com.tejas.mvvm.test.data.network.response.productlist.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    companion object {
        const val ENDPOINT = "https://carsearch.wiremockapi.cloud/"
    }

    @GET("search")
    suspend fun getProductList(@Query("make") make: String,
                               @Query("model") model: String,
                               @Query("year") year: String): ProductListResponse

}