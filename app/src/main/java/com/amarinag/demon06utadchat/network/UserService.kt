package com.amarinag.demon06utadchat.network

import com.amarinag.demon06utadchat.network.request.UserRequest
import com.amarinag.demon06utadchat.network.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET("getUsers")
    fun getUsers(): Call<UserResponse>

    @POST("addUser")
    fun postUser(@Body userRequest: UserRequest): Call<UserResponse>
}