package com.ahrybuk.swivltest.api.entities

import com.google.gson.annotations.SerializedName

class UserListEntity(
    @SerializedName("login")
    val username: String?,
    val id: Long?,
    @SerializedName("avatar_url")
    val avatarUrl: String?
)