package com.amarinag.demon06utadchat.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amarinag.demon06utadchat.databinding.ItemUserBinding
import com.amarinag.demon06utadchat.models.UserObject
import com.amarinag.demon06utadchat.network.response.User

class UserListAdapter(private val onUserClicked: (UserObject)-> Unit) :
    ListAdapter<UserObject, UserListAdapter.ViewHolder>(DiffUtilCallback) {

    inner class ViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemUserBinding = ItemUserBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.txtvUsername.text = user.Username
        holder.binding.txtvLevel.text = user.level
        holder.binding.txtvEmail.text = user.email
        holder.binding.root.setOnClickListener { onUserClicked(user)
        }
    }

}

private object DiffUtilCallback : DiffUtil.ItemCallback<UserObject>() {

    override fun areItemsTheSame(oldItem: UserObject, newItem: UserObject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserObject, newItem: UserObject): Boolean {
        return oldItem.id == newItem.id
    }

}
