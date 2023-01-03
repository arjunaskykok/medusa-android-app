package com.raywenderlich.android.medusaandroidapplication.model

data class ProductsResult(val products: List<Product>)

data class ProductResult(val product: Product)

data class CartResult(val cart: Cart)

data class CartRequest(val items: List<Item>)

data class Product(
  val id: String?,
  val title: String?,
  val thumbnail: String?,
  val variants: List<Variant>
)

data class Variant(
  val id: String?,
  val prices: List<Price>
)

class Price {
  var currency_code: String = ""
  var amount: Int = 0

  fun getPrice() : String {
    return "$currency_code $amount"
  }
}

data class Item(
  val variant_id: String,
  val title: String?,
  val thumbnail: String?,
  val quantity: Int
)

data class Cart(
  val id: String?,
  val items: List<Item>
)

data class LineItem(
  val variant_id: String,
  val quantity: Int
)