package com.example.todoapp.data

import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    val toDoStream: Flow<List<ToDoEntity>>
    suspend fun addToDo(toDoEntity: ToDoEntity)
    suspend fun updateToDo(toDoEntity: ToDoEntity)
}