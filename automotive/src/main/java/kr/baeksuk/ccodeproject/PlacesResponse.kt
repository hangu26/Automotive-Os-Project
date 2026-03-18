package kr.baeksuk.ccodeproject

data class PlacesResponse(
    val results: List<Place>,
    val status: String
)

data class Place(
    val name: String,
    val formatted_address: String,
    val geometry: Geometry
)

data class Geometry(
    val location: LocationLatLng
)

data class LocationLatLng(
    val lat: Double,
    val lng: Double
)