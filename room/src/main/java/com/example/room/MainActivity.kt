package com.example.room

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.room.dao.IUserDao

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var user = User("你好")
        Thread {
            getUserDao(this).insertAll(user)
        }.start()
        getUserDao(this).getAll().observe(this) {
            it?.forEach {
                Log.d(TAG, "onCreate: ${it.name}")
            }
        }
        Thread {
            getUserDao(this).insertAll(user)
        }.start()

    }

    fun getUserDao(context: Context): IUserDao {
        val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "userDao"
        ).build()
        return db.userDao()
    }
}