package com.example.weather.data.model

import com.example.weather.domain.model.CoordDomainModel

data class Coord(
    val lat: Double,
    val lon: Double
)

fun Coord.toDomainModel(): CoordDomainModel {
    return CoordDomainModel(
        lat = this.lat,
        lon = this.lon
    )
}