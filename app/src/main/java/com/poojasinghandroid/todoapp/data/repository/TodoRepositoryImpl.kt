package com.poojasinghandroid.todoapp.data.repository

import com.poojasinghandroid.todoapp.data.local.dao.TodoDao
import com.poojasinghandroid.todoapp.data.mapper.toDomain
import com.poojasinghandroid.todoapp.data.mapper.toEntity
import com.poojasinghandroid.todoapp.domain.model.Todo
import com.poojasinghandroid.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {

    override fun getTodos(query: String): Flow<List<Todo>> =
        dao.searchTodos(query).map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun add(todo: Todo) =
        dao.insert(todo.toEntity())

    override suspend fun update(todo: Todo) =
        dao.update(todo.toEntity())

    override suspend fun delete(todo: Todo) =
        dao.delete(todo.toEntity())
}