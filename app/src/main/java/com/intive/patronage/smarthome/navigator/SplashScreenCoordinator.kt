package com.intive.patronage.smarthome.navigator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.intive.patronage.smarthome.common.DESTINATION_URL
import com.intive.patronage.smarthome.common.ID_PARAMETER_URL
import com.intive.patronage.smarthome.common.ID_QUERY_URL
import com.intive.patronage.smarthome.feature.dashboard.view.SmartHomeActivity
import com.intive.patronage.smarthome.feature.login.view.LoginActivity
import com.intive.patronage.smarthome.feature.developer_settings.view.DeveloperSettingsActivity

class SplashScreenCoordinator(private val navigator: Navigator) : DeeplinkCoordinator {

    override fun goToMainScreen(bundle: Bundle?) {
        navigator.goToScreen(ActivityEvent(SmartHomeActivity::class.java, bundle))
        navigator.close()
    }

    override fun goToLoginScreen() {
        navigator.goToScreen(ActivityEvent(LoginActivity::class.java))
        navigator.close()
    }

    fun goToDeveloperSettings() {
        navigator.goToScreen(ActivityEvent(DeveloperSettingsActivity::class.java))
        navigator.close()
    }

    override fun goToScreenBasedOnDeeplinkIntent(intent: Intent) {
        goToScreenBasedOnDeeplinkUri(intent.data)
    }

    private fun goToScreenBasedOnDeeplinkUri(data: Uri?) {
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
        val id = data?.getQueryParameter(ID_QUERY_URL)
        id?.let { bundle.putInt(ID_PARAMETER_URL, Integer.parseInt(it)) }
        bundle.putString(tag, value)
        return bundle
    }
}