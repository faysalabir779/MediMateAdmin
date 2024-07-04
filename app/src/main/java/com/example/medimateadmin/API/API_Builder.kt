package com.example.medimateadmin.API

import com.example.medimateadmin.Response.AddProductResponse
import com.example.medimateadmin.Response.GetAllOrderDetails
import com.example.medimateadmin.Response.ProductDetails
import com.example.medimateadmin.Response.UpdateAllUser
import com.example.medimateadmin.Response.UpdateOrderStatus
import com.example.medimateadmin.Response.UserDetails
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface API_Builder {

    @GET("/getAllUsers")
    suspend fun getAllUser() : Response<UserDetails>

    @GET("/getAllProduct")
    suspend fun getAllProduct() : Response<ProductDetails>

    @GET("/getAllOrdersDetail")
    suspend fun getAllOrderDetails():Response<GetAllOrderDetails>

    @FormUrlEncoded
    @POST("/addProduct")
    suspend fun addProduct(
        @Field("name") name: String,
        @Field("price") price: String,
        @Field("category") category: String,
        @Field("stack") stock: String,
        @Field("certified") certified: String,
    ):Response<AddProductResponse>

    @FormUrlEncoded
    @PATCH("/updateOrderDetails")
    suspend fun updateOrderStats(
        @Field("id") orderId: String,
        @Field("isApproved") isApproved: String
    ): Response<UpdateOrderStatus>

    @FormUrlEncoded
    @PATCH("/updateUserAllDetails")
    suspend fun updateAllUsersDetails(
        @Field("User_id") userId: String,
        @Field("isApproved") name: String
    ):Response<UpdateAllUser>

    companion object{
        const val BASE_URL = "https://faysalabir779.pythonanywhere.com"
    }
}