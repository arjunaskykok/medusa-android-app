package com.medusajs.android.medusaandroidapplication.model

import retrofit2.Call
import retrofit2.http.*

interface MedusaService {

  @GET("/store/products")
  fun retrieveProducts(): Call<ProductsResult>

  @GET("/store/products/{id}")
  fun getProduct(@Path("id") id: String) : Call<ProductResult>

  @Headers("Content-Type: application/json")
  @POST("/store/carts")
  fun createCart(@Body cart: CartRequest): Call<CartResult>

  @Headers("Content-Type: application/json")
  @POST("/store/carts/{id}/line-items")
  fun addProductToCart(@Path("id") id: String, @Body lineItem: LineItem): Call<CartResult>

  @GET("/store/carts/{id}")
  fun getCart(@Path("id") id: String) : Call<CartResult>
}