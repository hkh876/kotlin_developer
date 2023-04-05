package com.example.todoapp.data

import android.content.Context

class AppDataContainer(private val context: Context) : AppContainer {
    override val toDoRepository: ToDoRepository by lazy {
        RoomToDoRepository(AppDatabase.getDatabase(context).toDoDao())
    }
}