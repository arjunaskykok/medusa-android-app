package com.raywenderlich.android.medusaandroidapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import com.raywenderlich.android.medusaandroidapplication.model.Product
import com.raywenderlich.android.medusaandroidapplication.model.ProductsResult
import com.raywenderlich.android.medusaandroidapplication.model.ProductsRetriever
import com.raywenderlich.android.medusaandroidapplication.ui.MainApp
import com.raywenderlich.android.medusaandroidapplication.ui.theme.MedusaAndroidApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {

  private val productsRetriever = ProductsRetriever()

  private var products : List<Product> = emptyList()

  private val callback = object : Callback<ProductsResult> {
    override fun onFailure(call: Call<ProductsResult>, t: Throwable) {
      Log.e("MainActivity", t.message!!)
    }

    override fun onResponse(call: Call<ProductsResult>, response: Response<ProductsResult>) {
      response.isSuccessful.let {
        products = response.body()?.products ?: emptyList()
        setContentWithProducts()
      }
    }
  }

  fun setContentWithProducts() {
    setContent {
      MedusaAndroidApplicationTheme {
        Scaffold(
          topBar = { TopAppBar(title = { Text(text = "Medusa App") }) }
        ) {
          MainApp(products = products)
        }
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    productsRetriever.getProducts(callback)
  }
}
