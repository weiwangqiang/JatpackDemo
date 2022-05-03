package com.example.jatpack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log
import androidx.work.*

import com.example.jatpack.databinding.ActivityMainBinding;
import com.example.jatpack.workmanager.UploadWorker;

private const val TAG = "MainActivity"
private const val KEY = "key"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tag = "tag1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.welcomeButton.setOnClickListener {
            val workRequest = OneTimeWorkRequestBuilder<UploadWorker>()
                .setInputData(workDataOf("title" to "hhaha"))
                .addTag(tag)
                .build()
            WorkManager.getInstance(this).enqueue(workRequest)
            // 监听进度
            WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(workRequest.id)
                .observe(this) {
                    if (it != null) {
                        val progress = it.progress
                        val value = progress.getInt("key", 0)
                        Log.d(TAG, "onData change: $value")
                    }
                }

            WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(workRequest.id)
                .observe(this) {
                    Log.d(TAG, "onCreate: onData change  ${it.outputData.getString(KEY)}")
                }
        }
    }
}