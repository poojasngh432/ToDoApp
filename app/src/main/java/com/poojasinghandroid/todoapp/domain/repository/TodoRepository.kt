package com.poojasinghandroid.todoapp.domain.repository

import com.poojasinghandroid.todoapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(query: String): Flow<List<Todo>>
    suspend fun add(todo: Todo)
    suspend fun update(todo: Todo)
    suspend fun delete(todo: Todo)
}