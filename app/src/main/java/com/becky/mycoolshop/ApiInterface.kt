package com.becky.mycoolshop

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.sql.RowId

interface ApiInterface {
    @GET("/product")
    fun getProducts(): Call<ProductResponse>


//    @GET("/product/{id}")
//    fun  getProductById(@Path ("id") productId: RowId)
}