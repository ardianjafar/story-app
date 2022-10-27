package com.elapp.mystoryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elapp.mystoryapp.data.local.dao.StoryDao
import com.elapp.mystoryapp.data.local.entity.StoryEntity

@Database(
    entities =[StoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StoryAppDatabase: RoomDatabase() {
    abstract fun getStoryDao(): StoryDao
}