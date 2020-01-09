package com.ahrybuk.swivltest.presentation.userlist

import androidx.recyclerview.widget.DiffUtil
import com.ahrybuk.swivltest.api.entities.UserListEntity

class UserListDiffCallback(
    private val oldUsers: List<UserListEntity>,
    private val newUsers: List<UserListEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldUsers.size

    override fun getNewListSize(): Int = newUsers.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUsers[oldItemPosition].id == newUsers[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUsers[oldItemPosition] == newUsers[newItemPosition]
    }
}