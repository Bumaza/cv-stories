package sk.bumaza.cvstories.dto.request

import sk.bumaza.cvstories.constant.ContractType
import sk.bumaza.cvstories.constant.CurrencyType
import sk.bumaza.cvstories.constant.StoryType
import sk.bumaza.cvstories.dto.response.FieldError
import sk.bumaza.cvstories.entity.data.Company
import sk.bumaza.cvstories.entity.data.Story
import sk.bumaza.cvstories.entity.managment.Account
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class StoryRequest (

    var type: @NotNull StoryType? = null,
    var contractType: @NotNull ContractType? = null,

    var company: @NotNull CompanyRequest? = null,
    var positionName: String? = null,
    var text: @NotBlank String? = null,

    var location: @NotNull LocationRequest? = null,

    var startDate: @NotNull  Date? = null,
    var endDate: Date? = null,

    var salary: Double? = null,
    var currency: CurrencyType? = null,

    var links: List<LinkRequest>? = null
)

fun StoryRequest.toEntity(account: Account? = null) : Story {
    return Story(type=type, contractType=contractType,
        positionName = positionName,  account = account,
        company = company?.toEntity(),
        text = text, startDate = startDate, endDate = endDate,
        salary = salary, currency = currency, location=location?.toEntity(),
        links = links?.map { it.toEntity(account) }?.toMutableList())
}

fun Story.toDAO() : StoryRequest {
    return StoryRequest(type, contractType, company?.toDAO(), positionName,
        text, location?.toDAO(), startDate, endDate,
        salary, currency, links?.map { it.toDao() })
}