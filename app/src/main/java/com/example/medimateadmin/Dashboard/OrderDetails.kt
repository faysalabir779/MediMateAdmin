package com.example.medimateadmin.Dashboard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medimateadmin.API.Routes
import com.example.medimateadmin.R
import com.example.medimateadmin.viewmodel.PendingOrderViewModel
import com.example.medimateadmin.viewmodel.UpdateOrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetails(
    navController: NavHostController,
    pendingOrderViewModel: PendingOrderViewModel,
    orderId: String,
    updateOrderViewModel: UpdateOrderViewModel,
    applicationContext: Context,
) {
    LaunchedEffect(key1 = true) {
        pendingOrderViewModel.getAllOrder()
    }

    val data = pendingOrderViewModel.orderList.value.find { it.id == orderId.toInt() }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Order Details", fontWeight = FontWeight.ExtraBold) },
            navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        painterResource(id = R.drawable.back),
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .clickable { navController.navigate(Routes.PendingOrder) }
                            .size(26.dp)
                    )
                }
            }
        )

    }) {
        Column(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Name: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(
                        text = data!!.user_name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }

                Spacer(modifier = Modifier.height(7.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Product Name: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(
                        text = data!!.product_name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }


                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Quantity: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(
                        text = data!!.quantity.toString(),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }

                Spacer(modifier = Modifier.height(7.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Date: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(
                        text = data!!.date_of_order_creation,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(7.dp))


            }

        }
    }
}