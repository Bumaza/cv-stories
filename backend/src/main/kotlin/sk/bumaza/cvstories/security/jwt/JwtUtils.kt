package sk.bumaza.cvstories.security.jwt

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import sk.bumaza.cvstories.security.services.User
import java.lang.IllegalArgumentException
import java.util.*

@Component
class JwtUtils {
    @Value("\${resmalizer.app.jwtExpirationMs}")
    private val jwtExpirationMs = 0

    @Value("\${resmalizer.app.jwtSecret}")
    private val jwtSecret: String? = null


    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

    fun generateJwtToken(authentication: Authentication): String {
        val accountPrincipal = authentication.principal as User
        return generateJwtToken(accountPrincipal.username)
    }

    fun generateJwtToken(username: String) : String{
        return Jwts.builder().setSubject(username).setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret).compact()
    }

    fun getUsernameFromJwtToken(token: String?): String {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body.subject
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtUtils::class.toString())
    }
}