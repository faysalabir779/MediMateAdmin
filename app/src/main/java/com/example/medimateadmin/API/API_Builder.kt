package com.example.medimateadmin.API

import com.example.medimateadmin.Response.AddProductResponse
import com.example.medimateadmin.Response.AddToAvailableProduct
import com.example.medimateadmin.Response.DeleteSpecificUser
import com.example.medimateadmin.Response.GetAllOrderDetails
import com.example.medimateadmin.Response.ProductDetails
import com.example.medimateadmin.Response.UpdateAllUser
import com.example.medimateadmin.Response.UpdateOrderStatus
import com.example.medimateadmin.Response.UpdateProductStock
import com.example.medimateadmin.Response.UserDetails
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
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
        @Field("stock") stock: String,
        @Field("certified") certified: String,
    ):Response<AddProductResponse>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "deleteSpacficUser", hasBody = true)
    suspend fun deleteSpecificUser(
        @Field("user_id") userId: String
    ):Response<DeleteSpecificUser>

    @FormUrlEncoded
    @PATCH("/updateOrderDetails")
    suspend fun updateOrderStats(
        @Field("order_id") orderId: String,
        @Field("isApproved") isApproved: String
    ): Response<UpdateOrderStatus>

    @FormUrlEncoded
    @PATCH("/updateUserAllDetails")
    suspend fun updateAllUsersDetails(
        @Field("user_id") userId: String,
        @Field("isApproved") name: String
    ):Response<UpdateAllUser>

    @FormUrlEncoded
    @POST("/addToAvailableProducts")
    suspend fun addToAvailableProducts(
        @Field("product_name") product_Name : String,
        @Field("category") category : String,
        @Field("certified") certified : String,
        @Field("price") price : String,
        @Field("stock") stock : String,
        @Field("user_name") user_name : String,
        @Field("user_id") user_id : String,
    ): Response<AddToAvailableProduct>

    @FormUrlEncoded
    @PATCH("/updateProduct")
    suspend fun updateProduct(
        @Field("products_id") product_id: String,
        @Field("stock") stock: Int,
    ): Response<UpdateProductStock>

    companion object{
        const val BASE_URL = "https://faysalabir779.pythonanywhere.com"
    }
}