package com.ahrybuk.swivltest.presentation.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahrybuk.swivltest.R
import com.ahrybuk.swivltest.api.entities.UserListEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user_list.view.*


class UserListAdapter(val itemClickListener: (Long) -> Unit) : RecyclerView.Adapter<UserListAdapter.UserItemViewHolder>() {

    private var items = arrayListOf<UserListEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false)
        return UserItemViewHolder(rootView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(items: List<UserListEntity>) {
        val diffResult = DiffUtil.calculateDiff(UserListDiffCallback(this.items, items))
        this.items = ArrayList(items)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class UserItemViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

        fun bind(userListEntity: UserListEntity) {
            itemView.tvUsername.text = userListEntity.username

            Glide.with(itemView)
                .load(userListEntity.avatarUrl)
                .into(itemView.ivAvatar)

            itemView.setOnClickListener {
                itemClickListener(userListEntity.id ?: -1)
            }
        }
    }
}