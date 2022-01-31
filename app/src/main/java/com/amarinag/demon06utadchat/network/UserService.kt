package com.amarinag.demon06utadchat.network

import com.amarinag.demon06utadchat.network.request.UserRequest
import com.amarinag.demon06utadchat.network.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @GET("getUsers")
    fun getUsers(): Call<UserResponse>

    @GET("getUsersById/{id}")
    fun getUserById(@Path("id") id: Int): Call<UserResponse>

    @POST("addUser")
    fun postUser(@Body userRequest: UserRequest): Call<UserResponse>

    @DELETE("deleteUser/{id}")
    fun deleteUser(@Path("id") id: Int): Call<UserResponse>
}