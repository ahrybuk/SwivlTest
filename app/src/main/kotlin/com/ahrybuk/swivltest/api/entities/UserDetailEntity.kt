package com.ahrybuk.swivltest.api.entities

import com.google.gson.annotations.SerializedName

class UserDetailEntity(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("html_url")
    val url: String?,
    @SerializedName("name")
    val fullName: String?,
    @SerializedName("public_repos")
    val publicRepositories: Int?,
    @SerializedName("public_gists")
    val publicGists: Int?,
    val followers: Int?
)