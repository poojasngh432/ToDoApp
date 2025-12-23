package com.poojasinghandroid.todoapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.poojasinghandroid.todoapp.databinding.ItemTodoBinding
import com.poojasinghandroid.todoapp.domain.model.Todo

class TodoAdapter(
    private val onCheckedChange: (Todo) -> Unit,
    private val onClick: (Todo) -> Unit,
    private val onDelete: (Todo) -> Unit
) : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TodoViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo) {
            binding.title.text = todo.title
            binding.checkbox.isChecked = todo.isCompleted
            binding.title.paint.isStrikeThruText = todo.isCompleted

            binding.root.alpha = if (todo.isCompleted) 0.5f else 1f

            binding.checkbox.setOnCheckedChangeListener { _, _ ->
                onCheckedChange(todo)
            }

            binding.root.setOnClickListener { onClick(todo) }

            binding.deleteBtn.setOnClickListener { onDelete(todo) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(old: Todo, new: Todo) = old.id == new.id
        override fun areContentsTheSame(old: Todo, new: Todo) = old == new
    }
}