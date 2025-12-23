package com.poojasinghandroid.todoapp.domain.usecase

import com.poojasinghandroid.todoapp.domain.model.Todo
import com.poojasinghandroid.todoapp.domain.repository.TodoRepository

class AddTodoUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) {
        require(todo.title.length <= 80) { "Title too long" }
        require((todo.notes?.length ?: 0) <= 500) { "Notes too long" }
        repository.add(todo)
    }
}