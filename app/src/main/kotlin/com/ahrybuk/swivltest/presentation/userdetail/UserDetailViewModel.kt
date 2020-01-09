package com.ahrybuk.swivltest.presentation.userdetail

import androidx.lifecycle.MutableLiveData
import com.ahrybuk.swivltest.api.entities.UserDetailEntity
import com.ahrybuk.swivltest.presentation.base.BaseViewModel
import com.ahrybuk.swivltest.repositories.UsersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.inject

class UserDetailViewModel(private val username: String) : BaseViewModel() {

    val userLiveData = MutableLiveData<UserDetailEntity>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Throwable>()
    private val usersRepository: UsersRepository by inject()

    init {
        load()
    }

    private fun load() {
        loadingLiveData.postValue(true)
        subscriptions.add(
            usersRepository.getUserDetail(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { user ->
                        loadingLiveData.postValue(false)
                        userLiveData.postValue(user)
                    },
                    { error ->
                        loadingLiveData.postValue(false)
                        errorLiveData.postValue(error)
                    }
                )
        )
    }
}