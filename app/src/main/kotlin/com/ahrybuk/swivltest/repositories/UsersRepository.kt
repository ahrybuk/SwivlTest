package com.ahrybuk.swivltest.repositories

import com.ahrybuk.swivltest.api.entities.UserDetailEntity
import com.ahrybuk.swivltest.api.entities.UserListEntity
import io.reactivex.Observable

interface UsersRepository {

    fun getUsers(): Observable<List<UserListEntity>>

    fun getUsers(from: Long): Observable<List<UserListEntity>>

    fun getUserDetail(username: String): Observable<UserDetailEntity>
}