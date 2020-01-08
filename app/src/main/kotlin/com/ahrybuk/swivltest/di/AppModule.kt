package com.ahrybuk.swivltest.di

import com.ahrybuk.swivltest.api.configuration.RetrofitProvider
import org.koin.dsl.module

val appModule = module {

    single { RetrofitProvider().retrofit }
}