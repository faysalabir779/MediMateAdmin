package com.example.medimateadmin.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medimateadmin.API.RetrofitInstance
import kotlinx.coroutines.launch

class AddToAvailableProductsViewModel : ViewModel() {

    fun addToAvailableProducts(
        productName: String,
        category: String,
        certified: String,
        price: String,
        quantity: String,
        userName: String,
        userId: String,
        applicationContext: Context
    ) {
        viewModelScope.launch {
            val result = RetrofitInstance.api.addToAvailableProducts(
                productName,
                category,
                certified,
                price,
                quantity,
                userName,
                userId
            )
            if (result.isSuccessful){
                Toast.makeText(applicationContext, "Added to available order", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}