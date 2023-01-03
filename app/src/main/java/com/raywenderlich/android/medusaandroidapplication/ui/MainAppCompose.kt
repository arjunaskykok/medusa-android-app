package com.raywenderlich.android.medusaandroidapplication

import android.content.Context
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raywenderlich.android.medusaandroidapplication.model.*
import com.raywenderlich.android.medusaandroidapplication.ui.CartCompose
import com.raywenderlich.android.medusaandroidapplication.ui.ProductItem
import com.raywenderlich.android.medusaandroidapplication.ui.ProductsList
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.dataStore by preferencesDataStore(
  name = "settings"
)

@Composable
fun MainApp(products: List<Product>) {
  var cartId by rememberSaveable { mutableStateOf("") }

  val navController = rememberNavController()
  var product : Product? = null

  NavHost(navController = navController, startDestination = "list") {
    composable("list") {
      ProductsList(products,
        onItemSelected = { product_id ->
          navController.navigate("item/$product_id")
        },
        onCartButtonClick = {
          navController.navigate("cart")
        }
      )
    }
    composable(
      "item/{product_id}",
            arguments =  listOf(navArgument("product_id") { type = NavType.StringType})) { it ->
      val productId = it.arguments?.getString("product_id")!!
      val callback = object : Callback<ProductResult> {
        override fun onFailure(call: Call<ProductResult>, t: Throwable) {
          Log.e("MainActivity", t.message!!)
        }

        override fun onResponse(call: Call<ProductResult>, response: Response<ProductResult>) {
          response.isSuccessful.let {
            response.body()?.let { p ->
              product = p.product
            }
          }
        }
      }
      val productsRetriever = ProductsRetriever()
      productsRetriever.getProduct(productId, callback)
      product?.let { ProductItem(it, cartId, onCartChange = { cartId = it }) }
    }

    composable("cart") {
      CartCompose(cartId)
    }
  }
}