package com.example.medimateadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.medimateadmin.ui.theme.MediMateAdminTheme
import com.example.medimateadmin.viewmodel.AddProductViewModel
import com.example.medimateadmin.viewmodel.AddToAvailableProductsViewModel
import com.example.medimateadmin.viewmodel.GetProductViewModel
import com.example.medimateadmin.viewmodel.GetUserViewModel
import com.example.medimateadmin.viewmodel.PendingOrderViewModel
import com.example.medimateadmin.viewmodel.UpdateAllUsersDetailsViewModel
import com.example.medimateadmin.viewmodel.UpdateOrderViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: GetUserViewModel by viewModels()
            val addProductViewModel: AddProductViewModel by viewModels()
            val getProductViewModel: GetProductViewModel by viewModels()
            val pendingOrderViewModel : PendingOrderViewModel by viewModels()
            val updateOrderViewModel : UpdateOrderViewModel by viewModels()
            val updateAllUserViewModel: UpdateAllUsersDetailsViewModel by viewModels()
            val addToAvailableProductsViewModel : AddToAvailableProductsViewModel by viewModels()

            val navController = rememberNavController()
            MediMateAdminTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()

                    ) {
                        NavGraph(
                            viewModel,
                            navController,
                            addProductViewModel,
                            getProductViewModel,
                            pendingOrderViewModel,
                            updateOrderViewModel,
                            updateAllUserViewModel,
                            addToAvailableProductsViewModel,
                            applicationContext
                        )
                    }
                }
            }
        }
    }
}


