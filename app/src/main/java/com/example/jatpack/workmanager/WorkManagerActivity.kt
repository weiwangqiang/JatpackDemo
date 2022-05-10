package com.example.jatpack.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.jatpack.databinding.ActivityWorkManagerBinding

private const val TAG = "workManagerActivity"
private const val KEY = "key"

class WorkManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkManagerBinding
    private val tag = "tag1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
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
                        if (it?.state == WorkInfo.State.RUNNING) {
                            val progress = it.progress
                            val value = progress.getInt("key", 0)
                            Log.d(TAG, "onData change: $value other ${progress.getString("other")}")
                        }
                        if (it?.state == WorkInfo.State.SUCCEEDED) {
                            Toast.makeText(this@WorkManagerActivity, "完成", Toast.LENGTH_SHORT).show()
                        }
                    }
        }
    }
}