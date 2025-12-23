package com.poojasinghandroid.todoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poojasinghandroid.todoapp.domain.model.Priority

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val notes: String?,
    val dueDate: Long?,
    val priority: Priority,
    val isCompleted: Boolean,
    val createdAt: Long
)