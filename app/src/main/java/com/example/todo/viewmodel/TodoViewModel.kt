package com.example.todo.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.Todo
import com.example.todo.model.TodosApi
import kotlinx.coroutines.launch
import java.lang.Exception

class TodoViewModel: ViewModel() {
    // List of Todos stored as mutable state
    var todos = mutableStateListOf<Todo>()
        private set

    init {
        getTodosList()
    }

    // Function to fetch Todos List
    private fun getTodosList(){
        // Launching within the ViewModel's scope
        viewModelScope.launch {
            try {
                val todosApi = TodosApi.getInstance()
                todos.clear()
                todos.addAll(todosApi.getTodos())
            } catch (e: Exception){
                Log.d("TODOVIEWMODEL", e.message.toString())
            }
        }
    }
}
