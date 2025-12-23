package com.poojasinghandroid.todoapp.data.mapper

import com.poojasinghandroid.todoapp.data.local.entity.TodoEntity
import com.poojasinghandroid.todoapp.domain.model.Todo

fun TodoEntity.toDomain() = Todo(
    id, title, notes, dueDate, priority, isCompleted, createdAt
)

fun Todo.toEntity() = TodoEntity(
    id, title, notes, dueDate, priority, isCompleted, createdAt
)