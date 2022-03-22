package sk.bumaza.cvstories.util

enum class StrengthPasswordTypes {
    STRONG,
    WEAK
}

private const val REGEX_STRONG_PASSWORD = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})"


fun String.strengthChecker() : StrengthPasswordTypes =
    when {
        REGEX_STRONG_PASSWORD.toRegex().containsMatchIn(this) -> StrengthPasswordTypes.STRONG
        else -> StrengthPasswordTypes.WEAK
    }