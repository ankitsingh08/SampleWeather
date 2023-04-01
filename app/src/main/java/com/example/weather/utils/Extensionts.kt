package com.example.weather.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import com.example.weather.R
import com.example.weather.view.WeatherActivity
import kotlin.Exception

fun Double.format(digits: Int) = "%.${digits}f".format(this).toDouble()

fun checkPermissionStatus(
    context: Context,
    permission: String,
    isGranted: () -> Unit,
    showRational: () -> Unit,
    isNotGranted: () -> Unit,
) {
    when {
        ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED -> {
            isGranted()
        }
        ActivityCompat.shouldShowRequestPermissionRationale(
            context as WeatherActivity,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) -> {
            showRational()
        }
        else -> {
            isNotGranted()
        }
    }
}

fun isLocationServiceEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return LocationManagerCompat.isLocationEnabled(locationManager)
}

fun enableLocation(context: Context){
    try {
        context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    } catch (exception: Exception) {
        Log.d("ERROR", "${exception.message}")
    }
}

@DrawableRes
fun String.mapToDrawableResource(): Int {
    return when (this) {
        "01d" -> {
            R.drawable._01d}
        "01n" -> {
            R.drawable._01n}
        "02d" -> {
            R.drawable._02d}
        "02n" -> {
            R.drawable._02n}
        "03d" -> {
            R.drawable._03d}
        "03n" -> {
            R.drawable._03d}
        "04d" -> {
            R.drawable._04d}
        "04n" -> {
            R.drawable._04d}
        "09d" -> {
            R.drawable._09d}
        "09n" -> {
            R.drawable._09d}
        "10d" -> {
            R.drawable._10d}
        "10n" -> {
            R.drawable._10n}
        "11d" -> {
            R.drawable._11d}
        "11n" -> {
            R.drawable._11d}
        "13d" -> {
            R.drawable._13d}
        "13n" -> {
            R.drawable._13d}
        "50d" -> {
            R.drawable._50d}
        "50n" -> {
            R.drawable._50n}
        else -> {R.drawable._defaultweathericon}
    }
}