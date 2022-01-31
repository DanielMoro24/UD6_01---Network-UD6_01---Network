package com.amarinag.demon06utadchat.network.response



import com.google.gson.annotations.Expose
data class UserResponse(
    @Expose
    val resp: String,
    @Expose
    val users: List<User>
)