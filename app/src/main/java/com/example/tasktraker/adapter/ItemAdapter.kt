package com.example.tasktraker.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktraker.EditActivity

import com.example.tasktraker.R
import com.example.tasktraker.model.Task
import android.app.Activity



class ItemAdapter : ListAdapter<Task, ItemAdapter.ViewHolder>(ItemDiffCallback) {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        private val itemViewContext : Context = itemView.context
        private val itemTextView :TextView = itemView.findViewById(R.id.text_view)
        private val itemEditButton: Button = itemView.findViewById(R.id.edit_button)

        fun bind(data: Task){
            itemTextView.text = data.str
            itemEditButton.setOnClickListener{
                val intent = Intent(itemViewContext, EditActivity::class.java)
                intent.putExtra("Task", data.str)
                intent.putExtra("TaskId", data.id)
                (itemViewContext as Activity).startActivityForResult(intent, 200)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object ItemDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }
}