package com.example.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(val name: String, @PrimaryKey val id: Int = 0)
