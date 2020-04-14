package com.intive.patronage.smarthome.feature.splashcreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.intive.patronage.smarthome.R
import com.intive.patronage.smarthome.navigator.SplashScreenCoordinator
import com.intive.patronage.smarthome.feature.splashcreen.viewmodel.SplashScreenViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SplashScreenActivity : AppCompatActivity() {

    private val splashScreenViewModel: SplashScreenViewModel by viewModel()
    private val splashScreenCoordinator: SplashScreenCoordinator by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        observeViewModel()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) fullScreen()
    }

    private fun observeViewModel() {
        splashScreenViewModel.complete.observe(this, Observer { complete ->
            if (complete) splashScreenCoordinator.goToMainScreen()
        })
    }

    // Setting fullscreen
    private fun fullScreen() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN)

    }

}
