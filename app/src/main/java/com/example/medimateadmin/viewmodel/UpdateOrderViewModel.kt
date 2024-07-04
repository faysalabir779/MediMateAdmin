package com.example.medimateadmin.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medimateadmin.API.RetrofitInstance
import kotlinx.coroutines.launch

class UpdateOrderViewModel: ViewModel() {

    fun updateOrderStatus(id: String, isApproved: String, applicationContext: Context) {
        viewModelScope.launch {
            val result = RetrofitInstance.api.updateOrderStats(id, isApproved)
            if (result.isSuccessful) {
                Toast.makeText(applicationContext, "Order Updated Successfully", Toast.LENGTH_SHORT)
                    .show()
            }else{
                Toast.makeText(applicationContext, "Order Update Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}