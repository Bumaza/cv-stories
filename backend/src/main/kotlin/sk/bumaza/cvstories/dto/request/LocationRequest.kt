package sk.bumaza.cvstories.dto.request

import sk.bumaza.cvstories.entity.data.Location
import sk.bumaza.cvstories.entity.managment.Account
import javax.validation.constraints.NotBlank

data class LocationRequest (

    var address: String? = null,
    var city: @NotBlank String? = null,
    var country: @NotBlank String? = null

)

fun LocationRequest.toEntity() : Location {
    return Location(address = address, city=city, country = country)
}

fun Location.toDAO() = LocationRequest(address, city, country)