package com.medusajs.android.medusaandroidapplication.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.medusajs.android.medusaandroidapplication.model.Product
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductsList(products: List<Product>,
                 onItemSelected: (String) -> Unit,
                 onCartButtonClick: () -> Unit) {
  Column(modifier = Modifier
    .verticalScroll(rememberScrollState())
    .fillMaxSize()
    .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Button(onClick = {
      onCartButtonClick()
    }) {
      Text("My Cart")
    }
    products.forEach { product ->
      Column(
        modifier = Modifier
          .padding(8.dp)
          .border(BorderStroke(2.dp, Color.Gray))
          .clickable {
            onItemSelected(product.id!!)
          }
        ,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        GlideImage(
          imageModel = { product.thumbnail!! },
          imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            requestSize = IntSize(400,600),
            alignment = Alignment.Center
          )
        )
        Text(product.title!!, fontWeight = FontWeight.Bold)
        Text(product.variants[0].prices[0].getPrice())
      }
    }

  }
}