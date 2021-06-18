package com.alfilippov.junetechsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alfilippov.junetechsample.data.User

class ListCatAdapter(private val onClickUser: (User) -> Unit) :
    ListAdapter<User, ListCatAdapter.ListViewHolder>(CatDiffCallBack) {

    class ListViewHolder(itemView: View, private val onClickUser: (User) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private var clickUser: User? = null
        val tv: TextView = itemView.findViewById(R.id.tv_name)
        fun bind(user: User) {
            clickUser = user
            itemView.apply {
                tv.text = user.name
            }
        }

        init {
            tv.setOnClickListener {
                clickUser?.let {
                    onClickUser(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false),
            onClickUser
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object CatDiffCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
        }

    }

}