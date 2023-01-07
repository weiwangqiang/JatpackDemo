package com.example.jatpack.workmanager

import android.content.Context
import android.util.Log
import androidx.work.*

private const val TAG = "UploadWorker"

class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
        CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork: ${inputData.getString("title")}")
        for (i in 1..10) {
            Log.d(TAG, "doWork: $i")
            val data = workDataOf("key" to i)
            setProgress(data)
            Thread.sleep(1000)
        }
        return Result.success()
    }
}