package com.example.medimateadmin.Dashboard

import android.content.Context
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medimateadmin.API.Routes
import com.example.medimateadmin.R
import com.example.medimateadmin.Response.UserDetailsItem
import com.example.medimateadmin.viewmodel.GetUserViewModel
import com.example.medimateadmin.viewmodel.State
import com.example.medimateadmin.viewmodel.UpdateAllUsersDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllUser(
    navController: NavHostController = rememberNavController(),
    viewModel: GetUserViewModel = GetUserViewModel(),
    updateAllUserViewModel: UpdateAllUsersDetailsViewModel = UpdateAllUsersDetailsViewModel(),
    applicationContext: Context
) {
    var data = viewModel.userData.value

    LaunchedEffect(key1 = true) {
        viewModel.getUser()
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("All User", fontWeight = FontWeight.ExtraBold) },
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


            when (viewModel.state.value) {
                State.SUCCESS.name -> {
                    LazyColumn {
                        itemsIndexed(data.reversed()) { index, item ->
                            userDataItem(
                                item,
                                navController,
                                viewModel,
                                updateAllUserViewModel,
                                applicationContext
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }

                State.DEFAULT.name -> CircularProgressIndicator()
                State.FAILED.name -> Text(text = "Try Again")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun userDataItem(
    item: UserDetailsItem,
    navController: NavHostController,
    viewModel: GetUserViewModel,
    updateAllUserViewModel: UpdateAllUsersDetailsViewModel,
    applicationContext: Context
) {
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
                    Routes.userDetails(item.user_id)
                )
            }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, start = 15.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.name,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF221938),
                        modifier = Modifier.width(150.dp)
                    )
                    Text(text = item.date_of_account_creation)
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
                                updateAllUserViewModel.updateUserAccess(
                                    item.user_id,
                                    "1",
                                    applicationContext
                                )
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF111111))
                        ) {

                            Text(text = if (item.isApproved == 1) "Approved" else "Approve")
                        }

                        Button(
                            onClick = {
                                updateAllUserViewModel.updateUserAccess(
                                    item.user_id,
                                    "0",
                                    applicationContext
                                )
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFF80808))
                        ) {
                            Text(text = if (item.isApproved == 0) "Blocked" else "Block")
                        }
                    }
                }
            }
        }

    }

}
