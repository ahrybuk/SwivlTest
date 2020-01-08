package com.ahrybuk.swivltest.di

import com.ahrybuk.swivltest.api.configuration.RetrofitProvider
import com.ahrybuk.swivltest.repositories.NetworkUsersRepository
import com.ahrybuk.swivltest.repositories.UsersRepository
import org.koin.dsl.module

val appModule = module {

    single { RetrofitProvider().retrofit }

    factory<UsersRepository> { NetworkUsersRepository(get()) }
}