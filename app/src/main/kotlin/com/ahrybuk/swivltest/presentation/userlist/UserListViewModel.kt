package com.ahrybuk.swivltest.presentation.userlist

import androidx.lifecycle.MutableLiveData
import com.ahrybuk.swivltest.api.entities.UserListEntity
import com.ahrybuk.swivltest.presentation.base.BaseViewModel
import com.ahrybuk.swivltest.repositories.UsersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.inject

class UserListViewModel : BaseViewModel() {

    val usersLiveData = MutableLiveData<List<UserListEntity>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Throwable>()
    private val usersRepository: UsersRepository by inject()
    private val loadedUsers = arrayListOf<UserListEntity>()

    init {
        loadInitial()
    }

    fun loadInitial() {
        loadedUsers.clear()
        loadingLiveData.postValue(true)
        subscriptions.add(
            usersRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { users ->
                        loadedUsers.addAll(users)
                        loadingLiveData.postValue(false)
                        usersLiveData.postValue(loadedUsers)
                    }, { error ->
                        loadingLiveData.postValue(false)
                        errorLiveData.postValue(error)
                    }
                )
        )
    }

    fun loadNext() {
        if (loadedUsers.isEmpty()) {
//            TODO() handle
            return
        }

        val from = loadedUsers.last().id ?: return

        loadingLiveData.postValue(true)
        subscriptions.add(
            usersRepository.getUsers(from)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { users ->
                        loadedUsers.addAll(users)
                        loadingLiveData.postValue(false)
                        usersLiveData.postValue(loadedUsers)
                    }, { error ->
                        loadingLiveData.postValue(false)
                        errorLiveData.postValue(error)
                    }
                )
        )
    }
}