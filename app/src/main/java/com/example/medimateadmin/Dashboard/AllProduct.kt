package com.example.medimateadmin.Dashboard

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medimateadmin.API.Routes
import com.example.medimateadmin.R
import com.example.medimateadmin.Response.ProductDetailsItem
import com.example.medimateadmin.viewmodel.GetProductViewModel

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllProduct(
    navController: NavHostController = rememberNavController(),
    getProductViewModel: GetProductViewModel = GetProductViewModel()
) {

    val data = getProductViewModel.data.value
    Log.d("size", "OrderCard: ${data.size}")

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("All Product", fontWeight = FontWeight.ExtraBold) },
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
                itemsIndexed(data.reversed()) { index, item ->
                    productCard(item)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun productCard(item: ProductDetailsItem) {
    Column {
        ElevatedCard(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(width = 2.dp, color = Color(0xFF111111), shape = RoundedCornerShape(15.dp)),
            colors = CardDefaults.cardColors(
                Color.White
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 17.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Name: ${item.name}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (item.certified == 0) {
                        Icon(
                            Icons.Filled.Close, contentDescription = null, tint = Color(
                                0xFFD82828
                            )
                        )
                    } else {
                        Icon(
                            Icons.Filled.CheckCircle, contentDescription = null, tint = Color(
                                0xFF258629
                            )
                        )
                    }
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 17.dp)) {
                    Text(text = "Price: ৳ ${item.price}/-", fontWeight = FontWeight.Bold)
                    Text(text = "Category: ${item.category}")
                    Text(text = "Stock: ${item.stock}")
                }
            }

        }
    }
}

