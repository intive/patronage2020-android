package com.intive.patronage.smarthome

import android.view.View
import com.intive.patronage.smarthome.feature.dashboard.model.DashboardSensor
import kotlinx.android.synthetic.main.dialog_sensor_list_item.view.*

enum class SensorDialogType(val type: String) {

    RGB_LIGHT("LED_CONTROLLER") {
        override fun setAttributes(sensor: DashboardSensor, view: View) {
            setText(view, R.string.light_sensor_name)
        }
    },
    TEMPERATURE_SENSOR("TEMPERATURE_SENSOR") {
        override fun setAttributes(sensor: DashboardSensor, view: View) {
            setText(view, R.string.temperature_sensor_name)
        }
    },
    SMOKE_SENSOR("smokeSensor") {
        override fun setAttributes(sensor: DashboardSensor, view: View) {
            setText(view, R.string.smoke_sensor_name)
        }
    },
    WINDOW_BLIND("windowBlind") {
        override fun setAttributes(sensor: DashboardSensor, view: View) {
            setText(view, R.string.blinds_sensor_name)
        }
    },
    WINDOW_SENSOR("windowSensor") {
        override fun setAttributes(sensor: DashboardSensor, view: View) {
            setText(view, R.string.window_sensor_name)
        }
    },
    RFID_SENSOR("RFIDSensor") {
        override fun setAttributes(sensor: DashboardSensor, view: View) {
            setText(view, R.string.RFID_sensor_name)
        }
    },
    HVAC_ROOM("HVACRoom") {
        override fun setAttributes(sensor: DashboardSensor, view: View) {
            setText(view, R.string.HVAC_sensor_name)
        }
    };

    abstract fun setAttributes(sensor: DashboardSensor, view: View)
    fun setText(view: View, nameSensor: Int) {
        view.dialogSensorName.text = view.context.getString(nameSensor)
    }
}