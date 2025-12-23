package com.poojasinghandroid.todoapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poojasinghandroid.todoapp.domain.model.Todo
import com.poojasinghandroid.todoapp.domain.usecase.AddTodoUseCase
import com.poojasinghandroid.todoapp.domain.usecase.DeleteTodoUseCase
import com.poojasinghandroid.todoapp.domain.usecase.GetTodosUseCase
import com.poojasinghandroid.todoapp.domain.usecase.UpdateTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodos: GetTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    private val filterType = MutableStateFlow(FilterType.ALL)

    val todos = combine(searchQuery, filterType) { query, filter ->
        query to filter
    }.flatMapLatest { (query, filter) ->
        getTodos(query, filter)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    /* ---------------- Search & Filters ---------------- */

    fun onSearch(query: String) {
        searchQuery.value = query
    }

    fun onFilterChanged(filter: FilterType) {
        filterType.value = filter
    }

    /* ---------------- CRUD ---------------- */

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            addTodoUseCase(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            updateTodoUseCase(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
        }
    }

    fun toggleCompleted(todo: Todo) {
        updateTodo(todo.copy(isCompleted = !todo.isCompleted))
    }
}