package com.bimm.takehomeassignmnent.ui.home

data class Shop(
  val id: Int,
  val name: String,
  val address: String,
  val stars: Int,
  val imageUrl: String,
  val description: String,
  val website: String,
)

val stores = (1..50).map { i ->
    Shop(
        id = i,
        name = "Store $i",
        address = "Address $i",
        stars = (1..5).random(),
        imageUrl = "https://picsum.photos/200/300?random=$i",
        description = "Description for Store $i. This is a sample description to fill the content.",
        website = "https://store$i.com"
    )
}
