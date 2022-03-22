package sk.bumaza.cvstories.dto.response

data class TokenRefreshResponse(
    var accessToken : String,
    var refreshToken : String,
    var tokenType: String = "Bearer"
)