package com.poojasinghandroid.todoapp.ui.addedit

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.poojasinghandroid.todoapp.databinding.ActivityAddEditTodoBinding
import com.poojasinghandroid.todoapp.domain.model.Priority
import com.poojasinghandroid.todoapp.domain.model.Todo
import com.poojasinghandroid.todoapp.ui.main.TodoViewModel
import com.poojasinghandroid.todoapp.ui.main.TodoViewModelFactory

class AddEditTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditTodoBinding
    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            saveTodo()
        }
    }

    private fun saveTodo() {
        val title = binding.titleInput.text.toString()
        val notes = binding.notesInput.text.toString()

        val todo = Todo(
            title = title,
            notes = notes,
            dueDate = null,
            priority = Priority.MEDIUM,
            isCompleted = false,
            createdAt = System.currentTimeMillis()
        )

        viewModel.addTodo(todo)
        finish()
    }
}