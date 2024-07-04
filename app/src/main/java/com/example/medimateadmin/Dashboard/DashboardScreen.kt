package com.example.medimateadmin.Dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.medimateadmin.viewmodel.GetUserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DashboardScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: GetUserViewModel = GetUserViewModel()
) {


    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "MediMate",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 28.sp,
                )
            },
        )

    }) {
        Box(modifier = Modifier.padding(it)) {
            Column {
                ElevatedCard(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF171717))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { navController.navigate(Routes.AddProduct) },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Add Product", color = Color.White)
                        Spacer(modifier = Modifier.height(5.dp))
                        Icon(
                            Icons.Rounded.Add,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ElevatedCard(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(90.dp)
                            .weight(1f)
                            .padding(start = 20.dp, end = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF171717))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { navController.navigate(Routes.AllUser) },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "All User", color = Color.White)
                            Spacer(modifier = Modifier.height(5.dp))
                            Icon(
                                Icons.Rounded.Person,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = Color.White
                            )
                        }
                    }
                    ElevatedCard(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(90.dp)
                            .weight(1f)
                            .padding(end = 20.dp, start = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF171717))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { navController.navigate(Routes.AllProduct) },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "All Product", color = Color.White)
                            Spacer(modifier = Modifier.height(5.dp))
                            Icon(
                                painterResource(id = R.drawable.name),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = Color.White
                            )
                        }
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))
                ElevatedCard(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF171717))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize().clickable {
                                navController.navigate(Routes.PendingOrder)
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Pending Orders", color = Color.White)
                        Spacer(modifier = Modifier.height(5.dp))
                        Icon(
                            painterResource(id = R.drawable.pendingorder),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                            tint = Color.White
                        )
                    }

                }
            }
        }

    }
}


