package com.intive.patronage.smarthome.common

import android.graphics.Color

fun convertRGBtoHSV(red: Int, green: Int, blue: Int): FloatArray {
    val hsv = FloatArray(3)
    Color.RGBToHSV(red, green, blue, hsv)
    return hsv
}

fun convertHSVtoRGB(hue: Int, saturation: Int, value: Int): Int {
    val hsv = floatArrayOf(hue.toFloat(), saturation.toFloat() / 100, value.toFloat() / 100)
    return Color.HSVToColor(hsv)
}