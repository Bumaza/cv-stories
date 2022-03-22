package sk.bumaza.cvstories.dto.request

import sk.bumaza.cvstories.entity.data.Link
import sk.bumaza.cvstories.entity.managment.Account
import javax.validation.constraints.NotBlank

data class LinkRequest (
    var url: @NotBlank String? = null,
    var description: String? = null
)

fun LinkRequest.toEntity(account: Account? = null)
    : Link = Link(url = url, description = description, account = account)

fun Link.toDao() = LinkRequest(url, description)