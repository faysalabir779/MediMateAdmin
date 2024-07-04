package com.example.medimateadmin.Dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medimateadmin.API.Routes
import com.example.medimateadmin.viewmodel.PendingOrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetails(
    navController: NavHostController,
    pendingOrderViewModel: PendingOrderViewModel,
    orderId: String
) {

    val data = pendingOrderViewModel.orderList.value.find { it.id == orderId.toInt() }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Order Details") },
            navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        modifier = Modifier.clickable {
                            navController.navigate(Routes.PendingOrder)
                        },
                        contentDescription = "Localized description"
                    )
                }
            }
        )

    }) {
        Column(modifier = Modifier.padding(it)) {
            Text(
                text = "OrderId: ${data?.id}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Name: ${data?.user_name}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Address: ${data?.user_address}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Product Name: ${data?.product_name}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Quantity: ${data?.quantity}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Phone: ${data?.phone}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Date Of Account Creation: ${data?.date_of_craete_order}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Approved: ",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Blocked: ",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Button(
                    onClick = {  },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF3061DA))
                ) {
                    Text(text = "Approved")
                }
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF6767))
                ) {
                    Text(text = "Block")
                }
            }
        }
    }
}