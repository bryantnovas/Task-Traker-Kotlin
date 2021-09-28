package com.example.tasktraker.data

import com.example.tasktraker.model.Task

class Datasource {
    companion object{
        private val taskList = mutableListOf<Task>()
        fun getAll(): MutableList<Task>{
            return taskList
        }

        fun add(task: Task){
            taskList.add(task)
        }

        fun remove(task: Task){
            taskList.remove(task)
        }
        fun set(id: Int, str: String){
            taskList.set(id, Task(str, id))
        }
        fun getSize(): Int = taskList.size
    }
}