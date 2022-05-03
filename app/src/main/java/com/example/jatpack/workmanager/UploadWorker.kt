package com.example.jatpack.workmanager

import android.content.Context
import android.util.Log
import androidx.work.*

private const val TAG = "UploadWorker"

class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork: ${inputData.getString("title")}")
        val data = workDataOf("key" to 80)
        setProgress(data)
        Thread.sleep(1000)
        Log.d(TAG, "doWork: finish it !!")
        return Result.success()
    }
}