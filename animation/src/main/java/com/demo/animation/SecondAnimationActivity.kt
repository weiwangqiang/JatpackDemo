package com.demo.animation

import android.os.Bundle
import android.transition.Transition
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.demo.animation.databinding.ActivitySecondAnimationBinding


const val VIEW_NAME_HEADER_IMAGE = "detail:header:image"

class SecondAnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // BEGIN_INCLUDE(detail_set_view_name)
        /*
         * Set the name of the view's which will be transition to, using the static values above.
         * This could be done in the layout XML, but exposing it via static variables allows easy
         * querying from other Activities
         */
        ViewCompat.setTransitionName(binding.imageView,
            VIEW_NAME_HEADER_IMAGE)
        addTransitionListener()
        // 可以加载预览图
        binding.imageView.setImageResource(R.drawable.image)
    }

    private fun addTransitionListener(): Boolean {
        val transition = window.sharedElementEnterTransition
        if (transition != null) {
            // There is an entering shared element transition so add a listener to it
            transition.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition) {
                    // As the transition has ended, we can now load the full-size image
                    // 加载清晰的图片
                    binding.imageView.setImageResource(R.drawable.image)
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionStart(transition: Transition) {
                    // No-op
                }

                override fun onTransitionCancel(transition: Transition) {
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionPause(transition: Transition) {
                    // No-op
                }

                override fun onTransitionResume(transition: Transition) {
                    // No-op
                }
            })
            return true
        }

        // If we reach here then we have not added a listener
        return false
    }
}