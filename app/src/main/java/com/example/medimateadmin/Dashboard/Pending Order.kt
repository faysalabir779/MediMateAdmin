package com.example.medimateadmin.Dashboard

import android.content.Context
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medimateadmin.API.Routes
import com.example.medimateadmin.R
import com.example.medimateadmin.Response.GetAllOrderDetailsItem
import com.example.medimateadmin.viewmodel.AddToAvailableProductsViewModel
import com.example.medimateadmin.viewmodel.GetProductViewModel
import com.example.medimateadmin.viewmodel.PendingOrderViewModel
import com.example.medimateadmin.viewmodel.UpdateOrderViewModel
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PendingOrder(
    pendingOrderViewModel: PendingOrderViewModel,
    applicationContext: Context,
    navController: NavHostController,
    updateOrderViewModel: UpdateOrderViewModel,
    getProductViewModel: GetProductViewModel,
    addToAvailableProductsViewModel: AddToAvailableProductsViewModel,
    getProductViewModel1: GetProductViewModel
) {

    var orderList = pendingOrderViewModel.orderList.value

    LaunchedEffect(key1 = true) {
        pendingOrderViewModel.getAllOrder()
        getProductViewModel.getProduct()
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Pending Order", fontWeight = FontWeight.ExtraBold) },
            navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        painterResource(id = R.drawable.back),
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .clickable { navController.navigate(Routes.Dashboard) }
                            .size(26.dp)
                    )
                }
            }
        )

    }) {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn {
                itemsIndexed(orderList.reversed()) { index, item ->
                    if (item.isApproved == 2) {
                        OrderCard(
                            item,
                            navController,
                            pendingOrderViewModel,
                            getProductViewModel1,
                            updateOrderViewModel,
                            addToAvailableProductsViewModel,
                            applicationContext
                        )
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
    getProductViewModel1: GetProductViewModel,
    updateOrderViewModel: UpdateOrderViewModel,
    addToAvailableProductsViewModel: AddToAvailableProductsViewModel,
    applicationContext: Context,

    ) {
    val allProduct = getProductViewModel1.data.value.filter { it.products_id == item.product_id}
    Log.d("size", "OrderCard: ${allProduct.size}")


    var availableStock by remember {
        mutableStateOf(0)
    }
    var productId by remember {
        mutableStateOf("")
    }
    allProduct.forEach {
        availableStock = it.stock
        Log.d("availableStock", "OrderCard: ${it.stock}")
        productId = it.products_id
    }


    Column(modifier = Modifier) {
        ElevatedCard(
            onClick = { /* Handle item click */ },
            modifier = Modifier
                .height(110.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(
                    width = 2.dp, color = Color(0xFF111111), shape = RoundedCornerShape(15.dp)
                ),
            colors = CardDefaults.cardColors(
                Color.White
            )

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
                                var remainingStock = availableStock - item.quantity
                                updateOrderViewModel.updateOrderStatus(
                                    item.order_id,
                                    "1",
                                    applicationContext
                                )

                                pendingOrderViewModel.updateProductStock(
                                    productId,
                                    remainingStock
                                )

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
                            colors = ButtonDefaults.buttonColors(Color(0xFF111111))
                        ) {

                            Text(text = "Approve")
                        }

                        Button(
                            onClick = {
                                updateOrderViewModel.updateOrderStatus(
                                    item.order_id,
                                    "0",
                                    applicationContext
                                )
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFF80808))
                        ) {
                            Text(text = "Block")
                        }
                    }
                }
            }
        }

    }
}