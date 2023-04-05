package com.example.todoapp.ui.screens

import com.example.todoapp.data.ToDoEntity

data class AddToDoUiState(
    val id: Long = 0,
    val title: String = "",
)

fun AddToDoUiState.toItem(): ToDoEntity = ToDoEntity(
    id = id,
    title = title,
)

fun AddToDoUiState.isValid(): Boolean {
    return title.isNotBlank()
}