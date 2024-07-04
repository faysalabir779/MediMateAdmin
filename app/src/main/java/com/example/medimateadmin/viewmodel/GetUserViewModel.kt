package com.example.medimateadmin.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medimateadmin.API.RetrofitInstance
import com.example.medimateadmin.Response.UserDetailsItem
import kotlinx.coroutines.launch
import retrofit2.Response

class GetUserViewModel : ViewModel() {
    var state = mutableStateOf("")

    var userData = mutableStateOf<List<UserDetailsItem>>(emptyList())

    init {
        state.value = State.DEFAULT.name
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

}

sealed class State(var name: String) {
    object DEFAULT : State("DEFAULT")
    object SUCCESS : State("SUCCESS")
    object LOADING : State("LOADING")
    object FAILED : State("FAILED")
}

