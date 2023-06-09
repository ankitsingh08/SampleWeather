package com.example.weather.view.locationcheck

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.weather.R
import com.example.weather.utils.enableLocation
import com.example.weather.utils.isLocationServiceEnabled

@Composable
fun LocationCheck(navigateToWeatherDetails: () -> Unit) {

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->

            val isLocationEnabled = isLocationServiceEnabled(context = context)

            if (event == Lifecycle.Event.ON_CREATE && isLocationEnabled) {
                navigateToWeatherDetails()
            }

            if (event == Lifecycle.Event.ON_RESUME && isLocationEnabled) {
                navigateToWeatherDetails()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            painter = painterResource(id = R.drawable.ic_location_off),
            contentDescription = stringResource(id = R.string.str_location_permission_need)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
        ) {
            Text(
                text = stringResource(id = R.string.str_open_location_setting),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            )

            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = { enableLocation(context = context) }) {
                Text(text = stringResource(id = R.string.str_click_to_enable))
            }
        }
    }
}

@Preview
@Composable
fun LocationCheckPreview() {
    LocationCheck {}
}