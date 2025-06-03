package com.bimm.takehomeassignmnent.ui.home

data class Shop(
  val id: Int,
  val name: String,
  val address: String,
  val stars: Int,
)

val stores = (1..10).map { i ->
    Shop(
        id = i,
        name = "Store $i",
        address = "Address $i",
        stars = (1..5).random()
    )
}
