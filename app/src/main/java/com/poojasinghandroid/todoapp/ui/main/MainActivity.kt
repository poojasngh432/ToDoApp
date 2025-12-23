package com.poojasinghandroid.todoapp.ui.main

import com.poojasinghandroid.todoapp.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.poojasinghandroid.todoapp.databinding.ActivityMainBinding
import com.poojasinghandroid.todoapp.ui.addedit.AddEditTodoActivity
import com.poojasinghandroid.todoapp.util.DateUtils
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(application)
    }

    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        setupObservers()
        setupClicks()
        setupHeader()
        setupFilters()
    }

    private fun setupHeader() {
        binding.dateText.text = DateUtils.todayDate()
    }

    private fun setupRecycler() {
        adapter = TodoAdapter(
            onCheckedChange = viewModel::toggleCompleted,
            onClick = {
                startActivity(
                    Intent(this, AddEditTodoActivity::class.java)
                        .putExtra("todo_id", it.id)
                )
            },
            onDelete = viewModel::deleteTodo
        )

        binding.todoRecycler.layoutManager = LinearLayoutManager(this)
        binding.todoRecycler.adapter = adapter
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.todos.collect { todos ->
                    adapter.submitList(todos)
                    binding.emptyState.visibility =
                        if (todos.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }
    }

    private fun setupFilters() {
        binding.filterGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            when (checkedIds.firstOrNull()) {
                R.id.filterAll -> viewModel.onFilterChanged(FilterType.ALL)
                R.id.filterOpen -> viewModel.onFilterChanged(FilterType.ACTIVE)
                R.id.filterClosed -> viewModel.onFilterChanged(FilterType.COMPLETED)
                R.id.filterArchived -> viewModel.onFilterChanged(FilterType.OVERDUE)
            }
        }
    }

    private fun setupClicks() {
        binding.addTaskBtn.setOnClickListener {
            startActivity(Intent(this, AddEditTodoActivity::class.java))
        }
    }
}