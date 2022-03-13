package sk.bumaza.cvstories.security.jwt

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import sk.bumaza.cvstories.security.services.UserService
import java.lang.Exception
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthTokenFilter : OncePerRequestFilter() {

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @Autowired
    lateinit var userService: UserService
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse, filterChain: FilterChain
    ) {
        try {
            val jwt = parseJwt(request)
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                val username = jwtUtils.getUsernameFromJwtToken(jwt)
                val account = userService.loadUserByUsername(username)
                val authentication = UsernamePasswordAuthenticationToken(
                    account, null, account.authorities
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            Companion.logger.error("Cannot set employee authentication: {}", e)
        }
        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader("Authorization")

        return if (StringUtils.hasText(headerAuth) && StringUtils.startsWithIgnoreCase(headerAuth, "Bearer")) {
            headerAuth.subSequence(7, headerAuth.length).toString()
        } else null
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AuthTokenFilter::class.toString())
    }
}