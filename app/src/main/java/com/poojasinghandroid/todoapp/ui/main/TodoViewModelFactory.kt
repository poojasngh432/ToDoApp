package com.poojasinghandroid.todoapp.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.poojasinghandroid.todoapp.data.local.AppDatabase
import com.poojasinghandroid.todoapp.data.repository.TodoRepositoryImpl
import com.poojasinghandroid.todoapp.domain.usecase.AddTodoUseCase
import com.poojasinghandroid.todoapp.domain.usecase.DeleteTodoUseCase
import com.poojasinghandroid.todoapp.domain.usecase.GetTodosUseCase
import com.poojasinghandroid.todoapp.domain.usecase.UpdateTodoUseCase

class TodoViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val db = Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "todo_db"
        ).build()

        val repository = TodoRepositoryImpl(db.todoDao())

        return TodoViewModel(
            getTodos = GetTodosUseCase(repository),
            addTodoUseCase = AddTodoUseCase(repository),
            updateTodoUseCase = UpdateTodoUseCase(repository),
            deleteTodoUseCase = DeleteTodoUseCase(repository)
        ) as T
    }
}