package com.medusajs.android.medusaandroidapplication.model

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsRetriever {
  private val service: MedusaService

  companion object {
    const val BASE_URL = "http://10.0.2.2:9000"
  }

  init {
    val retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    service = retrofit.create(MedusaService::class.java)
  }

  fun getProducts(callback: Callback<ProductsResult>) {
    val call = service.retrieveProducts()
    call.enqueue(callback)
  }

  fun getProduct(productId: String, callback: Callback<ProductResult>) {
    val call = service.getProduct(productId)
    call.enqueue(callback)
  }

  fun createCart(cartId: String, variantId: String, callback: Callback<CartResult>) {
    if (cartId.isNotEmpty()) {
      val lineItemRequest = LineItem(variantId, 1)
      val call = service.addProductToCart(cartId, lineItemRequest)
      call.enqueue(callback)
    } else {
      val items = listOf(
        Item(variant_id=variantId, quantity=1, thumbnail=null, title=null)
      )
      val cartRequest = CartRequest(items)
      val call = service.createCart(cartRequest)
      call.enqueue(callback)
    }
  }

  fun getCart(cartId: String, callback: Callback<CartResult>) {
    if (cartId.isNotEmpty()) {
      val call = service.getCart(cartId)
      call.enqueue(callback)
    }
  }
}