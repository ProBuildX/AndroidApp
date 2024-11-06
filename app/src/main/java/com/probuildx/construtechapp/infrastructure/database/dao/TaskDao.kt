package com.probuildx.construtechapp.infrastructure.database.dao

import androidx.room.*
import com.probuildx.construtechapp.domain.tasks.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM task_table WHERE id = :taskId")
    suspend fun getTaskById(taskId: String): Task?

    @Query("SELECT * FROM task_table")
    suspend fun getAllTasks(): List<Task>
}
