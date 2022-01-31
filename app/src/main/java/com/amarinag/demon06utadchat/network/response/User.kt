package com.amarinag.demon06utadchat.network.response


import com.amarinag.demon06utadchat.models.UserObject
import com.google.gson.annotations.Expose

data class User(
    @Expose
    val Surname: String,
    @Expose
    val Username: String,
    @Expose
    val age: Int,
    @Expose
    val email: String,
    @Expose
    val id: Int,
    @Expose
    val level: String,
    @Expose
    val name: String
)

fun User.toMsgObjectExt(): UserObject {
    return UserObject(id, Username, name, Surname, email, age, level)
}

fun List<User>?.toMap(): List<UserObject> {
    return this?.map { it.toMsgObjectExt() } ?: emptyList()
}

