package com.example.todoapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT * from todo")
    fun getAll(): Flow<List<ToDoEntity>>

    @Insert
    suspend fun insert(toDoEntity: ToDoEntity)

    @Update
    suspend fun update(toDoEntity: ToDoEntity)
}