package com.ahrybuk.swivltest.api.services

import com.ahrybuk.swivltest.api.entities.UserDetailEntity
import com.ahrybuk.swivltest.api.entities.UserListEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersService {

    @GET("/users")
    fun getUsers(): Observable<List<UserListEntity>>

    @GET("/users")
    fun getUsers(@Query("since") from: Long): Observable<List<UserListEntity>>

    @GET("/users/{username}")
    fun getUserDetail(@Path(value = "username", encoded = true) username: String): Observable<UserDetailEntity>
}