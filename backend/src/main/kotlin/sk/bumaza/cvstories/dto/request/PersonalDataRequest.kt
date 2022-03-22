package sk.bumaza.cvstories.dto.request

import sk.bumaza.cvstories.entity.data.PersonalInfo

data class PersonalDataRequest (

    var description: String? = null,
    var skills: String? = null,

    var firstName: String? = null,
    var lastName: String? = null,
    var middleName: String? = null,

    var location: LocationRequest? = null,
    var links: List<LinkRequest>? = null

)

fun PersonalDataRequest.toEntity() : PersonalInfo {
    return PersonalInfo(skills=skills, description = description,
        firstName = firstName, middleName = middleName, lastName = lastName,
    location = location?.toEntity(), links = links?.map { it.toEntity() }?.toMutableSet())
}

fun PersonalInfo.toDAO() : PersonalDataRequest {
    return PersonalDataRequest(description, skills, firstName, lastName, middleName, location?.toDAO(), links?.map{it.toDao()})
}