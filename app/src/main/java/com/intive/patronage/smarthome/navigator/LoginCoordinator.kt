package com.intive.patronage.smarthome.navigator

import android.net.Uri
import android.os.Bundle
import com.intive.patronage.smarthome.feature.dashboard.view.SmartHomeActivity
import com.intive.patronage.smarthome.feature.login.LoginActivity
import com.intive.patronage.smarthome.feature.splashcreen.SplashScreenActivity

class LoginCoordinator(private val navigator: Navigator) {

    fun goToMainScreen(bundle: Bundle? = null) {
        navigator.goToScreen(ActivityEvent(SmartHomeActivity::class.java, bundle))
        navigator.close()
    }

    fun goToSplashScreen() {
        navigator.goToScreen(ActivityEvent(SplashScreenActivity::class.java))
        navigator.close()
    }

    fun goToScreen() {
        navigator.goToScreen(ActivityEvent(SmartHomeActivity::class.java))
        navigator.close()
    }

    fun goToLoginScreen() {
        navigator.goToScreen(ActivityEvent(LoginActivity::class.java))
        navigator.close()
    }

    fun goToBack() {
        navigator.goBack()
    }

    fun goToScreenBasedOnDeeplinkUri(data: Uri?) {
        val queryParameter = data?.getQueryParameter(DESTINATION_URL)

        queryParameter?.let {
            goToMainScreen(
                createBundleWithStringAndId(
                    DESTINATION_URL,
                    it,
                    data
                )
            )
        } ?: goToMainScreen()

    }

    private fun createBundleWithStringAndId(tag: String, value: String, data: Uri?): Bundle {
        val bundle = Bundle()
        val id = data?.getQueryParameter("id")
        id?.let { bundle.putInt("ID", Integer.parseInt(it)) }
        bundle.putString(tag, value)
        return bundle
    }
}