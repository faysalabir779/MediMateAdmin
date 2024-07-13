package com.example.medimateadmin.Dashboard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medimateadmin.API.Routes
import com.example.medimateadmin.R
import com.example.medimateadmin.viewmodel.AddProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    navController: NavHostController = rememberNavController(),
    addProductViewModel: AddProductViewModel,
    applicationContext: Context
) {

    var productName by rememberSaveable { mutableStateOf("") }
    var productPrice by rememberSaveable { mutableStateOf("") }
    var productCategory by rememberSaveable { mutableStateOf("") }
    var productStock by rememberSaveable { mutableStateOf("") }
    var certification by rememberSaveable { mutableStateOf(false) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Add Product", fontWeight = FontWeight.ExtraBold) },
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
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = productName, onValueChange = { productName = it },
                placeholder = {
                    Text(text = "Product Name", fontSize = 15.sp, color = Color(0xFF111111))
                },
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.name),
                        contentDescription = null,
                        tint = Color(0xFF111111),
                        modifier = Modifier.size(25.dp)
                    )
                },
                maxLines = 1,
                textStyle = TextStyle(fontSize = 15.sp, color = Color(0xFF111111)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),

                shape = RoundedCornerShape(15.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF111111)
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = productPrice, onValueChange = { productPrice = it },
                placeholder = {
                    Text(text = "Product Price", fontSize = 15.sp, color = Color(0xFF111111))
                },
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.price),
                        contentDescription = null,
                        tint = Color(0xFF111111),
                        modifier = Modifier.size(24.dp)
                    )
                },
                textStyle = TextStyle(fontSize = 15.sp, color = Color(0xFF111111)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),

                shape = RoundedCornerShape(15.dp),
                maxLines = 1,

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF111111)
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            val listOfCategory = listOf(
                "Antivirals",
                "Anxiolytics",
                "Bipolar agents",
                "Antibacterials",
                "Anticonvulsants"
            )
            var isExpend by remember { mutableStateOf(false) }

            Column {
                OutlinedTextField(
                    value = productCategory, onValueChange = { productCategory = it },
                    placeholder = {
                        Text(text = "Select Category", fontSize = 15.sp, color = Color(0xFF111111))
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Drop Down Icon",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable {
                                    isExpend = !isExpend
                                }
                                .rotate(
                                    if (isExpend) 180f else 0f
                                )
                        )
                    },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),

                    shape = RoundedCornerShape(15.dp),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF111111)
                    ),
                )

                DropdownMenu(
                    expanded = isExpend,
                    onDismissRequest = { isExpend = false },
                    modifier = Modifier
                        .fillMaxWidth().padding(horizontal = 20.dp)
                        .background(color = Color.White)
                ) {
                    listOfCategory.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(text = label) },
                            onClick = {
                                productCategory = label
                                isExpend = false

                            })
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = productStock, onValueChange = { productStock = it },
                placeholder = {
                    Text(
                        text = "Product Stock",
                        fontSize = 15.sp,
                        color = Color(0xFF111111)
                    )
                },
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.stock),
                        contentDescription = null,
                        tint = Color(0xFF111111),
                        modifier = Modifier.size(21.dp)
                    )
                },
                textStyle = TextStyle(fontSize = 15.sp, color = Color(0xFF111111)),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),

                shape = RoundedCornerShape(15.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF111111)
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = certification,
                    onCheckedChange = { certification = it },
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = Color.Black,
                        checkedColor = Color.Black
                    )
                )
                Text(
                    text = "Certified Product", style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }


            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    if (productName.isBlank() || productPrice.isBlank() || productCategory.isBlank() || productStock.isBlank()) {
                        Toast.makeText(
                            applicationContext,
                            "Please Fill All Fields",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        addProductViewModel.createUser(
                            applicationContext,
                            productName,
                            productPrice,
                            productCategory,
                            productStock,
                            certification = if (certification) "1" else "0"
                        )
                        productName = ""
                        productPrice = ""
                        productCategory = ""
                        productStock = ""
                        certification = false
                    }
                }, modifier = Modifier
                    .width(200.dp)
                    .height(52.dp)
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(start = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF111111),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Add Product",
                    fontSize = 18.sp
                )
            }
        }
    }
}