package com.example.medimateadmin.API

object Routes {
    val Dashboard = "dashboard"
    val UserDetails = "user-details/{userId}"
    val AddProduct = "addProduct"

    val AllUser = "allUser"
    val AllProduct = "allProduct"
    val PendingOrder = "pendingOrder"
    val OrderDetails = "order-details/{orderId}"

    fun userDetails(userId: String) : String{
        return "user-details/$userId"
    }

    fun orderDetails(orderId: Int) : String{
        return "order-details/$orderId"
    }
}