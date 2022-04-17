package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "NewsViewModel"
class NewsViewModel : ViewModel() {
    var newLiveData = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}