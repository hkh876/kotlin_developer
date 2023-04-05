package com.example.todoapp.ui.screens

import com.example.todoapp.R
import com.example.todoapp.data.CommonRes.ADD_TODO_ROUTE
import com.example.todoapp.ui.navigation.NavigationDestination

object AddToDoDestination : NavigationDestination {
    override val route = ADD_TODO_ROUTE
    override val titleRes = R.string.add_todo_destination_text
}