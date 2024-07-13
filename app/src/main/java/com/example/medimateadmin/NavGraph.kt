package com.example.medimateadmin

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.medimateadmin.API.Routes
import com.example.medimateadmin.Dashboard.AddProductScreen
import com.example.medimateadmin.Dashboard.AllProduct
import com.example.medimateadmin.Dashboard.AllUser
import com.example.medimateadmin.Dashboard.DashboardScreen
import com.example.medimateadmin.Dashboard.OrderDetails
import com.example.medimateadmin.Dashboard.PendingOrder
import com.example.medimateadmin.Dashboard.userdetails
import com.example.medimateadmin.viewmodel.AddProductViewModel
import com.example.medimateadmin.viewmodel.AddToAvailableProductsViewModel
import com.example.medimateadmin.viewmodel.GetProductViewModel
import com.example.medimateadmin.viewmodel.GetUserViewModel
import com.example.medimateadmin.viewmodel.PendingOrderViewModel
import com.example.medimateadmin.viewmodel.UpdateAllUsersDetailsViewModel
import com.example.medimateadmin.viewmodel.UpdateOrderViewModel

@Composable
fun NavGraph(
    viewModel: GetUserViewModel,
    navController: NavHostController,
    addProductViewModel: AddProductViewModel,
    getProductViewModel: GetProductViewModel,
    pendingOrderViewModel: PendingOrderViewModel,
    updateOrderViewModel: UpdateOrderViewModel,
    updateAllUserViewModel: UpdateAllUsersDetailsViewModel,
    addToAvailableProductsViewModel: AddToAvailableProductsViewModel,
    applicationContext: Context
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Dashboard
    ) {
        composable(Routes.Dashboard) {
            DashboardScreen(navController, viewModel)
        }
        composable(
            Routes.UserDetails,
            arguments = listOf(navArgument("userId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            userdetails(navController, viewModel, userId, updateAllUserViewModel, applicationContext)
        }



        composable(
            Routes.OrderDetails,
            arguments = listOf(navArgument("orderId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
            OrderDetails(navController, pendingOrderViewModel, orderId)
        }



        composable(Routes.AddProduct) {
            AddProductScreen(navController, addProductViewModel, applicationContext)
        }
        composable(Routes.AllUser) {
            AllUser(navController, viewModel, updateAllUserViewModel, applicationContext)
        }
        composable(Routes.AllProduct) {
            AllProduct(navController, getProductViewModel)
        }
        composable(Routes.PendingOrder) {
            PendingOrder(
                pendingOrderViewModel,
                applicationContext,
                navController,
                updateOrderViewModel,
                getProductViewModel,
                addToAvailableProductsViewModel
            )
        }
    }
}