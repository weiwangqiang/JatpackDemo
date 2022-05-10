package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.dao.IUserDao

@Database(entities = [User::class], version = 1)
open abstract class AppDatabase : RoomDatabase() {
    companion object {

    }

    abstract fun userDao(): IUserDao
}