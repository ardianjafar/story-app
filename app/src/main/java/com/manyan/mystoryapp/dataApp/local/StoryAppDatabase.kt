package com.manyan.mystoryapp.dataApp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manyan.mystoryapp.dataApp.local.dao.StoryDao
import com.manyan.mystoryapp.dataApp.local.entity.StoryEntity

@Database(
    entities =[StoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StoryAppDatabase: RoomDatabase() {
    abstract fun getStoryDao(): StoryDao
}