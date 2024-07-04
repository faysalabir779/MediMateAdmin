package com.example.medimateadmin.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medimateadmin.API.RetrofitInstance
import kotlinx.coroutines.launch

class AddProductViewModel : ViewModel() {

    fun createUser(
        applicationContext: Context,
        productName: String,
        productPrice: String,
        productCategory: String,
        productStock: String,
        certification: String
    ) {
        viewModelScope.launch {
            var result = RetrofitInstance.api.addProduct(
                productName,
                productPrice,
                productCategory,
                productStock,
                certification
            )

            if (result.body()?.status == 200) {
                Toast.makeText(applicationContext, "Product Upload Successfully 😀", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(applicationContext, "Product Upload Failed 😑", Toast.LENGTH_SHORT).show()
            }
        }
    }


}