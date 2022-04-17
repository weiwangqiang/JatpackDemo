package com.example.viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.VideoView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel

private const val TAG = "MainActivity"

/**
 * 实现fragment 共享 activity 的 liveData
 */
class MainActivity : AppCompatActivity() {

    private lateinit var model: NewsViewModel

    // 或者通过如下方式创建，需要依赖     implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
    // 该方式可以将viewModel与activity绑定
    // private  val model: NewsViewModel by viewModels()
    private val model2: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = obtainViewModel(NewsViewModel::class.java)
        model.newLiveData.value = "hello world"
        Log.d(TAG, "onCreate: ${model2.newLiveData.value} ")
        supportFragmentManager.beginTransaction().add(NewsFragment(), "newFragment").commit()
    }

    fun next(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    class NewsFragment : Fragment() {
        private var model: NewsViewModel? = null

        // 或者通过如下方式创建，需要依赖  implementation "androidx.fragment:fragment-ktx:1.4.1"
        // 可以将viewModel与fragment的周期绑定
        private val model2: NewsViewModel by activityViewModels()

        override fun onResume() {
            super.onResume()
//            model = obtainViewModel(NewsViewModel::class.java)
            Log.d(TAG, "onResume log: ${model2.newLiveData.value}")
        }
    }
}