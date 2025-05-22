package com.zybooks.todolistapp

import android.health.connect.datatypes.units.Length
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {

    var taskList = mutableStateListOf<Task>()

    private val archivedTasks = mutableListOf<Task>()

    fun addTask(body: String, length: String, rating: String) {
        taskList.add(Task(body = body, completed = length, rating = rating))
    }

    fun updateTask(taskID: String, newB: String, newL: String, newR: String) {
        val index = taskList.indexOfFirst { it.id.toString() == taskID } // Find index of the task
        if (index != -1) { // Ensure task exists
            taskList[index] = taskList[index].copy(body = newB, completed = newL, rating = newR) // Update task
        }
    }

    fun deleteTask(task : Task) {
        taskList.remove(task)
        archivedTasks.add(task)
    }

    fun archiveTask(task: Task) {
        taskList.remove(task)
        archivedTasks.add(task)
    }

    fun createTestTasks() {
        addTask("The Godfather", "175 min","(R)")
        addTask("World War Z", "125 min","(PG-13)")
        addTask("The Conjuring", "100 min","(PG-13)")
        addTask("The Gladiator", "130 min","(R)")
        addTask("Man of Steel", "135 min","(PG)")
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    fun restoreArchivedTasks() {
        taskList.add(archivedTasks.removeLast())
    }

    val archiveTasksExist: Boolean
        get() = archivedTasks.isNotEmpty()

    val completedTasksExist: Boolean
        get() = taskList.isNotEmpty()
}