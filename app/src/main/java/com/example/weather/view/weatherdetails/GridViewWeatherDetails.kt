package com.example.weather.view.weatherdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.weather.R
import com.example.weather.view.theme.Typography

@Composable
fun GridViewForExtraWeatherDetails(weatherState: WeatherState) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(top = dimensionResource(id = R.dimen.margin_xxlarge), bottom = dimensionResource(id = R.dimen.margin_xxlarge)),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_large)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_large))
    ) {
        items(weatherState.weatherDetails.toList()) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .fillMaxSize()
                    .padding(
                        top = dimensionResource(id = R.dimen.margin_medium),
                        bottom = dimensionResource(id = R.dimen.margin_xlarge)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_small)),
                        style = Typography.subtitle1,
                        text = it.first
                    )
                    Text(
                        style = Typography.subtitle2,
                        text = it.second
                    )
                }
            }
        }
    }
}
