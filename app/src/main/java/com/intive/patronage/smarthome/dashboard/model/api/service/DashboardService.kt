package com.intive.patronage.smarthome.dashboard.model.api.service

import com.intive.patronage.smarthome.api.SmartHomeAPI
import com.intive.patronage.smarthome.dashboard.model.Dashboard
import com.intive.patronage.smarthome.dashboard.model.DashboardSensor
import com.intive.patronage.smarthome.dashboard.model.api.respository.DashboardRepositoryAPI
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class DashboardService(
    private val smartHomeAPI: SmartHomeAPI,
    private val dashboardRepository: DashboardRepositoryAPI
) {

    fun getDashboard(): Single<Dashboard> = dashboardRepository.getDashboard()
        .switchIfEmpty(getDashboardFromNetwork())

    private fun getDashboardFromNetwork(): Single<Dashboard> {
        return smartHomeAPI.getDashboard().doOnSuccess {
            dashboardRepository.setDashboard(it)
        }
    }

    fun getDashboardSensors(): Observable<List<DashboardSensor>> {
        return dashboardRepository.getDashboard()
            .map {
                val sensors = mutableListOf<DashboardSensor>()
                sensors.addAll(transformFromLights(it.lights))
                sensors.addAll(transformFromTemperatureSensors(it.temperatureSensors))
                sensors.addAll(transformFromSmokeSensors(it.smokeSensors))
                sensors.addAll(transformFromWindowBlinds(it.windowBlinds))
                sensors.addAll(transfromFromWindowSensors(it.windowSensors))
                sensors.addAll(transformFromRFIDSensors(it.RFIDSensors))
                sensors.addAll(transformFromHVACRooms(it.HVACRooms))
                //add hvac status
                sensors.toList()
            }.toObservable()
    }

    fun updateSensors(): Observable<List<DashboardSensor>> =
        Observable.interval(1, 10, TimeUnit.SECONDS)
            .flatMap { getDashboardSensors() }
}