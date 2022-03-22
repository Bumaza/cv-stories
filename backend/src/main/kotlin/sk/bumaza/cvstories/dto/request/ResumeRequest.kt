package sk.bumaza.cvstories.dto.request

import sk.bumaza.cvstories.constant.AccessType
import sk.bumaza.cvstories.entity.data.Resume
import sk.bumaza.cvstories.entity.managment.Account
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


data class ResumeRequest (
    val slug: String? = null,
    val accessType: @NotNull AccessType? = null,
    val personalData: @NotBlank PersonalDataRequest? = null,
    val stories: @NotBlank List<StoryRequest>? = null,
)

fun ResumeRequest.toEntity(account: Account? = null) : Resume {
    return Resume(slug = slug, accessType= accessType,
        stories = stories?.map{ it.toEntity(account)}?.toMutableSet(),
        account = account, personalInfo = personalData?.toEntity())
}

fun Resume.toDAO() : ResumeRequest {
    return ResumeRequest(slug, accessType, personalInfo?.toDAO(), stories?.map { it.toDAO() })
}