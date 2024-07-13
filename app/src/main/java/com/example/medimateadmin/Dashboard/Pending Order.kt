package com.example.medimateadmin.Dashboard

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medimateadmin.API.Routes
import com.example.medimateadmin.Response.GetAllOrderDetailsItem
import com.example.medimateadmin.viewmodel.AddToAvailableProductsViewModel
import com.example.medimateadmin.viewmodel.GetProductViewModel
import com.example.medimateadmin.viewmodel.PendingOrderViewModel
import com.example.medimateadmin.viewmodel.UpdateOrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PendingOrder(
    pendingOrderViewModel: PendingOrderViewModel,
    applicationContext: Context,
    navController: NavHostController,
    updateOrderViewModel: UpdateOrderViewModel,
    getProductViewModel: GetProductViewModel,
    addToAvailableProductsViewModel: AddToAvailableProductsViewModel
) {

    var orderList = pendingOrderViewModel.orderList.value

    LaunchedEffect(key1 = true) {
        pendingOrderViewModel.getAllOrder()
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Pending Orders") },
            navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Localized description",
                        modifier = Modifier.clickable {
                            navController.navigate(Routes.Dashboard)
                        }
                    )
                }
            }
        )

    }){
        Column (modifier = Modifier.padding(it)){
            LazyColumn {
                itemsIndexed(orderList.reversed()) { index, item ->
                    if (item.isApproved == 2){
                        OrderCard(item, navController, pendingOrderViewModel, updateOrderViewModel, addToAvailableProductsViewModel, applicationContext)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderCard(
    item: GetAllOrderDetailsItem,
    navController: NavHostController,
    pendingOrderViewModel: PendingOrderViewModel,
    updateOrderViewModel: UpdateOrderViewModel,
    addToAvailableProductsViewModel: AddToAvailableProductsViewModel,
    applicationContext: Context,

    ) {
    Column(modifier = Modifier) {
        ElevatedCard(
            onClick = { /* Handle item click */ },
            modifier = Modifier
                .height(110.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = CardDefaults.cardColors(Color(0xFFE4D9FF))
        ) {
            Column(modifier = Modifier.clickable {
                navController.navigate(
                    Routes.orderDetails(item.id)
                )
            }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, start = 15.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.product_name,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF221938),
                        modifier = Modifier.width(150.dp)
                    )
                    Text(text = item.date_of_order_creation)
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 10.dp, start = 15.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                updateOrderViewModel.updateOrderStatus(item.order_id, "1", applicationContext)
                                addToAvailableProductsViewModel.addToAvailableProducts(
                                    item.product_name,
                                    item.category,
                                    item.certified.toString(),
                                    item.price.toString(),
                                    item.quantity.toString(),
                                    item.user_name,
                                    item.user_id,
                                    applicationContext
                                )
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF3061DA))
                        ) {

                            Text(text = "Approve")
                        }

                        Button(
                            onClick = {
                                updateOrderViewModel.updateOrderStatus(item.order_id, "0", applicationContext)
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFFF6767))
                        ) {
                            Text(text = "Block")
                        }
                    }
                }
            }
        }

    }
}