package com.example.medimateadmin.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medimateadmin.API.RetrofitInstance
import kotlinx.coroutines.launch

class UpdateAllUsersDetailsViewModel : ViewModel() {

    fun updateUserAccess(id: String, isApproved: String, applicationContext: Context) {
        viewModelScope.launch {
            Log.d("checkde", "updateUserAccess: $isApproved")
            val result = RetrofitInstance.api.updateAllUsersDetails(id, isApproved)
            Log.d("kirebhai", "id is: $id  status: $isApproved")
            if (result.isSuccessful) {
                Toast.makeText(applicationContext, "Order Updated Successfully", Toast.LENGTH_SHORT)
                    .show()
            }else{
                Toast.makeText(applicationContext, "Order Update Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}