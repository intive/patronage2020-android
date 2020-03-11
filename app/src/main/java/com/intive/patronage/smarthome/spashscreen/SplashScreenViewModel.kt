package com.intive.patronage.smarthome.spashscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intive.patronage.smarthome.spashscreen.forTestOnly.Post
import com.intive.patronage.smarthome.spashscreen.forTestOnly.PostApi
import com.intive.patronage.smarthome.spashscreen.forTestOnly.PostFetcher
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SplashScreenViewModel : ViewModel() {

    private val minWaitTime = 5L
    private val maxWaitTime = 30L

    //For test
    private lateinit var api: PostApi

    //For test
    private val postFeather = PostFetcher()
    private var sensorsList = MutableLiveData<List<Post>>()
    var error = MutableLiveData<Boolean>().apply { value = false }
    var complete = MutableLiveData<Boolean>().apply { value = false }
    private var dashboardCall: Disposable? = null

    fun getSensors() {
        api = postFeather.fetchPosts()
        dashboardCall = api.getPosts().toObservable()
            .delay(minWaitTime, TimeUnit.SECONDS, true)
            .timeout(maxWaitTime, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                sensorsList.value = it
            }, { error.value = true }, { complete.value = true })
    }


    override fun onCleared() {
        super.onCleared()
        dashboardCall?.dispose()
    }


}