package com.example.medimateadmin.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medimateadmin.API.RetrofitInstance
import com.example.medimateadmin.Response.UserDetailsItem
import kotlinx.coroutines.launch

class GetUserViewModel : ViewModel() {
    var state = mutableStateOf("")

    var userData = mutableStateOf<List<UserDetailsItem>>(emptyList())

    init {
        state.value = State.DEFAULT.name
        viewModelScope.launch {
            getUser()
        }
    }

    suspend fun getUser() {
        state.value = State.LOADING.name
        viewModelScope.launch {
            val data = RetrofitInstance.api.getAllUser()
            if (data.isSuccessful) {
                val dataBody = data.body()
                userData.value = dataBody ?: emptyList()
                state.value = State.SUCCESS.name
            } else {
                state.value = State.FAILED.name
            }
        }
    }

    fun deleteSpecificUser(userId: String, applicationContext: Context){
        viewModelScope.launch {
            val result = RetrofitInstance.api.deleteSpecificUser(userId)
            if (result.isSuccessful) {
                Toast.makeText(applicationContext, "User Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

sealed class State(var name: String) {
    object DEFAULT : State("DEFAULT")
    object SUCCESS : State("SUCCESS")
    object LOADING : State("LOADING")
    object FAILED : State("FAILED")
}

