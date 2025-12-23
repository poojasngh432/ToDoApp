package com.poojasinghandroid.todoapp.domain.usecase

import com.poojasinghandroid.todoapp.domain.model.Todo
import com.poojasinghandroid.todoapp.domain.repository.TodoRepository

class UpdateTodoUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) {
        repository.update(todo)
    }
}