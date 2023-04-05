package com.example.todoapp.data

import kotlinx.coroutines.flow.Flow

class RoomToDoRepository(private val toDoDao: ToDoDao) : ToDoRepository {
    override val toDoStream: Flow<List<ToDoEntity>> = toDoDao.getAll()
    override suspend fun addToDo(toDoEntity: ToDoEntity) = toDoDao.insert(toDoEntity)
    override suspend fun updateToDo(toDoEntity: ToDoEntity) = toDoDao.update(toDoEntity)
}