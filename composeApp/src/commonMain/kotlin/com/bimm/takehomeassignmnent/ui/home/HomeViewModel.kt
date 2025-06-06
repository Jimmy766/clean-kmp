package com.bimm.takehomeassignmnent.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bimm.takehomeassignmnent.domain.models.ShopStore
import com.bimm.takehomeassignmnent.domain.usecase.ShopStoreUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
  private val shopStoreUseCase:ShopStoreUseCase
): ViewModel() {

  private val _observable = MutableStateFlow(UIState())
  val observable = _observable.asStateFlow()

  data class UIState(
    val loading: Boolean = false,
    val shops: List<ShopStore> = emptyList()
  )

  fun fetchShops(){
    viewModelScope.launch {
      _observable.value = UIState(loading = true)
      shopStoreUseCase.getAll().collect { shops ->
        if(shops.isNotEmpty()) {
          _observable.value = UIState(loading = false, shops = shops)
        }
      }
    }
  }
}