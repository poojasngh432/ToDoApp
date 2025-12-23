package com.poojasinghandroid.todoapp.domain.usecase

import com.poojasinghandroid.todoapp.domain.model.Todo
import com.poojasinghandroid.todoapp.domain.repository.TodoRepository

class DeleteTodoUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) {
        repository.delete(todo)
    }
}