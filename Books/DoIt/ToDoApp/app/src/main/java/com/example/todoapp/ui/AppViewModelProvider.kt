package com.example.todoapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todoapp.ToDoApplication
import com.example.todoapp.ui.screens.AddToDoViewModel
import com.example.todoapp.ui.screens.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AddToDoViewModel(
                toDoApplication().container.toDoRepository
            )
        }

        initializer {
            HomeViewModel(
                toDoApplication().container.toDoRepository
            )
        }
    }
}

fun CreationExtras.toDoApplication(): ToDoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ToDoApplication)
