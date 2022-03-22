package sk.bumaza.cvstories.dto.request

import sk.bumaza.cvstories.entity.data.Company
import javax.validation.constraints.NotBlank

data class CompanyRequest (
    var name: @NotBlank String? = null,
    var logoUrl: String? = null
)

fun CompanyRequest.toEntity() = Company(name = name, logoUrl = logoUrl)

fun Company.toDAO() = CompanyRequest(name, logoUrl)
