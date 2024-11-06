package com.probuildx.construtechapp.infrastructure.database

import Project
import androidx.room.Database
import androidx.room.RoomDatabase
import com.probuildx.construtechapp.domain.documents.Document
import com.probuildx.construtechapp.domain.personnel.Worker
import com.probuildx.construtechapp.domain.personnel.Team
import com.probuildx.construtechapp.domain.resources.Resource
import com.probuildx.construtechapp.domain.tasks.Task
import com.probuildx.construtechapp.infrastructure.database.dao.DocumentDao
import com.probuildx.construtechapp.infrastructure.database.dao.PersonnelDao
import com.probuildx.construtechapp.infrastructure.database.dao.ProjectDao
import com.probuildx.construtechapp.infrastructure.database.dao.ResourceDao
import com.probuildx.construtechapp.infrastructure.database.dao.TaskDao

@Database(entities = [Project::class, Worker::class, Team::class, Resource::class, Task::class, Document::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun personnelDao(): PersonnelDao
    abstract fun resourceDao(): ResourceDao
    abstract fun taskDao(): TaskDao
    abstract fun documentDao(): DocumentDao
}
