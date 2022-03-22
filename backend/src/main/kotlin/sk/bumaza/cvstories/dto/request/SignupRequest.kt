package sk.bumaza.cvstories.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class SignupRequest {

    var username: @NotBlank @Size(min=3, max=20) String? = null
    var email: @NotBlank @Size(max=50) String? = null
    var password: @NotBlank @Size(min=8, max=50) String? = null
}