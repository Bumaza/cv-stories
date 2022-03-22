package sk.bumaza.cvstories.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class ForgotPasswordRequest {
    var email: @NotBlank @Size(max=50) String? = null
}