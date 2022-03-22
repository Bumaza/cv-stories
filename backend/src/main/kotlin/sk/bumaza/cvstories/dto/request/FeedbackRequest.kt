package sk.bumaza.cvstories.dto.request

import sk.bumaza.cvstories.constant.FeedbackType
import sk.bumaza.cvstories.entity.data.Feedback
import sk.bumaza.cvstories.entity.managment.Account
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class FeedbackRequest (
    val comment: @NotBlank String? = null,
    val type: @NotNull FeedbackType? = null
)

fun FeedbackRequest.toEntity(account: Account? = null) = Feedback(comment = comment, type = type, account = account)

fun Feedback.toDAO() = FeedbackRequest(comment, type)

