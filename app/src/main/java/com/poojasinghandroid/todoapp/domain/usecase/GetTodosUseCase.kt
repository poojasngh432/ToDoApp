package com.poojasinghandroid.todoapp.domain.usecase

import com.poojasinghandroid.todoapp.domain.model.Todo
import com.poojasinghandroid.todoapp.domain.repository.TodoRepository
import com.poojasinghandroid.todoapp.ui.main.FilterType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar

class GetTodosUseCase(
    private val repository: TodoRepository
) {

    operator fun invoke(
        query: String,
        filter: FilterType
    ): Flow<List<Todo>> {

        return repository.getTodos(query).map { todos ->
            when (filter) {
                FilterType.ALL -> todos
                FilterType.ACTIVE -> todos.filter { !it.isCompleted }
                FilterType.COMPLETED -> todos.filter { it.isCompleted }
                FilterType.DUE_TODAY -> {
                    val today = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, 0)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                    }.timeInMillis
                    todos.filter { it.dueDate == today }
                }
                FilterType.OVERDUE -> {
                    val now = System.currentTimeMillis()
                    todos.filter {
                        it.dueDate != null &&
                                it.dueDate < now &&
                                !it.isCompleted
                    }
                }
            }
        }
    }
}