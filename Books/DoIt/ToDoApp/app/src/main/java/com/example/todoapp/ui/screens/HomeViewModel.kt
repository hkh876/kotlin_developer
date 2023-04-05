package com.example.todoapp.ui.screens

import androidx.lifecycle.ViewModel
import com.example.todoapp.data.ToDoEntity
import com.example.todoapp.data.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val toDoRepository: ToDoRepository): ViewModel() {
    private val emptyToDo = ToDoEntity(0, "")
    private val _currentToDoStream = MutableStateFlow(emptyToDo)

    val currentToDoStream: StateFlow<ToDoEntity> = _currentToDoStream
    val todoListStream: Flow<List<ToDoEntity>> = toDoRepository.toDoStream
}