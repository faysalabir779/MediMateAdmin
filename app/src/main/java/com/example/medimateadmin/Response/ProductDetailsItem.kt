package com.example.medimateadmin.Response

data class ProductDetailsItem(
    val category: String,
    val certified: Int,
    val id: Int,
    val name: String,
    val price: Double,
    val stock: Int
)