package com.amarinag.demon06utadchat.network.response


import com.amarinag.demon06utadchat.models.UserObject


data class User(

    val id: Int,

    val Username: String,

    val name: String,

    val Surname: String,

    val email: String,

    val age: Int,

    val level: String,

)

fun User.toMsgObjectExt(): UserObject {
    return UserObject(id, Username, name, Surname, email, age, level)
}

fun List<User>?.toMap(): List<UserObject> {
    return this?.map { it.toMsgObjectExt() } ?: emptyList()
}

