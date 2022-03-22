package sk.bumaza.cvstories.service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import sk.bumaza.cvstories.dto.request.LoginRequest
import sk.bumaza.cvstories.dto.request.SignupRequest
import sk.bumaza.cvstories.dto.response.Error.Companion.emailUsed
import sk.bumaza.cvstories.dto.response.Error.Companion.usernameUsed
import sk.bumaza.cvstories.dto.response.JwtResponse
import sk.bumaza.cvstories.dto.response.ResponseWrapper
import sk.bumaza.cvstories.dto.response.TokenRefreshResponse
import sk.bumaza.cvstories.entity.managment.Account
import sk.bumaza.cvstories.repository.AccountRepository
import sk.bumaza.cvstories.repository.RefreshTokenRepository
import sk.bumaza.cvstories.security.jwt.JwtUtils
import sk.bumaza.cvstories.security.jwt.TokenRefreshException
import sk.bumaza.cvstories.security.services.User
import sk.bumaza.cvstories.util.StrengthPasswordTypes
import sk.bumaza.cvstories.util.strengthChecker
import java.util.stream.Collectors
import sk.bumaza.cvstories.dto.response.Error
import sk.bumaza.cvstories.extensions.toResponse


@Service
class AuthService(
    val authenticationManager: AuthenticationManager,
    val accountRepository: AccountRepository,
    val refreshTokenService: RefreshTokenService,
    val refreshTokenRepository: RefreshTokenRepository,
    val passwordEncoder: PasswordEncoder,
    val jwtUtils: JwtUtils
) {


    fun createAccount(signUpRequest: SignupRequest?) : ResponseEntity<*> {

        if (accountRepository.existsByUsername(signUpRequest!!.username)!!) {
            return ResponseEntity(usernameUsed.toResponse(), HttpStatus.CONFLICT)
        }

        if (accountRepository.existsByEmail(signUpRequest.email)!!) {
            return ResponseEntity(emailUsed.toResponse(), HttpStatus.CONFLICT)
        }

        if(signUpRequest.password?.strengthChecker() == StrengthPasswordTypes.WEAK){
            return ResponseEntity(Error.weakPassword.toResponse(), HttpStatus.NOT_ACCEPTABLE)
        }


        // Create new account
        val account = Account(
            username = signUpRequest.username,
            email = signUpRequest.email,
            password = passwordEncoder.encode(signUpRequest.password!!))

        return ResponseEntity(ResponseWrapper(data = accountRepository.save(account)), HttpStatus.CREATED)
    }

    fun login(loginRequest: LoginRequest?) : ResponseEntity<*> {

        if(loginRequest == null){
            return ResponseEntity.badRequest().body(Error.notFound.toResponse())
        }

        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))

        SecurityContextHolder.getContext().authentication = authentication
        val accessToken = jwtUtils.generateJwtToken(authentication)
        val account = authentication.principal as User

        if(account.id == null){
            return ResponseEntity.badRequest().body(Error.notFound.toResponse())
        }

        val roles = account.authorities.stream().
        map { item: GrantedAuthority -> item.authority }
            .collect(Collectors.toList())

        val refreshToken = refreshTokenService.createRefreshToken(account.id)
        refreshToken?.let { refreshTokenRepository.save(it) }

        val jwtResponse = JwtResponse(accessToken, account.id, account.username,
            account.email!!, refreshToken?.token ?: "")

        return ResponseEntity.ok(ResponseWrapper(data = jwtResponse))
    }

    fun createNewToken(request : TokenRefreshResponse?) : ResponseEntity<*> {
        val requestRefreshToken: String = request?.refreshToken ?: ""

        return refreshTokenRepository.findByToken(requestRefreshToken)
            .map(refreshTokenService::verifyExpiration)
            .map { res ->
                val token: String = jwtUtils.generateJwtToken(res!!.account!!.username!!)
                ResponseEntity(ResponseWrapper(TokenRefreshResponse(token, requestRefreshToken)), HttpStatus.CREATED)
            }.orElseThrow {
                ResponseEntity.badRequest().body(Error.badToken.toResponse())
                TokenRefreshException(requestRefreshToken,
                    "Refresh token is not in database!")
            }
    }
}