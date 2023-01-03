package com.raywenderlich.android.medusaandroidapplication.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raywenderlich.android.medusaandroidapplication.model.CartModel
import com.raywenderlich.android.medusaandroidapplication.model.CartResult
import com.raywenderlich.android.medusaandroidapplication.model.CartViewModel
import com.raywenderlich.android.medusaandroidapplication.model.ProductsRetriever
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun CartCompose(cartId: String,
                cartViewModel: CartViewModel = viewModel()
) {
  val cartStates by cartViewModel.cartState.collectAsState()

  val callback = object : Callback<CartResult> {
    override fun onFailure(call: Call<CartResult>, t: Throwable) {
      Log.e("MainActivity", t.message!!)
    }

    override fun onResponse(call: Call<CartResult>, response: Response<CartResult>) {
      response.isSuccessful.let {
        response.body()?.let { c ->
          val cartModels : MutableList<CartModel> = mutableListOf()
          c.cart.items.forEach { item ->
            val title = item.title!!
            val thumbnail = item.thumbnail!!
            val quantity = item.quantity
            val cartModel = CartModel(title, quantity, thumbnail)
            cartModels.add(cartModel)
          }
          cartViewModel.setCart(cartModels.toList())
        }
      }
    }
  }
  val productsRetriever = ProductsRetriever()
  productsRetriever.getCart(cartId, callback)
  Column(modifier = Modifier
    .verticalScroll(rememberScrollState())
    .fillMaxSize()
    .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("My Cart")
    cartStates.forEach { cartState ->
      Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
      horizontalArrangement = Arrangement.SpaceBetween) {
        GlideImage(
          imageModel = { cartState.thumbnail },
          imageOptions = ImageOptions(
            alignment = Alignment.Center,
            requestSize = IntSize(200,300)
          )
        )
        Text(cartState.title)
        Text("${cartState.quantity} pcs")
      }
    }
  }
}