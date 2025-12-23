package com.poojasinghandroid.todoapp.domain.model

data class Todo(
    val id: Long = 0,
    val title: String,
    val notes: String?,
    val dueDate: Long?,
    val priority: Priority,
    val isCompleted: Boolean,
    val createdAt: Long
)

enum class Priority {
    LOW, MEDIUM, HIGH
}