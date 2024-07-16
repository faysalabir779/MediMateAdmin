package com.example.medimateadmin.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medimateadmin.API.RetrofitInstance
import com.example.medimateadmin.Response.ProductDetailsItem
import kotlinx.coroutines.launch

class GetProductViewModel : ViewModel() {
    val data = mutableStateOf<List<ProductDetailsItem>>(emptyList())

    init {
        viewModelScope.launch {
            getProduct()
        }
    }

    suspend fun getProduct() {
        viewModelScope.launch {
            val result = RetrofitInstance.api.getAllProduct()
            if (result.isSuccessful){
                val dataBody = result.body()
                data.value = dataBody ?: emptyList()
            }
        }
    }
}