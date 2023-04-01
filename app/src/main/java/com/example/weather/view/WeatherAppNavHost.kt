package com.example.weather.view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weather.view.locationcheck.LocationCheck
import com.example.weather.view.permissioncheck.PermissionCheck
import com.example.weather.view.weatherdetails.WeatherDetails
import com.example.weather.view.weatherdetails.WeatherDetailsViewModel

@Composable
fun WeatherAppNavHost(appState: WeatherAppState = rememberWeatherAppState()) {

    NavHost(
        navController = appState.navController,
        startDestination = NavControllerRoute.PermissionCheck.route
    ) {
        composable(route = NavControllerRoute.PermissionCheck.route) {
            PermissionCheck(navigateToCheckIfLocationIsEnabled = {
                appState.navigateTo(NavControllerRoute.LocationCheck.route)
            })
        }
        composable(route = NavControllerRoute.LocationCheck.route) {
            LocationCheck {
                appState.navigateTo(NavControllerRoute.WeatherDetails.route)
            }
        }
        composable(route = NavControllerRoute.WeatherDetails.route) {
            val viewModel: WeatherDetailsViewModel = hiltViewModel()

            WeatherDetails(viewModel)
        }
    }
}