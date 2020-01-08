package com.ahrybuk.swivltest.repositories

import com.ahrybuk.swivltest.api.entities.UserDetailEntity
import com.ahrybuk.swivltest.api.entities.UserListEntity
import com.ahrybuk.swivltest.api.services.UsersService
import io.reactivex.Observable
import retrofit2.Retrofit

class NetworkUsersRepository(retrofit: Retrofit) : UsersRepository {

    private val usersService = retrofit.create(UsersService::class.java)

    override fun getUsers(): Observable<List<UserListEntity>> {
        return usersService.getUsers()
    }

    override fun getUsers(from: Long): Observable<List<UserListEntity>> {
        return usersService.getUsers(from)
    }

    override fun getUserDetail(username: String): Observable<UserDetailEntity> {
        return usersService.getUserDetail(username)
    }
}