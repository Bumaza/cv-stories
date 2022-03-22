package sk.bumaza.cvstories.dto.response

data class Error(
    val message: String,
    val code: String? = null) {

    companion object {
        val generalError = Error("General Errror", "BE000")
        val usernameUsed = Error("Username is already taken!", "BE001")
        val emailUsed = Error("Email is already in use!", "BE002")
        val notFound = Error("Not found", "BE003")
        val badToken = Error("Bad Token", "BE004")
        val notAuthorized = Error("Not authorizate", "BE005")
        val weakPassword = Error("Weak password", "BE006")
        val emptyTeam = Error("Empty teams", "BE007")
        val freeAccountLimit = Error("Free limit exceeded", "BE008")
        val loginError = Error("Username, Email or password is incorrect", "BE009")
        val expirationError = Error("Data expire", "BE010")
        val equalsError = Error("Equals data", "BE011")
        val permissionError = Error("You have no permission to do this", "BE012")
        val emptyData = Error("Empty data", "BE013")
        val memberError = Error("You are already a member.", "BE014")
        val selfPermissionError = Error("You can not modify your own permissions.", "BE015")
        val permissionExistError = Error("Permission already exists.", "BE016")
        val notMemberError = Error("The user is not member", "BE017")
    }
}

data class ResponseWrapper<T>(
    var data: T,
    var success: Boolean = true
)

data class FieldError(
    var field: String,
    var desc: String
)