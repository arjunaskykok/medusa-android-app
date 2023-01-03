package com.raywenderlich.android.medusaandroidapplication.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raywenderlich.android.medusaandroidapplication.model.CartResult
import com.raywenderlich.android.medusaandroidapplication.model.Product
import com.raywenderlich.android.medusaandroidapplication.model.ProductsRetriever
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun ProductItem(product: Product,
                cartId: String,
                onCartChange: (String) -> Unit) {
  Column(
    modifier = Modifier
      .padding(8.dp)
      .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    GlideImage(
      imageModel = { product.thumbnail!! },
      imageOptions = ImageOptions(
        alignment = Alignment.Center,
        requestSize = IntSize(800,1200)
      )
    )
    Text(product.title!!, fontSize = 30.sp)
    Text(product.variants[0].prices[0].getPrice())
    Button(onClick = {
      val productsRetriever = ProductsRetriever()
      val callback = object : Callback<CartResult> {
        override fun onFailure(call: Call<CartResult>, t: Throwable) {
          Log.e("MainActivity", t.message!!)
        }

        override fun onResponse(call: Call<CartResult>, response: Response<CartResult>) {
          response.isSuccessful.let {
            response.body()?.let { c ->
              onCartChange(c.cart.id!!)
            }
          }
        }
      }
      productsRetriever.createCart(cartId, product.variants[0].id!!, callback)
    }) {
      Text("Add 1 Item to Cart")
    }
  }
}