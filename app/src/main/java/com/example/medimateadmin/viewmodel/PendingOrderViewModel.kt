package com.example.medimateadmin.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medimateadmin.API.RetrofitInstance
import com.example.medimateadmin.Response.GetAllOrderDetailsItem
import kotlinx.coroutines.launch

class PendingOrderViewModel: ViewModel() {

    val orderList = mutableStateOf<List<GetAllOrderDetailsItem>>(emptyList())

    fun getAllOrder(){
        viewModelScope.launch {
            val result = RetrofitInstance.api.getAllOrderDetails()
            if (result.isSuccessful) {
                val dataBody = result.body()
                orderList.value = dataBody ?: emptyList()
            }
        }
    }
}