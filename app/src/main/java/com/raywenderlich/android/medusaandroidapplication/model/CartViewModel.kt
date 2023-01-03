package com.raywenderlich.android.medusaandroidapplication.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class CartModel (
  val title: String,
  val quantity: Int,
  val thumbnail: String,
)

class CartViewModel : ViewModel() {

  private val _cartState = MutableStateFlow(emptyList<CartModel>())
  val cartState: StateFlow<List<CartModel>> = _cartState.asStateFlow()

  fun setCart(cartModels: List<CartModel>) {
    _cartState.value = cartModels
  }
}