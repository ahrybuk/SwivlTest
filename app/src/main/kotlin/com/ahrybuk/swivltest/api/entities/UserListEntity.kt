package com.ahrybuk.swivltest.api.entities

import com.google.gson.annotations.SerializedName

class UserListEntity(
    val login: String?,
    val id: Long?,
    @SerializedName("avatar_url")
    val avatarUrl: String?
)