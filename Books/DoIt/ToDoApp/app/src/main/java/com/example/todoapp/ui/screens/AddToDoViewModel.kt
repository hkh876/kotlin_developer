package com.example.todoapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.ToDoRepository

class AddToDoViewModel(private val toDoRepository: ToDoRepository) : ViewModel() {
    var addToDoUiState by mutableStateOf(AddToDoUiState())
        private set

    fun updateUiState(newAddToDoUiState: AddToDoUiState) {
        addToDoUiState = newAddToDoUiState
    }

    suspend fun saveToDo() {
        if (addToDoUiState.isValid()) {
            toDoRepository.addToDo(addToDoUiState.toItem())
        }
    }
}