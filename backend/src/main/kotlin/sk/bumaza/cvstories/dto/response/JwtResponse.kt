package sk.bumaza.cvstories.dto.response

class JwtResponse(
    var accessToken: String, var id: Long,
    var username: String, var email: String,
    var refreshToken: String
) {
    var tokenType = "Bearer"
}