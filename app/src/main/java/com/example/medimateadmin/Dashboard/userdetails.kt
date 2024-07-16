package com.example.medimateadmin.Dashboard

import android.content.Context
import androidx.compose.foundation.Image
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

    val data = viewModel.userData.value.find { it.user_id == userId }

    if (data == null){
        navController.navigate(Routes.AllUser){
            popUpTo(Routes.AllUser){
                inclusive = true
            }
        }
        return
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("User Details", fontWeight = FontWeight.ExtraBold) },
            navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        painterResource(id = R.drawable.back),
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .clickable { navController.navigate(Routes.AllUser) }
                            .size(26.dp)
                    )
                }
            }
        )

    }) {
        Column(modifier = Modifier.padding(it)) {

            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)) {
                Spacer(modifier = Modifier.height(7.dp))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        text = "Name: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(
                        text = data!!.name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(7.dp))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        text = "Address: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(
                        text = data!!.address,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(7.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Email: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(
                        text = data!!.email,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(7.dp))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        text = "Phone: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(
                        text = data!!.phone_info,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(7.dp))


                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        text = "Date: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(
                        text = data!!.date_of_account_creation,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }


                Spacer(modifier = Modifier.height(7.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Approved: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(
                        text = if (data?.isApproved == 1) "Yes" else "No",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(7.dp))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        text = "Blocked: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Text(
                        text = if (data?.isApproved == 0) "Yes" else "No",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            updateAllUserViewModel.updateUserAccess(
                                userId,
                                "1",
                                applicationContext
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF111111))
                    ) {
                        Text(text = if (data!!.isApproved == 1) "Approved" else "Approve")
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Button(
                        onClick = {
                            updateAllUserViewModel.updateUserAccess(
                                userId,
                                "0",
                                applicationContext
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFF80808))
                    ) {
                        Text(text = if (data!!.isApproved == 0) "Blocked" else "Block")
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Image(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = null,
                        modifier = Modifier
                            .size(34.dp)
                            .clickable {
                                if (data != null) {
                                    viewModel.deleteSpecificUser(data.user_id, applicationContext)
                                    navController.navigate(Routes.AllUser){
                                        popUpTo(Routes.AllUser){
                                            inclusive = true
                                        }
                                    }
                                }
                                else{

                                }
                            }
                    )
                }
            }
        }
    }
}