package com.demo.animation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.demo.animation.databinding.ActivityMainBinding

/**
 * 演示
 * 1、弹簧动画
 * 2、activity之间的元素共享动画
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val animation = SpringAnimation(binding.textview,
            FlingAnimation.TRANSLATION_Y, 0f)
        animation.spring.dampingRatio = SpringForce.STIFFNESS_VERY_LOW
        binding.textview.setOnClickListener {
            animation.setStartValue(1000f)
            animation.start()
        }
        binding.imageView.setOnClickListener {
            // BEGIN_INCLUDE(start_activity)
            /*
             * Now create an {@link android.app.ActivityOptions} instance using the
             * {@link ActivityOptionsCompat#makeSceneTransitionAnimation(Activity, Pair[])} factory
             * method.
             */
            val intent = Intent(this@MainActivity, SecondAnimationActivity::class.java)
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@MainActivity,
                // Now we provide a list of Pair items which contain the view we can transitioning
                // from, and the name of the view it is transitioning to, in the launched activity
                Pair<View, String>(binding.imageView, VIEW_NAME_HEADER_IMAGE))
            // Now we can start the Activity, providing the activity options as a bundle
            ActivityCompat.startActivity(this@MainActivity, intent, activityOptions.toBundle())
        }
    }
}