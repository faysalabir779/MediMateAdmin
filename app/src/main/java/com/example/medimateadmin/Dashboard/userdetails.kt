package com.example.medimateadmin.Dashboard

import android.content.Context
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
import com.example.medimateadmin.viewmodel.GetUserViewModel
import com.example.medimateadmin.viewmodel.UpdateAllUsersDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun userdetails(
    navController: NavHostController,
    viewModel: GetUserViewModel,
    userId: String,
    updateAllUserViewModel: UpdateAllUsersDetailsViewModel,
    applicationContext: Context
) {

    val data = viewModel.userData.value.find { it.User_id == userId }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("User Details") },
            navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        modifier = Modifier.clickable {
                            navController.navigate(Routes.AllUser)
                        },
                        contentDescription = "Localized description"
                    )
                }
            }
        )

    }) {
        Column(modifier = Modifier.padding(it)) {
            Text(
                text = "UserId: ${data?.User_id}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Name: ${data?.Name}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Address: ${data?.Address}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Email: ${data?.Email}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Phone: ${data?.Phone}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Date Of Account Creation: ${data?.Date_of_account_creation}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Approved: ${if (data?.isApproved == "1") "Yes" else "No"}",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Blocked: ${if (data?.isApproved == "2") "Yes" else "No"}",
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
                    onClick = { updateAllUserViewModel.updateUserAccess(userId, "1", applicationContext) },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF3061DA))
                ) {
                    Text(text = if (data?.isApproved == "1") "Approved" else "Approve")
                }
                Button(
                    onClick = { updateAllUserViewModel.updateUserAccess(userId, "2", applicationContext)},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF6767))
                ) {
                    Text(text = if (data?.isApproved == "2") "Blocked" else "Block")
                }
            }
        }
    }
}