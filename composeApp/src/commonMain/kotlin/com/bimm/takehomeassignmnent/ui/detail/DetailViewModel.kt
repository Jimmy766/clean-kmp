package com.bimm.takehomeassignmnent.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bimm.takehomeassignmnent.domain.models.ShopStore
import com.bimm.takehomeassignmnent.domain.usecase.ShopStoreUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
  private val shopStoreUseCase: ShopStoreUseCase
):ViewModel() {

  private val _observable = MutableStateFlow(UIState())
  val observable = _observable.asStateFlow()

  fun getShop(id: Int) {
    viewModelScope.launch {
      _observable.value = UIState(loading = true)
      shopStoreUseCase.findById(id).collect { shop ->
      _observable.value = UIState(store = shop)
      }
    }
  }

  data class UIState(
    val loading: Boolean = false,
    val store: ShopStore? = null
  )
}